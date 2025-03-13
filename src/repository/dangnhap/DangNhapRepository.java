/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.dangnhap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.TaiKhoan;
import until.jdbc;

/**
 *
 * @author HUNGpYN
 */
public class DangNhapRepository implements DangNhapInterface {

    private Connection con = null;
    private PreparedStatement pre = null;
    private ResultSet res = null;
    private String sql = null;

// Kiem tra mat khau va tai khoan
    @Override
    public TaiKhoan checkTaiKhoan(String taiKhoan, String matKhau) {
        sql = "select HoTen, ChucVu,IDTaiKhoan from TaiKhoan where TaiKhoan = ? and MatKhau = ?";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            pre.setString(1, taiKhoan);
            pre.setString(2, matKhau);
            res = pre.executeQuery();
            if (res.next()) {
                String hoTen = res.getString(1);
                boolean chuVu = res.getBoolean(2);
                String idTaiKhoan = res.getString(3);
                return new TaiKhoan(idTaiKhoan, hoTen, chuVu);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            try {
                if (res != null) {
                    res.close();
                }
                if (pre != null) {
                    pre.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
