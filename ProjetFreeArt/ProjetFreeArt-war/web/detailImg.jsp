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
        <p>test</p>
        <img src="<c:out value="${creationDetail.file}" />"/>   
        <p>nom : ${creationDetail.nom}</p>
        <p>description : ${creationDetail.description}</p>
        <p>dimension : ${creationDetail.dimensFile}</p>
        <p>poids : ${creationDetail.poidsFile}</p>
        <a href="${creationDetail.file}" download="${creationDetail.file}">dl</a>
    </body>
</html>
