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
<!DOCTYPE html>
<html>
    <head>
        <title>FreeArt</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.2/flatly/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="navbar navbar-default navbar-static-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a href="index.jsp" class="navbar-brand">FreeArt /</a>
                </div>
                
                <nav class="navbar-collapse">
                    <c:if test="${!empty sessionScope.sessionUtilisateur}">
                        <ul class="nav navbar-nav">
                            
                            <li>
                                <a href="UploadServlet">Upload</a>
                            </li>
                            <li>
                                <a href="AfficherPanierServlet">Afficher mon panier</a>
                            </li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li>
                                <a>Vous êtes connecté(e) sous le nom de : ${sessionScope.sessionUtilisateur}</a>
                            </li>
                        </ul>
                    </c:if>
                    <c:if test="${empty sessionScope.sessionUtilisateur}">
                        <ul class="nav navbar-nav">
                            <li>
                                <a href="join.jsp">Join</a>
                            </li>
                            <li>
                                <a href="login.jsp">Login</a>
                            </li>
                            <li>
                                <a href="AfficherPanierServlet">Afficher mon panier</a>
                            </li>
                        </ul>
                    </c:if>    
                </nav>
            </div>
        </div>
        <fieldset>
            <legend>Les dernières créations...</legend>
        </fieldset>
        
        <form method="POST" action="CreationServlet" id="cheat">
            <input type="hidden" name="action" value="testNath"/>
            <input type="submit" value="Afficher les images"/>
        </form>
        
        <form method="POST" action="CreationServlet" id="cheat">
            <input type="hidden" name="action" value="afficherParCat"/>
            <input type="submit" value="Afficher les images par catégories"/>
        </form>
        
        <ul>
            <c:forEach var="item" items="${categories}" varStatus="status">
                <table>
                    <tr>
                        <td><a href="AfficheDetailImgServlet?chemin=${item.getFile()}">${item.getNom()}</a> déposé par ${nomUtilisateur[status.index].getLogin()}</td>
                    </tr>
                    <tr>
                        <td><a class="thumbnail" href="AfficheDetailImgServlet?chemin=${item.getFile()}"><img src="<c:out value="${item.getFile()}" />"/></a></td>
                    </tr>
                </table>                
            </c:forEach>
        </ul>
        
        <c:forEach var="itemCategorie" items="${lesCategories}">
            <h2>${itemCategorie.getNom()}</h2>
            <c:forEach var="itemCreation" items="${lesCreations}" varStatus="status">                
                <c:if test="${itemCategorie.getId() == itemCreation.getIdCategorie()}"> 
                        <div class="row">
                            <div class="col-sm-6 col-md-4">
                                <div class="thumbnail">
                                    <a href="AfficheDetailImgServlet?chemin=${itemCreation.getFile()}"><img src="<c:out value="${itemCreation.getFile()}"/>"/></a> 
                                </div>
                                <div class="caption">
                                    <h4>${itemCreation.getNom()}</h4>
                                    <p>déposé par ${nomUtilisateur[status.index].getLogin()}</p>
                                </div>
                            </div>
                        </div>
                </c:if> 
            </c:forEach>
        </c:forEach>
            
            <form method="POST" action="RechercheImageServlet">
                <input type="text" name="recherche" placeholder="Votre recherche"/>
                <input type="submit" value="Lancer la recherche"/>
            </form>
    </body>
</html>
