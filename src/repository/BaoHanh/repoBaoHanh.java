/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.BaoHanh;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.BaoHanh;
import model.HoaDon;
import model.HoaDonChiTiet;
import model.KhachHang;
import model.SanPham;
import model.SerialNumber;
import until.jdbc;

/**
 *
 * @author nguyentrikhoi
 */
public class repoBaoHanh implements InterfaceRepoBaoHanh {

    private Connection con = null;
    private PreparedStatement pre = null;
    private ResultSet res = null;
    private String sql = null;

    @Override
    public List<BaoHanh> getAll() {
        List<BaoHanh> listBH = new ArrayList<>();
        sql = "SELECT * FROM View_DanhSachSanPham_BaoHanh";

        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            res = pre.executeQuery();
            while (res.next()) {
                BaoHanh bh = new BaoHanh();
                bh.setIDBaoHanh(res.getString("IDBaoHanh"));
                bh.setGhiChu(res.getString("GhiChu"));
                bh.setNgayYeuCau(res.getDate("NgayYeuCau"));
                bh.setTrangThai(res.getBoolean("TrangThai"));
                KhachHang kh = new KhachHang();
                kh.setHoTen(res.getString("HoTen"));
                kh.setSoDienThoai(res.getString("SoDienThoai"));
                kh.setDiaChi(res.getString("DiaChi"));
                SanPham sp = new SanPham();
                sp.setTenSanPham(res.getString("TenSanPham"));
                bh.setIDKhachHang(kh);
                bh.setIDSanPham(sp);
                listBH.add(bh);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listBH;
    }

    @Override
    public int capNhat(BaoHanh bh) {
        int rowsAffected = 0;
        String sql = "UPDATE BaoHanh SET GhiChu = ?, TrangThai = ? WHERE IDBaoHanh = ?";

        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);

            pre.setString(1, bh.getGhiChu());
            pre.setBoolean(2, bh.isTrangThai());
            pre.setString(3, bh.getIDBaoHanh());

            rowsAffected = pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (pre != null) {
                    pre.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return rowsAffected;
    }

    @Override
    public List<BaoHanh> getSanPhamBaoHanh(String soDienThoaiBaoHanh) {
        List<BaoHanh> listSP = new ArrayList<>();
        String sql = "SELECT * FROM View_BaoHanh_ChiTiet WHERE SoDienThoai = ?";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            pre.setString(1, soDienThoaiBaoHanh);
            try (ResultSet res = pre.executeQuery()) {
                while (res.next()) {

                    SanPham sp = new SanPham();
                    sp.setTenSanPham(res.getString("TenSanPham"));
                    sp.setIDSanPham(res.getString("IDSanPham"));

                    SerialNumber sn = new SerialNumber();
                    sn.setThoiGianBaoHanh(res.getDate("ThoiHanBaoHanh"));
                    sn.setIDSerialNumber(res.getString("SerialNumber"));
                    sn.setTrangThai(res.getBoolean("TrangThai"));

                    HoaDonChiTiet hdCT = new HoaDonChiTiet();
                    hdCT.setIDHoaDonChiTiet(res.getString("IDHoaDonChiTiet"));
                    hdCT.setNgayTao(res.getDate("NgayTao"));

                    BaoHanh bh = new BaoHanh();
                    bh.setIDSanPham(sp);
                    bh.setIDHoaDonChiTiet(hdCT);
                    bh.setIDSerialNumber(sn);

                    listSP.add(bh);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listSP;
    }

    @Override
    public boolean themBaoHanh(String idKhachHang, String seriSp, String idSanPham, String idHoaDonChiTiet, java.sql.Date ngayYeuCau, String ghiChu, boolean trangThai) {

        String sql = "{CALL sp_ThemBaoHanh(?, ?, ?, ?, ?, ?, ?)}";

        try (Connection connection = jdbc.getConnection(); CallableStatement stmt = connection.prepareCall(sql)) {

            stmt.setString(1, idKhachHang);     // @IDKhachHang
            stmt.setString(2, seriSp);          // @SerialNumber
            stmt.setString(3, idSanPham);       // @IDSanPham
            stmt.setString(4, idHoaDonChiTiet); // @IDHoaDonChiTiet
            stmt.setDate(5, ngayYeuCau);        // @NgayHenTra
            stmt.setString(6, ghiChu);          // @GhiChu
            stmt.setBoolean(7, trangThai);      // @TrangThai

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean capNhatBaoHanh(String idBaoHanh, String ghiChu, boolean trangThai) {

        String sql = "UPDATE BaoHanh SET GhiChu = ?, TrangThai = ? WHERE IDBaoHanh = ?";
        System.out.println("IDBaoHanh: " + idBaoHanh + " repo");
        System.out.println("GhiChu: " + ghiChu + " repo");
        System.out.println("TrangThai: " + trangThai + " repo");

        try (Connection connection = jdbc.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, ghiChu);     // GhiChu
            stmt.setBoolean(2, trangThai); // TrangThai
            stmt.setString(3, idBaoHanh);  // IDBaoHanh

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<BaoHanh> getDanhSachSanPhamBaoHanhByDateAndPhone(java.sql.Date tuNgay, java.sql.Date toiNgay, String soDienThoai) {
        List<BaoHanh> baoHanhList = new ArrayList<>();
        String sql = "SELECT * FROM View_DanhSachSanPham_BaoHanh WHERE NgayYeuCau BETWEEN ? AND ? AND SoDienThoai LIKE ?";

        try (Connection connection = jdbc.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setDate(1, tuNgay);
            stmt.setDate(2, toiNgay);
            stmt.setString(3, "%" + soDienThoai + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    BaoHanh bh = new BaoHanh();
                    bh.setIDBaoHanh(rs.getString("IDBaoHanh"));
                    bh.setNgayYeuCau(rs.getDate("NgayYeuCau"));
                    bh.setTrangThai(rs.getBoolean("TrangThai"));
                    bh.setGhiChu(rs.getString("GhiChu"));

                    KhachHang kh = new KhachHang();
                    kh.setHoTen(rs.getString("HoTen"));
                    kh.setSoDienThoai(rs.getString("SoDienThoai"));
                    kh.setDiaChi(rs.getString("DiaChi"));
                    bh.setIDKhachHang(kh);

                    SanPham sp = new SanPham();
                    sp.setTenSanPham(rs.getString("TenSanPham"));
                    bh.setIDSanPham(sp);

                    baoHanhList.add(bh);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return baoHanhList;
    }

}
