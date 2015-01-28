<%-- 
    Document   : index
    Created on : 9 janv. 2015, 11:08:53
    Author     : Arthur Ogé, Nataly Luck, Sebastian Lendre
--%>

<%@page import="java.util.List"%>
<%@page import="freeart.Creation"%>
<%@page import="freeart.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    //freeart.CreationServlet
    %>
<!DOCTYPE html>
<html>
    <head>
        <title>FreeArt</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="style.css" />
    </head>
    <body>
        <header>
            <h1>FreeArt /</h1>
            
            <c:if test="${!empty sessionScope.sessionUtilisateur}">
                <p class="connexion">Vous êtes connecté(e) sous le nom de : ${sessionScope.sessionUtilisateur}</p>
                <a href="upload.jsp">Upload</a>
                <a href="AfficherPanierServlet">Afficher mon panier</a>
            </c:if>
            <c:if test="${empty sessionScope.sessionUtilisateur}">
                <div class="connexion">
                    <a href="join.jsp">Join</a>
                    <a href="login.jsp">Login</a>
                    <a href="AfficherPanierServlet">Afficher mon panier</a>
                </div>
            </c:if>    
        </header>
        <fieldset>
            <legend>Les dernières créations...</legend>
        </fieldset>
        
        <form method="POST" action="CreationServlet" id="cheat">
            <input type="hidden" name="action" value="testNath"/>
            <input type="submit" value="Afficher les noms d'images"/>
        </form>
        
        <ul>
            <c:forEach var="item" items="${categories}" varStatus="status">
                <table>
                    <tr>
                        <td><a href="AfficheDetailImgServlet?chemin=${item.getFile()}"><img src="<c:out value="${item.getFile()}" />"/></a></td>
                    </tr>
                    <tr>
                        <td><a href="AfficheDetailImgServlet?chemin=${item.getFile()}">${item.getNom()}</a> déposé par ${nomUtilisateur[status.index].getLogin()}</td>
                    </tr>
                </table>                
            </c:forEach>
        </ul>
    </body>
</html>
