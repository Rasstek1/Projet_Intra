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

@Controller
@RequestMapping("/achat")
@SessionAttributes("panier")
public class AchatController {



    @Autowired
    private LibrairieDataContext librairieDataContext;



    @GetMapping("/listeLivres")
    public String listeLivres(Model model) {
        model.addAttribute("livres", librairieDataContext.selectLivres());
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
    public String acheterLivre(@RequestParam String isbn, HttpSession session) {

        // Récupérer le panier de la session
        Panier panier = (Panier) session.getAttribute("panier");

        // Si le panier n'existe pas, le créer
        if (panier == null) {
            panier = new Panier();
        }

        // Récupérer le livre à partir de l'ISBN (La methode selectLivreParIsbn est à créer dans LibrairieDataContext)
        Livre livreSelectionne = librairieDataContext.selectLivreParIsbn(isbn);

        // Ajouter le livre au panier
        LivreAchete livreAchete = new LivreAchete(livreSelectionne.getIsbn(), livreSelectionne.getTitre(), livreSelectionne.getPrix());
        panier.ajouter(livreAchete);

        // Enregistrer le panier dans la session
        session.setAttribute("panier", panier);

        // Rediriger vers la liste des livres
        return "redirect:/listeLivre";
    }


    @GetMapping("/afficherPanier")
    public String afficherPanier(Model model, HttpSession session) {
        model.addAttribute("panier", getPanier(session));
        return "afficherPanier";
    }

    @GetMapping("/supprimerLivre")
    public String supprimerLivre(@RequestParam String isbn, HttpSession session) {

        // Récupérer le panier de la session
        Panier panier = (Panier) session.getAttribute("panier");

        if (panier != null) {
            // Supprimer le livre du panier
            panier.supprimer(isbn);

            // Remettre à jour le panier dans la session (bien que ce ne soit pas nécessaire car le panier est une référence, c'est une bonne pratique)
            session.setAttribute("panier", panier);
        }

        // Rediriger vers la vue du panier
        return "redirect:/afficherPanier";
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
        // Récupérer le panier de la session
        Panier panier = (Panier) session.getAttribute("panier");

        // Calculer le montant total HT et les taxes
        double montantHt = panier.getListe().stream().mapToDouble(LivreAchete::getPrix).sum();
        double tauxTaxe = 0.15; // 15%
        double mtTaxe = montantHt * tauxTaxe;

        // Insérer dans la table Factures
        librairieDataContext.insertFacture(telephone, nomClient, adresse, email, montantHt, mtTaxe);

        // Récupérer le dernier NumFacture inséré
        int dernierNumFacture = librairieDataContext.getLastNumFacture();

        // Insérer chaque livre du panier dans la table DetailsFacture
        for (LivreAchete livre : panier.getListe()) {
            librairieDataContext.insertDetailsFacture(dernierNumFacture, livre.getIsbn(), livre.getPrix());
        }

        // Vider le panier
        panier.getListe().clear();

        // (Optionnel) Si vous souhaitez supprimer le panier de la session après le paiement :
        session.removeAttribute("panier");

        // Rediriger vers la page de confirmation
        return "confirmation";
    }
}
