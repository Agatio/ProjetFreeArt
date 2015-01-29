<%-- 
    Document   : afficherPanier
    Created on : 28 janv. 2015, 19:22:20
    Author     : Nathalie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FreeArt</title>
    </head>
    <body>
        <c:forEach var="item" items="${monPanier}">
            <table>
                <tr>
                    <td><img width="200" src="<c:out value="${item.getFile()}" />"/></td>
                </tr>
                <tr>
                    <td>${item.getNom()}</td>
                </tr>
            </table>   
        </c:forEach>
        <a href="img/tempZip.zip" download="img/tempZip.zip">Télécharger le panier</a>
    </body>
</html>
