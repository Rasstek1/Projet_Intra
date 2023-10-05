<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Panier</title>
</head>
<body>
<jsp:include page="header.jsp" />

<div id="content">
    <h1>Panier</h1>
    <p>Contenu de votre panier :</p>

    <table border="1">
        <tr>
            <th>ISBN</th>
            <th>Titre</th>
            <th>Auteur</th>
            <th>Prix</th>
            <th>Quantité</th>
            <th>Sous-total</th>
            <th>Taxe</th>
            <th>Prix Total</th>

        </tr>
        <c:choose>
            <c:when test="${empty panier or empty panier.liste}">
                <tr>
                    <td colspan="8">Votre panier est vide.</td>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach var="livre" items="${panier.liste}">
                    <tr>
                        <td>${livre.isbn}</td>
                        <td>${livre.titre}</td>
                        <td>${livre.auteur}</td>
                        <td>${livre.prix}</td>
                        <td>${livre.quantite}</td>
                        <td>${livre.prix * livre.quantite}</td> <!-- Calcul du sous-total -->
                        <td>${livre.prix * livre.quantite * 0.15}</td> <!-- Calcul de la taxe (15%) -->
                        <td>${(livre.prix * livre.quantite) + (livre.prix * livre.quantite * 0.15)}</td> <!-- Calcul du prix total (Sous-total + Taxe) -->
                    </tr>

                        <td><a href="supprimerLivre?isbn=${livre.isbn}">Supprimer</a></td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </table>

    <!-- Liens pour le paiement, l'annulation et le retour à la liste d'achat -->
    <a href="paiement">Paiement</a> |
    <a href="annulerAchat">Annuler</a> |
    <a href="listeLivres">Retour à la liste d'achat</a>
</div>

<jsp:include page="footer.jsp" />
</body>
</html>

