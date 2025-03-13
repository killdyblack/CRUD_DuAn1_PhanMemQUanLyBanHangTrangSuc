/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.NhaCungCap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.NhaCungCap;

import until.jdbc;

/**
 *
 * @author HUNGpYN
 */
public class NhaCungCapRepository implements NhaCungCapInterface{

    private Connection con = null;
    private PreparedStatement pre = null;
    private ResultSet res = null;
    private String sql = null;
    @Override
    public List<NhaCungCap> getAll() {
         List kd = new ArrayList();
        sql = "select * from NhaCungCap";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            res = pre.executeQuery();
            while (res.next()) {                
                NhaCungCap ng = new NhaCungCap();
                ng.setIDNhaCungCap(res.getString(1));
                ng.setTenNhaCungCap(res.getString(2));
                ng.setSoDienThoai(res.getString(3));
                ng.setDiaChi(res.getString(4));
                ng.setEmail(res.getString(5));
                ng.setNgayTao(res.getDate(6));
                ng.setNgaySua(res.getDate(7));
                ng.setTrangThai(res.getBoolean(8));
                kd.add(ng);
            }
            return kd;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addNhaCungCap(NhaCungCap nhaCungCap) {
        String sql = "INSERT INTO [dbo].[NhaCungCap] ([TenNhaCungCap], [SoDienThoai], [DiaChi], [Email], [TrangThai]) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = jdbc.getConnection(); PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setString(1, nhaCungCap.getTenNhaCungCap());
            pre.setString(2, nhaCungCap.getSoDienThoai());
            pre.setString(3, nhaCungCap.getDiaChi());
            pre.setString(4, nhaCungCap.getEmail());
            pre.setBoolean(5, nhaCungCap.isTrangThai());

            int rowsInserted = pre.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int updateNhaCungCap(NhaCungCap ncc) {
    String sql = "UPDATE [dbo].[NhaCungCap] SET " +
                 "[TenNhaCungCap] = ?, " +
                 "[SoDienThoai] = ?, " +
                 "[DiaChi] = ?, " +
                 "[Email] = ?, " +
                 "[TrangThai] = ? " +
                 "WHERE [IDNhaCungCap] = ?";

    try (Connection con = jdbc.getConnection(); 
         PreparedStatement pre = con.prepareStatement(sql)) {
        pre.setString(1, ncc.getTenNhaCungCap());
        pre.setString(2, ncc.getSoDienThoai());
        pre.setString(3, ncc.getDiaChi());
        pre.setString(4, ncc.getEmail());
        pre.setBoolean(5, ncc.isTrangThai());
        pre.setString(6, ncc.getIDNhaCungCap());

        return pre.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        return 0;
    }
}


    @Override
    public int delete() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int creat() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
