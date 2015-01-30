<%-- 
    Document   : detailImg
    Created on : 25 janv. 2015, 13:02:22
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
        
        <img src="<c:out value="${creationDetail.file}" />"/>   
        <p>nom : ${creationDetail.nom}</p>
        <p>description : ${creationDetail.description}</p>
        <p>Déposé par : ${nomUtilisateur.getLogin()}</p>
        <p>Date dépôt : ${creationDetail.date}</p>
        <p>dimension : ${creationDetail.dimensFile}</p>
        <p>poids : ${creationDetail.poidsFile}</p>
        
         
            <p> Commentaires : </p>
        <c:forEach var="itemCom" items="${listcom}" varStatus="status">
                <c:if test="${itemCom.getIdCreation() == creationDetail.getId()}">
                    
                        <ul> 
                            <li> Date : ${itemCom.getDate()} </li>
                            <c:forEach var="itemUser" items="${listuser}" varStatus="status">
                                <c:if test="${itemCom.getIdUser() == itemUser.getId()}">
                                     <li> Login : ${itemUser.getLogin()} </li>
                                </c:if> 
                             </c:forEach>
                            <li> Contenu : ${itemCom.getContenu()} </li>
                        </ul>
                        
                        <c:if test="${itemCom.getIdUser() == nomUtilisateur.getId()}">
                            <form method="POST" action="AfficheDetailImgServlet" id="cheat">
                                <input type="hidden" name="action" value="modify"/>
                                <input type="hidden" name="idCom" value="${itemCom.getId()}" />
                                <input type="hidden" name="creafile" value="${creationDetail.getFile()}" />
                                <input type="submit" value="Modifier par :"/>
                                <label>Nouveau contenu : </label><input type="text" name="newContenuCom" value="${itemCom.getContenu()}"/>
                            </form>
        
                            <form method="POST" action="AfficheDetailImgServlet" id="cheat">
                                <input type="hidden" name="action" value="delete"/>
                                <input type="hidden" name="idCom" value="${itemCom.getId()}" />
                                <input type="hidden" name="creafile" value="${creationDetail.getFile()}" />
                                <input type="submit" value="Supprimer"/>
                            </form>
                        </c:if>
                </c:if> 
        </c:forEach>

<c:if test="${!empty sessionScope.sessionUtilisateur}">
<h3>Ajouter un commentaire</h3>        
        <form action="AfficheDetailImgServlet" method="post">
                <label>Contenu : </label><input type="text" name="contenuCom" />
                <input type="hidden" name="idCrea" value="${creationDetail.getId()}" />
                <input type="hidden" name="creafile" value="${creationDetail.getFile()}" />
                <input type="hidden" name="action" value="add"/>
                <input type="submit" value="Ajouter !" />
        </form>
 </c:if> 
        
        <a href="PanierServlet?id=${creationDetail.file}">Ajouter l'image au panier</a>
        <a href="${creationDetail.file}" download="${creationDetail.file}">Télécharger l'image directement</a>
    </body>
</html>
