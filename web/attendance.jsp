<%-- 
    Document   : schedule
    Created on : Feb 18, 2024, 10:49:49 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Slots" %>
<%@page import="dal.DAOLectOfGroup" %>
<%@page import="dal.DAOToSchedule" %>
<%@page import="java.util.ArrayList" %>
<%@page import="model.Group" %>
<%@page import="model.Student" %>
<%@page import="dal.DAOGroups" %>
<%@page import="dal.DAOAttendance" %>
<%@page import="model.StatusStudent" %>


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
            <%
            
                       Slots sl = (Slots) request.getAttribute("slot");
                       DAOToSchedule ob = new DAOToSchedule();
                        String MoveTo = ob.loadSchedule((int) request.getAttribute("gr"));
            
            %>
            <div style="display: flex; justify-content: center">
                <table border="1px">
                    <tr>
                        <th>Class</th>
                        <th>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="<%= MoveTo %>"><%= sl.getName() %></a>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </th>
                    </tr>

                    <tr>
                        <th>Date</th>
                        <th><%= sl.getDay() %></th>
                    </tr>
                    <tr>
                        <th>Lecturer</th>
                            <%
                                DAOLectOfGroup daoL = new DAOLectOfGroup();
                                String msg = daoL.loadLecturers(sl.getCode());
                            %>
                        <th><%= msg %></th>
                    </tr>
                    <tr>
                        <th>Slot Number</th>
                        <th><%= sl.getSlotNumber() %></th>
                    </tr>
                    <tr>
                        <th>Room</th>
                        <th><%= sl.getRoom() %></th>
                    </tr>
                </table>
            </div>
            <div>
                <c:if test="${sessionScope.account.getRole() != 0}">
                    <% 
                        DAOAttendance obs = new DAOAttendance();
                        ArrayList<StatusStudent> status = obs.loadStatus(sl.getCode(),sl.getSlotNumber());
                        if(status == null){
                    %>
                    <%@include file="Attendance/NoData.jsp"%>

                    <% 
                        }
                        else{
                    %>
                    <%@include file="Attendance/Data.jsp"%>
                    <% 
                        }
                    
                    %>
                </c:if>
            </div>
        </div>
    </body>
</html>
