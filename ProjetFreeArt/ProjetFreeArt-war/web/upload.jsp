<%-- 
    Document   : upload
    Created on : 25 janv. 2015, 16:12:34
    Author     : Nathalie
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
                                <a style="text-decoration: none;">Vous êtes connecté(e) sous le nom de : ${sessionScope.sessionUtilisateur}</a>
                                <form method="post" action="ConnexionServlet">
                                    <a href="javascript:;" onclick="parentNode.submit();">Deconnexion</a>
                                    <input type="hidden" name="action" value="logout">
                                </form>
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
        
        <h1>Upload a file</h1>        
        <form action="UploadServlet" method="post" enctype="multipart/form-data">
                <input type="file" name="file" /><br/>
                <label>Nom de l'image : </label><input type="text" name="nomImg" />
                <label>Description : </label><input type="text" name="descr" />
                <label>Categorie de l'image : </label>
                <select name="catImg">
                    <c:forEach var="item" items="${cat}">
                        <option value="${item.getId()}">${item.getNom()}</option>
                    </c:forEach> 
                </select>
                <input type="submit" value="upload" />
        </form>
    </body>
</html>
