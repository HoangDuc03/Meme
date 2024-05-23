/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Login;

public class DAOLogin extends DBContext{
    
    public Login login(String user , String password)
    {
        String sql = "SELECT [UserName]\n"
                + "      ,[PassWord]\n"
                + "      ,[Role]\n"
                + "  FROM [dbo].[Logins]\n"
                + "  WHere UserName = ? AND PassWord = ?";
        try {
            PreparedStatement st= connection.prepareStatement(sql);
            st.setString(1, user);
            st.setString(2, password);
            ResultSet rs=st.executeQuery();
            
            while(rs.next())
            {
                Login lg = new Login(rs.getString("UserName"),
                                     rs.getString("PassWord"),
                                     rs.getInt("Role"));
                return lg;
            }
        } catch (SQLException  | NumberFormatException e) {
            System.out.println("when accessing the database \"login\" got an error");
        }
        return null;
    }
        public static void main(String[] args) {
        DAOLogin object= new DAOLogin();
        Login lg = object.login("admin", "admin");
        if(lg != null)
                System.out.println("success");
        }
}
