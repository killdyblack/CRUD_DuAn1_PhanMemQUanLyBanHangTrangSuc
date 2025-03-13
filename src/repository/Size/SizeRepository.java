/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.Size;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Size;
import until.jdbc;

/**
 *
 * @author HUNGpYN
 */
public class SizeRepository implements SizeInterface {

    private Connection con = null;
    private PreparedStatement pre = null;
    private ResultSet res = null;
    private String sql = null;

    @Override
    public List<Size> getAll() {
        List size = new ArrayList<>();
        sql = "select * from Size";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            res = pre.executeQuery();
            while (res.next()) {
                Size s = new Size();
                s.setIDSize(res.getString(1));
                s.setSoSize(res.getInt(2));
                s.setTrangThai(res.getBoolean(5));
                size.add(s);
            }
            return size;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public int creat(Size s) {
        sql = "INSERT INTO Size(SoSize, TrangThai) VALUES(?,?)";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            pre.setInt(1, s.getSoSize());
            pre.setBoolean(2, s.isTrangThai());
            return pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(Size s) {
        sql = "UPDATE Size SET SoSize =?, TrangThai = ?  WHERE IDSize = ?";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            pre.setInt(1, s.getSoSize());
            pre.setBoolean(2, s.isTrangThai());
            pre.setString(3, s.getIDSize());

            return pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(Size s) {
        sql = "UPDATE Size SET TrangThai = 0   WHERE IDSize = ?";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            pre.setString(1, s.getIDSize());

            return pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
