<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="now" value="${java.util.Calendar.getInstance().time}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta charset="UTF-8">
    <title>Header</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Header</title>
</head>
<body>
<!-- Bande bord en bord -->
<div class="top-bar">
    <div class="heures">
        <p>Lundi au Mercredi: 10:00 à 18:00</p>
        <p>Jeudi et Vendredi: 10:00 à 21:00</p>
        <p>Samedi: 10:00 à 18:00</p>
        <p>Dimanche: 10:00 à 17:00</p>
    </div>
</div>


<!-- Header -->
<header class="header-bg">
    <!-- Barre de navigation -->
    <nav class="navbar navbar-expand-lg">
        <div class="container-fluid">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/accueil">
                <img src="${pageContext.request.contextPath}/img/Logo.png" alt="Logo" class="logo-img"/>
                <h2 class="brand-name" style="color:mediumseagreen;">Librairie <span style="color:#007dc7; font-weight: bold;">Equinox</span> </h2>
            </a>



            <!-- Bouton hamburger -->
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <nav id="monNavUnique">

                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/accueil">Accueil</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/achat/listeLivres">Liste de livres</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/admin/ajouterLivre">Administration</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    </nav>
</header>


<!-- Bannière publicitaire -->
<div class="banner">

</div>

<!-- Contenu de la page -->


</body>
</html>
