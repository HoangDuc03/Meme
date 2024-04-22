<%-- 
    Document   : newjsp
    Created on : Apr 22, 2024, 5:37:55 PM
    Author     : Admin
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
        <form action="next" method="post">
            Number: <input type="text" name="num"><!-- comment -->
            <div style="background-color: red; width: 200px; height:100px">
                <c:out value="${requestScope.option}"/>    
            </div>  
            <input type="submit">
         </form>

    </body>
</html>
