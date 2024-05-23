
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.time.DayOfWeek" %>
<%@page import="model.Slots" %>
<%@page import="java.time.LocalDate" %>
<%@page import="java.time.format.DateTimeFormatter" %>
<%@page import="java.util.ArrayList" %>
<%@page import="dal.DAOAttendance" %>
<%@page import="model.StatusStudent" %>
<%@page import="dal.DAOGroups" %>
<%@page import="dal.DAOSlot" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HE173344</title>
    </head>
    <body>
        <%
            LocalDate DayThisWeek = LocalDate.now();
        %>
        <div class="container">
            <div>
                <%@include file="header.jsp"%>
            </div>
            <div>
                <p>
                    Các phòng bắt đầu bằng AL thuộc tòa nhà Alpha. VD: AL...<br/>
                    Các phòng bắt đầu bằng BE thuộc tòa nhà Beta. VD: BE,..<br/>
                    Các phòng bắt đầu bằng G thuộc tòa nhà Gamma. VD: G201,...<br/>
                    Các phòng tập bằng đầu bằng R thuộc khu vực sân tập Vovinam.<br/>
                    Các phòng bắt đầu bằng DE thuộc tòa nhà Delta. VD: DE,..<br/>
                    Little UK (LUK) thuộc tầng 5 tòa nhà Delta
                </p>

            </div>
            <div>
                <table border="1px">
                    <thead>
                        <tr>
                            <th rowspan="2">
                                <span style="color: red"><strong>Year</strong></span>
                                <input type="hidden" id="ThisYear" value="${requestScope.years }">
                                <select name="Year" onchange="setTimeYear()" id="SelectYear">
                                    <c:forEach var="i" begin="${requestScope.years -2}" end="${requestScope.years +1}">  <!-- Shown from 2 years ago to one year later -->
                                        <option<c:if test="${requestScope.year == i}"> selected</c:if> value="${i}">${i} </option> <!-- If i == this year => selected -->
                                    </c:forEach>
                                </select>
                                <br/>
                                Week
                                <select name="Week" onchange="setTime()" id="SelectWeek">
                                    <%
                                        //
                                
                                        int year = (int) request.getAttribute("year");
                               
                                        int value =1, thisweek=0; // count week
                                        LocalDate fMonOfYear =(LocalDate) request.getAttribute("fMonOfYear");
                                        DateTimeFormatter fm = DateTimeFormatter.ofPattern("dd-MM");
                                        LocalDate TimeDay = fMonOfYear; // auto print select weekly
                                        String days;
                              
                                        LocalDate DayNow = LocalDate.now();
                                        while(TimeDay.compareTo(LocalDate.of(year+1,1,1))<0)
                                        {
                                             days= TimeDay.format(fm);
                                    %>

                                    <option value="<%=value%>" 
                                            <c:if test="${requestScope.week == null}">
                                                <%if(TimeDay.compareTo(DayNow)<0)  {%>
                                                selected
                                                <% thisweek = value;} %>
                                            </c:if>
                                            <c:if test="${requestScope.week != null}">
                                                <%if(value == Integer.parseInt((String)request.getAttribute("week")))  {%>
                                                selected
                                                <% } %>
                                            </c:if>
                                            >
                                        <%= days %> to 
                                        <%
                                            TimeDay=TimeDay.plusDays(6);
                                            days= TimeDay.format(fm);
                                        %>
                                        <%= days %>

                                    </option>
                                    <%
                                        TimeDay=TimeDay.plusDays(1);
                                        value++;
                                        }
                                    %>
                                </select>
                            </th>

                    <div id="NameDay">
                        <th align="center">Mon</th>
                        <th align="center">Tue</th>
                        <th align="center">Wed</th>
                        <th align="center">Thu</th>
                        <th align="center">Fri</th>
                        <th align="center">Sat</th>
                        <th align="center">Sun</th>
                    </div>

                    </tr>
                    <tr>
                        <c:if test="${requestScope.week == null}">
                            <%
                                 LocalDate DayOfWeek = fMonOfYear; // print day in the weekly
                                 String dayweek;
                                 thisweek = 7 * thisweek -7 ;
                                 DayOfWeek = DayOfWeek.plusDays(thisweek);
                     
                                 DayThisWeek = DayOfWeek;
                                 for(int i=1;i<=7;i++)
                                 {
                                    dayweek= DayOfWeek.format(fm);
                            %>
                            <td align="center">
                                <%= dayweek %> 
                            </td>

                            <%
                                    DayOfWeek=DayOfWeek.plusDays(1);
                                }
                            %>
                        </c:if>

                        <c:if test="${requestScope.week != null}">
                            <%
                                 LocalDate DayOfWeek = fMonOfYear; // print day in the weekly
                                 String dayweek;
                                 thisweek = Integer.parseInt((String)request.getAttribute("week"));
                                 thisweek = 7 * thisweek -7;
                                 DayOfWeek = DayOfWeek.plusDays(thisweek );
                     
                                 DayThisWeek = DayOfWeek;
                                 for(int i=1;i<=7;i++)
                                 {
                                    dayweek= DayOfWeek.format(fm);
                            %>
                            <td align="center">
                                <%= dayweek %>
                            </td>

                            <%
                                    DayOfWeek=DayOfWeek.plusDays(1);
                                }
                    
                            %>
                        </c:if>
                        </div>
                    </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<Slots> sl = (ArrayList<Slots>) request.getAttribute("slot");
                            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        %>

                        <%
                            for(int i=0;i<=7;i++){
                        %>
                        <tr>
                            <td>Slot <%=i %></td>

                            <%
                                LocalDate Dayss = DayThisWeek;
                                for(int j=0;j<7;j++)
                                {
                            %>
                            <td> 
                                <%
            
                                    for(Slots x : sl)
                                    {
                                        if(Dayss.format(format).equals(x.getDay()))
                                        {
                                            if(i == x.getSlot())
                                            {
                                %>

                                <a href="ToSchedule?groups=<%= x.getCode()%>&slot=<%= x.getSlotNumber()%>">
                                    <%= x.getName() %>
                                </a>
                                <br>
                                <span style="color: purple"><%= x.getSign()%></span>

                                <br><%= x.getRoom() %>               



                                <% 
                                    
                    DAOAttendance obs = new DAOAttendance();
                    ArrayList<StatusStudent> status = obs.loadStatus(x.getCode(),x.getSlotNumber());
                    
                    if(status == null){
                                %>
                                <br><span style="color: red"> not yet </span>
                                <% 
                                    }
                                    else{
                                %>
                                <br><span style="color: green"> attendance </span>
                                <% 
                                    }
                                %>

                                <%
                                                }
                                            }
                                        }
                                %>
                                <br>--
                                <%
                                        Dayss=Dayss.plusDays(1);
                                %>
                            </td>
                            <%
                                }
                            %>

                        </tr>
                        <%
                            }
                        %>
                        </div>


                    </tbody>
                </table>
            </div>
        </div>
        <script>
            var selectYear;

            function setTimeYear()
            {
                selectYear = document.getElementById("SelectYear").value;
                var slWeek = document.getElementById("SelectWeek").value;
                var thisyear = document.getElementById("ThisYear").value;

                if (thisyear == selectYear)
                    window.location.href = "timetableL?year=" + selectYear;
                else
                    window.location.href = "timetableL?year=" + selectYear + "&week=" + 1;
            }

            function setTime()
            {
                selectYear = document.getElementById("SelectYear").value;
                var slWeek = document.getElementById("SelectWeek").value;

                window.location.href = "timetableL?year=" + selectYear + "&week=" + slWeek;
            }
        </script>
    </body>
</html>
