/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.hoadon;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.GiaoDien.DoanhThuModel;
import model.HoaDon;
import model.TaiKhoan;
import until.jdbc;

/**
 *
 * @author HUNGpYN
 */
public class RepositoryHoaDon implements RepositoryHoaDonInterface {

    private Connection con = null;
    private PreparedStatement pre = null;
    private ResultSet res = null;
    private CallableStatement call = null;
    private String sql = null;

    @Override
    public List<HoaDon> getAll() {
        List<HoaDon> list = new ArrayList<>();
        sql = "Select * from v_HoaDonCho";
        try {
            con = jdbc.getConnection();
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            res = pre.executeQuery();
            while (res.next()) {
                HoaDon hd = new HoaDon();
                hd.setIDHoaDon(res.getString("IDHoaDon"));
                hd.setNgayTao(res.getDate("NgayTao"));
                hd.setTongTienTRuoc(res.getDouble("TongTien"));
                hd.setTrangThai(res.getBoolean("TrangThai"));
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int create(String tk) {
        sql = "insert into HoaDon(IDTaiKhoan,TrangThai,TrangThaiTichDiem) values(?,?,?)";
        try {
            con = jdbc.getConnection();
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            pre.setString(1,tk );
            pre.setBoolean(2, false);
            pre.setBoolean(3, true);
            return pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(String text) {
        String sql = "EXEC DeleteHoaDon @idhoadon = ?";

        try {
            con = jdbc.getConnection();
            call = con.prepareCall(sql);
            call.setString(1, text);
            return call.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            // Đảm bảo đóng tài nguyên
            try {
                if (call != null) {
                    call.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int update(HoaDon hd) {

        String sql = "UPDATE HoaDon\n"
                + "SET IDKhachHang = ?,\n"
                + "    IDVoucher = ?,\n"
                + "    TongTienTruoc = ?,\n"
                + "    TongTienSau = ?,\n"
                + "    TrangThai = ?\n"
                + "WHERE IDHoaDon = ?";

        Connection con = null;
        PreparedStatement pre = null;

        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            pre.setString(1, hd.getIdKhachHang().getIDKhachHang());
            pre.setString(2, hd.getIdVoucher().getIDVoucher());
            pre.setDouble(3, hd.getTongTienTRuoc());
            pre.setDouble(4, hd.getTongTienSau());
            pre.setBoolean(5, true);
            pre.setString(6, hd.getIDHoaDon());

            // Thực hiện cập nhật và trả về số lượng bản ghi bị ảnh hưởng
            return pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            // Đảm bảo đóng tài nguyên
            try {
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
    }

    @Override
    public int updateTichDiem(String IDKhachHang, String IDHoaDon) {
        sql = "UPDATE HoaDon\n"
                + "SET TrangThaiTichDiem = ?\n"
                + "WHERE IDKhachHang = ?\n"
                + "AND IDHoaDon <> ?;";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            pre.setBoolean(1, false);
            pre.setString(2, IDKhachHang);
            pre.setString(3, IDHoaDon);
            // Thực hiện cập nhật và trả về số lượng bản ghi bị ảnh hưởng
            return pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            // Đảm bảo đóng tài nguyên
            try {
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
    }

    
    // code HoaDon phúc
     @Override
    public List<HoaDon> getAllDT() {
        sql = "select * from v_DoanhThu_Khoang_Thoi_Gian";
        List<HoaDon> lstDT = new ArrayList<>();
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            res = pre.executeQuery();
            while (res.next()) {
                HoaDon hd = new HoaDon();
                TaiKhoan tk = new TaiKhoan();
                tk.setIDTaiKhoan(res.getString("IDTaiKhoan"));
                tk.setHoTen(res.getString("HoTen"));
                hd.setIdTaiKhoan(tk);
                hd.setSoLuongDon(res.getInt("TongSoDon"));
                hd.setTongTienTRuoc(res.getDouble("TongDoanhThu"));
                hd.setTongTienSau(res.getDouble("DoanhThuThuc"));
                hd.setGiamGiaSanPham(res.getDouble("GiamGiaSanPham"));
                lstDT.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return lstDT;

    }

    @Override
    public List<HoaDon> getDoanhThuTheoKhoangThoiGian(DoanhThuModel dtmd) {
        
        StringBuilder sql = new StringBuilder("SELECT * FROM v_DoanhThu_NhanVien_ThoiGian WHERE 1 = 1");
        List<HoaDon> hoaDonList = new ArrayList<>();

        
        if (dtmd.getNgayStart() != 0) {
            sql.append(" AND Ngay >= ?");
        }
        if (dtmd.getNgayEnd() != 0) {
            sql.append(" AND Ngay <= ?");
        }
        if (dtmd.getThangStart() != 0) {
            sql.append(" AND Thang >= ?");
        }
        if (dtmd.getThangEnd() != 0) {
            sql.append(" AND Thang <= ?");
        }
        if (dtmd.getNamStart() != 0) {
            sql.append(" AND Nam >= ?");
        }
        if (dtmd.getNamEnd() != 0) {
            sql.append(" AND Nam <= ?");
        }
        if (dtmd.getHoTen() != null && !dtmd.getHoTen().isEmpty() && !"Tất Cả".equals(dtmd.getHoTen())) {
            sql.append(" AND HoTen = ?");
        }

        try (Connection con = jdbc.getConnection(); PreparedStatement pre = con.prepareStatement(sql.toString())) {

            
            int paramIndex = 1;
            if (dtmd.getNgayStart() != 0) {
                pre.setInt(paramIndex++, dtmd.getNgayStart());
            }
            if (dtmd.getNgayEnd() != 0) {
                pre.setInt(paramIndex++, dtmd.getNgayEnd());
            }
            if (dtmd.getThangStart() != 0) {
                pre.setInt(paramIndex++, dtmd.getThangStart());
            }
            if (dtmd.getThangEnd() != 0) {
                pre.setInt(paramIndex++, dtmd.getThangEnd());
            }
            if (dtmd.getNamStart() != 0) {
                pre.setInt(paramIndex++, dtmd.getNamStart());
            }
            if (dtmd.getNamEnd() != 0) {
                pre.setInt(paramIndex++, dtmd.getNamEnd());
            }
            if (dtmd.getHoTen() != null && !dtmd.getHoTen().isEmpty() && !"Tất Cả".equals(dtmd.getHoTen())) {
                pre.setString(paramIndex++, dtmd.getHoTen());
            }

            
            try (ResultSet res = pre.executeQuery()) {
                while (res.next()) {
                    HoaDon hd = new HoaDon();
                    TaiKhoan tk = new TaiKhoan();
                    tk.setIDTaiKhoan(res.getString("IDTaiKhoan"));
                    tk.setHoTen(res.getString("HoTen"));
                    hd.setIdTaiKhoan(tk);
                    hd.setSoLuongDon(res.getInt("TongSoDon"));
                    hd.setTongTienTRuoc(res.getDouble("TongTienTruoc"));
                    hd.setTongTienSau(res.getDouble("TongTienSau"));
                    hd.setGiamGiaSanPham(res.getDouble("GiamGiaSanPham"));
                    hoaDonList.add(hd);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hoaDonList;
    }

    @Override
    public List<DoanhThuModel> findByDate(DoanhThuModel dtmd) {
        String sql = "SELECT * FROM v_DoanhThu_Khoang_Thoi_Gian_NhanVien WHERE Ngay BETWEEN ? AND ? AND Thang = ? AND Nam = ? AND HoTen like ?";
        List<DoanhThuModel> lmd = new ArrayList<>();
        try (Connection con = jdbc.getConnection(); PreparedStatement pre = con.prepareStatement(sql)) {

            pre.setInt(1, dtmd.getNgayStart());
            pre.setInt(2, dtmd.getNgayEnd());
            pre.setInt(3, dtmd.getThangStart());
            pre.setInt(4, dtmd.getNamStart());
            pre.setString(5, "%" + dtmd.getHoTen() + "%");

            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                DoanhThuModel dtm = new DoanhThuModel();
                dtm.setHoTen(rs.getString("HoTen"));
                dtm.setNgayEnd(rs.getInt("Ngay"));
                dtm.setThangEnd(rs.getInt("Thang"));
                dtm.setNamEnd(rs.getInt("Nam"));
                dtm.setTongTienTruoc(rs.getDouble("TongTienTruoc"));
                dtm.setTongTienSau(rs.getDouble("TongTienSau"));
                dtm.setTongGiaGia(rs.getDouble("GiamGiaSanPham"));
                lmd.add(dtm);
            }
            return lmd;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<DoanhThuModel> getDoanhThuByMonthAndYear() {
        List<DoanhThuModel> result = new ArrayList<>();
        String sql = "SELECT Nam, Thang, TongDoanhThu, DoanhThuThuc, GiamGiaSanPham FROM v_DoanhThu_Thang_Nv";

        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            res = pre.executeQuery();
            while (res.next()) {
                DoanhThuModel model = new DoanhThuModel();
                model.setNamEnd(res.getInt("Nam"));
                model.setThangEnd(res.getInt("Thang"));
                model.setTongTienTruoc(res.getDouble("TongDoanhThu"));
                model.setTongTienSau(res.getDouble("DoanhThuThuc"));
                model.setTongGiaGia(res.getDouble("GiamGiaSanPham"));
                result.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        return result;
    }
}
