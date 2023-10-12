package com.martin.projet_intra.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class DateController {


    @GetMapping("/current-time")
    public String showCurrentTime(Model model) {
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        model.addAttribute("currentTime", s.format(date));
        return "footer";
    }
}
