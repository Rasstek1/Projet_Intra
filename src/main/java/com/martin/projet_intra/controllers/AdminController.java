package com.martin.projet_intra.controllers;

import com.martin.projet_intra.models.LibrairieDataContext;
import com.martin.projet_intra.models.Livre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class AdminController {

    @Autowired
    private LibrairieDataContext librairieDataContext;

    // Méthode pour afficher la vue "ajouterLivre"
    @GetMapping("/admin/ajouterLivre")
    public String afficherAjouterLivre(Model model) {
        // Vous pouvez ajouter des données au modèle si nécessaire
        return "ajouterLivre";
    }

    // Méthode pour ajouter un livre à la table "Livres"
    @PostMapping("/admin/ajouterLivre")
    public String ajouterLivre(Livre livre) {
        // Insérer le livre dans la base de données en utilisant le LibrairieDataContext
        librairieDataContext.insertLivre(
                livre.getIsbn(),
                livre.getAuteur(),
                livre.getTitre(),
                livre.getPrix(),
                livre.getQuantite(),
                livre.getPhoto(),
                livre.getResume()
        );

        // Rediriger vers une page de confirmation ou ailleurs selon vos besoins
        return "redirect:/admin/ajouterLivre";
    }
}