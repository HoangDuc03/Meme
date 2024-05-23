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

            <div style="border: 2px solid black; padding: 5px 10px 5px 10px; margin: 5px 0 5px 0">
                <div style="padding: 0 0 5px 0">
                    <a href="Group">Schedule</a>
                </div>
                <c:if test="${sessionScope.account.getRole() == 0}">
                    <div>
                        <a href="Curriculum" >Curriculum</a>
                    </div>
                </c:if>

                <c:if test="${sessionScope.account.getRole() == 1}">
                    <a href="Department">Department</a>
                </c:if>

            </div>

            <div style="border: 2px solid black; padding: 5px 10px 5px 10px">
                <c:if test="${sessionScope.account.getRole() == 0}">
                    <a href="timetable">timetable</a>
                </c:if>
                <c:if test="${sessionScope.account.getRole() == 2}">
                    <a href="timetable">timetable</a>
                </c:if>
                <c:if test="${sessionScope.account.getRole() == 1}">
                    <a href="timetableL">timetable</a><br>
                    <a href="Progress" >Progress</a><br>
                    <a href="Marks" >Marks</a>
                </c:if>
            </div>
            <%
                Login lgs = (Login) session.getAttribute("account");
                if(lgs.getUsername().equals("admin"))
                {
            %>
            <div style="border: 2px solid black; padding: 5px 10px 5px 10px ; margin: 7px 0 0 0">
                <a href="#">Edit</a>
            </div>
            <%
                }
            %>
        </div>
    </body>
</html>
