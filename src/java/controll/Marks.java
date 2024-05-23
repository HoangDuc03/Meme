/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controll;

import dal.DAOGroups;
import dal.DAOMarks;
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
import model.Student;
import model.Mark;

/**
 *
 * @author Admin
 */
public class Marks extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Marks</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Marks at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("account") == null) {
            response.sendRedirect("Login");
        } else {
            Login lg = (Login) session.getAttribute("account");
            if (lg.getRole() == 1) {
                DAOProgress object = new DAOProgress();
                DAOGroups objectG = new DAOGroups();
                DAOMarks objectM = new DAOMarks();
                String gr_init = request.getParameter("gr");
                int gr = -1;
                if (gr_init != null) {
                    gr = Integer.parseInt(gr_init);
                }
                
                Lecturer lec = (Lecturer) session.getAttribute("inforLecturer");
                ArrayList<LecturerGroups> LG = object.loadGrOfLecturer(Integer.parseInt(lec.getMSSV()));
                if (LG != null && gr == -1) {
                    gr = LG.get(0).getCodeG();
                    request.setAttribute("Gr", gr);
                } else {
                    if (gr != -1) {
                        request.setAttribute("Gr", gr);
                    }
                }
                int cr_id = objectG.CourseOfGroups(gr);
                ArrayList<Mark> list = objectM.LoadMarks(cr_id);
                
                request.setAttribute("listMarks", list);
                request.setAttribute("GrLect", LG);
                request.getRequestDispatcher("marks.jsp").forward(request, response);
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
        DAOGroups objectG = new DAOGroups();
        DAOMarks objectM = new DAOMarks();
        int gr = Integer.parseInt(request.getParameter("GR"));
        int cr = objectG.CourseOfGroups(gr);
        ArrayList<Mark> list = objectM.LoadMarks(cr);
        ArrayList<Student> crs = objectG.loadStudents(gr);
        
        for(Student std : crs)
        {
            for(Mark tp : list)
            {
                String msg =  std.getMSSV()+"_"+tp.getCode();
                String gtr = request.getParameter(msg);
                if(!gtr.equals(""))
                {
                    float mark = Float.parseFloat(gtr);
                    objectM.DeleteMarks(tp.getCode(), std.getMSSV());
                    objectM.InsertMarks(tp.getCode(), std.getMSSV(), mark);
                }
                
            }
        }
        response.sendRedirect("Home");
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
