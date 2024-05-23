/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controll;

import dal.DAOCurriculum;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Login;
import model.Major;
import model.Student;

/**
 *
 * @author Admin
 */
public class Curriculum extends HttpServlet {
    
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
                String Cur = st.getMajor(); //Curriculum
                DAOCurriculum ob = new DAOCurriculum();
                ArrayList<Major> curr = ob.loadCurr(st.getMajor());
                String Major = st.getMajor().trim();
                for (int i = 0; i < curr.size(); i++) {
                    if (!curr.get(i).getMajor().contains(Major)) {
                        curr.remove(i);
                    }
                }
                request.setAttribute("Cur", curr);
                request.getRequestDispatcher("curriculum.jsp").forward(request, response);
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
