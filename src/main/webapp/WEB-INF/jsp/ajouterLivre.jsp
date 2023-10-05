<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter un Livre</title>
</head>
<body>
<jsp:include page="header.jsp" />

<!-- Contenu de la page ajouterLivre.jsp -->
<div id="content">
    <h1>Ajouter un Livre</h1>
    <form action="ajouterLivre" method="post">
        <label for="isbn">ISBN :</label>
        <input type="text" id="isbn" name="isbn" required><br>
        <input type="text" id="auteur" name="auteur" required><br>
        <label for="titre">Titre :</label>
        <input type="text" id="titre" name="titre" required><br>
        <label for="prix">Prix :</label>
        <input type="text" id="prix" name="prix" required><br>
        <label for="quantite">Quantité :</label>
        <input type="text" id="quantite" name="quantite" required><br>
        <label for="photo">URL de la Photo :</label>
        <input type="text" id="photo" name="photo"><br>
        <label for="resume">Résumé :</label>
        <textarea id="resume" name="resume" required></textarea><br>
        <button type="submit">Ajouter le Livre</button>
    </form>
</div>

<jsp:include page="footer.jsp" />
</body>
</html>
