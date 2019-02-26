<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
        <title>catalogue</title>
    </head>
    <body>
        <nav id="menuMain" class="navbar navbar-expand-sm bg-dark navbar-dark">
            <c:url value="ControleurMain?section=menu-main" var="urlMenuMain" />
            <c:import url="${urlMenuMain}" />
        </nav>
        <main>
            <h1>Catalogue</h1>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">Reference</th>
                        <th scope="col">Nom</th>
                        <th scope="col">prix HT/u</th>
                        <th scope="col">operation</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${produits}" var="p">
                        <tr>
                            <td>
                                <c:url value="ControleurMain?section=detail-produit&ref=${p.ref}&origine=catalogue" var="urlDetail" />
                                <a href="${urlDetail}">${p.ref}</a>
                            </td>
                            <td>${p.nom}</td>
                            <td>
                                <fmt:formatNumber value="${p.prix}" minIntegerDigits="1" minFractionDigits="2" maxFractionDigits="2" /> €
                            </td>
                            <td>
                                <c:url value="ControleurMain?section=operations-panier&operation=ajouter&ref=${p.ref}&origine=catalogue" var="url333" />
                                <a href="${url333}" class="btn btn-outline-dark" onclick="actualiserMenuMain('${p.ref}');return false">ajouter au panier</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </main>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
        <script src="js/monJavascript.js" type="text/javascript"></script>
    </body>
</html>
