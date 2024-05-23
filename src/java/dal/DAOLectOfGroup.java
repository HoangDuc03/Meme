/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Lecturer;

/**
 *
 * @author Admin
 
// SELECT distinct        Lecturers.Name, Lecturers.Mail
FROM            Slots INNER JOIN
                         Lecturers ON Slots.CodeLecturer = Lecturers.Code
where Slots.CodeGroup = 1
*/
public class DAOLectOfGroup extends DBContext{
    public String loadLecturers(int CodeGroup)
    {
        String sql = "SELECT distinct        Lecturers.Name, Lecturers.Mail\n" +
"FROM            Slots INNER JOIN\n" +
"                         Lecturers ON Slots.CodeLecturer = Lecturers.Code\n" +
"where Slots.CodeGroup = ? ";
        try {
            PreparedStatement st= connection.prepareStatement(sql);
            st.setInt(1, CodeGroup);
            
            ResultSet rs=st.executeQuery(); 
            while(rs.next())
            {
                String msg = rs.getString("Mail");
                return msg;
            }
        } catch (SQLException  | NumberFormatException e) {
            System.out.println("when accessing the database \"Lecturer of Group\" got an error");
        }
        return null;
    }
    public static void main(String[] args) {
        DAOLectOfGroup daoL = new DAOLectOfGroup();
        String msg = daoL.loadLecturers(9);
        System.out.println(msg);
    }
}
