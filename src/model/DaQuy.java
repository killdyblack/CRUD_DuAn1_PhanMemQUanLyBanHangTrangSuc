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
public class DaQuy {
    private String IDDaQuy;
    private String TenDaQuy;
    private Date NgayTao;
    private Date NgaySua;
    private boolean TrangThai;

    public DaQuy() {
    }

    public DaQuy(String IDDaQuy, String TenDaQuy, Date NgayTao, Date NgaySua, boolean TrangThai) {
        this.IDDaQuy = IDDaQuy;
        this.TenDaQuy = TenDaQuy;
        this.NgayTao = NgayTao;
        this.NgaySua = NgaySua;
        this.TrangThai = TrangThai;
    }

    public String getIDDaQuy() {
        return IDDaQuy;
    }

    public void setIDDaQuy(String IDDaQuy) {
        this.IDDaQuy = IDDaQuy;
    }

    public String getTenDaQuy() {
        return TenDaQuy;
    }

    public void setTenDaQuy(String TenDaQuy) {
        this.TenDaQuy = TenDaQuy;
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
