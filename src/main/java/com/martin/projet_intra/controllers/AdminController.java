package com.martin.projet_intra.controllers;

import com.martin.projet_intra.models.LibrairieDataContext;
import com.martin.projet_intra.models.Livre;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import org.slf4j.Logger;

@Controller  // Définit la classe comme un contrôleur Spring MVC
@RequestMapping("/admin")  // Mappe toutes les méthodes de cette classe à l'URL "/admin"
public class AdminController {

    @Autowired  // Injecte automatiquement l'objet librairieDataContext
    private LibrairieDataContext librairieDataContext;

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);  // Logger pour cette classe

    // Gère les requêtes GET pour "/admin/ajouterLivre"
    @GetMapping("/ajouterLivre")
    public String afficherAjouterLivre(Model model) {
        return "ajouterLivre";  // Retourne la vue "ajouterLivre"
    }

    // Gère les requêtes POST pour "/admin/ajouterLivre"
    @PostMapping("/ajouterLivre")
    public String ajouterLivre(@ModelAttribute Livre livre, @RequestParam("photoFile") MultipartFile photoFile, Model model) {
        // Échappe les caractères HTML dans les champs pour des raisons de sécurité
        String isbn = HtmlUtils.htmlEscape(livre.getIsbn());
        String auteur = HtmlUtils.htmlEscape(livre.getAuteur());
        String titre = HtmlUtils.htmlEscape(livre.getTitre());

        // Vérifie si un livre avec le même ISBN existe déjà
        if (librairieDataContext.isbnExiste(livre.getIsbn())) {
            model.addAttribute("errorMessage", "L'ISBN fourni existe déjà. Veuillez fournir un ISBN unique.");
            return "ajouterLivre";  // Retour à la même page avec le message d'erreur
        }

        // Gère le téléchargement de la photo
        if (!photoFile.isEmpty()) {
            String originalFilename = photoFile.getOriginalFilename();
            String safeFilename = originalFilename.replace(" ", "_").replaceAll("[^a-zA-Z0-9\\.\\-]", "");
            String imagePath = "src/main/resources/static/img/" + safeFilename;

            try {
                File directory = new File("src/main/resources/static/img/");
                if (!directory.exists()) {
                    directory.mkdir();  // Crée le dossier s'il n'existe pas
                }
                Files.copy(photoFile.getInputStream(), Paths.get(imagePath), StandardCopyOption.REPLACE_EXISTING);
                livre.setPhoto(safeFilename);  // Met à jour le champ photo du livre
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("errorMessage", "Une erreur s'est produite lors de la sauvegarde de la photo.");
                return "ajouterLivre";
            }

            // Vérification du format du prix
            if (String.valueOf(livre.getPrix()).contains(",")) {
                model.addAttribute("errorMessage", "Format du prix invalide. Veuillez utiliser un point comme séparateur décimal.");
                return "ajouterLivre";
            }
        }

        // Insertion du livre dans la base de données
        librairieDataContext.insertLivre(
                livre.getIsbn(),
                livre.getAuteur(),
                livre.getTitre(),
                livre.getPrix(),
                livre.getQuantite(),
                livre.getPhoto(),
                livre.getResume()
        );

        return "redirect:/admin/ajouterLivre";  // Redirige vers la même page pour un nouvel ajout
    }

    // Méthode pour générer un ISBN unique
    private String generateUniqueISBN() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 13);
    }

    // Méthode pour générer un nom de fichier unique
    private String generateUniqueFileName(String originalFileName) {
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        return UUID.randomUUID().toString() + extension;
    }
}
