/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Major;

/**
 *
 * @author Admin
 */
public class DAOCurriculum extends DBContext {

    public ArrayList<Major> loadCurr(String major) {
        ArrayList<Major> Curr = new ArrayList();
        String sql = "SELECT        Courses.Name, Courses.Sign, Semesters.CourseChange, Courses_1.Name AS NameChange, Courses_1.Sign as SignChange, Semesters.Major, Semesters.term\n"
                + "FROM            Courses INNER JOIN\n"
                + "                         Semesters ON Courses.Code = Semesters.CodeCourse AND Courses.Code = Semesters.CodeCourse INNER JOIN\n"
                + "                         Courses AS Courses_1 ON Semesters.CourseChange = Courses_1.Code\n"
                + "Where Major = ?\n"
                + "order by term asc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, major);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Major cur = new Major(rs.getString("Name"), rs.getString("Sign"), rs.getString("NameChange"), rs.getString("SignChange"), rs.getString("Major"), rs.getInt("term"));
                Curr.add(cur);
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println("when accessing the database \"Curriculum\" got an error");
        }

        return Curr;
    }

    public static void main(String[] args) {
        DAOCurriculum object = new DAOCurriculum();
        ArrayList<Major> crs = object.loadCurr("SE");
        for (Major i : crs) {
            System.out.println(i.getName() + " " + i.getNamechange() + " ");
        }
    }
}
