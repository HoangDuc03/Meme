/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controll;

import dal.DAOLecturerSubject;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Course;
import model.Lecturer;
import model.Login;

/**
 *
 * @author Admin
 */
public class Department extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("account") == null) {
            response.sendRedirect("Login");
        } else {
            Login lg = (Login) session.getAttribute("account");
            if (lg.getRole() == 1) {
                DAOLecturerSubject obj = new DAOLecturerSubject();
                Lecturer lec = (Lecturer) session.getAttribute("inforLecturer");
                String Dept = obj.DEPT(lec.getDept());
                ArrayList<Course> course = obj.Subject(lec.getDept());

                request.setAttribute("dept", Dept);
                request.setAttribute("Cour", course);
                request.getRequestDispatcher("department.jsp").forward(request, response);

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
