<%-- 
    Document   : inforstudent
    Created on : Feb 7, 2024, 11:24:04 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Student" %>
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
                <table border=" 1px" >
                    <tr>
                        <td>Name</td>
                        <td>Student Code</td>
                        <td>Gender</td>
                        <td>Mail</td>
                        <td>Number Phone</td>
                        <td>Citizen Identification Card</td>
                        <td>Address</td>
                    </tr>
                    <tr>
                        <%
                            Student std = (Student) request.getAttribute("infor");
                        %>
                        <td><%=std.getName()%></td>
                        <td><%=std.getMSSV()%></td>

                        <td>
                            <% if(std.isGender())
                                {
                            %>

                            <%="Male"%>
                            <% }
                                else{
                            %>
                            <%="Female"%>
                            <% }
                            %>
                        </td>
                        <td><%=std.getMail()%>@mail.com</td>
                        <td><%=std.getPhone()%></td>
                        <td><%=std.getCCCD()%></td>
                        <td><%=std.getAddress()%></td>
                    </tr>
                </table  >
                <table border="1px" style="margin-right:10px">
                    <tr>
                        <td>Semester</td>
                        <td>Major</td>
                    </tr>
                    <tr>
                        <td><%=std.getSemester()%></td>
                        <td><%=std.getMajor()%></td>
                    </tr>
                </table>

            </div>

        </div>
    </body>
</html>
