<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="now" value="${java.util.Calendar.getInstance().time}"/>
<%@ page isELIgnored="false" %>


<fmt:formatDate pattern="yyyy" value="${now}" var="year"/>
<fmt:formatDate pattern="HH:mm:ss" value="${now}" var="time"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Footer</title>
</head>
<body>

<div id="footer" class="footer-container">
    <div class="footer-section">
        <p id="current-time"></p>
    </div>
    <div class="footer-section">
        <p>&copy; ${year} Librairie Equinox - 102 de la mine, Gatineau, Quebec</p>
    </div>

    <!-- Section d'heure au centre -->


    <!-- Liens à droite -->
    <div class="footer-section">
        <ul class="footer-links">
            <li>
                <a href="${pageContext.request.contextPath}/accueil">Accueil</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/achat/listeLivres">Liste de livres</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/admin/ajouterLivre">Administration</a>
            </li>
        </ul>
    </div>
</div>



<script>
    document.addEventListener('DOMContentLoaded', (event) => {
        function updateTime() {
            let now = new Date();
            let formattedDate = now.toLocaleDateString('fr-FR', {
                year: 'numeric', month: 'long', day: 'numeric',
                hour: '2-digit', minute: '2-digit', second: '2-digit'
            });
            document.getElementById('current-time').textContent = formattedDate;
        }

        // Appel initial pour définir l'heure au moment du chargement de la page
        updateTime();

        // Mise à jour de l'heure toutes les secondes
        setInterval(updateTime, 1000);
    });

</script>

</body>
</html>
