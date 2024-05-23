package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.UIManager;
import model.Slots;
import model.Student;
import model.Group;
import model.Lecturer;

/**
 *
 * @author Admin
 */
public class DAOSlot extends DBContext {

    public ArrayList<Slots> loadSlot(String Code, String date1, String date2) {
        ArrayList<Slots> sl = new ArrayList();

        String sql = "SELECT        Slots.CodeGroup, Groups.Name,Courses.Sign AS ClassName, Slots.Day, Slots.SlotStart, Slots.CodeLecturer, Slots.SlotNumber, Rooms.Name AS Room\n"
                + "FROM            Students INNER JOIN\n"
                + "                         Groups ON Students.CodeGroup = Groups.Code INNER JOIN\n"
                + "                         Slots ON Groups.Code = Slots.CodeGroup INNER JOIN\n"
                + "                         Rooms ON Slots.Room = Rooms.Code INNER JOIN\n"
                + "                         Courses ON Groups.CodeCourse = Courses.Code\n"
                + "WHERE        (Students.Code = ?) AND (Slots.Day >= ?) AND (Slots.Day < ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, Code);
            st.setDate(2, java.sql.Date.valueOf(date1));
            st.setDate(3, java.sql.Date.valueOf(date2));

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Slots clone = new Slots(rs.getInt("CodeGroup"),
                        rs.getString("Name"),
                        rs.getString("Day"),
                        rs.getInt("SlotStart"),
                        rs.getInt("CodeLecturer"),
                        rs.getInt("SlotNumber"),
                        rs.getString("Room"));
                clone.setSign(rs.getString("ClassName"));

                sl.add(clone);

            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println("when accessing the database \"Slots\" got an error");
        }
        return sl;
    }

    public ArrayList<Slots> loadSlotL(int Lect, String date1, String date2) {
        ArrayList<Slots> sl = new ArrayList();

        String sql = "SELECT        Slots.CodeGroup, Groups.Name, Courses.Sign AS ClassName, Slots.Day, Slots.SlotStart, Slots.CodeLecturer, Slots.SlotNumber, Rooms.Name AS Room\n"
                + "FROM            Groups INNER JOIN\n"
                + "                         Slots ON Groups.Code = Slots.CodeGroup INNER JOIN\n"
                + "                         Rooms ON Slots.Room = Rooms.Code INNER JOIN\n"
                + "                         Courses ON Groups.CodeCourse = Courses.Code\n"
                + "WHERE        (Slots.CodeLecturer = ?) AND (Slots.Day >= ?) AND (Slots.Day < ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Lect);
            st.setDate(2, java.sql.Date.valueOf(date1));
            st.setDate(3, java.sql.Date.valueOf(date2));

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Slots clone = new Slots(rs.getInt("CodeGroup"),
                        rs.getString("Name"),
                        rs.getString("Day"),
                        rs.getInt("SlotStart"),
                        rs.getInt("CodeLecturer"),
                        rs.getInt("SlotNumber"),
                        rs.getString("Room"));
                clone.setSign(rs.getString("ClassName"));
                sl.add(clone);

            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println("when accessing the database \"Slots\" got an error");
        }
        return sl;
    }

    public ArrayList<Integer> loadClassOfStudent(String CodeStudent) {
        ArrayList<Integer> gr = new ArrayList();

        String sql = "SELECT       Students.CodeGroup as Code\n"
                + "FROM            Students INNER JOIN\n"
                + "                         InforStudents ON Students.Code = InforStudents.Code\n"
                + "Where Students.Code = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, CodeStudent);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                gr.add(rs.getInt("Code"));

            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println("when accessing the database \"Group TimeTable\" got an error");
        }
        return gr;
    }

    public ArrayList<Integer> loadClassOfLecturer(String CodeLecturer) {
        ArrayList<Integer> gr = new ArrayList();

        String sql = "SELECT distinct Slots.CodeGroup\n"
                + "FROM Lecturers INNER JOIN\n"
                + "Slots ON Lecturers.Code = Slots.CodeLecturer\n"
                + "Where Lecturers.Code = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, CodeLecturer);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                gr.add(rs.getInt("CodeGroup"));

            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println("when accessing the database \"Group TimeTable Lecturer\" got an error");
        }
        return gr;
    }

    public static void main(String[] args) {
        DAOSlot object = new DAOSlot();
        ArrayList<Slots> crs = object.loadSlot("HE170001", "2024-2-18", "2024-2-25");
        for (Slots i : crs) {
            System.out.println(i.getName() + " " + i.getSign());
        }
    }
}
