<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<ul class="navbar-nav">
    <li class="nav-item active">
        <c:url value="ControleurMain" var="url149" />
      <a class="nav-link" href="${url149}">Home</a>
    </li>
    <li class="nav-item">
        <c:url value="ControleurMain?section=creer-donnees" var="url150" />
      <a class="nav-link" href="${url150}">Creer les données</a>
    </li>
    <li class="nav-item">
        <c:url value="ControleurMain?section=catalogue" var="url155" />
      <a class="nav-link" href="${url155}">Catalogue</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#">S'inscrire</a>
    </li>
    <li class="nav-item">
       <c:url value="ControleurMain?section=vers-login-form" var="versLogin" /> 
      <a class="nav-link" href="${versLogin}">Se connecter</a>
    </li>
    <li class="nav-item">
      <c:url value="ControleurMain?section=detail-panier" var="url450" />
      <a class="nav-link" href="${url450}">Mon panier - ${infoPanier}</a>
    </li>
    <li class="nav-item">
      <a class="nav-link disabled" href="#">Disabled</a>
    </li>
</ul>
