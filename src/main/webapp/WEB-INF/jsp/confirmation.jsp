<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Confirmation de Paiement</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="content">
    <div class="container" style="margin-bottom: 200px; margin-top:50px;">

        <h1>Confirmation de Paiement</h1>
        <p>Votre paiement a été traité avec succès. Merci pour votre achat !</p>
    </div>
</div>

<% if (Boolean.TRUE.equals(request.getAttribute("emailSent"))) { %>
<p>Un courriel de confirmation a été envoyé à votre adresse.</p>
<% } else { %>
<p>Désolé, nous n'avons pas pu envoyer le courriel de confirmation. Veuillez vérifier votre boîte de réception
    ultérieurement ou nous contacter.</p>
<% } %>
<jsp:include page="footer.jsp"/>
</body>
</html>
