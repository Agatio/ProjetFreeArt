<%-- 
    Document   : detailImg
    Created on : 25 janv. 2015, 13:02:22
    Author     : Nathalie
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       
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
                            <li> ${itemCom.getDate()} </li>
                            <c:forEach var="itemUser" items="${listuser}" varStatus="status">
                                <c:if test="${itemCom.getIdUser() == itemUser.getId()}">
                                     <li> ${itemUser.getLogin()} </li>
                                </c:if> 
                             </c:forEach>
                            <li> ${itemCom.getContenu()} </li>
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
