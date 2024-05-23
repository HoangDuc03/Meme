/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controll;

import dal.DAOStudent;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Login;
import model.Student;

/**
 *
 * @author Admin
 */
public class InformationStudent extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        if (session.getAttribute("account") == null) {
            response.sendRedirect("Login");
        } else {
            Login lg = (Login) session.getAttribute("account");
            if (lg.getRole() == 0) {
                Student st = (Student) session.getAttribute("inforStudent");
                request.setAttribute("infor", session.getAttribute("inforStudent"));
                request.getRequestDispatcher("inforstudent.jsp").forward(request, response);
            }
            else
            {
                response.sendRedirect("Home");
            }
        }
    }

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
