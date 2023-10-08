package com.martin.projet_intra.controllers;

import com.martin.projet_intra.models.LibrairieDataContext;
import com.martin.projet_intra.models.Livre;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    public String ajouterLivre(@ModelAttribute Livre livre, MultipartFile photo) {

        String isbn = HtmlUtils.htmlEscape(livre.getIsbn());
        String auteur = HtmlUtils.htmlEscape(livre.getAuteur());
        String titre = HtmlUtils.htmlEscape(livre.getTitre());

        // Vérifiez si un ISBN identique est déjà présent, si oui, attribuez le prochain numéro disponible
        if (librairieDataContext.isbnExiste(livre.getIsbn())) {
            livre.setIsbn(generateUniqueISBN());
        }

        String photoFileName = generateUniqueFileName(photo.getOriginalFilename());
        String imagePath = "src/main/resources/static/img/" + photoFileName; // Chemin complet vers le dossier img
        try {
            Files.copy(photo.getInputStream(), Paths.get(imagePath));
            livre.setPhoto("img/" + photoFileName); // Stockez le chemin relatif dans la base de données
        } catch (IOException e) {
            e.printStackTrace();
            // Gérez l'erreur de téléchargement ici
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
