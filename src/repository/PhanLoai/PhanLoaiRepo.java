/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.PhanLoai;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.PhanLoai;
import until.jdbc;

/**
 *
 * @author WINDOWS10
 */
public class PhanLoaiRepo implements InterfaceRepoPhanLoai {
    
    
    private Connection con = null;
    private PreparedStatement pre = null;
    private ResultSet res = null;
    private String sql = null;
    @Override
    public List<PhanLoai> getAll() {
        List<PhanLoai> listpl = new ArrayList<>();
        sql = "select * from PhanLoai";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            res = pre.executeQuery();
            while (res.next()) { 
                PhanLoai pl = new PhanLoai();
                pl.setIDPhanLoai(res.getString("IDPhanLoai"));
                pl.setTenPhanLoai(res.getString("TenPhanLoai"));
                pl.setTrangThai(res.getBoolean("TrangThai"));
               listpl.add(pl);
            }
            return listpl;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    public boolean updatePhanLoai(PhanLoai phanLoai) {
    String sql = "UPDATE [dbo].[PhanLoai] "
               + "SET [TenPhanLoai] = ?, "
               + "[TrangThai] = ? "
               + "WHERE [IDPhanLoai] = ?";
    try (Connection con = jdbc.getConnection(); PreparedStatement pre = con.prepareStatement(sql)) {
        pre.setString(1, phanLoai.getTenPhanLoai());
        pre.setBoolean(2, phanLoai.isTrangThai());
        pre.setString(3, phanLoai.getIDPhanLoai());

        int rowsUpdated = pre.executeUpdate();
        return rowsUpdated > 0; 
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    @Override
    public boolean addPhanLoai(PhanLoai pl) {
        String sql = "INSERT INTO [dbo].[PhanLoai] ([TenPhanLoai], [TrangThai]) VALUES (?, ?)";

        try (Connection conn = jdbc.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, pl.getTenPhanLoai());
            pstmt.setBoolean(2, pl.isTrangThai());
            int row = pstmt.executeUpdate();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
