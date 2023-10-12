<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Accueil</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="header.jsp"/>

<!-- Contenu de la page d'accueil -->
<div class="content">
    <div class="container" style="margin-bottom: 200px; margin-top:50px;">
        <div class="welcome">
            <h1>Bienvenue à la Librairie Equinox</h1>
            <p>Plongez dans l'univers des livres et découvrez des histoires fascinantes, des connaissances
                inestimables<br>
                et des mondes imaginaires. Que vous soyez un passionné de littérature, un étudiant en quête de
                savoir<br>
                ou simplement un amateur de bonnes lectures, la Librairie Equinox est votre destination privilégiée pour<br>
                des ouvrages de qualité. Parcourez notre collection variée et trouvez votre prochain coup de cœur !</p>
        </div>


        <!-- Section existante -->
        <div class="row mt-5">
            <div class="col-md-4 d-flex align-items-center justify-content-center">
                <a href="${pageContext.request.contextPath}/achat/listeLivres">
                    <img src="${pageContext.request.contextPath}/img/welc1.jpg" alt="Description de l'image 1" class="img-fluid mx-auto">
                </a>
            </div>
            <div class="col-md-4 d-flex align-items-center justify-content-center">
                <a href="${pageContext.request.contextPath}/achat/listeLivres">
                    <img src="${pageContext.request.contextPath}/img/welc2.jpg" alt="Description de l'image 2" class="img-fluid mx-auto">
                </a>
            </div>
            <div class="col-md-4 d-flex align-items-center justify-content-center">
                <a href="${pageContext.request.contextPath}/achat/listeLivres">
                    <img src="${pageContext.request.contextPath}/img/welc3.jpg" alt="Description de l'image 3" class="img-fluid mx-auto">
                </a>
            </div>
        </div>

        <!-- Nouvelle section -->
        <div class="row mt-5">
            <!-- Section de gauche avec des liens -->
            <div class="col-md-2">  <!-- Modification de la largeur de 'col-md-3' à 'col-md-2' pour la rendre moins large -->
                <ul class="list-unstyled custom-links">
                    <li><a href="#" class="text-dark">CATALOGUES></a></li>
                    <hr>
                    <li><a href="#" class="text-dark">JEUX ET JOUETS POUR TOUT ÂGES></a></li>
                    <hr>
                    <li><a href="#" class="text-dark">JEUX ET JOUETS DE TOUTES SORTES></a></li>
                    <hr>
                    <li><a href="#" class="text-dark">CADEAUX></a></li>
                    <hr>
                    <li><a href="#" class="text-dark">PAPETRIE></a></li>
                    <hr>
                    <li><a href="#" class="text-dark">INFOLETTRE EQUINOX></a></li>
                    <hr>
                </ul>
            </div>

            <!-- Section de droite avec un titre, un paragraphe et 3 images -->
            <div class="col-md-8 ml-4">  <!-- Ajout d'une marge à gauche avec 'ml-4' -->
                <h3 class="custom-title" style="color:grey">Nouveautés</h3>
                <p class="custom-desc" style="color:grey; font-size:13px;">Plus de nouveautés</p>
                <div class="row mt-3 custom-images">
                    <div class="col-md-4 d-flex flex-column justify-content-end"> <!-- Alignement vertical en bas avec 'justify-content-end' -->
                        <img src="${pageContext.request.contextPath}/img/nouv1.jpg" alt="Description de l'image 1" class="img-fluid mb-2">
                        <h5 style="font-weight:bold; text-align:center">Guinness World Records 2024:Edition francaise</h5>
                        <p style="font-size:11px; text-align:center;">En stock : Expedie en 48 heures.</p>
                        <span style="text-align: center; font-weight:bold;">19,95 $</span>
                    </div>
                    <div class="col-md-4 d-flex flex-column justify-content-end">
                        <img src="${pageContext.request.contextPath}/img/nouv2.jpg" alt="Description de l'image 2" class="img-fluid mb-2">
                        <h5 style="font-weight:bold; text-align:center">La ou bat le coeur du monde</h5>
                        <p style="font-size:11px; text-align:center;">En stock : Expedie en 48 heures.</p>
                        <span style="text-align: center; font-weight:bold;">24,95 $</span>
                    </div>
                    <div class="col-md-4 d-flex flex-column justify-content-end">
                        <img src="${pageContext.request.contextPath}/img/nouv3.jpg" alt="Description de l'image 3" class="img-fluid mb-2">
                        <h5 style="font-weight:bold; text-align:center">Une paillette a la fois : journal d'une reine</h5>
                        <p style="font-size:11px; text-align:center;">En stock : Expedie en 48 heures.</p>
                        <span style="text-align: center; font-weight:bold;">29,95 $</span>
                    </div>
                </div>
            </div>
        </div>


    </div>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
