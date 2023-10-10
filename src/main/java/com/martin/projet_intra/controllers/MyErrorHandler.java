package com.martin.projet_intra.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.io.StringWriter;

@Controller
public class MyErrorHandler implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object message = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);


        Throwable exception = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        Object requestURL = request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI);
        Object method = request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);

        modelAndView.addObject("errorCode", status != null ? status.toString() : "N/A");
        modelAndView.addObject("errorMessage", message != null ? message.toString() : "N/A");
        modelAndView.addObject("exception", exception != null ? exception.toString() : "N/A");
        modelAndView.addObject("requestURL", requestURL != null ? requestURL.toString() : "N/A");
        modelAndView.addObject("method", request.getMethod());

        // Obtenir les détails de l'erreur à partir de l'exception
        String errorDetails = "N/A";
        if (exception != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            exception.printStackTrace(pw);
            errorDetails = sw.toString();  // Obtient la trace de l'exception
        }
        modelAndView.addObject("errorDetails", errorDetails);

        modelAndView.setViewName("error");
        return modelAndView;
    }



}
