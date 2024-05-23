<%-- 
    Document   : login
    Created on : Feb 6, 2024, 7:33:57 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HE173344</title>
        <link rel="stylesheet" href="CSS/css.css" />
        <link rel="Stylesheet" href="CSS/bootstrap.css" type="text/css" /><link rel="Stylesheet" href="CSS/bootstrap.min.css" type="text/css" /><link rel="Stylesheet" href="Content/bootstrap-theme.min.css" type="text/css" />
    </head>
    <body>
        <div class="container">
            <%@include file="header.jsp" %>
            <h1>Login</h1>
            <form action="Login" method="post">
                <div class="space">
                    <label>UserName: </label><input type="text" name="user" value="">
                </div>
                <div class="space">
                    <label>PassWord: </label><input type="password" name="password" value="">
                </div>
                <input type="submit" name="done" value="Login">
            </form>
            <c:if test="${requestScope.error != null}">
                <p style="color: red">${requestScope.error}</p>
            </c:if>
        </div>
    </body>
</html>
