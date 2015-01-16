<%-- 
    Document   : index
    Created on : 9 janv. 2015, 11:08:53
    Author     : Arthur Ogé, Nataly Luck, Sebastian Lendre
--%>

<%@page import="java.util.List"%>
<%@page import="freeart.Creation"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    freeart.EJBCreation.getCreations();
    %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <p> coucou </p>
        <p>Test Nath</p>
        <span>Le poussin piou</span>  
        <h1>i gwec</h1>
        <ul>
	<c:forEach var="crea" items="${listcrea}">
		<li><c:out value="${crea}" /></li>
	</c:forEach>
        </ul>
        
    </body>
</html>
