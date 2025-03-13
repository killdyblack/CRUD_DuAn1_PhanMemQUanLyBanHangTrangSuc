/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.SanPham;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import model.ChatLieu;
import model.DaQuy;
import model.GiamGia;
import model.GiaoDien.GiaoDienSanPhamModel;
import model.KiemDinh;
import model.MauSac;
import model.NhaCungCap;
import model.PhanLoai;
import model.SanPham;
import model.Size;
import until.jdbc;

/**
 *
 * @author WINDOWS10
 */
public class repoChiTietSanPham implements InterfaceRepoChiTietSanPham {

    private List<SanPham> listsp = new ArrayList<>();
    private Connection con = null;
    private PreparedStatement pre = null;
    private ResultSet res = null;

    @Override
    public List<SanPham> getAll() {
        List<SanPham> listctsp = new ArrayList<>();
        String sql = "SELECT * FROM v_SanPham_PhanLoai";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            res = pre.executeQuery();
            while (res.next()) {
                // Giả sử view v_SanPham_PhanLoai có các cột giống như bảng SanPham
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
                String idGiamGia = res.getString("IDGiamGia");
                if (res.wasNull()) {
                    idGiamGia = null;
                }
                gg.setIDGIamGia(idGiamGia);

                Float tyLeGiamGia = res.getFloat("TyLeGiamGia");
                if (res.wasNull()) {
                    tyLeGiamGia = 0.0f;
                }
                gg.setTyLeGiamGia(tyLeGiamGia);
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
                        res.getBoolean("TrangThai")
                );

                listctsp.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
        return listctsp;
    }

    @Override
    public List<SanPham> getAllWithConditional(GiaoDienSanPhamModel gdmd) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM v_SanPham_PhanLoai WHERE 1=1 ");
        List<Object> params = new ArrayList<>();
        List<SanPham> listctsp = new ArrayList<>();

        int defaultIntValue = 0;

        if (gdmd.getGioiTinh() != defaultIntValue) {
            sqlBuilder.append(" AND GioiTinh = ?");
            params.add(gdmd.getGioiTinh() == 1);
        }

        if (gdmd.getTrangThai() != defaultIntValue) {
            sqlBuilder.append(" AND TrangThai = ?");
            params.add(gdmd.getTrangThai() == 1);
        }

        if (gdmd.getLoaiTrangSuc() != null && !gdmd.getLoaiTrangSuc().isEmpty() && !"Tất Cả".equals(gdmd.getLoaiTrangSuc())) {
            sqlBuilder.append(" AND IDPhanLoai IN (SELECT IDPhanLoai FROM PhanLoai WHERE TenPhanLoai LIKE ?)");
            params.add("%" + gdmd.getLoaiTrangSuc() + "%");
        }

        if (gdmd.getTenTrangSuc() != null && !gdmd.getTenTrangSuc().isEmpty()) {
            sqlBuilder.append(" AND TenSanPham LIKE ?");
            params.add("%" + gdmd.getTenTrangSuc() + "%");
        }

