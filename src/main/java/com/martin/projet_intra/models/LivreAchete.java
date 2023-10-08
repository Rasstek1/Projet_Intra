package com.martin.projet_intra.models;

public class LivreAchete {
    private String isbn;
    private String titre;
    private String auteur;
    private double prix;
    public int quantite;

    private String photo;

    private Livre livre; // Référence au livre



    // Constructeur par défaut
    public LivreAchete() {}

    // Constructeur avec paramètres
    public LivreAchete(String isbn, String titre, String auteur, double prix, int quantite, String photo) {
        this.isbn = isbn;
        this.titre = titre;
        this.auteur = auteur;
        this.prix = prix;
        this.quantite = quantite;
        this.photo = photo;
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

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /*Methodes*/

    public void augmenterQuantite() {
        this.quantite++;
    }

    public LivreAchete(Livre livre, int quantite) {
        this.livre = livre;
        this.quantite = quantite;
    }

    public int getQuantiteInitiale() {
        return livre.getQuantiteInitiale();
    }

}
