package com.martin.projet_intra.models;

public class Livre {
    private String isbn;
    private String auteur;
    private String titre;
    private double prix;
    private int quantite;
    private String photo;
    private String resume;
    private String erreurStock;


    public Livre() {}

    public Livre(String isbn, String auteur, String titre, double prix, int quantite, String photo, String resume) {
        this.isbn = isbn;
        this.auteur = auteur;
        this.titre = titre;
        this.prix = prix;
        this.quantite = quantite;
        this.photo = photo;
        this.resume = resume;

    }

    //Methodes d'acces
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
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

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getErreurStock() {
        return erreurStock;
    }

    public void setErreurStock(String erreurStock) {
        this.erreurStock = erreurStock;
    }


}