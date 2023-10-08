<%@ include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div id="content">
    <h2>Oups! Quelque chose s'est mal passé.</h2>

    <h3>Détails de l'erreur:</h3>
    <p><strong>Titre :</strong> <c:out value="${errorViewModel.title}" /></p>
    <p><strong>Message :</strong> <c:out value="${errorViewModel.message}" /></p>
    <p><strong>Code d'erreur :</strong> <c:out value="${errorViewModel.errorCode}" /></p>
    <p><strong>Description :</strong> <c:out value="${errorViewModel.description}" /></p>

    <p>Nous nous excusons pour ce désagrément. S'il vous plaît <a href="contact">contactez-nous</a> si cette erreur persiste.</p>
</div>

<%@ include file="footer.jsp" %>

