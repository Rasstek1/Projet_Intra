package com.martin.projet_intra.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LibrairieController {

    @GetMapping("/accueil")
    public String accueil() {
        return "accueil";
    }
}
