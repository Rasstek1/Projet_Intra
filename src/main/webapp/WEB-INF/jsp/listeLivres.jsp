<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des Livres</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h1>Liste des Livres</h1>
            <p>Nous vous présentons une liste de livres disponibles :</p>

            <!-- Affichage du nombre total d'articles dans le panier -->
            <p>Nombre total d'articles dans le panier : ${panier.getNombreTotalLivres()}</p>
        </div>
    </div>

    <div class="row">
        <c:forEach var="livre" items="${livres}">
            <div class="col-md-4" style="padding-left: 0px;">
                <div class="book-box">
                    <img src="${pageContext.request.contextPath}/img/${livre.photo}" alt="${livre.titre}"
                         style="max-height: 300px; width: auto;">
                    <h4>${livre.titre}</h4>
                    <p>Auteur : ${livre.auteur}</p>
                    <p>Prix unitaire : <fmt:formatNumber value="${livre.prix}" pattern="0.00" />$</p>

                    <p>Quantité disponible : ${livre.quantite}</p>

                    <form action="${pageContext.request.contextPath}/achat/acheterLivre" method="get" id="acheterForm${livre.isbn}">
                        <input type="hidden" name="isbn" value="${livre.isbn}">
                        <div class="col-4">
                            <input type="number" name="quantite" value="1" min="1" max="${livre.quantite}" class="form-control" style="margin-bottom:10px;" id="quantite${livre.isbn}" onchange="updateQuantity('${livre.isbn}', ${livre.quantite})">
                            <button type="submit" class="btn btn-primary" onclick="preventFormSubmit('${livre.isbn}', '${livre.quantite}')">Acheter</button>
                        </div>
                    </form>

                    <!-- Lien pour aller au panier -->
                    <a href="${pageContext.request.contextPath}/achat/afficherPanier" class="btn btn-success" style="margin-top:10px;">Aller au Panier</a>
                </div>
            </div>
        </c:forEach>
    </div>
</div>


<jsp:include page="footer.jsp"/>

<script>
    function preventFormSubmit(isbn) {
        event.preventDefault();
        var form = document.getElementById('acheterForm' + isbn);
        form.submit();
    }
    function updateQuantity(isbn) {
        var input = document.getElementById('quantite' + isbn);
        var newQuantity = input.value;
        // Mettez à jour la quantité dans le panier (vous devrez implémenter cette fonction)
        updateCartQuantity(isbn, newQuantity);
    }
</script>
</body>
</html>
