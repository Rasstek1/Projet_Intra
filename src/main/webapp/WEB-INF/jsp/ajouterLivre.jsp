<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter un Livre</title>
    <!-- Lien vers Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="content">
    <div class="container" style="margin-bottom: 200px; margin-top:50px;">

        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="border p-4">
                    <h1 class="text-center">Ajouter un Livre</h1>


                    <!-- Affichez le message d'erreur s'il existe -->
                    <c:if test="${not empty errorMessage and errorMessage.trim() != ''}">
                        <div class="alert alert-danger">
                                ${errorMessage}
                        </div>
                    </c:if>

                    <form action="${pageContext.request.contextPath}/admin/ajouterLivre" method="post"
                          enctype="multipart/form-data">
                        <div class="mb-3">
                            <label for="isbn" class="form-label">ISBN :</label>
                            <input type="text" id="isbn" name="isbn" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label for="auteur" class="form-label">Auteur :</label>
                            <input type="text" id="auteur" name="auteur" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label for="titre" class="form-label">Titre :</label>
                            <input type="text" id="titre" name="titre" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label for="prix" class="form-label">Prix :</label>
                            <input type="text" id="prix" name="prix" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label for="quantite" class="form-label">Quantité :</label>
                            <input type="text" id="quantite" name="quantite" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label for="photo" class="form-label">Télécharger la Photo :</label>
                            <input type="file" id="photo" name="photoFile" class="form-control" accept="image/*"
                                   required>
                        </div>
                        <div class="mb-3">
                            <label for="resume" class="form-label">Nom du fichier PDF :</label>
                            <input type="text" id="resume" name="resume" class="form-control">
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Ajouter le Livre</button>
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
