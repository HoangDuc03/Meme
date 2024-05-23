/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controll;

import dal.DAOLecturer;
import dal.DAOLogin;
import dal.DAOStudent;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Lecturer;
import model.Login;
import model.Student;

/**
 *
 * @author Admin
 */
public class RequestLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("account") != null)
            response.sendRedirect("Home");
        else
            request.getRequestDispatcher("login.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        String user = request.getParameter("user"),
                password = request.getParameter("password");
        DAOLogin login = new DAOLogin();
        System.out.println(login.login(user, password));
        if(login.login(user, password) != null)
        {
            Login lg = login.login(user, password);
            if (lg.getRole() == 0) {
                DAOStudent object = new DAOStudent();
                ArrayList<Student> std = object.loadStudents();
                Student infor = new Student();
                for (Student i : std) {
                    if (i.getMail().equals(lg.getUsername())) {
                        infor = i;
                        break;
                    }
                }
                session.setAttribute("inforStudent", infor);
            }
            if (lg.getRole() == 1) {
                DAOLecturer object = new DAOLecturer();
                ArrayList<Lecturer> lec = object.loadLecturers();
                Lecturer infor = new Lecturer();
                for (Lecturer i : lec) {
                    if (i.getMail().equals(lg.getUsername())) {
                        infor = i;
                        break;
                    }
                }
                session.setAttribute("inforLecturer", infor);
            }
            session.setAttribute("account", lg);
            response.sendRedirect("Home");
        }
        else
        {
            request.setAttribute("error", "UserName or PassWord incorrect ");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
