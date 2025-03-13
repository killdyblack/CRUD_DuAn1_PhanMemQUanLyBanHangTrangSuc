/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.GiaoDien;

/**
 *
 * @author WINDOWS10
 */
public class GiaoDienKhuyenMaiModel {
    private String MaGiamGiaGiamGia ;
    private String MaGiamGiaSanPham ;
    private String LoaiTrangSuc ;
    private int TrangThai ;

    public GiaoDienKhuyenMaiModel() {
    }

    public GiaoDienKhuyenMaiModel(String MaGiamGiaGiamGia, String MaGiamGiaSanPham, String LoaiTrangSuc, int TrangThai) {
        this.MaGiamGiaGiamGia = MaGiamGiaGiamGia;
        this.MaGiamGiaSanPham = MaGiamGiaSanPham;
        this.LoaiTrangSuc = LoaiTrangSuc;
        this.TrangThai = TrangThai;
    }

    public String getMaGiamGiaGiamGia() {
        return MaGiamGiaGiamGia;
    }

    public void setMaGiamGiaGiamGia(String MaGiamGiaGiamGia) {
        this.MaGiamGiaGiamGia = MaGiamGiaGiamGia;
    }

    public String getMaGiamGiaSanPham() {
        return MaGiamGiaSanPham;
    }

    public void setMaGiamGiaSanPham(String MaGiamGiaSanPham) {
        this.MaGiamGiaSanPham = MaGiamGiaSanPham;
    }

    public String getLoaiTrangSuc() {
        return LoaiTrangSuc;
    }

    public void setLoaiTrangSuc(String LoaiTrangSuc) {
        this.LoaiTrangSuc = LoaiTrangSuc;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }
    
}
