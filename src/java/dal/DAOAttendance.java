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
import model.StatusStudent;

/**
 *
 * @author Admin
 */
public class DAOAttendance extends DBContext {

    public ArrayList<StatusStudent> loadStatus(int GR, int Slot) {
        ArrayList<StatusStudent> std = new ArrayList();
        String sql = "SELECT        Slots.SlotNumber, InforStudents.Code AS StudentCode, Attendances.Status, Groups.Code,Attendances.Describe, Attendances.Time\n"
                + "FROM            Slots INNER JOIN\n"
                + "                         Attendances ON Slots.CodeGroup = Attendances.CodeGroup AND Slots.SlotNumber = Attendances.SlotNumber INNER JOIN\n"
                + "                         InforStudents ON Attendances.CodeStudent = InforStudents.Code INNER JOIN\n"
                + "                         Groups ON Slots.CodeGroup = Groups.Code\n"
                + "Where Groups.Code = ?  AND Slots.SlotNumber = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, GR);
            st.setInt(2, Slot);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                StatusStudent clone = new StatusStudent(rs.getString("StudentCode"),
                        rs.getInt("Code"),
                        rs.getInt("SlotNumber"),
                        rs.getBoolean("Status"),
                        rs.getString("Describe"),
                        rs.getDate("Time"));
                std.add(clone);
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println("when accessing the database \"Attenden\" got an error");
        }

        if (std.isEmpty()) {
            return null;
        } else {
            return std;
        }
    }

    public void InsertStatus(String ST, int gr, int slot, boolean status, String desc) {
        String sql = "INSERT INTO Attendances VALUES(?,?,?,?,?,GETDATE())";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, ST);
            st.setInt(2, gr);
            st.setInt(3, slot);
            st.setBoolean(4, status);
            st.setString(5, desc);

            st.executeUpdate();
        } catch (SQLException | NumberFormatException e) {
            System.out.println("when accessing the database \"Insert Attend\" got an error");
        }
    }

    public void UpdateStatus(String ST, int gr, int slot, boolean status,String desc) {
        String sql = "UPDATE [dbo].[Attendances]\n"
                + "SET [Status] = ?\n"
                + "      ,[Describe] = ?\n"
                + "      ,[Time] = GETDATE()"
                + "WHERE [CodeStudent] = ? AND [CodeGroup] = ? AND [SlotNumber] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setBoolean(1, status);
            st.setString(2, desc);
            
            st.setString(3, ST);
            st.setInt(4, gr);
            st.setInt(5, slot);

            st.executeUpdate();

        } catch (SQLException | NumberFormatException e) {
            System.out.println("when accessing the database \"Update Attend\" got an error");
        }
    }

    public boolean CheckStatus(String ST, int gr, int slot) {
        String sql = "select Status from Attendances\n"
                + "where CodeStudent=? AND CodeGroup = ? AND SlotNumber = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, ST);
            st.setInt(2, gr);
            st.setInt(3, slot);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getBoolean("Status");
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println("when accessing the database \"check status Attend\" got an error");
        }
        return false;
    }

    public static void main(String[] args) {
        DAOAttendance object = new DAOAttendance();
        ArrayList<StatusStudent> crs = object.loadStatus(32, 1);
        if (crs != null) {
            for (StatusStudent i : crs) {
                System.out.println(i.getStudentCode() + " " + i.isStatus() + " ");
            }
        }
    }

//    public void update(Student std)
//    {
//        
//        try {
//            String sql = " Update Student "
//                + "SET name= ?, gender= ?, dob= ?, did= ? "
//                + "WHERE id =?";
//            PreparedStatement st= connection.prepareStatement(sql);
//            //System.out.println(std.getName());
//            st.setString(1, std.getName());
//            st.setBoolean(2, std.isGender());
//            st.setString(3, std.getDob());
//            st.setInt(4, std.getDid());
//            st.setInt(5, std.getId());
//            st.executeUpdate();
//   
//        } catch (SQLException e) {
//            System.out.println("e update");
//        }
//    }
}
