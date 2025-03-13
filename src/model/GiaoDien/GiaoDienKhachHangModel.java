/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.GiaoDien;

/**
 *
 * @author WINDOWS10
 */
public class GiaoDienKhachHangModel {
    private String HoTen ;
    private int TrangThai;

    public GiaoDienKhachHangModel() {
    }

    public GiaoDienKhachHangModel(String HoTen, int TrangThai) {
        this.HoTen = HoTen;
        this.TrangThai = TrangThai;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }
    
}
