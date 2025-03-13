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
public class HoaDonChiTiet {
    private String IDHoaDonChiTiet;
    private SanPham IDSanPham;
    private HoaDon IDHoaDon;
    private int SoLUongSanPHam;
    private double GiaSanPham;
    private double GiaSauGiamGia;
    private Date ThoiHanBaoHanh ;
    private Date NgayTao;
    private Date NgaySua;
    private boolean TrangThai;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(SanPham IDSanPham, HoaDon IDHoaDon, int SoLUongSanPHam) {
        this.IDSanPham = IDSanPham;
        this.IDHoaDon = IDHoaDon;
        this.SoLUongSanPHam = SoLUongSanPHam;
    }

 

    
    public HoaDonChiTiet(String IDHoaDonChiTiet, SanPham IDSanPham, HoaDon IDHoaDon, int SoLUongSanPHam, double GiaSanPham, double GiaSauGiamGia, Date ThoiHanBaoHanh, Date NgayTao, Date NgaySua, boolean TrangThai) {
        this.IDHoaDonChiTiet = IDHoaDonChiTiet;
        this.IDSanPham = IDSanPham;
        this.IDHoaDon = IDHoaDon;
        this.SoLUongSanPHam = SoLUongSanPHam;
        this.GiaSanPham = GiaSanPham;
        this.GiaSauGiamGia = GiaSauGiamGia;
        this.ThoiHanBaoHanh = ThoiHanBaoHanh;
        this.NgayTao = NgayTao;
        this.NgaySua = NgaySua;
        this.TrangThai = TrangThai;
    }

    public String getIDHoaDonChiTiet() {
        return IDHoaDonChiTiet;
    }

    public void setIDHoaDonChiTiet(String IDHoaDonChiTiet) {
        this.IDHoaDonChiTiet = IDHoaDonChiTiet;
    }

    public SanPham getIDSanPham() {
        return IDSanPham;
    }

    public void setIDSanPham(SanPham IDSanPham) {
        this.IDSanPham = IDSanPham;
    }

    public HoaDon getIDHoaDon() {
        return IDHoaDon;
    }

    public void setIDHoaDon(HoaDon IDHoaDon) {
        this.IDHoaDon = IDHoaDon;
    }

    public int getSoLUongSanPHam() {
        return SoLUongSanPHam;
    }

    public void setSoLUongSanPHam(int SoLUongSanPHam) {
        this.SoLUongSanPHam = SoLUongSanPHam;
    }

    public double getGiaSanPham() {
        return GiaSanPham;
    }

    public void setGiaSanPham(double GiaSanPham) {
        this.GiaSanPham = GiaSanPham;
    }

    public double getGiaSauGiamGia() {
        return GiaSauGiamGia;
    }

    public void setGiaSauGiamGia(double GiaSauGiamGia) {
        this.GiaSauGiamGia = GiaSauGiamGia;
    }

    public Date getThoiHanBaoHanh() {
        return ThoiHanBaoHanh;
    }

    public void setThoiHanBaoHanh(Date ThoiHanBaoHanh) {
        this.ThoiHanBaoHanh = ThoiHanBaoHanh;
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
