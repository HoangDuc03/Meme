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
<%@page import="model.Mark" %>
<%@page import="dal.DAOMarks" %>


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
            
            
            <div >
                Groups: 
                <select id="gr" onchange="Change()">
                    <c:forEach var="i" items="${requestScope.GrLect}">
                        <option value="${i.getCodeG()}" <c:if test="${i.getCodeG() == requestScope.Gr}"> selected </c:if> >
                            ${i.getNameG()}
                        </option>
                    </c:forEach>
                </select>
                <form action="Marks" method="post">
                    <input type="hidden" name="GR" value="<%=gr%>">
                    <table border="1px soild black">
                        <tr>
                            <td> Name </td>
                            <td> Student Code </td>
                            <td colspan="<%= slotN%>" style="text-align: center">
                                Marks
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <c:forEach var="i" items="${requestScope.listMarks}" >
                                <td >${i.getName()}</td>
                            </c:forEach>
                        </tr>
                        
                        <%
                             DAOGroups ob= new DAOGroups();
                             DAOMarks objectM = new DAOMarks();
                             ArrayList<Student> crs = ob.loadStudents(gr);
                            for(Student i : crs){
                        %>
                        <tr >
                            <td>
                                <%= i.getName() %>
                            </td>
                            <td>
                                <%= i.getMSSV() %>
                            </td>
                            <%
                                ArrayList<Mark> list = (ArrayList<Mark>) request.getAttribute("listMarks");
                                for(Mark c : list)
                                {
                                float mark = objectM.Mark(c.getCode(),i.getMSSV());
                                    if(mark == -1){
                            %>
                                <td> 
                                    
                                    <input type="number" name="<%= i.getMSSV() %>_<%= c.getCode()%>" min="0" max="10" step="0.1" value="">
                                </td>
                            <%
                                    }
                                    else{
                            %>
                                <td> 
                                    <input type="number" name="<%= i.getMSSV() %>_<%= c.getCode()%>" min="0" max="10" step="0.1" value="<%=mark%>">
                                </td>
                            <%
                                    }
                                }
                            %>

                        </tr>    
                        <%
                            }
                        %>

                    </table>
                        <div style="text-align: center; margin-top: 10px ">
                            <input type="submit" value="Update">
                        <div>
                </form>
            </div>
        </div>  
        <script >
            function Change()
            {
                var gr = document.getElementById("gr").value;
                window.location.href = "Marks?gr=" + gr;

            }
        </script>

    </body>
</html>
