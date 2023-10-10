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
        <h1>Bienvenue à la Librairie Equinox</h1>
        <p>Plongez dans l'univers des livres et découvrez des histoires fascinantes, des connaissances inestimables<br>
            et des mondes imaginaires. Que vous soyez un passionné de littérature, un étudiant en quête de savoir<br>
            ou simplement un amateur de bonnes lectures, la Librairie Equinox est votre destination privilégiée pour<br>
            des ouvrages de qualité. Parcourez notre collection variée et trouvez votre prochain coup de cœur !</p>

    </div>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
