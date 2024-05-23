package controll;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */


import dal.DAOAttendance;
import dal.DAOGroups;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Student;

/**
 *
 * @author Admin
 */
public class Attendance extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("account") == null)
            response.sendRedirect("Login");
        else{
            response.sendRedirect("Home");
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String gr_S = request.getParameter("group"),
                slot_S=request.getParameter("slot");
        int gr =0,slot=0;
        try {
            gr  = Integer.parseInt(gr_S);
            slot = Integer.parseInt(slot_S);
        } catch (Exception e) {
            //request.getRequestDispatcher("timetableL").forward(request, response);
        }
        DAOGroups object= new DAOGroups();
        ArrayList<Student> std = object.loadStudents(gr);
        for(Student i : std)
        {
            String status = request.getParameter("status"+i.getMSSV()),
                    desc = request.getParameter("desc"+i.getMSSV());
            DAOAttendance db = new DAOAttendance();
            if(request.getParameter("execute").equals("insert"))
                db.InsertStatus(i.getMSSV(), gr, slot, status.equals("1") ? true : false , desc);
            else
            {
                
                if(db.CheckStatus(i.getMSSV(), gr, slot) != (status.equals("1") ? true : false))
                {
                    db.UpdateStatus(i.getMSSV(), gr, slot, (status.equals("1") ? true : false), desc);
                
                }
            }
        }
        response.sendRedirect("Home");
        
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