        String sql = sqlBuilder.toString();
        try (Connection con = jdbc.getConnection(); PreparedStatement pre = con.prepareStatement(sql)) {

            for (int i = 0; i < params.size(); i++) {
                pre.setObject(i + 1, params.get(i));

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
                            res.getBoolean("TrangThai")
                    );
                    listctsp.add(sp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

        return listctsp;
    }

    @Override
    public int add(String idPhanLoai, String idKiemDinh, String idMauSac, String idSize,
            String tenSanPham, boolean gioiTinh, String idChatLieu, int soLuongTonKho,
            double giaChiTiet, String idGiamGia, String idNhaCungCap, int soLuongDaQuy,
            float kichThuocDa, float trongLuong, String hinhAnhSanPham, String idDaQuy,
            boolean trangThai) {
        String sql = "{call ThemSanPham(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        try (Connection con = jdbc.getConnection(); CallableStatement stmt = con.prepareCall(sql)) {

            // Set parameters for the stored procedure
            stmt.setString(1, idPhanLoai);
            stmt.setString(2, idKiemDinh);
            stmt.setString(3, idMauSac);
            stmt.setString(4, idSize);
            stmt.setString(5, tenSanPham);
            stmt.setBoolean(6, gioiTinh);
            stmt.setString(7, idChatLieu);
            stmt.setInt(8, soLuongTonKho);
            stmt.setDouble(9, giaChiTiet);
            if (idGiamGia == null || idGiamGia.trim().isEmpty()) {
                stmt.setNull(10, java.sql.Types.VARCHAR); // Sửa từ pre.setNull thành stmt.setNull
            } else {
                stmt.setString(10, idGiamGia);
            }
            stmt.setString(11, idNhaCungCap);
            stmt.setInt(12, soLuongDaQuy);
            stmt.setFloat(13, kichThuocDa);
            stmt.setFloat(14, trongLuong);
            stmt.setString(15, hinhAnhSanPham);
            stmt.setString(16, idDaQuy);
            stmt.setBoolean(17, trangThai);

            // Execute the stored procedure
            System.out.println("Product inserted successfully.");
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public SanPham getSanPhamSua(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public SanPham getChiTietSanPham(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(String idSanPham, String idPhanLoai, String idKiemDinh, String idMauSac, String idSize,
            String tenSanPham, boolean gioiTinh, String idChatLieu, int soLuongTonKho, double giaChiTiet,
            String idGiamGia, String idNhaCungCap, int soLuongDaQuy, float kichThuocDa, float trongLuong,
            String hinhAnhSanPham, String idDaQuy, boolean trangThai) {
        String sql = "UPDATE [dbo].[SanPham] "
                + "SET [IDPhanLoai] = ?, [IDKiemDinh] = ?, [IDMauSac] = ?, [IDSize] = ?, [TenSanPham] = ?, "
                + "[GioiTinh] = ?, [IDChatLieu] = ?, [SoLuongTonKho] = ?, [GiaChiTiet] = ?, [IDGiamGia] = ?, [IDNhaCungCap] = ?, "
                + "[SoLuongDaQuy] = ?, [KichThuocDa] = ?, [TrongLuong] = ?, [HinhAnhSanPham] = ?, [IDDaQuy] = ?, [TrangThai] = ? "
                + "WHERE [IDSanPham] = ?";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            pre.setString(1, idPhanLoai);
            pre.setString(2, idKiemDinh);
            pre.setString(3, idMauSac);
            pre.setString(4, idSize);
            pre.setString(5, tenSanPham);
            pre.setBoolean(6, gioiTinh);
            pre.setString(7, idChatLieu);
            pre.setInt(8, soLuongTonKho);
            pre.setDouble(9, giaChiTiet);

            // Xử lý idGiamGia nếu là null
            if (idGiamGia == null || idGiamGia.trim().isEmpty()) {
                pre.setNull(10, java.sql.Types.NULL); // Hoặc dùng java.sql.Types.NULL nếu cần
            } else {
                pre.setString(10, idGiamGia);
            }

            pre.setString(11, idNhaCungCap);
            pre.setInt(12, soLuongDaQuy);
            pre.setFloat(13, kichThuocDa);
            pre.setFloat(14, trongLuong);
            pre.setString(15, hinhAnhSanPham);
            pre.setString(16, idDaQuy);
            pre.setBoolean(17, trangThai);
            pre.setString(18, idSanPham);

            int rowsUpdated = pre.executeUpdate();
            return rowsUpdated;
        } catch (SQLException e) {
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<SanPham> getByID(String id) {
        List<SanPham> listctsp = new ArrayList<>();
        String sql = "select * from v_SanPham_all where IDSanPham like ?";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            pre.setString(1, "%" + id + "%");
            res = pre.executeQuery();

            while (res.next()) {
                PhanLoai pl = new PhanLoai();
                pl.setIDPhanLoai(res.getString("IDPhanLoai"));
                pl.setTenPhanLoai(res.getString("TenPhanLoai"));

                KiemDinh kd = new KiemDinh();
                kd.setIDKiemDinh(res.getString("IDKiemDinh"));
                kd.setNgayKiemDinh(res.getDate("NgayKiemDinh"));
                kd.setDonViKiemDinh(res.getString("DonViKiemDinh"));

                MauSac ms = new MauSac();
                ms.setIDMauSac(res.getString("IDMauSac"));
                ms.setChiTietMauSac(res.getString("ChiTietMauSac"));

                Size sz = new Size();
                sz.setIDSize(res.getString("IDSize"));
                sz.setSoSize(res.getInt("SoSize"));

                ChatLieu cl = new ChatLieu();
                cl.setIDChatLieu(res.getString("IDChatLieu"));
                cl.setTenChatLieu(res.getString("TenChatLieu"));
                cl.setTyLe(res.getFloat("TyLe"));

                NhaCungCap ncc = new NhaCungCap();
                ncc.setIDNhaCungCap(res.getString("IDNhaCungCap"));
                ncc.setTenNhaCungCap(res.getString("TenNhaCungCap"));

                GiamGia gg = new GiamGia();
                gg.setIDGIamGia(res.getString("IDGiamGia"));

                DaQuy dq = new DaQuy();
                dq.setIDDaQuy(res.getString("IDDaQuy"));
                dq.setTenDaQuy(res.getString("TenDaQuy"));

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
                        res.getBoolean("TrangThai")
                );
                listctsp.add(sp);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return listctsp;
    }

    @Override
    public int updateTonKho(SanPham sp) {
        String sql = "UPDATE SanPham SET SoLuongTonKho = ? WHERE IDSanPham LIKE ?";
        Connection con = null;
        PreparedStatement pre = null;

        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            pre.setInt(1, sp.getSoLuongTonKho());
            pre.setString(2, "%" + sp.getIDSanPham() + "%"); // Dùng LIKE để tìm kiếm phần của chuỗi
            return pre.executeUpdate();
        } catch (Exception e) {
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

}
