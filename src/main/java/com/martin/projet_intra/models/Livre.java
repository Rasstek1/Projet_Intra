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

    private int quantiteInitiale;
    private int quantiteDisponible;
    public Livre() {}

    public Livre(String isbn, String auteur, String titre, double prix, int quantite, String photo, String resume, String quantiteInitiale, String quantiteDisponible) {
        this.isbn = isbn;
        this.auteur = auteur;
        this.titre = titre;
        this.prix = prix;
        this.quantite = quantite;
        this.photo = photo;
        this.resume = resume;
        this.quantiteInitiale = Integer.parseInt(quantiteInitiale);
        this.quantiteDisponible = Integer.parseInt(quantiteDisponible);
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

    public int getQuantiteInitiale() {
        return quantiteInitiale;
    }

    public void setQuantiteInitiale(int quantiteInitiale) {
        this.quantiteInitiale = quantiteInitiale;
    }
    public int getQuantiteDisponible() {
        return quantiteDisponible;
    }

    public void setQuantiteDisponible(int quantiteDisponible) {
        this.quantiteDisponible = quantiteDisponible;
    }

}