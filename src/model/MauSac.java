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
public class MauSac {
    private String IDMauSac;
    private String ChiTietMauSac;
    private Date NgayTao;
    private Date NgaySua;
    private boolean TrangThai;

    public MauSac() {
    }

    public MauSac(String IDMauSac, String ChiTietMauSac, Date NgayTao, Date NgaySua, boolean TrangThai) {
        this.IDMauSac = IDMauSac;
        this.ChiTietMauSac = ChiTietMauSac;
        this.NgayTao = NgayTao;
        this.NgaySua = NgaySua;
        this.TrangThai = TrangThai;
    }

    public String getIDMauSac() {
        return IDMauSac;
    }

    public void setIDMauSac(String IDMauSac) {
        this.IDMauSac = IDMauSac;
    }

    public String getChiTietMauSac() {
        return ChiTietMauSac;
    }

    public void setChiTietMauSac(String ChiTietMauSac) {
        this.ChiTietMauSac = ChiTietMauSac;
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
