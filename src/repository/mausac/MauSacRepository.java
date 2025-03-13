/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.mausac;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.MauSac;
import until.jdbc;

/**
 *
 * @author HUNGpYN
 */
public class MauSacRepository implements MauSacInterface{
    private Connection con = null;
    private PreparedStatement pre = null;
    private ResultSet res = null;
    private String sql = null;
    @Override
    public List<MauSac> getAll() {
            List ms = new ArrayList();
        sql = "select * from MauSac";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            res = pre.executeQuery();
            while(res.next()){
                MauSac  mauSac= new MauSac();
                mauSac.setIDMauSac(res.getString(1));
                mauSac.setChiTietMauSac(res.getString(2));
                mauSac.setTrangThai(res.getBoolean("TrangThai"));
                ms.add(mauSac);
            }
            return ms;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int creat(MauSac ms) {
        sql = "EXEC ADDMauSac ?";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            pre.setString(1, ms.getChiTietMauSac());
            return pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(MauSac ms) {
        sql = "UPDATE MauSac SET ChiTietMauSac = ?, TrangThai = ?   WHERE IDMauSac = ?";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            pre.setString(3, ms.getIDMauSac());
            pre.setBoolean(2, ms.isTrangThai());
            pre.setString(1, ms.getChiTietMauSac());

            return pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(MauSac ms) {
       sql = "UPDATE MauSac SET TrangThai = 0   WHERE IDMauSac = ?";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            pre.setString(1, ms.getIDMauSac());
            return pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public boolean addMauSac(MauSac mauSac) {
        String sql = "INSERT INTO [dbo].[MauSac] ([ChiTietMauSac], [TrangThai]) VALUES (?, ?)";
        try {con = jdbc.getConnection();
            pre = con.prepareStatement(sql) ;
            pre.setString(1, mauSac.getChiTietMauSac());
            pre.setBoolean(2, mauSac.isTrangThai());

            int rowsInserted = pre.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    
}
