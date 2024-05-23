package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Course;
import model.Department;
import model.LecturerSubject;

/**
 *
 * @author Admin
 */
public class DAOLecturerSubject extends DBContext {

    public ArrayList<Course> Subject(int dept) {
        ArrayList<Course> courses = new ArrayList<>();
        int Code_dp;
        String Name_dp; // dp = Department
        String sql = "SELECT Departments.Code, Departments.Name, Courses.Code AS CodeCourse, Courses.Name AS NameCourse, Courses.[Sign] AS SignCourse\n"
                + "FROM Departments INNER JOIN\n"
                + " Courses ON Departments.Code = Courses.CodeDP\n"
                + "Where Departments.Code = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, dept);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Course cr = new Course(rs.getInt("CodeCourse"), rs.getString("NameCourse"), rs.getString("SignCourse"));
                courses.add(cr);
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println("when accessing the database \"Lecturer Subject\" got an error");
        }
        return courses;
    }

    public String DEPT(int code) {
        String dept;
        String sql = "SELECT       Departments.Code, Departments.Name\n"
                + "FROM            Departments\n"
                + "WHERE Departments.Code = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, code);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                dept = rs.getString("Name");
                return dept;
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println("when accessing the database \"Lecturer Subject\" got an error");
        }
        return null;
    }

    public static void main(String[] args) {
        DAOLecturerSubject object = new DAOLecturerSubject();
        ArrayList<Course> crs = object.Subject(101);
        System.out.println(crs.size());
        for (Course i : crs) {
            //System.out.println("1");
            System.out.println(i.getName());
        }
    }

}
