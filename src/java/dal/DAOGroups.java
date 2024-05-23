/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Course;
import model.Department;
import model.Group;
import model.Student;

/**
 *
 * @author Admin
 */
public class DAOGroups extends DBContext {

    public ArrayList<Course> loadCourse() {
        int out = 0; // 
        ArrayList<Group> group = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();
        int Code_cr;
        String Name_cr; // cr = course
        String sign_cr;
        String sql = "SELECT Courses.Code, Courses.Name,Courses.[Sign], Groups.Code AS CodeGroup, Groups.Name AS NameGroup\n"
                + "FROM Courses INNER JOIN\n"
                + " Groups ON Courses.Code = Groups.CodeCourse";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            rs.next();
            Code_cr = rs.getInt("Code");
            Name_cr = rs.getString("Name");
            sign_cr = rs.getString("Sign");
            while (true) {
                while (rs.getInt("Code") == Code_cr) {
                    Group gr = new Group(rs.getInt("CodeGroup"), rs.getString("NameGroup"));
                    group.add(gr);
                    if (!rs.next()) {
                        out = 1;
                        break;
                    }
                }
                courses.add(new Course(Code_cr, Name_cr, sign_cr, group));
                group = new ArrayList<>();

                if (out == 0) {
                    Code_cr = rs.getInt("Code");
                    Name_cr = rs.getString("Name");
                    sign_cr = rs.getString("Sign");
                    Group gr = new Group(rs.getInt("CodeGroup"), rs.getString("NameGroup"));
                    group.add(gr);
                }
                if (!rs.next()) {
                    break;
                }
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println("when accessing the database \"Course\" got an error");
        }
        return courses;
    }

    public ArrayList<Department> loadDepartment() {
        int out = 0;
        ArrayList<Department> department = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();

        int Code_dp;
        String Name_dp; // dp = Department
        String sql = "SELECT Departments.Code, Departments.Name, Courses.Code AS CodeCourse, Courses.Name AS NameCourse, Courses.[Sign] AS SignCourse\n"
                + "FROM Departments INNER JOIN\n"
                + " Courses ON Departments.Code = Courses.CodeDP\n"
                + "order by Code asc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            rs.next();
            Code_dp = rs.getInt("Code");
            Name_dp = rs.getString("Name");
            while (true) {
                while (rs.getInt("Code") == Code_dp) {

                    Course cr = new Course(rs.getInt("CodeCourse"), rs.getString("NameCourse"), rs.getString("SignCourse"));
                    courses.add(cr);

                    if (!rs.next()) {
                        out = 1;
                        break;
                    }
                }
                department.add(new Department(Code_dp, Name_dp, courses));
                courses = new ArrayList<>();
                if (out == 0) {
                    Code_dp = rs.getInt("Code");
                    Name_dp = rs.getString("Name");
                    Course cr = new Course(rs.getInt("CodeCourse"), rs.getString("NameCourse"), rs.getString("SignCourse"));
                    courses.add(cr);
                }
                if (!rs.next()) {
                    break;
                }
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println("when accessing the database \"Department\" got an error");
        }
        return department;
    }

    public ArrayList<Group> loadGroups() {
        int out = 0;
        ArrayList<Group> gr = new ArrayList<>();
        ArrayList<Student> std = new ArrayList<>();

        int Code_gr;
        String Name_gr; // gr = group std = student
        String sql = "SELECT        Groups.Code AS ClassID,Groups.Name AS Class, InforStudents.Code, InforStudents.Name AS Name\n"
                + "FROM            Groups INNER JOIN\n"
                + "                         Students ON Groups.Code = Students.CodeGroup INNER JOIN\n"
                + "                         InforStudents ON Students.Code = InforStudents.Code\n"
                + "ORDER BY ClassID";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            rs.next();
            Code_gr = rs.getInt("ClassID");
            Name_gr = rs.getString("Class");
            while (true) {
                while (rs.getInt("ClassID") == Code_gr) {
                    std.add(new Student(rs.getString("Name"), rs.getString("Code")));

                    if (!rs.next()) {
                        out = 1;
                        break;
                    }

                }
                gr.add(new Group(Code_gr, Name_gr, std));
                std = new ArrayList<>();
                if (out == 0) {
                    Code_gr = rs.getInt("ClassID");
                    Name_gr = rs.getString("Class");
                    std.add(new Student(rs.getString("Name"), rs.getString("Code")));
                }
                if (!rs.next()) {
                    break;
                }
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println("when accessing the database \"Groups\" got an error");
        }
        return gr;
    }

    public ArrayList<Student> loadStudents(int Code) {
        ArrayList<Student> std = new ArrayList();
        int Code_gr;
        String Name_gr; // gr = group std = student
        String sql = "SELECT        Groups.Code AS ClassID,Groups.Name AS Class, InforStudents.Code, InforStudents.Name AS Name\n"
                + "FROM            Groups INNER JOIN\n"
                + "                         Students ON Groups.Code = Students.CodeGroup INNER JOIN\n"
                + "                         InforStudents ON Students.Code = InforStudents.Code\n"
                + "where Groups.Code = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Code);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                std.add(new Student(rs.getString("Name"), rs.getString("Code")));
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println("when accessing the database \"Student\" got an error");
        }
        return std;
    }

    public int CourseOfGroups(int Code) {
        String sql = "SELECT        Groups.Code as CodeGroup, Courses.Code AS CodeCourse\n"
                + "FROM            Courses INNER JOIN\n"
                + "                         Groups ON Courses.Code = Groups.CodeCourse\n"
                + "Where Groups.Code =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Code);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("CodeCourse");
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println("when accessing the database \"Course Of Group\" got an error");
        }
        return -1;
    }

    public static void main(String[] args) {
        DAOGroups object = new DAOGroups();
        ArrayList<Group> crs = object.loadGroups();
        for (Group i : crs) {
            System.out.println(i.getName() + " " + i.getCode() + " ");
            for (Student j : i.getStudent()) {
                System.out.println("       " + j.getMSSV() + " " + j.getName());
            }
        }
    }
}
