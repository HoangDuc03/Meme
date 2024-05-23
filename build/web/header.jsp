<%-- 
    Document   : Header
    Created on : Feb 7, 2024, 3:00:59 PM
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
        <link rel="stylesheet" href="css.css" />
        <link rel="Stylesheet" href="CSS/bootstrap.css" type="text/css" /><link rel="Stylesheet" href="CSS/bootstrap.min.css" type="text/css" /><link rel="Stylesheet" href="Content/bootstrap-theme.min.css" type="text/css" />
    </head>
    <body>

        <div class="row">
            <div class="col-md-8">
                <h1><span>University Academic Portal</span>
                </h1>
            </div>
            <div class="col-md-4">
                <table>
                    <tr>
                        <td colspan="2" class="auto-style1"><strong>FAP mobile app (myFAP) is ready at</strong></td>
                    </tr>
                    <tr>
                        <td><a href="#">
                                <img src="https://fap.fpt.edu.vn/images/app-store.png" style="width: 120px; height: 40px" alt="apple store" /></a></td>
                        <td><a href="#">
                                <img src="https://fap.fpt.edu.vn/images/play-store.png" style="width: 120px; height: 40px" alt="google store" /></a></td>
                    </tr>
                </table>
            </div>
        </div>
        <div id="ctl00_divUser" style="float: right; margin-right: 16px;">
            <c:if test="${sessionScope.account != null}">
                <c:if test="${sessionScope.account.getRole() == 0}">
                    <a href="Infor"><span id="ctl00_lblLogIn" class="label label-success">${sessionScope.account.getUsername()}</span></a>
                    </c:if>
                    <c:if test="${sessionScope.account.getRole() == 1}">
                    <a href="Lecturer" ><span id="ctl00_lblLogIn" class="label label-success">${sessionScope.account.getUsername()}</span></a>
                    </c:if>
                |
                <a href="Logout" class="label label-success">logout</a> 
            </c:if>

        </div>
        <div style="position: absolute; top: -35px; right: 30px; width: 150px;">
        </div>
        <ol class="breadcrumb">
            <li>
                <span id="ctl00_lblNavigation"><b>&nbsp;</b></span>
            </li>
            <li>
                <span id="ctl00_lblNavigation"><a href="Home">Home</a></span>
            </li>
        </ol>

    </body>
</html>
