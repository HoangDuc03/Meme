/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Mark;

public class DAOMarks extends DBContext {

    public ArrayList<Mark> LoadMarks(int Code) {
        ArrayList<Mark> list = new ArrayList();
        String sql = "SELECT        Code, CodeCourse, NameMarks\n"
                + "FROM            Marks\n"
                + "WHERE        CodeCourse = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Code);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Mark mr = new Mark();
                mr.setCode(rs.getInt("Code"));
                mr.setName(rs.getString("NameMarks"));
                list.add(mr);

            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println("when accessing the database \"Student\" got an error");
        }
        return list;
    }

    public void InsertMarks(int CodeMark, String CodeSTD, float mark) {
        String sql = "INSERT INTO [dbo].[MarkOfSTD]\n"
                + "     VALUES (?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, CodeMark);
            st.setString(2, CodeSTD);
            st.setFloat(3, mark);
            st.executeUpdate();
        } catch (SQLException | NumberFormatException e) {
            System.out.println("when accessing the database \"Insert MArk\" got an error");
        }
    }

    public void DeleteMarks(int CodeMark, String CodeSTD) {
        String sql = "DELETE FROM [dbo].[MarkOfSTD]\n"
                + "Where CodeMark = ? AND CodeStudent = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, CodeMark);
            st.setString(2, CodeSTD);

            st.executeUpdate();
        } catch (SQLException | NumberFormatException e) {
            System.out.println("when accessing the database \"Delete MArk\" got an error");
        }
    }

    public float Mark(int Code, String CodeST) {
        String sql = "select * from MarkOfSTD\n"
                + "Where CodeMark = ? AND CodeStudent = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Code);
            st.setString(2, CodeST);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getFloat("Mark");
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println("when accessing the database \"Mark student\" got an error");
        }
        return -1;
    }

    public static void main(String[] args) {
        DAOMarks object = new DAOMarks();
        object.InsertMarks(27, "HE19324", 9);
    }
}
