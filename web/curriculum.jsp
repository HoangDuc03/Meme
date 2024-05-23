<%-- 
    Document   : curriculum
    Created on : Feb 9, 2024, 1:20:02 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
         <div>
            <%@include file="header.jsp"%>
        </div>
        <div style="display: flex; justify-content: center;">
            <table border="1px">
                <tr>
                
                    <td style="color: blue; text-align: center"><strong>SUBJECT Name</strong></td>
                    <td style="color: blue; text-align: center"><strong>SUBJECT Change</strong></td>
                    <td style="color: blue; text-align: center"><strong>TERM</strong></td>
                    
                </tr>
            
                <c:forEach var="i" items="${requestScope.Cur}">
                    <tr>
                        <td>${i.getName()} (${i.getSign()})</td>
                        <c:if test="${i.getName() == i.getNamechange()}">
                            <td> - </td>
                        </c:if>
                        <c:if test="${i.getName() != i.getNamechange()}">
                            <td>${i.getNamechange()} (${i.getSignchange()}) </td>
                        </c:if>
                        
                        <td>${i.getTerm()} </td>
                    </tr>
                </c:forEach> 
            </table>
        </div>
        </div>
    </body>
</html>
