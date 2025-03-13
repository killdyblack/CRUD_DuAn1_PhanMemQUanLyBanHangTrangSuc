/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class Voucher {
    private String IDVoucher;
    private String TenVoucher;
    private float TyLe;
    private Date NgayBatDau;
    private Date NgayKetThuc;
    private Date NgaySua;
    private boolean TrangThai;

    public Voucher() {
    }

    public Voucher(String IDVoucher, String TenVoucher, float TyLe, Date NgayBatDau, Date NgayKetThuc, boolean TrangThai) {
        this.IDVoucher = IDVoucher;
        this.TenVoucher = TenVoucher;
        this.TyLe = TyLe;
        this.NgayBatDau = NgayBatDau;
        this.NgayKetThuc = NgayKetThuc;
        this.TrangThai = TrangThai;
    }

    
    
    

    public Voucher(String IDVoucher, float TyLe, String TenVoucher, Date NgayBatDau, Date NgayKetThuc, Date NgaySua, boolean TrangThai) {
        this.IDVoucher = IDVoucher;
        this.TyLe = TyLe;
        this.TenVoucher = TenVoucher;
        this.NgayBatDau = NgayBatDau;
        this.NgayKetThuc = NgayKetThuc;
        this.NgaySua = NgaySua;
        this.TrangThai = TrangThai;
    }

    public String getIDVoucher() {
        return IDVoucher;
    }

    public void setIDVoucher(String IDVoucher) {
        this.IDVoucher = IDVoucher;
    }

    public float getTyLe() {
        return TyLe;
    }

    public void setTyLe(float TyLe) {
        this.TyLe = TyLe;
    }

    public String getTenVoucher() {
        return TenVoucher;
    }

    public void setTenVoucher(String TenVoucher) {
        this.TenVoucher = TenVoucher;
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