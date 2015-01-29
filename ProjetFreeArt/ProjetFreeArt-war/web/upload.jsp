<%-- 
    Document   : upload
    Created on : 25 janv. 2015, 16:12:34
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
