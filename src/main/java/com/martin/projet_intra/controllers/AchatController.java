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

        // Vérifier que la quantité est supérieure à 0
        if (quantite > 0) {
            // Obtenir la quantité disponible du livre dans la base de données
            int quantiteDisponible = livreSelectionne.getQuantite();

            // Vérifier si la quantité demandée est inférieure ou égale à la quantité disponible
            if (quantite <= quantiteDisponible) {
                // Ajouter le livre au panier avec la quantité spécifiée
                for (int i = 0; i < quantite; i++) {
                    LivreAchete livreAchete = new LivreAchete(livreSelectionne.getIsbn(), livreSelectionne.getTitre(), livreSelectionne.getAuteur(), livreSelectionne.getPrix(), 1); // Quantité fixée à 1
                    panier.ajouter(livreAchete);
                }
                session.setAttribute("panier", panier);
            } else {
                // Stock insuffisant, ajoutez un message d'erreur
                redirectAttributes.addFlashAttribute("erreurStock", "Stock insuffisant. La quantité demandée n'est pas disponible.");
            }
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
        return "redirect:/achat/afficherPanier";
    }

    @GetMapping("/annulerAchat")
    public String annulerAchat(HttpSession session) {
        // Supprimer le panier de la session
        session.removeAttribute("panier");

        // Rediriger vers la liste des livres
        return "redirect:/achat/listeLivres";
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
        for (LivreAchete livre : panier.getListe()) {
            librairieDataContext.insertDetailsFacture(dernierNumFacture, livre.getIsbn(), livre.getPrix());
        }
        panier.getListe().clear();
        session.removeAttribute("panier");
        return "confirmation";
    }
}
