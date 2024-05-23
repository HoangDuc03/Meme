<%-- 
    Document   : groups
    Created on : Feb 7, 2024, 3:16:54 PM
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
            <% int index=1; %>
            <div>
                <%@include file="header.jsp"%>
               
            </div>
            <div style="display: flex ;justify-content: center">
                <table>
                    <tr>
                        <td>SEMESTER</td>
                        <td>DEPARTMENT</td>
                        <td>COURSE</td>
                        <td>GROUP</td>
                    </tr>
                    <tr>
                        <td>
                            ${requestScope.term}
                        </td>
                        <td>
                            <table>
                                <c:forEach var="i" items="${requestScope.dept}">
                                    <tr>
                                        <td>
                                            <a href="Group?dept=${i.getCode()}" style="text-decoration:  none;
                                               <c:if test="${i.getCode() == param.dept}">
                                                   color: red;
                                               </c:if>
                                               <c:if test="${i.getCode() != param.dept}">
                                                   color: blue;
                                               </c:if>">
                                                ${i.getName()}
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </td>

                        <td>
                            <table>
                                <c:forEach var="i" items="${requestScope.course}">
                                    <tr>
                                        <td>
                                            <a href="Group?dept=${param.dept}&course=${i.getCode()}" style="text-decoration:  none;
                                               <c:if test="${i.getCode() == param.course}">
                                                   color: red;
                                               </c:if>
                                               <c:if test="${i.getCode() != param.course}">
                                                   color: blue;
                                               </c:if>">
                                                ${i.getName()} ( ${i.getSign()} )
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </td>

                        <td>
                            <table>
                                <c:forEach var="i" items="${requestScope.groups}">
                                    <tr>
                                        <td>
                                            <a href="Group?dept=${param.dept}&course=${param.course}&group=${i.getCode()}" style="text-decoration:  none;
                                               <c:if test="${i.getCode() == param.group}">
                                                   color: red;
                                               </c:if>
                                               <c:if test="${i.getCode() != param.group}">
                                                   color: blue;
                                               </c:if>
                                               ">
                                                ${i.getName()}
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </td>
                    </tr>
                </table> 
            </div>
            <div style="display: flex ;justify-content: center">
                <table border="1px" >

                    <tr>
                        <td>Index</td>
                        <td>Student Code</td>
                        <td>Student Name</td>
                    </tr>

                    <c:forEach var="i" items="${requestScope.listSTD}">
                        <tr>
                            <td>
                                <%=index ++ %>
                            </td>
                            <td>
                                ${i.getName()}
                            </td>
                            <td>
                                ${i.getMSSV()}
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
</html>
