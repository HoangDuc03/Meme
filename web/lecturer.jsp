<%-- 
    Document   : inforstudent
    Created on : Feb 7, 2024, 11:24:04 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Lecturer" %>
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
            <div style="display:flex    ; justify-content: center;">
                <table border=" 1px" >
                    <tr>
                        <td>Name</td>
                        <td>Gender</td>
                        <td>Mail</td>
                        <td>Number Phone</td>
                        <td>Citizen Identification Card</td>
                        <td>Address</td>
                    </tr>
                    <tr>
                        <%
                            Lecturer std = (Lecturer) request.getAttribute("infor");
                        %>
                        <td><%=std.getName()%></td>

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
            </div>
            <div style="display:flex    ; justify-content: center ">
                <table border="1px" style="margin-right:10px">
                    <tr>
                        <td>Contract</td>
                        <td>CSF</td>
                        <td>Duration</td>
                    </tr>
                    <tr>
                        <td>
                            <% if(std.isContract())
                                {
                            %>

                            <%="7 years"%>
                            <% }
                                else{
                            %>
                            <%="0"%>
                            <% }
                            %>
                        </td>
                        <td><%=std.getCsf()%></td>
                        <td><%=std.getDuration()%></td>
                    </tr>
                </table>

            </div>

        </div>
    </body>
</html>
