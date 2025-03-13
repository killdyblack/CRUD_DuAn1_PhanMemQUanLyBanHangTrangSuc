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
public class Size {
    private String IDSize;
    private int SoSize;
    private Date NgayTao;
    private Date NgaySua;
    private boolean TrangThai;

    public Size() {
    }

    public Size(String IDSize, int SoSize) {
        this.IDSize = IDSize;
        this.SoSize = SoSize;
    }

    
    
    public Size(String IDSize, int SoSize, Date NgayTao, Date NgaySua, boolean TrangThai) {
        this.IDSize = IDSize;
        this.SoSize = SoSize;
        this.NgayTao = NgayTao;
        this.NgaySua = NgaySua;
        this.TrangThai = TrangThai;
    }

    public String getIDSize() {
        return IDSize;
    }

    public void setIDSize(String IDSize) {
        this.IDSize = IDSize;
    }

    public int getSoSize() {
        return SoSize;
    }

    public void setSoSize(int SoSize) {
        this.SoSize = SoSize;
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
