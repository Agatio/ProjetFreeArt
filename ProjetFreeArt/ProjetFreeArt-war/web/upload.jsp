<%-- 
    Document   : upload
    Created on : 25 janv. 2015, 16:12:34
    Author     : Nathalie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Upload a file</h1>
       <!-- <form method="post" action="UploadServlet" enctype="multipart/form-data">
            <label>Fichier : </label><input type="file" name="filePath" multiple="multiple"/><br/>
            <label>Nom : </label><input type="texte" name="fileName"/><br/>
            <label>Description : </label><input type="texte" name="fileDesc"/><br/>
            <input type="submit" value="Envoyer" name="insertimage"/>
        </form>-->
        
        <form action="UploadServlet" method="post" enctype="multipart/form-data">
                <input type="file" name="file" />
                <input type="submit" value="upload" />
            </form>
    </body>
</html>
