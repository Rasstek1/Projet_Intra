package com.martin.projet_intra.models;

import java.util.ArrayList;
import java.util.List;

public class Panier {

    // la liste des livres achetés
    private List<LivreAchete> liste;

    private double montantTotal;
    private double montantTaxes;
    private static final double TAUX_TAXE = 0.15;

    private int nombreTotalLivres;

    private double MontantHT;
    private double MtTaxe;
    private double MtTotal;


    //Le constructeur par défaut qui initialise la liste
    public Panier() {
        this.liste = new ArrayList<>();
    }

    /**
     * La méthode "ajouter"
     *
     * @param element
     */
    public void ajouter(LivreAchete element) {
        // Recherche du livre dans le panier
        for (LivreAchete livreAchete : liste) {
            if (livreAchete.getIsbn().equals(element.getIsbn())) {
                // Si le livre est trouvé, augmentez sa quantité et quittez la méthode
                livreAchete.augmenterQuantite();
                return;
            }
        }

        // Si le livre n'est pas déjà dans le panier, ajoutez-le
        liste.add(element);
    }

    /**
     * La méthode "supprimer"
     *
     * @param isbn
     */
    public void supprimer(String isbn) {
        liste.removeIf(livre -> livre.getIsbn().equals(isbn));
    }

    /**
     * La méthode "getListe"
     *
     * @return
     */
    public List<LivreAchete> getListe() {
        return liste;
    }

    /**
     * La méthode "vider"
     */
    public void vider() {
        liste.clear();
    }

    /**
     * La méthode "isPresent"
     *
     * @param isbn
     * @return
     */
    public boolean contains(Livre livre) {
        for (LivreAchete livreAchete : liste) {
            if (livreAchete.getIsbn().equals(livre.getIsbn())) {
                return true;
            }
        }
        return false;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public double getMontantTaxes() {
        return montantTaxes;
    }
    public void recalculerNombreTotalLivres() {
        nombreTotalLivres = liste.size();
    }

    public int getNombreTotalLivres() {
        return nombreTotalLivres;
    }
    public void recalculerMontantTotalEtTaxes() {
        montantTotal = liste.stream().mapToDouble(livre -> livre.getPrix() * livre.getQuantite()).sum();
        montantTaxes = montantTotal * TAUX_TAXE;
    }


}
