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
public class BaoHanh {
    private String IDBaoHanh;
    private KhachHang IDKhachHang;
    private SanPham IDSanPham ;
    private HoaDonChiTiet IDHoaDonChiTiet;
    private Date NgayYeuCau;
    private Date NgayHenTra ;
    private String GhiChu;
    private boolean TrangThai;
    private SerialNumber IDSerialNumber;

    public BaoHanh() {
    }

    public BaoHanh(String IDBaoHanh, KhachHang IDKhachHang, SanPham IDSanPham, HoaDonChiTiet IDHoaDonChiTiet, Date NgayYeuCau, Date NgayHenTra, String GhiChu, boolean TrangThai, SerialNumber IDSerialNumber) {
        this.IDBaoHanh = IDBaoHanh;
        this.IDKhachHang = IDKhachHang;
        this.IDSanPham = IDSanPham;
        this.IDHoaDonChiTiet = IDHoaDonChiTiet;
        this.NgayYeuCau = NgayYeuCau;
        this.NgayHenTra = NgayHenTra;
        this.GhiChu = GhiChu;
        this.TrangThai = TrangThai;
        this.IDSerialNumber = IDSerialNumber;
    }

    public String getIDBaoHanh() {
        return IDBaoHanh;
    }

    public void setIDBaoHanh(String IDBaoHanh) {
        this.IDBaoHanh = IDBaoHanh;
    }

    public KhachHang getIDKhachHang() {
        return IDKhachHang;
    }

    public void setIDKhachHang(KhachHang IDKhachHang) {
        this.IDKhachHang = IDKhachHang;
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

    public Date getNgayYeuCau() {
        return NgayYeuCau;
    }

    public void setNgayYeuCau(Date NgayYeuCau) {
        this.NgayYeuCau = NgayYeuCau;
    }

    public Date getNgayHenTra() {
        return NgayHenTra;
    }

    public void setNgayHenTra(Date NgayHenTra) {
        this.NgayHenTra = NgayHenTra;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    public SerialNumber getIDSerialNumber() {
        return IDSerialNumber;
    }

    public void setIDSerialNumber(SerialNumber IDSerialNumber) {
        this.IDSerialNumber = IDSerialNumber;
    }
    
    

   
}
