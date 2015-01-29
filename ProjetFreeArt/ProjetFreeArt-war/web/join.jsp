<%-- 
    Document   : join
    Created on : 25 janv. 2015, 11:05:17
    Author     : Nathalie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FreeAert</title>
        <link rel="stylesheet" href="styleJoinAndLogin.css" />
    </head>
    <body>
        <div>
            <fieldset>
                <legend>Join us</legend>
                <form method="post" action="JoinServlet">
                    <label>Login</label><input type="text" name="login"><br/>
                    <label>Password</label><input type="password" name="password"><br/>
                    <input type="submit" value="ok">
                    <input type="hidden" name="action" value="joinform">
                </form>
            </fieldset>
        </div>
    </body>
</html>