package com.martin.projet_intra.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;


public class LibrairieDataContext {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //********** Méthodes pour la gestion des livres **********//

    /**
     * Insérer un livre
     * @param isbn L'ISBN du livre
     * @param auteur L'auteur du livre
     * @param titre Le titre du livre
     * @param prix  Le prix du livre
     * @param quantite La quantité du livre
     * @param photo La photo du livre
     * @param resume Le résumé du livre
     */
    public void insertLivre(String isbn, String auteur, String titre, double prix, int quantite, String photo, String resume) {
        String sql = "INSERT INTO Livres (Isbn, Auteur, Titre, Prix, Quantite, Photo, Resume) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, isbn, auteur, titre, prix, quantite, photo, resume);
    }


    /**
     * Sélectionner tous les livres
     * @return
     */
    public List<Livre> selectLivres() {
        String sql = "SELECT * FROM Livres";
        RowMapper<Livre> rowMapper = (rs, rowNum) -> {
            Livre livre = new Livre();
            livre.setIsbn(rs.getString("Isbn"));
            livre.setAuteur(rs.getString("Auteur"));
            livre.setTitre(rs.getString("Titre"));
            livre.setPrix(rs.getDouble("Prix"));
            livre.setQuantite(rs.getInt("Quantite"));
            livre.setPhoto(rs.getString("Photo"));
            livre.setResume(rs.getString("Resume"));
            return livre;
        };
        return jdbcTemplate.query(sql, rowMapper);
    }


    /**
     * Insérer les informations d'une facture
     * @param telephone Le numéro de téléphone du client
     * @param nomClient Le nom du client
     * @param adresse L'adresse du client
     * @param email L'email du client
     * @param montantHt Le montant HT de la facture
     * @param mtTaxe Le montant de la taxe
     */
    public void insertFacture(String telephone, String nomClient, String adresse, String email, double montantHt, double mtTaxe) {
        String sql = "INSERT INTO Factures (Telephone, Nomclient, Adresse, Email, Montantht, Mttaxe, mttotal) VALUES (?, ?, ?, ?, ?, ?, ?)";
        double mtTotal = montantHt + mtTaxe; // Calcul du montant total
        jdbcTemplate.update(sql, telephone, nomClient, adresse, email, montantHt, mtTaxe, mtTotal);
    }




    /**
     * Insérer les détails d'une facture
     * @param numFacture Le numéro de la facture
     * @param isbn L'ISBN du livre
     * @param prix Le prix du livre
     */
    public void insertDetailsFacture(int numFacture, String isbn, double prix) {
        String sql = "INSERT INTO DetailsFacture (NumFacture, Isbn, Prix) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, numFacture, isbn, prix);
    }

    /**
     * Obtenir le dernier numéro de facture
     * @return
     */
    public int getLastNumFacture() {
        String sql = "SELECT IDENT_CURRENT('Factures')";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    /**
     * Sélectionner un livre par son ISBN
     * @param isbn L'ISBN du livre
     * @return Livre ou null si non trouvé
     */
    public Livre selectLivreParIsbn(String isbn) {
        String sql = "SELECT * FROM Livres WHERE Isbn = ?";
        RowMapper<Livre> rowMapper = (rs, rowNum) -> {
            Livre livre = new Livre();
            livre.setIsbn(rs.getString("Isbn"));
            livre.setAuteur(rs.getString("Auteur"));
            livre.setTitre(rs.getString("Titre"));
            livre.setPrix(rs.getDouble("Prix"));
            livre.setQuantite(rs.getInt("Quantite"));
            livre.setPhoto(rs.getString("Photo"));
            livre.setResume(rs.getString("Resume"));
            return livre;
        };
        try {
            return jdbcTemplate.queryForObject(sql, rowMapper, isbn);
        } catch (Exception e) {
            return null; // ou vous pouvez gérer l'exception selon vos besoins
        }
    }





}
