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
import model.Student;

/**
 *
 * @author Admin
 */
public class DAOLecturer extends DBContext{
    public ArrayList<Lecturer> loadLecturers()
    {
        ArrayList<Lecturer> lec = new ArrayList();
        String sql = "Select * from Lecturers";
        try {
            PreparedStatement st= connection.prepareStatement(sql);
            ResultSet rs=st.executeQuery(); 
            while(rs.next())
            {
                Lecturer clone = new Lecturer(rs.getNString("Name"), 
                        rs.getString("Code"), 
                        rs.getBoolean("Gender"), 
                        rs.getString("Mail"),//+"@gmail.com", 
                        rs.getString("NumberPhone"), 
                        rs.getString("CCCD"), 
                        rs.getNString("Address"),
                        rs.getInt("Department"),
                        rs.getBoolean("Contract"),
                        rs.getDate("Csf"),
                        rs.getDate("Duration"));
                lec.add(clone);
                
            }
        } catch (SQLException  | NumberFormatException e) {
            System.out.println("when accessing the database \"Lecturer\" got an error");
        }
        return lec;
    }
    public static void main(String[] args) {
        DAOLecturer object= new DAOLecturer();
        ArrayList<Lecturer> crs = object.loadLecturers();
        for(Lecturer i : crs)
        {
            System.out.println(i.getMSSV()+" "+i.getName()+" ");
        }
    }
}
