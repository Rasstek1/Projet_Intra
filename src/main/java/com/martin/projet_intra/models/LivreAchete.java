package com.martin.projet_intra.models;

public class LivreAchete {
    private String isbn;
    private String titre;
    private double prix;
    public int quantite;


    // Constructeur par défaut
    public LivreAchete() {}

    // Constructeur avec paramètres
    public LivreAchete(String isbn, String titre, double prix) {
        this.isbn = isbn;
        this.titre = titre;
        this.prix = prix;
    }

    // Méthodes d'accès

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    /*Methodes*/

    public void augmenterQuantite() {
        this.quantite++;
    }
}
