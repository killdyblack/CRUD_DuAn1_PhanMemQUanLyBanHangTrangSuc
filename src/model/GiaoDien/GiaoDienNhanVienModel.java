/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.GiaoDien;

/**
 *
 * @author WINDOWS10
 */
public class GiaoDienNhanVienModel {
    private String HoTen ;
    private int GioiTinh ;
    private int TrangThai;

    public GiaoDienNhanVienModel() {
    }

    public GiaoDienNhanVienModel(String HoTen, int GioiTinh, int TrangThai) {
        this.HoTen = HoTen;
        this.GioiTinh = GioiTinh;
        this.TrangThai = TrangThai;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
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
    
}
