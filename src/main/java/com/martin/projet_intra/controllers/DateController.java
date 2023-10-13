package com.martin.projet_intra.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class DateController {

    // Gère les requêtes GET pour l'URL "/current-time"
    @GetMapping("/current-time")
    public String showCurrentTime(Model model) {
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  // Crée un objet SimpleDateFormat pour formater la date et l'heure
        Date date = new Date();  // Obtient la date et l'heure actuelles
        model.addAttribute("currentTime", s.format(date));  // Ajoute la date et l'heure formatées au modèle
        return "footer";  // Retourne la vue "footer" pour afficher la date et l'heure actuelles
    }
}
