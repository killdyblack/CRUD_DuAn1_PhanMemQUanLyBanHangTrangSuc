/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.QuenMatKhau;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.TaiKhoan;
import until.jdbc;

/**
 *
 * @author nguyentrikhoi
 */
public class QuenMatKhauRepo implements InterfaceRepoQuenMatKhau{
    private Connection con = null;
        private PreparedStatement pre = null;
        private ResultSet res = null;
        private String sql = null;

    @Override
    public String getMatKhau(String taiKhoan, String soDienThoai, String email) {
        String sql = "SELECT MatKhau FROM TaiKhoan WHERE TaiKhoan = ? AND SoDienThoai = ? AND Email = ?";
        try {
            Connection con = jdbc.getConnection();
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, taiKhoan);
            pre.setString(2, soDienThoai);
            pre.setString(3, email);
            ResultSet res = pre.executeQuery();
            
            if (res.next()) {
                return res.getString("MatKhau");
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
        
    
}
