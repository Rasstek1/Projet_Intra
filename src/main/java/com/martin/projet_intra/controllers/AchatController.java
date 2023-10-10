package com.martin.projet_intra.controllers;

import com.martin.projet_intra.models.LibrairieDataContext;
import com.martin.projet_intra.models.Livre;
import com.martin.projet_intra.models.LivreAchete;
import com.martin.projet_intra.models.Panier;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/achat")
@SessionAttributes("panier")
public class AchatController {

    @Autowired
    private LibrairieDataContext librairieDataContext;

    @GetMapping("/listeLivres")
    public String listeLivres(Model model, HttpSession session) {
        model.addAttribute("livres", librairieDataContext.selectLivres());
        getPanier(session); // S'assure que le panier est initialisé
        return "listeLivres";
    }

    private Panier getPanier(HttpSession session) {
        Panier panier = (Panier) session.getAttribute("panier");
        if (panier == null) {
            panier = new Panier();
            session.setAttribute("panier", panier);
        }
        return panier;
    }

    @GetMapping("/acheterLivre")
    public String acheterLivre(@RequestParam String isbn, @RequestParam int quantite, HttpSession session, RedirectAttributes redirectAttributes) {
        Panier panier = getPanier(session);
        Livre livreSelectionne = librairieDataContext.selectLivreParIsbn(isbn);

        // Vérifier que la quantité est supérieure à 0 et que la quantité ne dépasse pas la quantité disponible
        if (quantite > 0 && quantite <= livreSelectionne.getQuantite()) {
            // Vérifier si le livre est déjà dans le panier
            boolean livreDejaDansPanier = false;
            for (LivreAchete livreAchete : panier.getListe()) {
                if (livreAchete.getIsbn().equals(isbn)) {
                    livreDejaDansPanier = true;
                    break;
                }
            }

            // Si le livre est déjà dans le panier, augmentez simplement sa quantité
            if (livreDejaDansPanier) {
                for (LivreAchete livreAchete : panier.getListe()) {
                    if (livreAchete.getIsbn().equals(isbn)) {
                        livreAchete.augmenterQuantite();
                        break;
                    }
                }
            } else {
                // Si le livre n'est pas dans le panier, ajoutez-le avec la quantité spécifiée
                LivreAchete livreAchete = new LivreAchete(livreSelectionne.getIsbn(), livreSelectionne.getTitre(), livreSelectionne.getAuteur(), livreSelectionne.getPrix(), quantite, livreSelectionne.getPhoto());
                panier.ajouter(livreAchete);
            }

            // Après l'ajout ou l'augmentation, recalculer les montants
            panier.recalculerMontantTotalEtTaxes();
        } else {
            // Stock insuffisant ou quantité invalide, ajoutez un message d'erreur
            redirectAttributes.addFlashAttribute("erreurStock", "Stock insuffisant ou quantité invalide.");
        }

        return "redirect:/achat/listeLivres";
    }








    @GetMapping("/afficherPanier")
    public String afficherPanier(Model model, HttpSession session) {
        model.addAttribute("panier", getPanier(session));
        return "afficherPanier";
    }

    @GetMapping("/supprimerLivre")
    public String supprimerLivre(@RequestParam String isbn, HttpSession session) {
        Panier panier = getPanier(session);
        panier.supprimer(isbn);
        session.setAttribute("panier", panier);

        // Après la suppression, recalculer les montants
        panier.recalculerMontantTotalEtTaxes();

        return "redirect:/achat/afficherPanier";
    }






    @GetMapping("/paiement")
    public String paiementForm() {
        return "paiement";
    }

    @PostMapping("/paiement")
    public String paiementProcessing(
            @RequestParam String telephone,
            @RequestParam String nomClient,
            @RequestParam String adresse,
            @RequestParam String email,
            HttpSession session
    ) {
        Panier panier = getPanier(session);
        double montantHt = panier.getListe().stream().mapToDouble(LivreAchete::getPrix).sum();
        double tauxTaxe = 0.15; // 15%
        double mtTaxe = montantHt * tauxTaxe;

        librairieDataContext.insertFacture(telephone, nomClient, adresse, email, montantHt, mtTaxe);
        int dernierNumFacture = librairieDataContext.getLastNumFacture();

        for (LivreAchete livreAchete : panier.getListe()) {
            String isbn = livreAchete.getIsbn();
            int quantiteAchete = livreAchete.getQuantite(); // Assurez-vous que votre classe LivreAchete a cette méthode.

            // Récupérer la quantité actuelle du livre dans la base de données
            int quantiteActuelle = librairieDataContext.getQuantiteLivre(isbn);

            // Mettre à jour la quantité du livre après l'achat
            int nouvelleQuantite = quantiteActuelle - quantiteAchete;
            librairieDataContext.updateQuantiteLivre(isbn, nouvelleQuantite);

            // Insérer les détails de la facture
            librairieDataContext.insertDetailsFacture(dernierNumFacture, isbn, livreAchete.getPrix());
        }

        panier.getListe().clear();
        session.removeAttribute("panier");
        return "confirmation";
    }




    @GetMapping("/annulerAchat")
    public String annulerAchat(HttpSession session) {
        // Récupérez le panier de la session
        Panier panier = getPanier(session);

        // Videz le panier
        panier.vider();

        // Redirigez l'utilisateur vers la liste des livres
        return "redirect:/achat/listeLivres";
    }


}


