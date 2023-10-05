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

<!-- Contenu de la page afficherPanier.jsp -->
<div id="content">
    <h1>Panier</h1>
    <p>Contenu de votre panier :</p>

    <table border="1">
        <tr>
            <th>Titre</th>
            <th>Prix</th>
            <th>Action</th>
        </tr>
        <c:forEach var="livre" items="${panier}">
            <tr>
                <td>${livre.titre}</td>
                <td>${livre.prix}</td>
                <td><a href="supprimerLivre?isbn=${livre.isbn}">Supprimer</a></td>
            </tr>
        </c:forEach>
    </table>

    <!-- Liens pour le paiement, l'annulation et le retour à la liste d'achat -->
    <a href="paiement">Paiement</a> |
    <a href="annulerAchat">Annuler</a> |
    <a href="listeLivres">Retour à la liste d'achat</a>
</div>

<jsp:include page="footer.jsp" />
</body>
</html>
