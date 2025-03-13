/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class GiamGia {
    private String IDGIamGia;
    private String TenMaGiamGia;
    private float TyLeGiamGia;
    private Date NgayBatDau;
    private Date NgayKetThuc;
    private Date NgaySua;
    private boolean TrangThai;

    public GiamGia() {
    }

    public GiamGia(String IDGIamGia, String TenMaGiamGia, float TyLeGiamGia, Date NgayBatDau, Date NgayKetThuc, Date NgaySua, boolean TrangThai) {
        this.IDGIamGia = IDGIamGia;
        this.TenMaGiamGia = TenMaGiamGia;
        this.TyLeGiamGia = TyLeGiamGia;
        this.NgayBatDau = NgayBatDau;
        this.NgayKetThuc = NgayKetThuc;
        this.NgaySua = NgaySua;
        this.TrangThai = TrangThai;
    }

    public String getIDGIamGia() {
        return IDGIamGia;
    }

    public void setIDGIamGia(String IDGIamGia) {
        this.IDGIamGia = IDGIamGia;
    }

    public String getTenMaGiamGia() {
        return TenMaGiamGia;
    }

    public void setTenMaGiamGia(String TenMaGiamGia) {
        this.TenMaGiamGia = TenMaGiamGia;
    }

    public float getTyLeGiamGia() {
        return TyLeGiamGia;
    }

    public void setTyLeGiamGia(float TyLeGiamGia) {
        this.TyLeGiamGia = TyLeGiamGia;
    }

    public Date getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(Date NgayBatDau) {
        this.NgayBatDau = NgayBatDau;
    }

    public Date getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(Date NgayKetThuc) {
        this.NgayKetThuc = NgayKetThuc;
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
