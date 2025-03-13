/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author WINDOWS10
 */
public class DatHang {
    private String IDDatHang ;
    private KhachHang IDKhachHang;
    private SanPham IDSanPham;
    private Date NgayYeuCau ;
    private String GhiChu ;
    private Date NgayTao ;
    private Date NgaySua ;
    private boolean TrangThai ;

    public DatHang() {
    }

    public DatHang(String IDDatHang, KhachHang IDKhachHang, SanPham IDSanPham, Date NgayYeuCau, String GhiChu, Date NgayTao, Date NgaySua, boolean TrangThai) {
        this.IDDatHang = IDDatHang;
        this.IDKhachHang = IDKhachHang;
        this.IDSanPham = IDSanPham;
        this.NgayYeuCau = NgayYeuCau;
        this.GhiChu = GhiChu;
        this.NgayTao = NgayTao;
        this.NgaySua = NgaySua;
        this.TrangThai = TrangThai;
    }

    public String getIDDatHang() {
        return IDDatHang;
    }

    public void setIDDatHang(String IDDatHang) {
        this.IDDatHang = IDDatHang;
    }

    public KhachHang getIDKhachHang() {
        return IDKhachHang;
    }

    public void setIDKhachHang(KhachHang IDKhachHang) {
        this.IDKhachHang = IDKhachHang;
    }

    public SanPham getIDSanPham() {
        return IDSanPham;
    }

    public void setIDSanPham(SanPham IDSanPham) {
        this.IDSanPham = IDSanPham;
    }

    public Date getNgayYeuCau() {
        return NgayYeuCau;
    }

    public void setNgayYeuCau(Date NgayYeuCau) {
        this.NgayYeuCau = NgayYeuCau;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }

    public Date getNgaySua() {
        return NgaySua;
    }

    public void setNgaySua(Date NgaySua) {
        this.NgaySua = NgaySua;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }
    
}
