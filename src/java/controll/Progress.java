/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controll;

import dal.DAOProgress;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Lecturer;
import model.LecturerGroups;
import model.Login;

/**
 *
 * @author Admin
 */
public class Progress extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("account") == null) {
            response.sendRedirect("Login");
        } else {
            Login lg = (Login) session.getAttribute("account");
            if (lg.getRole() == 1) {
                String gr_init = request.getParameter("gr");
                int gr = -1;
                if (gr_init != null) {
                    gr = Integer.parseInt(gr_init);
                }
                DAOProgress object = new DAOProgress();
                Lecturer lec = (Lecturer) session.getAttribute("inforLecturer");
                ArrayList<LecturerGroups> LG = object.loadGrOfLecturer(Integer.parseInt(lec.getMSSV()));
                if (LG != null && gr == -1) {
                    request.setAttribute("Gr", LG.get(0).getCodeG());
                } else {
                    if (gr != -1) {
                        request.setAttribute("Gr", gr);
                    }
                }
                request.setAttribute("GrLect", LG);
                request.getRequestDispatcher("Progress.jsp").forward(request, response);
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
