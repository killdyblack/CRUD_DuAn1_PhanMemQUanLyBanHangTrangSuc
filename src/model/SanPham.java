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
public class SanPham {
    private String IDSanPham;
    private PhanLoai IDPhanLoai ;
    private KiemDinh IDKIemDinh;
    private MauSac IDMauSac;
    private Size IDSize;
    private String TenSanPham;
    private boolean GioiTinh;
    private ChatLieu IDChatLieu;
    private int SoLuongTonKho;
    private double GiaChiTiet;
    private GiamGia IDGiamGia;
    private NhaCungCap IDNhaCungCap;
    private int SoLuongDaQuy;
    private float KichThuocDa;
    private float TrongLuong ;
    private String HinhAnhSanPham ;
    private DaQuy IDDaQuy;
    private Date NgaySua;
    private Date NgayTao;
    private boolean TrangThai;

    public SanPham() {
    }

    public SanPham(String IDSanPham, PhanLoai IDPhanLoai, KiemDinh IDKIemDinh, MauSac IDMauSac, Size IDSize, String TenSanPham, boolean GioiTinh, ChatLieu IDChatLieu, int SoLuongTonKho, Double GiaChiTiet, GiamGia IDGiamGia, NhaCungCap IDNhaCungCap, int SoLuongDaQuy, float KichThuocDa, float TrongLuong, String HinhAnhSanPham, DaQuy IDDaQuy, Date NgaySua, Date NgayTao, boolean TrangThai) {
        this.IDSanPham = IDSanPham;
        this.IDPhanLoai = IDPhanLoai;
        this.IDKIemDinh = IDKIemDinh;
        this.IDMauSac = IDMauSac;
        this.IDSize = IDSize;
        this.TenSanPham = TenSanPham;
        this.GioiTinh = GioiTinh;
        this.IDChatLieu = IDChatLieu;
        this.SoLuongTonKho = SoLuongTonKho;
        this.GiaChiTiet = GiaChiTiet;
        this.IDGiamGia = IDGiamGia;
        this.IDNhaCungCap = IDNhaCungCap;
        this.SoLuongDaQuy = SoLuongDaQuy;
        this.KichThuocDa = KichThuocDa;
        this.TrongLuong = TrongLuong;
        this.HinhAnhSanPham = HinhAnhSanPham;
        this.IDDaQuy = IDDaQuy;
        this.NgaySua = NgaySua;
        this.NgayTao = NgayTao;
        this.TrangThai = TrangThai;
    }
    

    public SanPham(String IDSanPham, int SoLuongTonKho, double GiaChiTiet, GiamGia IDGiamGia, String HinhAnhSanPham) {
        this.IDSanPham = IDSanPham;
        this.SoLuongTonKho = SoLuongTonKho;
        this.GiaChiTiet = GiaChiTiet;
        this.IDGiamGia = IDGiamGia;
        this.HinhAnhSanPham = HinhAnhSanPham;
    }

    public SanPham(String IDSanPham, String TenSanPham, int SoLuongTonKho, double GiaChiTiet, GiamGia IDGiamGia, String HinhAnhSanPham) {
        this.IDSanPham = IDSanPham;
        this.TenSanPham = TenSanPham;
        this.SoLuongTonKho = SoLuongTonKho;
        this.GiaChiTiet = GiaChiTiet;
        this.IDGiamGia = IDGiamGia;
        this.HinhAnhSanPham = HinhAnhSanPham;
    }
    
    

    public String getIDSanPham() {
        return IDSanPham;
    }

    public void setIDSanPham(String IDSanPham) {
        this.IDSanPham = IDSanPham;
    }

    public PhanLoai getIDPhanLoai() {
        return IDPhanLoai;
    }

    public void setIDPhanLoai(PhanLoai IDPhanLoai) {
        this.IDPhanLoai = IDPhanLoai;
    }

    public KiemDinh getIDKIemDinh() {
        return IDKIemDinh;
    }

    public void setIDKIemDinh(KiemDinh IDKIemDinh) {
        this.IDKIemDinh = IDKIemDinh;
    }

    public MauSac getIDMauSac() {
        return IDMauSac;
    }

    public void setIDMauSac(MauSac IDMauSac) {
        this.IDMauSac = IDMauSac;
    }

    public Size getIDSize() {
        return IDSize;
    }

    public void setIDSize(Size IDSize) {
        this.IDSize = IDSize;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String TenSanPham) {
        this.TenSanPham = TenSanPham;
    }

    public boolean isGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(boolean GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public ChatLieu getIDChatLieu() {
        return IDChatLieu;
    }

    public void setIDChatLieu(ChatLieu IDChatLieu) {
        this.IDChatLieu = IDChatLieu;
    }

    public int getSoLuongTonKho() {
        return SoLuongTonKho;
    }

    public void setSoLuongTonKho(int SoLuongTonKho) {
        this.SoLuongTonKho = SoLuongTonKho;
    }

    public double getGiaChiTiet() {
        return GiaChiTiet;
    }

    public void setGiaChiTiet(double GiaChiTiet) {
        this.GiaChiTiet = GiaChiTiet;
    }

    public GiamGia getIDGiamGia() {
        return IDGiamGia;
    }

    public void setIDGiamGia(GiamGia IDGiamGia) {
        this.IDGiamGia = IDGiamGia;
    }

    public NhaCungCap getIDNhaCungCap() {
        return IDNhaCungCap;
    }

    public void setIDNhaCungCap(NhaCungCap IDNhaCungCap) {
        this.IDNhaCungCap = IDNhaCungCap;
    }

    public int getSoLuongDaQuy() {
        return SoLuongDaQuy;
    }

    public void setSoLuongDaQuy(int SoLuongDaQuy) {
        this.SoLuongDaQuy = SoLuongDaQuy;
    }

    public float getKichThuocDa() {
        return KichThuocDa;
    }

    public void setKichThuocDa(float KichThuocDa) {
        this.KichThuocDa = KichThuocDa;
    }

    public float getTrongLuong() {
        return TrongLuong;
    }

    public void setTrongLuong(float TrongLuong) {
        this.TrongLuong = TrongLuong;
    }

    public String getHinhAnhSanPham() {
        return HinhAnhSanPham;
    }

    public void setHinhAnhSanPham(String HinhAnhSanPham) {
        this.HinhAnhSanPham = HinhAnhSanPham;
    }

    public DaQuy getIDDaQuy() {
        return IDDaQuy;
    }

    public void setIDDaQuy(DaQuy IDDaQuy) {
        this.IDDaQuy = IDDaQuy;
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
