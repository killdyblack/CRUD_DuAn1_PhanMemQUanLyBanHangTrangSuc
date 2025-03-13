/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.hoadonchitiet;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.HoaDon;
import model.HoaDonChiTiet;
import model.KhachHang;
import model.SanPham;
import model.TaiKhoan;
import model.Voucher;
import until.jdbc;

/**
 *
 * @author HUNGpYN
 */
public class RepositoryHoaDonChiTiet implements RepositoryHoaDonChiTietInteface {

    private Connection con = null;
    private PreparedStatement pre = null;
    private ResultSet res = null;
    private String sql = null;
    private CallableStatement call = null;

    @Override
    public List<HoaDonChiTiet> getAll() {
        List<HoaDonChiTiet> list = new ArrayList<>();
        sql = "select * from v_HoaDonChiTiet";
        try {
            con = jdbc.getConnection();
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            res = pre.executeQuery();
            while (res.next()) {
                KhachHang kh = new KhachHang();
                kh.setIDKhachHang(res.getString("IDKhachHang"));
                kh.setHoTen(res.getString("HoTenKhachHang"));
                TaiKhoan tk = new TaiKhoan();
                tk.setIDTaiKhoan(res.getString("IDTaiKhoan"));
                tk.setHoTen(res.getString("HoTenTaiKhoan"));
                Voucher vc = new Voucher();
                vc.setIDVoucher(res.getString("IDVoucher"));
                vc.setTenVoucher(res.getString("TenVoucher"));
                HoaDon hd = new HoaDon();
                hd.setIDHoaDon(res.getString("IDHoaDon"));
                hd.setTongTienTRuoc(res.getDouble("TongTienTruoc"));
                hd.setTongTienSau(res.getDouble("TongTienSau"));
                hd.setNgayTao(res.getDate("NgayTao"));
                hd.setTrangThai(res.getBoolean("TrangThai"));
                hd.setIdTaiKhoan(tk);
                hd.setIdKhachHang(kh);
                hd.setIdVoucher(vc);

                SanPham sp = new SanPham();
                sp.setIDSanPham(res.getString("IDSanPham"));
                sp.setTenSanPham(res.getString("TenSanPham"));
                sp.setSoLuongTonKho(res.getInt("SoLuongTonKho"));

                HoaDonChiTiet hdct = new HoaDonChiTiet();
                hdct.setIDHoaDonChiTiet(res.getString("IDHoaDonChiTiet"));
                hdct.setIDSanPham(sp);
                hdct.setSoLUongSanPHam(res.getInt("SoLuongSanPham"));
                hdct.setIDHoaDon(hd);
                hdct.setGiaSanPham(res.getDouble("GiaSanPham"));
                double giaSauGiamGia = res.getDouble("GiaSauGiamGia");
                if (res.wasNull()) {
                    // Giá sau giảm giá là null, gán giá mặc định (ví dụ: 0)
                    hdct.setGiaSauGiamGia(0.0);
                } else {
                    // Giá sau giảm giá không phải là null, gán giá lấy được
                    hdct.setGiaSauGiamGia(giaSauGiamGia);
                }
                list.add(hdct);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int create(HoaDonChiTiet ct) {
        sql = "EXEC sp_ThemHoaDonChiTiet "
                + "@IDSanPham = ?, "
                + "@IDHoaDon = ?, "
                + "@SoLuongSanPham = ?, "
                + "@TrangThai = 0;";

        try {
            con = jdbc.getConnection();
            call = con.prepareCall(sql);

            // Cung cấp các giá trị cho các tham số của stored procedure
            call.setString(1, ct.getIDSanPham().getIDSanPham());
            call.setString(2, ct.getIDHoaDon().getIDHoaDon());
            call.setInt(3, ct.getSoLUongSanPHam());

            // Thực thi stored procedure và trả về số hàng bị ảnh hưởng
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
    public int delete(String text) {
        sql = "Delete from HoaDonChitiet where IDHoaDonChiTiet = ?";

        try {
            con = jdbc.getConnection();
            pre = con.prepareCall(sql);
            pre.setString(1, text);

            return pre.executeUpdate();
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
    public int update(HoaDonChiTiet ct) {
        sql = "Update HoaDonChiTiet set SoLuongSanPham = ? where IDHoaDonChiTiet = ?";

        try {
            con = jdbc.getConnection();
            pre = con.prepareCall(sql);
            pre.setInt(1, ct.getSoLUongSanPHam());
            pre.setString(2, ct.getIDHoaDonChiTiet());

            return pre.executeUpdate();
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
    public List<HoaDonChiTiet> getWithConditions(java.sql.Date startDate, java.sql.Date endDate, String idTaiKhoan) {
        List<HoaDonChiTiet> list = new ArrayList<>();

        // Định dạng ngày theo yyyy-MM-dd
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Chuyển đổi startDate và endDate sang chuỗi theo định dạng yyyy-MM-dd
        String startDateStr = sdf.format(startDate);
        String endDateStr = sdf.format(endDate);

        // Tạo chuỗi cho ngày bắt đầu (00:00:01) và ngày kết thúc (23:59:59)
        String startTimestampStr = startDateStr + " 00:00:01";
        String endTimestampStr = endDateStr + " 23:59:59";

        String sql = "SELECT * FROM v_HoaDonChiTiet WHERE NgayTao BETWEEN ? AND ? AND IDTaiKhoan = ?";

        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);

            // Set parameters for the prepared statement
            pre.setString(1, startTimestampStr);
            pre.setString(2, endTimestampStr);
            pre.setString(3, idTaiKhoan);

            res = pre.executeQuery();
            while (res.next()) {
                KhachHang kh = new KhachHang();
                kh.setIDKhachHang(res.getString("IDKhachHang"));
                kh.setHoTen(res.getString("HoTenKhachHang"));

                TaiKhoan tk = new TaiKhoan();
                tk.setIDTaiKhoan(res.getString("IDTaiKhoan"));
                tk.setHoTen(res.getString("HoTenTaiKhoan"));

                Voucher vc = new Voucher();
                vc.setIDVoucher(res.getString("IDVoucher"));
                vc.setTenVoucher(res.getString("TenVoucher"));

                HoaDon hd = new HoaDon();
                hd.setIDHoaDon(res.getString("IDHoaDon"));
                hd.setTongTienTRuoc(res.getDouble("TongTienTruoc"));
                hd.setTongTienSau(res.getDouble("TongTienSau"));
                hd.setNgayTao(res.getDate("NgayTao"));
                hd.setTrangThai(res.getBoolean("TrangThai"));
                hd.setIdTaiKhoan(tk);
                hd.setIdKhachHang(kh);
                hd.setIdVoucher(vc);

                SanPham sp = new SanPham();
                sp.setIDSanPham(res.getString("IDSanPham"));
                sp.setTenSanPham(res.getString("TenSanPham"));
                sp.setSoLuongTonKho(res.getInt("SoLuongTonKho"));

                HoaDonChiTiet hdct = new HoaDonChiTiet();
                hdct.setIDHoaDonChiTiet(res.getString("IDHoaDonChiTiet"));
                hdct.setIDSanPham(sp);
                hdct.setSoLUongSanPHam(res.getInt("SoLuongSanPham"));
                hdct.setIDHoaDon(hd);
                hdct.setGiaSanPham(res.getDouble("GiaSanPham"));
                double giaSauGiamGia = res.getDouble("GiaSauGiamGia");

                if (res.wasNull()) {
                    // Giá sau giảm giá là null, gán giá mặc định (ví dụ: 0)
                    hdct.setGiaSauGiamGia(0.0);
                } else {
                    // Giá sau giảm giá không phải là null, gán giá lấy được
                    hdct.setGiaSauGiamGia(giaSauGiamGia);
                }

                list.add(hdct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
