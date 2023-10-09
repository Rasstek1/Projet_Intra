<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Paiement</title>
    <!-- Lien vers Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<jsp:include page="header.jsp"/>


<div class="content">
    <div class="container" style="margin-bottom: 200px; margin-top:50px;">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="border p-4"> <!-- Ajoutez la classe border pour le cadre -->
                    <h1 class="text-center">Paiement</h1>
                    <p class="text-center">Remplissez les informations de paiement :</p>
                    <form action="${pageContext.request.contextPath}/achat/paiement" method="post">
                        <div class="mb-3">
                            <label for="telephone" class="form-label">Téléphone :</label>
                            <input type="text" class="form-control" id="telephone" name="telephone" required>
                        </div>
                        <div class="mb-3">
                            <label for="nomClient" class="form-label">Nom du Client :</label>
                            <input type="text" class="form-control" id="nomClient" name="nomClient" required>
                        </div>
                        <div class="mb-3">
                            <label for="adresse" class="form-label">Adresse :</label>
                            <input type="text" class="form-control" id="adresse" name="adresse" required>
                        </div>
                        <div class="mb-3">
                            <label for="email" class="form-label">Email :</label>
                            <input type="email" class="form-control" id="email" name="email" required>
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Valider le Paiement</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
