<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta charset="UTF-8">
    <title>Header</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="/gestionPanier.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

    <script src="${pageContext.request.contextPath}/js/script.js"></script>
    <title>Header</title>
</head>
<body>
<!-- Bande bord en bord -->
<div class="top-bar"></div>

    <!-- Header -->
    <header class="header-bg">
        <!-- Barre de navigation -->
        <nav class="navbar navbar-expand-lg">
            <div class="container-fluid">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/accueil">
                    <img src="${pageContext.request.contextPath}/img/Logo.png" alt="Logo" class="logo-img"/>
                </a>
                <h2>Equinox Library</h2>

                <!-- Bouton hamburger -->
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav ms-auto pe-5">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/accueil">Accueil</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/achat/listeLivres">Liste de
                                livres</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/admin/ajouterLivre">Administration</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>


    <!-- Bannière publicitaire -->
    <div class="banner">

    </div>

<!-- Conteneur global -->
<div class="container">
    <!-- Contenu de la page -->
    <div id="content">
        <!-- Le contenu de votre page (tableaux, formulaires, etc.) ici -->
    </div>
</div>

</body>
</html>
