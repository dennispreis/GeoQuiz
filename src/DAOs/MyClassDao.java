/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Class;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class MyClassDao extends MySqlDao implements ClassDaoInterface
{

    @Override
    public List<Class> getClassByTeacherId(int id)
    {
        List<Class> classList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            con = this.getConnection();
            String query = "SELECT class_id,class_name FROM classes WHERE teacher_id=?";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next())
            {
                int class_id = rs.getInt("class_id");
                String class_name = rs.getString("class_name");
                classList.add(new Class(class_id, class_name));
            }
        } catch (SQLException e)
        {

        }
        return classList;
    }

}
