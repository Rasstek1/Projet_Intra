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

<div class="toast" role="alert" aria-live="assertive" aria-atomic="true" data-delay="3000">
    <div class="toast-header">
        <strong class="mr-auto">Notification</strong>
        <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
    <div class="toast-body">
        <% if (Boolean.TRUE.equals(request.getAttribute("emailSent"))) { %>
        Un courriel de confirmation a été envoyé à votre adresse.
        <% } else { %>
        Désolé, nous n'avons pas pu envoyer le courriel de confirmation. Veuillez vérifier votre boîte de réception ultérieurement ou nous contacter.
        <% } %>
    </div>
</div>

<jsp:include page="footer.jsp"/>

<script>
    $(document).ready(function(){
        $('.toast').toast('show');
    });
</script>



</body>
</html>
