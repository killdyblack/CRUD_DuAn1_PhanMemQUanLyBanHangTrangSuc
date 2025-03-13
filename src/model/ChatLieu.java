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
public class ChatLieu {
    private String IDChatLieu;
    private String TenChatLieu;
    private float TyLe;
    private Date NgayTao;
    private Date NgaySua;
    private boolean TrangThai;

    public ChatLieu() {
    }

    public ChatLieu(String IDChatLieu, String TenChatLieu, float TyLe, Date NgayTao, Date NgaySua, boolean TrangThai) {
        this.IDChatLieu = IDChatLieu;
        this.TenChatLieu = TenChatLieu;
        this.TyLe = TyLe;
        this.NgayTao = NgayTao;
        this.NgaySua = NgaySua;
        this.TrangThai = TrangThai;
    }

    public String getIDChatLieu() {
        return IDChatLieu;
    }

    public void setIDChatLieu(String IDChatLieu) {
        this.IDChatLieu = IDChatLieu;
    }

    public String getTenChatLieu() {
        return TenChatLieu;
    }

    public void setTenChatLieu(String TenChatLieu) {
        this.TenChatLieu = TenChatLieu;
    }

    public float getTyLe() {
        return TyLe;
    }

    public void setTyLe(float TyLe) {
        this.TyLe = TyLe;
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
