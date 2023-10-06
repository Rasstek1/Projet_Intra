package com.martin.projet_intra.models;

import java.util.ArrayList;
import java.util.List;

public class Panier {

    // la liste des livres achetés
    private List<LivreAchete> liste;

    private double montantTotal;

    private double montantTaxes;
    private static final double TAUX_TPS = 0.05;
    private static final double TAUX_TVQ = 0.09975;
    private int nombreTotalLivres;




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


    public void recalculerNombreTotalLivres() {
        nombreTotalLivres = liste.size();
    }

    public int getNombreTotalLivres() {
        return liste.stream().mapToInt(LivreAchete::getQuantite).sum();
    }
    public double getMontantTaxes() {
        return montantTaxes;
    }

    public double getTps() {
        double tps = montantTotal * TAUX_TPS;
        return Math.round(tps * 100.0) / 100.0; // Arrondir à deux décimales
    }

    public double getTvq() {
        double tvq = montantTotal * TAUX_TVQ;
        return Math.round(tvq * 100.0) / 100.0; // Arrondir à deux décimales
    }
    public void recalculerMontantTotalEtTaxes() {
        montantTotal = liste.stream().mapToDouble(livre -> livre.getPrix() * livre.getQuantite()).sum();
        double tps = montantTotal * TAUX_TPS; // Calcul de la TPS (5%)
        double tvq = montantTotal * TAUX_TVQ; // Calcul de la TVQ (9,975%)
        montantTaxes = tps + tvq; // Montant total des taxes
        montantTotal += montantTaxes; // Montant total avec les taxes
        montantTotal = Math.round(montantTotal * 100.0) / 100.0; // Arrondir à deux décimales
        montantTaxes = Math.round(montantTaxes * 100.0) / 100.0; // Arrondir à deux décimales
        tps = Math.round(tps * 100.0) / 100.0; // Arrondir à deux décimales
        tvq = Math.round(tvq * 100.0) / 100.0; // Arrondir à deux décimales
    }



}
