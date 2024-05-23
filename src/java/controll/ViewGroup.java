/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controll;

import dal.DAOGroups;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import model.Course;
import model.Department;
import model.Group;
import model.Student;

/**
 *
 * @author Admin
 */
public class ViewGroup extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         HttpSession session = request.getSession();
        if(session.getAttribute("account") == null)
            response.sendRedirect("Login");
        else
        {
            LocalDate NowDate = LocalDate.now();
            DateTimeFormatter fmy = DateTimeFormatter.ofPattern("yyyy");
            DateTimeFormatter fmterm = DateTimeFormatter.ofPattern("dd-MM");
            String term = "";
            String year = NowDate.format(fmy);
            int year_i=0;
            try {
                year_i = Integer.parseInt(year);
            } catch (Exception e) {
                System.out.println("e");
            }
            
            LocalDate Spring = LocalDate.of(year_i , 5, 1);
            LocalDate Summer = LocalDate.of(year_i , 9 , 1 );
            //LocalDate Fall = LocalDate.of(year_i , 1, 5);
            if(NowDate.isBefore(Spring))
            {
                term = "Spring";
            }
            if((NowDate.isAfter(Spring) || NowDate.isEqual(Spring)) && NowDate.isBefore(Summer))
            {
                term = "Summer";
            }
            if(NowDate.isAfter(Summer) || NowDate.isEqual(Summer))
            {
                term = "Fall";
            }
            term = term + year;
            request.setAttribute("term", term);
            
            
            DAOGroups object= new DAOGroups();
            ArrayList<Department> dp = object.loadDepartment();
            request.setAttribute("dept", dp);
            ArrayList<Course> cr = new ArrayList();
            ArrayList<Group> gr = new ArrayList();
            ArrayList<Student> std = new ArrayList();
            String dept_S = request.getParameter("dept"),
                    course_S = request.getParameter("course"),
                    groups_S = request.getParameter("group");
            boolean ERROR = false;
            
            int dept = 0,course=0, groups=0;
            
            try {
                if( dept_S != null )
                    dept = Integer.parseInt(dept_S);
                if( course_S != null )
                    course =Integer.parseInt(course_S); 
                if( groups_S != null )
                    groups =Integer.parseInt(groups_S);
            } catch (NumberFormatException e) {
                ERROR= true;
            }
            if(ERROR)
                response.sendRedirect("Home");
            else{
            if (request.getParameter("dept") != null) {
                for (Department i : dp) {
                    if(i.getCode() == dept)
                    {
                        cr = i.getCourse();
                    }
                }
                if(!cr.isEmpty())
                    request.setAttribute("course", cr);
            }
            if (request.getParameter("course") != null) {
                cr= object.loadCourse();
                for(Course i : cr) {
                    if(i.getCode() == course)
                    {
                        gr = i.getGr();
                        //System.out.println(gr);
                    }
                }
            }
            if(!gr.isEmpty())
                    request.setAttribute("groups", gr);
            
            if (request.getParameter("group") != null) {
                gr= object.loadGroups();
                for(Group i : gr) {
                    if(i.getCode() == groups)
                    {
                        std = i.getStudent();
                    }
                }
            }
            if(!std.isEmpty())
            {
               
                request.setAttribute("listSTD", std);
            }
            request.getRequestDispatcher("groups.jsp").forward(request, response);
            }
        }
        
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        //processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
