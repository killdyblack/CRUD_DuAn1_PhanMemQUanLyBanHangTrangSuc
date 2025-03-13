/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.KhuyenMai;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ChatLieu;
import model.DaQuy;
import model.GiamGia;
import model.GiaoDien.GiaoDienKhuyenMaiModel;
import model.GiaoDien.ThemGGModel;
import model.KiemDinh;
import model.MauSac;
import model.NhaCungCap;
import model.PhanLoai;
import model.SanPham;
import model.Size;
import model.Voucher;
import until.jdbc;

/**
 *
 * @author nguyentrikhoi
 */
public class KhuyenMaiRepository implements RepoKhuyenMaiInterface {

    private Connection con = null;
    private PreparedStatement pre = null;
    private ResultSet res = null;
    private String sql = null;

    @Override
    public List<Voucher> getAll() {
        List<Voucher> listVC = new ArrayList<>();
        sql = "select * from Voucher";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            res = pre.executeQuery();
            while (res.next()) {
                Voucher vc = new Voucher();
                vc.setIDVoucher(res.getString("IDVoucher"));
                vc.setTenVoucher(res.getString("TenVoucher"));
                vc.setTyLe(res.getFloat("TyLe"));
                vc.setNgayBatDau(res.getDate("NgayBatDau"));
                vc.setNgayKetThuc(res.getDate("NgayKetThuc"));
                vc.setTrangThai(res.getBoolean("TrangThai"));
                listVC.add(vc);
            }
            return listVC;

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

    }

