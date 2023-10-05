<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Paiement</title>
</head>
<body>
<jsp:include page="header.jsp" />

<!-- Contenu de la page paiement.jsp -->
<div id="content">
    <h1>Paiement</h1>
    <p>Remplissez les informations de paiement :</p>
    <form action="paiementProcessing" method="post">
        <label for="telephone">Téléphone :</label>
        <input type="text" id="telephone" name="telephone" required><br>
        <label for="nomClient">Nom du Client :</label>
        <input type="text" id="nomClient" name="nomClient" required><br>
        <label for="adresse">Adresse :</label>
        <input type="text" id="adresse" name="adresse" required><br>
        <label for="email">Email :</label>
        <input type="email" id="email" name="email" required><br>
        <button type="submit">Valider le Paiement</button>
    </form>
</div>

<jsp:include page="footer.jsp" />
</body>
</html>
