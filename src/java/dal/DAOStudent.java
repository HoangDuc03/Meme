package dal;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Student;

public class DAOStudent extends DBContext{
    public ArrayList<Student> loadStudents()
    {
        ArrayList<Student> std = new ArrayList();
        String sql = "SELECT * FROM InforStudents";
        try {
            PreparedStatement st= connection.prepareStatement(sql);
            ResultSet rs=st.executeQuery(); 
            while(rs.next())
            {
                Student clone = new Student(rs.getNString("Name"), 
                        rs.getString("Code"), 
                        rs.getBoolean("Gender"), 
                        rs.getString("Mail"),//+"@gmail.com", 
                        rs.getString("NumberPhone"), 
                        rs.getString("CCCD"), 
                        rs.getNString("Address"),
                        rs.getString("Major"),
                        rs.getInt("Semester"));
                std.add(clone);
                
            }
        } catch (SQLException  | NumberFormatException e) {
            System.out.println("when accessing the database \"Student\" got an error");
        }
        return std;
    }
    public static void main(String[] args) {
        DAOStudent object= new DAOStudent();
        ArrayList<Student> crs = object.loadStudents();
        for(Student i : crs)
        {
            System.out.println(i.getMSSV()+" "+i.getName()+" ");
        }
    }
}
