<%-- 
    Document   : recherche
    Created on : 29 janv. 2015, 23:06:02
    Author     : Nathalie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <c:forEach var="item" items="${resultats}">
                <table>
                    <tr>
                        <td><a href="AfficheDetailImgServlet?chemin=${item.getFile()}"><img src="${item.getFile()}"/></a> </td>
                    </tr>
                    <tr>
                        <td>${item.getNom()}</td>
                    </tr>
                    <tr>
                        <td>${item.getDescription()}</td>
                    </tr>
                </table>                
            </c:forEach>
    </body>
</html>
