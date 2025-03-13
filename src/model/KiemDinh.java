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
public class KiemDinh {
    private String IDKiemDinh;
    private String DonViKiemDinh;
    private Date NgayKiemDinh;
    private Date NgaySua;
    private Date NgayTao;
    private boolean TrangThai;

    public KiemDinh() {
    }

    public KiemDinh(String IDKiemDinh, String DonViKiemDinh, Date NgayKiemDinh, Date NgaySua, Date NgayTao, boolean TrangThai) {
        this.IDKiemDinh = IDKiemDinh;
        this.DonViKiemDinh = DonViKiemDinh;
        this.NgayKiemDinh = NgayKiemDinh;
        this.NgaySua = NgaySua;
        this.NgayTao = NgayTao;
        this.TrangThai = TrangThai;
    }

    public String getIDKiemDinh() {
        return IDKiemDinh;
    }

    public void setIDKiemDinh(String IDKiemDinh) {
        this.IDKiemDinh = IDKiemDinh;
    }

    public String getDonViKiemDinh() {
        return DonViKiemDinh;
    }

    public void setDonViKiemDinh(String DonViKiemDinh) {
        this.DonViKiemDinh = DonViKiemDinh;
    }

    public Date getNgayKiemDinh() {
        return NgayKiemDinh;
    }

    public void setNgayKiemDinh(Date NgayKiemDinh) {
        this.NgayKiemDinh = NgayKiemDinh;
    }

    public Date getNgaySua() {
        return NgaySua;
    }

    public void setNgaySua(Date NgaySua) {
        this.NgaySua = NgaySua;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }
    
    
}
