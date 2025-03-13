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
public class SerialNumber {
    private String IDSerialNumber ;
    private SanPham IDSanPham ;
    private HoaDonChiTiet IDHoaDonChiTiet ;
    private Date ThoiGianBaoHanh ;
    private Boolean TrangThai ;

    public SerialNumber() {
    }

    public SerialNumber(String IDSerialNumber, SanPham IDSanPham, HoaDonChiTiet IDHoaDonChiTiet, Date ThoiGianBaoHanh, Boolean TrangThai) {
        this.IDSerialNumber = IDSerialNumber;
        this.IDSanPham = IDSanPham;
        this.IDHoaDonChiTiet = IDHoaDonChiTiet;
        this.ThoiGianBaoHanh = ThoiGianBaoHanh;
        this.TrangThai = TrangThai;
    }

    public String getIDSerialNumber() {
        return IDSerialNumber;
    }

    public void setIDSerialNumber(String IDSerialNumber) {
        this.IDSerialNumber = IDSerialNumber;
    }

    public SanPham getIDSanPham() {
        return IDSanPham;
    }

    public void setIDSanPham(SanPham IDSanPham) {
        this.IDSanPham = IDSanPham;
    }

    public HoaDonChiTiet getIDHoaDonChiTiet() {
        return IDHoaDonChiTiet;
    }

    public void setIDHoaDonChiTiet(HoaDonChiTiet IDHoaDonChiTiet) {
        this.IDHoaDonChiTiet = IDHoaDonChiTiet;
    }

    public Date getThoiGianBaoHanh() {
        return ThoiGianBaoHanh;
    }

    public void setThoiGianBaoHanh(Date ThoiGianBaoHanh) {
        this.ThoiGianBaoHanh = ThoiGianBaoHanh;
    }

    public Boolean getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(Boolean TrangThai) {
        this.TrangThai = TrangThai;
    }
    
}
