<%-- 
    Document   : Home
    Created on : Feb 6, 2024, 8:44:20 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Login" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HE173344</title>
        
    </head>
    <body>
        <div class="container">
        <div>
            <%@include file="header.jsp"%>
        </div>
        <div style="display: flex; justify-content: center">
            <table border="1px">
                <tr>
                    <th>DEPARTMENT</th>
                    <th>Subjects can participate</th>
                </tr>
                
                <tr>
                    <td style="text-align: center">
                        <table>
                            ${requestScope.dept}
                        </table>
                    </td>

                    <td style="text-align: center">
                        <table>
                            <c:forEach var="i" items="${requestScope.Cour}">
                                <tr>
                                    <td>
                                        ${i.getName()}
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </td>
                </tr>
            </table>
        </div>
        </div>
    </body>
</html>
