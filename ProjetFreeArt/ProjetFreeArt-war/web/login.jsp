<%-- 
    Document   : login
    Created on : 12 déc. 2014, 08:50:26
    Author     : Agatio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FreeArt Grobert</title>
    </head>
    <body>
        <h1>Connexion</h1>
        <form method="post" action="ConnexionServlet">
            <label> Login <input type="text" name="login"></label>
            <label> Mot de Passe<input type="password" name="password"></label>
            <input type="submit" value="ok">
            <input type="hidden" name="action" value="loginform">
        </form>
    </body>
</html>
