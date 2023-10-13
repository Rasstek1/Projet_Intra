package com.martin.projet_intra.models;

import io.micrometer.common.util.internal.logging.AbstractInternalLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service  // Définit la classe comme un service Spring
public class LibrairieDataContext {

    @Autowired  // Injection de dépendance pour JdbcTemplate
    private JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(LibrairieDataContext.class);  // Logger pour la classe

    // ********** Méthodes pour la gestion des livres ********** //

    // Insérer un nouveau livre dans la base de données
    public void insertLivre(String isbn, String auteur, String titre, double prix, int quantite, String photo, String resume) {
        String sql = "INSERT INTO Livres (Isbn, Auteur, Titre, Prix, Quantite, Photo, Resume) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql, isbn, auteur, titre, prix, quantite, photo, resume);
            logger.info("Livre inséré avec succès.");
        } catch (Exception e) {
            logger.error("Erreur lors de l'insertion du livre : " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Sélectionner tous les livres
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

    // Insérer une nouvelle facture
    public void insertFacture(String telephone, String nomClient, String adresse, String email, double montantHt, double mtTaxe) {
        String sql = "INSERT INTO Factures (Telephone, Nomclient, Adresse, Email, MontantHT, MtTaxe, MtTotal) VALUES (?, ?, ?, ?, ?, ?, ?)";
        double mtTotal = montantHt + mtTaxe;
        jdbcTemplate.update(sql, telephone, nomClient, adresse, email, montantHt, mtTaxe, mtTotal);
    }

    // Insérer les détails d'une facture
    public void insertDetailsFacture(int numFacture, String isbn, double prix) {
        String sql = "INSERT INTO DetailsFacture (NumFacture, Isbn, Prix) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, numFacture, isbn, prix);
    }

    // Obtenir le dernier numéro de facture
    public int getLastNumFacture() {
        String sql = "SELECT IDENT_CURRENT('Factures')";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    // Sélectionner un livre par son ISBN
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
            return null;
        }
    }

    // Vérifier si un ISBN existe déjà
    public boolean isbnExiste(String isbn) {
        String sql = "SELECT COUNT(*) FROM Livres WHERE Isbn = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, isbn);
        return count > 0;
    }

    // Obtenir la quantité d'un livre
    public int getQuantiteLivre(String isbn) {
        String sql = "SELECT Quantite FROM Livres WHERE Isbn = ?";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, isbn);
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération de la quantité pour ISBN " + isbn + ": " + e.getMessage());
            return 0;
        }
    }

    // Mettre à jour la quantité d'un livre
    public void updateQuantiteLivre(String isbn, int nouvelleQuantite) {
        String sql = "UPDATE Livres SET Quantite = ? WHERE Isbn = ?";
        try {
            jdbcTemplate.update(sql, nouvelleQuantite, isbn);
            logger.info("Quantité mise à jour avec succès pour ISBN " + isbn);
        } catch (Exception e) {
            logger.error("Erreur lors de la mise à jour de la quantité pour ISBN " + isbn + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

}


