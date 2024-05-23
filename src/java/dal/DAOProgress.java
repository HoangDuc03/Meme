/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.LecturerGroups;

/**
 *
 * @author Admin
 */
public class DAOProgress extends DBContext {

    public ArrayList<LecturerGroups> loadGrOfLecturer(int CodeL) {
        ArrayList<LecturerGroups> std = new ArrayList();
        String sql = "SELECT  distinct      Lecturers.Code, Lecturers.Mail,Groups.Code as CodeG ,Groups.Name\n"
                + "FROM            Lecturers INNER JOIN\n"
                + "                         Slots ON Lecturers.Code = Slots.CodeLecturer INNER JOIN\n"
                + "                         Groups ON Slots.CodeGroup = Groups.Code\n"
                + "Where Lecturers.Code = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, CodeL);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                LecturerGroups clone = new LecturerGroups(rs.getInt("Code"),
                        rs.getString("Mail"),
                        rs.getInt("CodeG"),
                        rs.getString("Name"));
                std.add(clone);
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println("when accessing the database \"Progress\" got an error");
        }
        return std;
    }

    public int loadNumberSlot(int CodeG) {
        String sql = "SELECT        Groups.Code, Courses.Slot\n"
                + "FROM            Groups INNER JOIN\n"
                + "                         Courses ON Groups.CodeCourse = Courses.Code\n"
                + "WHERE Groups.Code = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, CodeG);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("Slot");
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println("when accessing the database \"Number slot\" got an error");
        }
        return 0;
    }

    public String CheckAttend(int CodeG, int slot, String CodeS) {
        String sql = "SELECT        Status\n"
                + "FROM            Attendances\n"
                + "Where Attendances.CodeStudent = ? AND Attendances.CodeGroup=? AND Attendances.SlotNumber= ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, CodeS);
            st.setInt(2, CodeG);
            st.setInt(3, slot);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                boolean status = rs.getBoolean("Status");
                if (status) {
                    return "T";
                } else {
                    return "F";
                }
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println("when accessing the database \"Number slot\" got an error");
        }
        return "N";
    }
    public int CheckAbsentQuantities(int CodeG, String CodeS) {
        
        int absent=0;
        String sql = "SELECT        Status\n"
                + "FROM            Attendances\n"
                + "Where Attendances.CodeStudent = ? AND Attendances.CodeGroup=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, CodeS);
            st.setInt(2, CodeG);
            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {

                boolean status = rs.getBoolean("Status");
                if (!status) {
                    absent++;
                } 
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println("when accessing the database \"Number slot absent\" got an error");
        }
        return absent;
    }
    public static void main(String[] args) {
        DAOProgress object = new DAOProgress();
        ArrayList<LecturerGroups> crs = object.loadGrOfLecturer(1);
        if (crs != null) {
            for (LecturerGroups i : crs) {
                System.out.println(i.getCodeL() + " " + i.getCodeG() + " ");
            }
        }
    }
}
