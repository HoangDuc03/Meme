<%-- 
    Document   : NoData
    Created on : Feb 20, 2024, 11:11:08 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Slots" %>
<%@page import="dal.DAOLectOfGroup" %>
<%@page import="dal.DAOToSchedule" %>
<%@page import="java.util.ArrayList" %>
<%@page import="model.Group" %>
<%@page import="model.Student" %>
<%@page import="dal.DAOGroups" %>
<%@page import="dal.DAOAttendance" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div>
            <%
                DAOGroups object= new DAOGroups();
                ArrayList<Student> std = object.loadStudents(sl.getCode());
            %>
            <form action="Attendance" method="POST" id ="attendance">
                <input type="hidden" value="insert" name="execute">
                <input type="hidden" value="<%= sl.getCode() %>" name="group">
                <input type="hidden" value="<%= sl.getSlotNumber() %>" name="slot">
                <div style="display: flex; justify-content: center ;margin-top: 10px">
                    <table border="1px">
                        <tr>
                            <td>
                                index
                            </td>
                            <td>
                                Name
                            </td>
                            <td>
                                StudentCode
                            </td>
                            <td>
                                Attendance
                            </td>
                            <td>
                                describe
                            </td>
                            <td>
                                Time check
                            </td>
                        </tr>
                        <%
                            int index =1;
                            for(Student i: std){
                        %>

                        <tr>
                            <td>
                                <%=index ++ %>
                            </td>
                            <td>
                                <%= i.getName() %>
                            </td>
                            <td>
                                <%= i.getMSSV() %>
                            </td>
                            <td>
                                <input type="radio" name="status<%= i.getMSSV() %>" value ="1"> Present
                                <input type="radio" name="status<%= i.getMSSV() %>" checked value="0"> Absent
                            </td>
                            <td>
                                <input type="text" value="" name="desc<%= i.getMSSV() %>">
                            </td>
                            <td>
                                
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </table>
                </div>

                <div style="display: flex; justify-content: center ;margin-top: 10px">

                    <input type="submit" value="Attendance">
                </div>
            </form>
        </div>
        <!-- window.location.href = "Attendance?execute=0&" + context; -->
    </body>
</html>
