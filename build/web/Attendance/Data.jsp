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
            <input type="hidden" value="update" name="execute">
            <input type="hidden" value="<%= sl.getCode() %>" name="group">
            <input type="hidden" value="<%= sl.getSlotNumber() %>" name = "slot">
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
                            Describe
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
                            <input type="radio" value="1" name="status<%= i.getMSSV() %>"
                                   <%
                                    for(int x=0;x<status.size();x++)
                                    {
                                        if(status.get(x).getStudentCode().equals(i.getMSSV()) && status.get(x).isStatus()!= false)
                                        {
                                   %>
                                   checked
                                   <%
                                            }
                                    }   
                                   %>   
                                   > Present
                            <input type="radio" value="0" name="status<%= i.getMSSV() %>"
                                   <%
                                    for(int x=0;x<status.size();x++)
                                    {
                                        if(status.get(x).getStudentCode().equals(i.getMSSV()) && status.get(x).isStatus()== false)
                                        {
                                   %>
                                   checked
                                   <%
                                   }
                               }
                                   %> 
                                   > Absent
                        </td>
                        <td>
                            <input type="text" value=" <%
                                    for(int x=0;x<status.size();x++)
                                    {
                                        if(status.get(x).getStudentCode().equals(i.getMSSV()))
                                        {
                                   %>
                                   <%= status.get(x).getDescribe().trim() %>
                                   <%
                                   }
                               }
                                   %>" name="desc<%= i.getMSSV() %>">
                        </td>
                        <td>
                            <%
                                    for(int x=0;x<status.size();x++)
                                    {
                                        if(status.get(x).getStudentCode().equals(i.getMSSV()))
                                        {
                                   %>
                                   <%= status.get(x).getTime() %>
                                   <%
                                   }
                               }
                                   %>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </div>

           <div style="display: flex; justify-content: center ;margin-top: 10px">

                    <input type="submit" value=" Update Attendance">
                </div>
            </form>
        </div>

       
    </body>
</html>
