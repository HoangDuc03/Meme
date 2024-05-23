<%-- 
    Document   : Progress
    Created on : Feb 27, 2024, 12:28:54 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Group" %>
<%@page import="dal.DAOGroups" %>
<%@page import="dal.DAOProgress" %>
<%@page import="model.Student" %>
<%@page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
th, td {
    padding: 8px;
    text-align: left;
}

th {
    background-color: #f2f2f2;
}
tr:hover {
    background-color: #ddd;
}
td:first-child {
    white-space: nowrap; /* Đảm bảo văn bản không bị ngắt dòng */
}
        </style>
    </head>
    <body>
        <div class="container">
            <div>
                <%@include file="header.jsp"%>
            </div>
            <%
                int gr = (int) request.getAttribute("Gr");
                DAOProgress object = new DAOProgress();
                int slotN = object.loadNumberSlot(gr);
                pageContext.setAttribute("slot", slotN);
            %>
            <div    >
                Groups: 
                <select id="gr" onchange="Change()">
                    <c:forEach var="i" items="${requestScope.GrLect}">
                        <option value="${i.getCodeG()}" <c:if test="${i.getCodeG() == requestScope.Gr}"> selected </c:if> >
                            ${i.getNameG()}
                        </option>
                    </c:forEach>
                </select>

                <table border="1px">
                    <tr>
                        <td> Name </td>
                        <td> Student Code </td>
                        <td> Absent </td>
                        <td colspan="<%= slotN%>" style="text-align: center">
                            Progress
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <c:forEach var="i" begin="1" end="${pageScope.slot}" >
                            <td >${i}</td>
                        </c:forEach>
                    </tr>
                    <%
                         DAOGroups ob= new DAOGroups();
                     
                         ArrayList<Student> crs = ob.loadStudents(gr);
                        for(Student i : crs){
                    %>
                    <tr>
                        <td>
                            <%= i.getName() %>
                        </td>
                        <td>
                            <%= i.getMSSV() %>
                        </td>
                        <td>
                            <span style="color: red"><%=  Math.round((float)object.CheckAbsentQuantities(gr,i.getMSSV())*100 / slotN ) %> %</span>

                        </td>
                        <%
                            for(int j=1;j<=slotN;j++){
                        %>
                        <td>
                            <%
                            String msg = object.CheckAttend(gr,j,i.getMSSV());
                            if(msg.equals("T")){
                            %>

                            <span style="color: green"><%= msg %></span>
                            <%
                                }
                                if(msg.equals("F")){
                            %>
                            <span style="color: red"><%= msg %></span>
                            <%
                                }
                                if(msg.equals("N")){
                            %>
                            <span style="color: black"><%= msg %></span>
                            <%
                                }
                            %>

                            <%
                                }
                            %>
                        </td>
                    </tr>    
                    <%
                        }
                    %>

                </table>
            </div>
            <div>
                <p><span style="color: black">N</span>: Not yet </p>
                <p><span style="color: red">F</span>: Absent </p>
                <p><span style="color: green">T</span>: Attendance </p>
            </div>
        </div>
        <script >
            function Change()
            {
                var gr = document.getElementById("gr").value;
                window.location.href = "Progress?gr=" + gr;

            }
        </script>

    </body>
</html>
