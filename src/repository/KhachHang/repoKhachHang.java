/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.KhachHang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.GiaoDien.GiaoDienKhachHangModel;
import model.KhachHang;
import until.jdbc;

/**
 *
 * @author nguyentrikhoi
 */
public class repoKhachHang implements InterfaceRepoKhachHang {

   
    private Connection con = null;
    private PreparedStatement pre = null;
    private ResultSet res = null;
    private String sql = null;

    @Override
    public List<KhachHang> getAll() {
        List<KhachHang> listKH = new ArrayList<>();
        sql = "select * from KhachHang";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            res = pre.executeQuery();

            while (res.next()) {
                KhachHang kh = new KhachHang();
                kh.setIDKhachHang(res.getString("IDKhachHang"));
                kh.setHoTen(res.getString("HoTen"));
                kh.setSoDienThoai(res.getString("SoDienThoai"));
                kh.setDiaChi(res.getString("DiaChi"));
                kh.setEmail(res.getString("Email"));
                kh.setTichDiem(res.getInt("TichDiem"));
                kh.setTrangThai(res.getBoolean("TrangThai"));
                listKH.add(kh);
            }
            return listKH;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public List<KhachHang> getAllByID(String str) {
        List<KhachHang> listKH = new ArrayList<>();
        sql = "select * from KhachHang where IDKhachHang = ?";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            pre.setString(1, str);
            res = pre.executeQuery();
            while (res.next()) {
                KhachHang kh = new KhachHang();
                kh.setIDKhachHang(res.getString("IDKhachHang"));
                kh.setHoTen(res.getString("HoTen"));
                kh.setSoDienThoai(res.getString("SoDienThoai"));
                kh.setDiaChi(res.getString("DiaChi"));
                kh.setEmail(res.getString("Email"));
                kh.setTichDiem(res.getInt("TichDiem"));
                kh.setTrangThai(res.getBoolean("TrangThai"));
                listKH.add(kh);
            }
            return listKH;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int creat(KhachHang kh) {
        sql = "INSERT into KhachHang(HoTen,SoDienThoai,DiaChi,Email) VALUES(?,?,?,?)";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            pre.setString(1, kh.getHoTen());
            pre.setString(2, kh.getSoDienThoai());
            pre.setString(3, kh.getDiaChi());
            pre.setString(4, kh.getEmail());

            return pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(KhachHang kh) {
        sql = "UPDATE KhachHang SET HoTen = ?, SoDienThoai = ?, DiaChi = ?, Email = ?, TrangThai = ? WHERE IDKhachHang = ?";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            pre.setString(1, kh.getHoTen());
            pre.setString(2, kh.getSoDienThoai());
            pre.setString(3, kh.getDiaChi());
            pre.setString(4, kh.getEmail());
            pre.setBoolean(5, kh.isTrangThai());
            pre.setString(6, kh.getIDKhachHang());

            return pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<KhachHang> getAllWithConditional(GiaoDienKhachHangModel gdmd) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM KhachHang WHERE 1=1 ");
        List<Object> params = new ArrayList<>();
        List<KhachHang> lstkh = new ArrayList<>();
        int defaultIntValue = 0;

        if (gdmd.getTrangThai() != defaultIntValue) {
            sqlBuilder.append(" AND TrangThai = ?");
            params.add(gdmd.getTrangThai() == 1);
        }

        if (gdmd.getHoTen() != null && !gdmd.getHoTen().isEmpty()) {
            sqlBuilder.append(" AND SoDienThoai LIKE ?");
            params.add("%" + gdmd.getHoTen() + "%");
        }

        String sql = sqlBuilder.toString();
        System.out.println(sql);
        try (Connection con = jdbc.getConnection(); PreparedStatement pre = con.prepareStatement(sql)) {

            for (int i = 0; i < params.size(); i++) {
                pre.setObject(i + 1, params.get(i));
                System.out.println(params.get(i));
            }

            try (ResultSet res = pre.executeQuery()) {
                while (res.next()) {
                    KhachHang kh = new KhachHang();
                    kh.setIDKhachHang(res.getString("IDKhachHang"));
                    kh.setHoTen(res.getString("HoTen"));
                    kh.setSoDienThoai(res.getString("SoDienThoai"));
                    kh.setDiaChi(res.getString("DiaChi"));
                    kh.setEmail(res.getString("Email"));
                    kh.setTichDiem(res.getInt("TichDiem"));
                    kh.setTrangThai(res.getBoolean("TrangThai"));
                    lstkh.add(kh);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

        return lstkh;
    }

}
