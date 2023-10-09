<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.text.DecimalFormat" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Panier</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .cart-box {
            border: 2px solid #ddd;
            padding: 20px;
            border-radius: 10px;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="content-wrapper" style="margin-right:300px; margin-left: 300px; ">

    <div id="content" class="cart-box">
        <h1>Panier</h1>
        <p>Contenu de votre panier :</p>

        <div class="row">
            <c:choose>
                <c:when test="${empty panier or empty panier.liste}">
                    <div class="col-md-12">
                        <p>Votre panier est vide.</p>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="col-md-8">
                        <div class="row">
                            <c:forEach var="livre" items="${panier.liste}">
                                <div class="col-md-12 mb-3">
                                    <div class="card">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <img src="${pageContext.request.contextPath}/img/${livre.photo}"
                                                     alt="${livre.titre}" class="card-img-top"
                                                     style="width: 150px; height: auto;">
                                            </div>
                                            <div class="col-md-8">
                                                <div class="card-body">
                                                    <h5 class="card-title">${livre.titre}</h5>
                                                    <p class="card-text">Auteur: ${livre.auteur}</p>
                                                    <p class="card-text">Prix: ${livre.prix}</p>
                                                    <p class="card-text">Quantité: ${livre.quantite}</p>
                                                    <a href="supprimerLivre?isbn=${livre.isbn}" class="btn btn-danger">Supprimer</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <h4>Récapitulatif</h4>
                        <table class="table">
                            <tbody>
                            <tr>
                                <td>Sous-total:</td>
                                <td><fmt:formatNumber value="${panier.getSousTotal()}" pattern="0.00"/>$</td>
                            </tr>
                            <tr>
                                <td>TPS (5%):</td>
                                <td><fmt:formatNumber value="${panier.getTps()}" pattern="0.00"/>$</td>
                            </tr>
                            <tr>
                                <td>TVQ (9.975%):</td>
                                <td><fmt:formatNumber value="${panier.getTvq()}" pattern="0.00"/>$</td>
                            </tr>
                            <tr>
                                <td>Total:</td>
                                <td><fmt:formatNumber value="${panier.getPrixTotal()}" pattern="0.00"/>$</td>
                            </tr>
                            </tbody>
                        </table>
                        <a href="paiement" class="btn btn-success">Paiement</a>
                        <a href="annulerAchat" class="btn btn-danger">Annuler</a>
                        <a href="listeLivres" class="btn btn-primary" style="margin-top:20px;">Retour à la liste d'achat</a>

                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>

</body>
</html>