    @Override
    public int creat(Voucher vc) {
        sql = "INSERT into Voucher(TyLe, TenVoucher ,NgayBatDau, NgayKetThuc, TrangThai) VALUES (?,?,?,?,?)";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            java.sql.Date sqlNgayBatDau = new java.sql.Date(vc.getNgayBatDau().getTime());
            java.sql.Date sqlNgayKetThuc = new java.sql.Date(vc.getNgayKetThuc().getTime());

            pre.setFloat(1, vc.getTyLe());
            pre.setString(2, vc.getTenVoucher());
            pre.setDate(3, sqlNgayBatDau);
            pre.setDate(4, sqlNgayKetThuc);
            pre.setBoolean(5, vc.isTrangThai());

            return pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(Voucher vc) {
        sql = "UPDATE Voucher Set TyLe = ?, TenVoucher =?, NgayBatDau = ?, NgayKetThuc = ?, TrangThai = ? where IDVoucher = ?";
        Connection con = null;
        PreparedStatement pre = null;
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);

            java.sql.Date sqlNgayBatDau = new java.sql.Date(vc.getNgayBatDau().getTime());
            java.sql.Date sqlNgayKetThuc = new java.sql.Date(vc.getNgayKetThuc().getTime());

            pre.setFloat(1, vc.getTyLe());
            pre.setString(2, vc.getTenVoucher());
            pre.setDate(3, sqlNgayBatDau);
            pre.setDate(4, sqlNgayKetThuc);
            pre.setBoolean(5, vc.isTrangThai());
            pre.setString(6, vc.getIDVoucher());

            return pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                if (pre != null) {
                    pre.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Voucher> getAllByID(String str) {
        List<Voucher> listVC = new ArrayList<>();
        String sql = "SELECT * FROM Voucher WHERE IDVoucher LIKE ?";
        try (Connection con = jdbc.getConnection(); PreparedStatement pre = con.prepareStatement(sql)) {

            pre.setString(1, "%" + str + "%");
            try (ResultSet res = pre.executeQuery()) {
                while (res.next()) {
                    Voucher vc = new Voucher();
                    vc.setIDVoucher(res.getString("IDVoucher"));
                    vc.setTenVoucher(res.getString("TenVoucher"));
                    vc.setTyLe(res.getFloat("TyLe"));
                    vc.setNgayBatDau(res.getDate("NgayBatDau"));
                    vc.setNgayKetThuc(res.getDate("NgayKetThuc"));
                    vc.setTrangThai(res.getBoolean("TrangThai"));
                    listVC.add(vc);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

        return listVC;
    }

    @Override
    public List<GiamGia> getAllGiamGia() {
        List<GiamGia> listGG = new ArrayList<>();
        String sql = "SELECT * FROM GiamGia";
        try (Connection con = jdbc.getConnection(); PreparedStatement pre = con.prepareStatement(sql)) {
            try (ResultSet res = pre.executeQuery()) {
                while (res.next()) {
                    GiamGia gg = new GiamGia();
                    gg.setIDGIamGia(res.getString("IDGiamGia"));
                    gg.setTenMaGiamGia(res.getString("TenMaGiamGia"));
                    gg.setTyLeGiamGia(res.getFloat("TyLeGiamGia"));
                    gg.setNgayBatDau(res.getDate("NgayBatDau"));
                    gg.setNgayKetThuc(res.getDate("NgayKetThuc"));
                    gg.setTrangThai(res.getBoolean("TrangThai"));
                    listGG.add(gg);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

        return listGG;
    }

    @Override
    public List<GiamGia> getAllByIDGiamGia(String str) {
        List<GiamGia> listGG = new ArrayList<>();
        String sql = "SELECT * FROM GiamGia where IDGiamGia like ?";
        try (Connection con = jdbc.getConnection(); PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setString(1, "%" + str + "%");
            try (ResultSet res = pre.executeQuery()) {
                while (res.next()) {
                    GiamGia gg = new GiamGia();
                    gg.setIDGIamGia(res.getString("IDGiamGia"));
                    gg.setTenMaGiamGia(res.getString("TenMaGiamGia"));
                    gg.setTyLeGiamGia(res.getFloat("TyLeGiamGia"));
                    gg.setNgayBatDau(res.getDate("NgayBatDau"));
                    gg.setNgayKetThuc(res.getDate("NgayKetThuc"));
                    gg.setTrangThai(res.getBoolean("TrangThai"));
                    listGG.add(gg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

        return listGG;
    }

    @Override
    public List<GiamGia> SearchGiamGiaGG(GiaoDienKhuyenMaiModel gdkmm) {
        StringBuilder spBuilder = new StringBuilder("select * from GiamGia where 1 = 1");
        List<Object> param = new ArrayList<>();
        List<GiamGia> lstgg = new ArrayList<>();
        int defaultIntValue = 0;
        if (gdkmm.getMaGiamGiaGiamGia() != null && !gdkmm.getMaGiamGiaGiamGia().isEmpty()) {
            spBuilder.append(" and IDGiamGia Like ?");
            param.add("%" + gdkmm.getMaGiamGiaGiamGia() + "%");
        }
        if (gdkmm.getTrangThai() != defaultIntValue) {
            spBuilder.append(" and TrangThai = ?");
            param.add(gdkmm.getTrangThai() == 1);
        }
        String sql = spBuilder.toString();
        try (Connection con = jdbc.getConnection(); PreparedStatement pre = con.prepareStatement(sql)) {

            for (int i = 0; i < param.size(); i++) {
                pre.setObject(i + 1, param.get(i));

            }
            try (ResultSet res = pre.executeQuery()) {
                while (res.next()) {
                    GiamGia gg = new GiamGia();
                    gg.setIDGIamGia(res.getString("IDGiamGia"));
                    gg.setTenMaGiamGia(res.getString("TenMaGiamGia"));
                    gg.setTyLeGiamGia(res.getFloat("TyLeGiamGia"));
                    gg.setNgayBatDau(res.getDate("NgayBatDau"));
                    gg.setNgayKetThuc(res.getDate("NgayKetThuc"));
                    gg.setTrangThai(res.getBoolean("TrangThai"));
                    lstgg.add(gg);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
        return lstgg;
    }

    @Override
    public List<SanPham> SearchGiamGiaSP(GiaoDienKhuyenMaiModel gdkmm) {
        StringBuilder spBuilder = new StringBuilder("select * from v_Sp_GG where 1 = 1");
        List<Object> param = new ArrayList<>();
        List<SanPham> lstspgg = new ArrayList<>();
        if (gdkmm.getMaGiamGiaGiamGia() != null && !gdkmm.getMaGiamGiaGiamGia().isEmpty()) {
            spBuilder.append(" and IDGiamGia like ?");
            param.add("%" + gdkmm.getMaGiamGiaGiamGia() + "%");
        }
        if (gdkmm.getLoaiTrangSuc() != null && !gdkmm.getLoaiTrangSuc().isEmpty()) {
            spBuilder.append(" and TenPhanLoai like ?");
            param.add("%" + gdkmm.getLoaiTrangSuc() + "%");
        }
        String sql = spBuilder.toString();
        try (Connection con = jdbc.getConnection(); PreparedStatement pre = con.prepareStatement(sql)) {

            for (int i = 0; i < param.size(); i++) {
                pre.setObject(i + 1, param.get(i));

            }
            try (ResultSet res = pre.executeQuery()) {
                while (res.next()) {
                    PhanLoai pl = new PhanLoai();
                    pl.setIDPhanLoai(res.getString("IDPhanLoai"));
                    pl.setTenPhanLoai(res.getString("TenPhanLoai"));

                    KiemDinh kd = new KiemDinh();
                    kd.setIDKiemDinh(res.getString("IDKiemDinh"));

                    MauSac ms = new MauSac();
                    ms.setIDMauSac(res.getString("IDMauSac"));

                    Size sz = new Size();
                    sz.setIDSize(res.getString("IDSize"));

                    ChatLieu cl = new ChatLieu();
                    cl.setIDChatLieu(res.getString("IDChatLieu"));

                    NhaCungCap ncc = new NhaCungCap();
                    ncc.setIDNhaCungCap(res.getString("IDNhaCungCap"));

                    GiamGia gg = new GiamGia();
                    gg.setIDGIamGia(res.getString("IDGiamGia"));
                    gg.setTenMaGiamGia(res.getString("TenMaGiamGia"));
                    gg.setTyLeGiamGia(res.getFloat("TyLeGiamGia"));
                    gg.setNgayBatDau(res.getDate("NgayBatDau"));
                    gg.setNgayKetThuc(res.getDate("NgayKetThuc"));
                    DaQuy dq = new DaQuy();
                    dq.setIDDaQuy(res.getString("IDDaQuy"));

                    SanPham sp = new SanPham(
                            res.getString("IDSanPham"),
                            pl,
                            kd,
                            ms,
                            sz,
                            res.getString("TenSanPham"),
                            res.getBoolean("GioiTinh"),
                            cl,
                            res.getInt("SoLuongTonKho"),
                            res.getDouble("GiaChiTiet"),
                            gg,
                            ncc,
                            res.getInt("SoLuongDaQuy"),
                            res.getFloat("KichThuocDa"),
                            res.getFloat("TrongLuong"),
                            res.getString("HinhAnhSanPham"),
                            dq,
                            res.getDate("NgayTao"),
                            res.getDate("NgaySua"),
                            true
                    );

                    lstspgg.add(sp);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
        return lstspgg;
    }

    @Override
    public List<SanPham> fillTableGiamGiaSP() {
        String sql = "SELECT * FROM v_Sp_GG";
        List<SanPham> lstsp = new ArrayList<>();
        try (Connection con = jdbc.getConnection(); PreparedStatement pre = con.prepareStatement(sql); ResultSet res = pre.executeQuery()) {

            while (res.next()) {
                PhanLoai pl = new PhanLoai();
                pl.setIDPhanLoai(res.getString("IDPhanLoai"));
                pl.setTenPhanLoai(res.getString("TenPhanLoai"));

                KiemDinh kd = new KiemDinh();
                kd.setIDKiemDinh(res.getString("IDKiemDinh"));

                MauSac ms = new MauSac();
                ms.setIDMauSac(res.getString("IDMauSac"));

                Size sz = new Size();
                sz.setIDSize(res.getString("IDSize"));

                ChatLieu cl = new ChatLieu();
                cl.setIDChatLieu(res.getString("IDChatLieu"));

                NhaCungCap ncc = new NhaCungCap();
                ncc.setIDNhaCungCap(res.getString("IDNhaCungCap"));

                GiamGia gg = new GiamGia();
                gg.setIDGIamGia(res.getString("IDGiamGia"));
                gg.setTyLeGiamGia(res.getFloat("TyLeGiamGia"));
                gg.setNgayBatDau(res.getDate("NgayBatDau"));
                gg.setNgayKetThuc(res.getDate("NgayKetThuc"));

                DaQuy dq = new DaQuy();
                dq.setIDDaQuy(res.getString("IDDaQuy"));

                // Chuyển đổi giá trị TrangThai từ Integer sang Boolean nếu cần thiết
                boolean trangThai = res.getBoolean("TrangThai");

                SanPham sp = new SanPham(
                        res.getString("IDSanPham"),
                        pl,
                        kd,
                        ms,
                        sz,
                        res.getString("TenSanPham"),
                        res.getBoolean("GioiTinh"),
                        cl,
                        res.getInt("SoLuongTonKho"),
                        res.getDouble("GiaChiTiet"),
                        gg,
                        ncc,
                        res.getInt("SoLuongDaQuy"),
                        res.getFloat("KichThuocDa"),
                        res.getFloat("TrongLuong"),
                        res.getString("HinhAnhSanPham"),
                        dq,
                        res.getDate("NgayTao"),
                        res.getDate("NgaySua"),
                        trangThai // Đảm bảo rằng giá trị này là boolean
                );

                lstsp.add(sp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

        return lstsp;
    }

    @Override
    public boolean addGiamGia(GiamGia gg) {
        String sql = "{call ThemGiamGia(?, ?, ?, ?, ?)}";
        try (Connection con = jdbc.getConnection(); CallableStatement cstmt = con.prepareCall(sql)) {
            cstmt.setString(1, gg.getTenMaGiamGia());
            cstmt.setFloat(2, gg.getTyLeGiamGia());
            cstmt.setDate(3, new java.sql.Date(gg.getNgayBatDau().getTime()));
            cstmt.setDate(4, new java.sql.Date(gg.getNgayKetThuc().getTime()));
            cstmt.setBoolean(5, gg.isTrangThai());

            int rowsAffected = cstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateGiamGia(GiamGia gg, String ID) {
        String sql = "UPDATE [dbo].[GiamGia] "
                + "SET [TenMaGiamGia] = ?, "
                + "[TyLeGiamGia] = ?, "
                + "[NgayBatDau] = ?, "
                + "[NgayKetThuc] = ?, "
                + "[TrangThai] = ? "
                + "WHERE [IDGiamGia] = ?";

        try (Connection con = jdbc.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, gg.getTenMaGiamGia());
            pstmt.setFloat(2, gg.getTyLeGiamGia());
            pstmt.setDate(3, new java.sql.Date(gg.getNgayBatDau().getTime()));
            pstmt.setDate(4, new java.sql.Date(gg.getNgayKetThuc().getTime()));
            pstmt.setBoolean(5, gg.isTrangThai()); // TrangThai
            pstmt.setString(6, ID); // IDGiamGia

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<SanPham> SearchTSP(ThemGGModel tggm) {
        StringBuilder spBuilder = new StringBuilder("SELECT * FROM v_SanPham_PhanLoai where 1 = 1");
        List<Object> param = new ArrayList<>();
        List<SanPham> lstspgg = new ArrayList<>();
        if (tggm.getMaTrangSuc() != null && !tggm.getMaTrangSuc().isEmpty()) {
            spBuilder.append(" and IDSanPham like ?");
            param.add("%" + tggm.getMaTrangSuc() + "%");
        }
        if (tggm.getLoaiTrangSuc() != null && !tggm.getLoaiTrangSuc().isEmpty()) {
            spBuilder.append(" and TenPhanLoai like ?");
            param.add("%" + tggm.getLoaiTrangSuc() + "%");
        }
        String sql = spBuilder.toString();
        try (Connection con = jdbc.getConnection(); PreparedStatement pre = con.prepareStatement(sql)) {

            for (int i = 0; i < param.size(); i++) {
                pre.setObject(i + 1, param.get(i));

            }
            try (ResultSet res = pre.executeQuery()) {
                while (res.next()) {
                    PhanLoai pl = new PhanLoai();
                    pl.setIDPhanLoai(res.getString("IDPhanLoai"));
                    pl.setTenPhanLoai(res.getString("TenPhanLoai"));

                    KiemDinh kd = new KiemDinh();
                    kd.setIDKiemDinh(res.getString("IDKiemDinh"));

                    MauSac ms = new MauSac();
                    ms.setIDMauSac(res.getString("IDMauSac"));

                    Size sz = new Size();
                    sz.setIDSize(res.getString("IDSize"));

                    ChatLieu cl = new ChatLieu();
                    cl.setIDChatLieu(res.getString("IDChatLieu"));

                    NhaCungCap ncc = new NhaCungCap();
                    ncc.setIDNhaCungCap(res.getString("IDNhaCungCap"));

                    GiamGia gg = new GiamGia();
                    gg.setIDGIamGia(res.getString("IDGiamGia"));
                    gg.setTenMaGiamGia(res.getString("TenMaGiamGia"));
                    gg.setTyLeGiamGia(res.getFloat("TyLeGiamGia"));

                    DaQuy dq = new DaQuy();
                    dq.setIDDaQuy(res.getString("IDDaQuy"));

                    SanPham sp = new SanPham(
                            res.getString("IDSanPham"),
                            pl,
                            kd,
                            ms,
                            sz,
                            res.getString("TenSanPham"),
                            res.getBoolean("GioiTinh"),
                            cl,
                            res.getInt("SoLuongTonKho"),
                            res.getDouble("GiaChiTiet"),
                            gg,
                            ncc,
                            res.getInt("SoLuongDaQuy"),
                            res.getFloat("KichThuocDa"),
                            res.getFloat("TrongLuong"),
                            res.getString("HinhAnhSanPham"),
                            dq,
                            res.getDate("NgayTao"),
                            res.getDate("NgaySua"),
                            true
                    );

                    lstspgg.add(sp);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
        return lstspgg;
    }

    @Override
    public boolean updateIDGiamGiaForMultipleProducts(List<String> idSanPhams, String idGiamGia) {
        String sql = "UPDATE [dbo].[SanPham] SET [IDGiamGia] = ? WHERE [IDSanPham] = ?";
        try (Connection con = jdbc.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            con.setAutoCommit(false);

            for (String idSanPham : idSanPhams) {
                pstmt.setString(1, idGiamGia);
                pstmt.setString(2, idSanPham);
                pstmt.addBatch();
            }

            int[] rowsAffected = pstmt.executeBatch();
            con.commit();
            for (int i : rowsAffected) {
                if (i == 0) {
                    return false;
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateNullIDGiamGiaForMultipleProducts(List<String> idSanPhams) {
        String sql = "UPDATE [dbo].[SanPham] SET [IDGiamGia] = NULL WHERE [IDSanPham] = ?";
        try (Connection con = jdbc.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            con.setAutoCommit(false);

            for (String idSanPham : idSanPhams) {
                pstmt.setString(1, idSanPham);
                System.out.println(idSanPham);
                pstmt.addBatch();
            }

            int[] rowsAffected = pstmt.executeBatch();
            con.commit();

            for (int i : rowsAffected) {
                if (i == 0) {
                    // Nếu không có hàng nào được cập nhật, trả về false
                    return false;
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
