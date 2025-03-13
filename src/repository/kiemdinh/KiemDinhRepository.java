/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.kiemdinh;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.KiemDinh;
import until.jdbc;

/**
 *
 * @author HUNGpYN
 */
public class KiemDinhRepository implements KiemDinhInterface {

    private Connection con = null;
    private PreparedStatement pre = null;
    private ResultSet res = null;
    private String sql = null;

    @Override
    public List<KiemDinh> getAll() {
        List kd = new ArrayList();
        sql = "select * from KiemDinh";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            res = pre.executeQuery();
            while (res.next()) {
                KiemDinh kiemDinh = new KiemDinh();
                kiemDinh.setIDKiemDinh(res.getString(1));
                kiemDinh.setDonViKiemDinh(res.getString(2));
                kiemDinh.setNgayKiemDinh(res.getDate(3));
                kiemDinh.setTrangThai(res.getBoolean(6));
                kd.add(kiemDinh);
            }
            return kd;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int creat() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int updateKiemDinh(KiemDinh kd) {
        String sql = "UPDATE [dbo].[KiemDinh] SET "
                + "[DonViKiemDinh] = ?, "
                + "[NgayKiemDinh] = ?, "
                + "[NgayTao] = ?, "
                + "[NgaySua] = ?, "
                + "[TrangThai] = ? "
                + "WHERE [IDKiemDinh] = ?";

        try (Connection con = jdbc.getConnection(); PreparedStatement pre = con.prepareStatement(sql)) {

            pre.setString(1, kd.getDonViKiemDinh());
            pre.setDate(2, toSqlDate(kd.getNgayKiemDinh()));
            pre.setDate(3, toSqlDate(kd.getNgayTao()));
            pre.setDate(4, toSqlDate(kd.getNgaySua()));
            pre.setBoolean(5, kd.isTrangThai());
            pre.setString(6, kd.getIDKiemDinh());

            return pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private java.sql.Date toSqlDate(java.util.Date date) {
        return (date == null) ? null : new java.sql.Date(date.getTime());
    }

    @Override
    public int delete() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean addKiemDinh(KiemDinh kiemDinh) {
        String sql = "INSERT INTO [dbo].[KiemDinh] ([DonViKiemDinh], [NgayKiemDinh], [TrangThai]) VALUES (?, ?, ?)";
        try (Connection con = jdbc.getConnection(); PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setString(1, kiemDinh.getDonViKiemDinh());
            pre.setDate(2, kiemDinh.getNgayKiemDinh());
            pre.setBoolean(3, kiemDinh.isTrangThai());
            System.out.println("kiểm định rp" + kiemDinh.getNgayKiemDinh());
            int rowsInserted = pre.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
