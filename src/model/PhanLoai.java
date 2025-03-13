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
public class PhanLoai {

    private String IDPhanLoai;
    private String TenPhanLoai;
    private boolean TrangThai;
    private Date NgayKetThuc;
    private Date NgaySua;

    public PhanLoai() {
    }

    public PhanLoai(String IDPhanLoai, String TenPhanLoai, boolean TrangThai, Date NgayKetThuc, Date NgaySua) {
        this.IDPhanLoai = IDPhanLoai;
        this.TenPhanLoai = TenPhanLoai;
        this.TrangThai = TrangThai;
        this.NgayKetThuc = NgayKetThuc;
        this.NgaySua = NgaySua;
    }

    public String getIDPhanLoai() {
        return IDPhanLoai;
    }

    public void setIDPhanLoai(String IDPhanLoai) {
        this.IDPhanLoai = IDPhanLoai;
    }

    public String getTenPhanLoai() {
        return TenPhanLoai;
    }

    public void setTenPhanLoai(String TenPhanLoai) {
        this.TenPhanLoai = TenPhanLoai;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
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

    

}
