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
import model.Slots;

/**
 *
 * @author Admin
 */
public class DAOToSchedule extends DBContext{
    public String loadSchedule(int Code)
    {
        ArrayList<Major> Curr = new ArrayList();
        String sql = "SELECT        Groups.Code, Departments.Code AS Dept, Courses.Code AS Course\n"
                + "FROM            Courses INNER JOIN\n"
                + "                         Departments ON Courses.CodeDP = Departments.Code INNER JOIN\n"
                + "                         Groups ON Courses.Code = Groups.CodeCourse\n"
                + "WHERE Groups.Code = ?";
        try {
            PreparedStatement st= connection.prepareStatement(sql);
            st.setInt(1,Code);
            ResultSet rs=st.executeQuery(); 
            while(rs.next())
            {
                String msg = "Group?dept="+rs.getInt("Dept")+"&course="+rs.getInt("Course")+"&group="+rs.getInt("Code");
                
                return msg;
                
            }
        } catch (SQLException  | NumberFormatException e) {
            System.out.println("when accessing the database \"Schedule\" got an error");
        }
        
        return null;
    }
    public Slots loadSClss(int Code, int Slot)
    {
        String sql = "SELECT        Groups.Code, Groups.Name, Slots.Day, Slots.CodeLecturer, Slots.SlotNumber, Rooms.Name AS Room, Slots.SlotStart\n" +
"FROM            Slots INNER JOIN\n" +
"                         Groups ON Slots.CodeGroup = Groups.Code INNER JOIN\n" +
"                         Rooms ON Slots.Room = Rooms.Code\n" +
"WHERE        Groups.Code = ? AND SlotNumber = ?";
        try {
            PreparedStatement st= connection.prepareStatement(sql);
            st.setInt(1,Code);
            st.setInt(2,Slot);
            ResultSet rs=st.executeQuery(); 
            while(rs.next())
            {
                Slots clone = new Slots(rs.getInt("Code"),
                        rs.getString("Name"),
                        rs.getString("Day"),
                        rs.getInt("SlotStart"),
                        rs.getInt("CodeLecturer"),
                        rs.getInt("SlotNumber"),
                        rs.getString("Room"));
                
                return clone;
                
            }
        } catch (SQLException  | NumberFormatException e) {
            System.out.println("when accessing the database \"Schedule load Class\" got an error");
        }
        
        return null;
    }
   
}
