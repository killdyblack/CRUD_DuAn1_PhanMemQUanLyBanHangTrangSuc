/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.GiaoDien;

/**
 *
 * @author WINDOWS10
 */
public class GiaoDienSanPhamModel {
    private String LoaiTrangSuc;
    private int GioiTinh ;
    private int TrangThai;
    private String TenTrangSuc ;

    public GiaoDienSanPhamModel() {
    }

    public GiaoDienSanPhamModel(String LoaiTrangSuc, int GioiTinh, int TrangThai, String TenTrangSuc) {
        this.LoaiTrangSuc = LoaiTrangSuc;
        this.GioiTinh = GioiTinh;
        this.TrangThai = TrangThai;
        this.TenTrangSuc = TenTrangSuc;
    }

    public String getLoaiTrangSuc() {
        return LoaiTrangSuc;
    }

    public void setLoaiTrangSuc(String LoaiTrangSuc) {
        this.LoaiTrangSuc = LoaiTrangSuc;
    }

    public int getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(int GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getTenTrangSuc() {
        return TenTrangSuc;
    }

    public void setTenTrangSuc(String TenTrangSuc) {
        this.TenTrangSuc = TenTrangSuc;
    }

    
}
