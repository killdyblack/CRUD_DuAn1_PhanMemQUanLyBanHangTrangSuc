/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.SanPham;

import java.util.List;
import model.GiaoDien.GiaoDienSanPhamModel;
import model.SanPham;

/**
 *
 * @author WINDOWS10
 */
public interface InterfaceRepoChiTietSanPham {
    public List<SanPham> getAll();
    public List<SanPham> getAllWithConditional (GiaoDienSanPhamModel gdmd);
    public int add(String idPhanLoai, String idKiemDinh, String idMauSac, String idSize,
                                     String tenSanPham, boolean gioiTinh, String idChatLieu, int soLuongTonKho,
                                     double giaChiTiet, String idGiamGia, String idNhaCungCap, int soLuongDaQuy,
                                     float kichThuocDa, float trongLuong, String hinhAnhSanPham, String idDaQuy,
                                     boolean trangThai);
    public SanPham getSanPhamSua(String string);
    public SanPham getChiTietSanPham(String string);
    public int delete(String string);
     public int update(String idSanPham, String idPhanLoai, String idKiemDinh, String idMauSac, String idSize,
                   String tenSanPham, boolean gioiTinh, String idChatLieu, int soLuongTonKho, double giaChiTiet,
                   String idGiamGia, String idNhaCungCap, int soLuongDaQuy, float kichThuocDa, float trongLuong,
                   String hinhAnhSanPham, String idDaQuy, boolean trangThai);
    public List<SanPham> getByID(String id);
    public int updateTonKho(SanPham sp);
}
