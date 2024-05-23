/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controll;

import dal.DAOAttendance;
import dal.DAOLectOfGroup;
import dal.DAOSlot;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import model.Login;
import model.Slots;
import model.StatusStudent;
import model.Student;

/**
 *
 * @author Admin
 */
public class timetable extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("account") == null) {
            response.sendRedirect("Login");
        } else {
            Login lg = (Login) session.getAttribute("account");
            if (lg.getRole() == 0) {
                boolean ERROR = false;
                DAOSlot object = new DAOSlot();
                Student st = (Student) session.getAttribute("inforStudent");
                String codeSt = st.getMSSV();

                int year, thisyears; // year define year , years define year of now
                DateTimeFormatter fmy = DateTimeFormatter.ofPattern("yyyy");
                LocalDate YearOfNow = LocalDate.now();
                String Year = YearOfNow.format(fmy);
                thisyears = Integer.parseInt(Year);
                if (request.getParameter("year") == null) {
                    year = Integer.parseInt(Year);
                } else {
                    year = 1;
                    try {
                        year = Integer.parseInt(request.getParameter("year"));
                    } catch (NumberFormatException e) {
                        ERROR = true;
                    }
                    if (year < thisyears - 2 || year > thisyears + 1) {
                        ERROR = true;
                    }

                }
                if (ERROR) {
                    response.sendRedirect("timetable");
                } else {
                    LocalDate fMonOfYear = LocalDate.of(year, 1, 1); // find the first Monday of this year
                    while (fMonOfYear.getDayOfWeek() != DayOfWeek.MONDAY) {
                        fMonOfYear = fMonOfYear.plusDays(1);
                    }
                    LocalDate DaySelect = fMonOfYear;

                    // --------------------------------------------------------------------------------------------
                    String week = request.getParameter("week");
                    if (week == null) {
                        do {
                            DaySelect = DaySelect.plusDays(7);
                        } while (DaySelect.compareTo(LocalDate.now()) < 0);
                        DaySelect = DaySelect.plusDays(-7);
                    } else {
                        int thisWeek = 0;
                        try {
                            thisWeek = Integer.parseInt(week);
                        } catch (NumberFormatException e) {
                            ERROR = true;
                        }
                        if (thisWeek >= 54 || thisWeek <= 0) {
                            ERROR = true;
                        }

                        DaySelect = DaySelect.plusDays(7 * thisWeek - 7);

                    }
                    if (ERROR) {
                        response.sendRedirect("timetable");
                    } else {
                        DateTimeFormatter fm = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        //System.out.println(DaySelect.format(fm));
                        String Date1 = DaySelect.format(fm), Date2;
                        DaySelect = DaySelect.plusDays(6);
                        Date2 = DaySelect.format(fm);
                        ArrayList<Slots> sl = object.loadSlot(codeSt, Date1, Date2);

                        request.setAttribute("CodeSt", codeSt);
                        request.setAttribute("fMonOfYear", fMonOfYear); // first monday of year
                        request.setAttribute("slot", sl);
                        request.setAttribute("week", week); // week select
                        request.setAttribute("year", year); // year select
                        request.setAttribute("years", thisyears); //this year
                        //request.setAttribute("years", year);

                        request.getRequestDispatcher("timetable.jsp").forward(request, response);
                    }
                }
            } else {
                response.sendRedirect("Home");
            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
