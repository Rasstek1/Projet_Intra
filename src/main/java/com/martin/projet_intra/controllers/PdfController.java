package com.martin.projet_intra.controllers;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;



@RestController
@RequestMapping("/pdf")  // Précise que les méthodes de ce contrôleur commencent par le chemin "/pdf"
public class PdfController {

    @GetMapping("/downloadPDF")  // Mappe les requêtes GET vers "/pdf/downloadPDF"
    public ResponseEntity<Resource> downloadPDF() throws IOException {
        Path path = Paths.get("static/pdfs/exemple.pdf");  // Chemin vers le fichier PDF que vous voulez télécharger

        // Crée une nouvelle ressource à partir du chemin URI du fichier PDF
        Resource resource = new UrlResource(path.toUri());

        // Construit la réponse HTTP
        return ResponseEntity.ok()  // Définit le statut de la réponse comme 200 OK
                .contentType(MediaType.APPLICATION_PDF)  // Définit le type de contenu de la réponse comme PDF
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")  // Ajoute un en-tête pour indiquer que le contenu doit être téléchargé et donne le nom du fichier
                .body(resource);  // Ajoute le fichier PDF comme corps de la réponse
    }
}
