<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des Livres</title>
</head>
<body>
<jsp:include page="header.jsp" />

<!-- Contenu de la page listeLivres.jsp -->
<div id="content">
    <h1>Liste des Livres</h1>
    <p>Nous vous présentons une liste de livres disponibles :</p>

    <!-- Affichage du nombre total d'articles dans le panier -->
    <p>Nombre total d'articles dans le panier : ${panier.getNombreTotalLivres()}</p>



    <!-- Lien pour voir le panier -->
    <a href="${pageContext.request.contextPath}/achat/afficherPanier">Voir mon Panier</a>


    <table border="1">
        <tr>
            <th>ISBN</th>
            <th>Titre</th>
            <th>Auteur</th>
            <th>Prix</th>
            <th>Quantité</th>
            <th>Photo</th>
            <th>Résumé</th>
            <th>Action</th>
        </tr>
        <c:forEach var="livre" items="${livres}">
            <tr>
                <td>${livre.isbn}</td>
                <td>${livre.titre}</td>
                <td>${livre.auteur}</td>
                <td>${livre.prix}</td>
                <td>${livre.quantite}</td>
                <td>${livre.photo}</td>
                <td>${livre.resume}</td>
                <td>
                    <!-- Vérification si le livre est dans le panier -->
                    <c:choose>
                        <c:when test="${panier.contains(livre)}">
                            <span>Livre dans le Panier</span>
                        </c:when>
                        <c:otherwise>
                            <!-- Lien pour acheter le livre avec formulaire de quantité -->
                            <form action="${pageContext.request.contextPath}/achat/acheterLivre" method="get">
                                <input type="hidden" name="isbn" value="${livre.isbn}">
                                <input type="number" name="quantite" value="1" min="1">
                                <button type="submit">Acheter</button>
                            </form>

                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
    <!-- Erreur quantite de livres -->
    <c:if test="${not empty erreurStock}">
        <p class="erreur">${erreurStock}</p>
    </c:if>

</div>

<jsp:include page="footer.jsp" />
</body>
</html>
