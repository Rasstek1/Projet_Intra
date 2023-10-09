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
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private LibrairieDataContext librairieDataContext;
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @GetMapping("/ajouterLivre")
    public String afficherAjouterLivre(Model model) {
        return "ajouterLivre";
    }

    @PostMapping("/ajouterLivre")
    public String ajouterLivre(@ModelAttribute Livre livre, @RequestParam("photoFile") MultipartFile photoFile, Model model) {

        String isbn = HtmlUtils.htmlEscape(livre.getIsbn());
        String auteur = HtmlUtils.htmlEscape(livre.getAuteur());
        String titre = HtmlUtils.htmlEscape(livre.getTitre());

        // Vérifiez si un ISBN identique est déjà présent
        if (librairieDataContext.isbnExiste(livre.getIsbn())) {
            // Ajoutez un message d'erreur au modèle
            model.addAttribute("errorMessage", "L'ISBN fourni existe déjà. Veuillez fournir un ISBN unique.");

            // Renvoyez l'utilisateur vers la même page avec le message d'erreur
            return "ajouterLivre";
        }

        if (!photoFile.isEmpty()) {
            String originalFilename = photoFile.getOriginalFilename();
            // Remplacer les espaces par des underscores et supprimer tous les caractères non désirés
            String safeFilename = originalFilename.replace(" ", "_").replaceAll("[^a-zA-Z0-9\\.\\-]", "");

            String imagePath = "src/main/resources/static/img/" + safeFilename; // Chemin complet vers le dossier img

            try {
                // Création du dossier s'il n'existe pas
                File directory = new File("src/main/resources/static/img/");
                if (!directory.exists()) {
                    directory.mkdir();
                }

                // Sauvegarde du fichier
                Files.copy(photoFile.getInputStream(), Paths.get(imagePath), StandardCopyOption.REPLACE_EXISTING);

                // Mise à jour du champ photo avec uniquement le nom du fichier
                livre.setPhoto(safeFilename);
            } catch (IOException e) {
                e.printStackTrace();
                // Ajoutez ici la gestion d'erreur pour le téléchargement
                model.addAttribute("errorMessage", "Une erreur s'est produite lors de la sauvegarde de la photo.");
                return "ajouterLivre";
            }
        }



        librairieDataContext.insertLivre(
                livre.getIsbn(),
                livre.getAuteur(),
                livre.getTitre(),
                livre.getPrix(),
                livre.getQuantite(),
                livre.getPhoto(),
                livre.getResume()
        );

        return "redirect:/admin/ajouterLivre";
    }

    private String generateUniqueISBN() {
        // Générez un ISBN unique, par exemple en utilisant UUID
        return UUID.randomUUID().toString().replace("-", "").substring(0, 13);
    }

    private String generateUniqueFileName(String originalFileName) {
        // Générez un nom de fichier unique, par exemple en utilisant UUID
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        return UUID.randomUUID().toString() + extension;
    }


}
