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
public class TaiKhoan {
    private String IDTaiKhoan;
    private String TaiKhoan;
    private String MatKhau;
    private String HoTen;
    private String DiaChi;
    private String SoDienThoai;
    private String Email;
    private Date NgaySinh;
    private String HinhAnh;
    private boolean ChucVu;
    private boolean GioiTinh;
    private Date NgayTao;
    private Date NgaySua;
    private boolean TrangThai;
   
    public TaiKhoan() {
    }

    public TaiKhoan(String TaiKhoan, String SoDienThoai, String Email) {
        this.TaiKhoan = TaiKhoan;
        this.SoDienThoai = SoDienThoai;
        this.Email = Email;
    }
    public TaiKhoan(String IDTaiKhoan, String TaiKhoan, String MatKhau, String HoTen, String DiaChi, String SoDienThoai, String Email, Date NgaySinh, String HinhAnh, boolean ChucVu, boolean GioiTinh, Date NgayTao, Date NgaySua, boolean TrangThai) {
        this.IDTaiKhoan = IDTaiKhoan;
        this.TaiKhoan = TaiKhoan;
        this.MatKhau = MatKhau;
        this.HoTen = HoTen;
        this.DiaChi = DiaChi;
        this.SoDienThoai = SoDienThoai;
        this.Email = Email;
        this.NgaySinh = NgaySinh;
        this.HinhAnh = HinhAnh;
        this.ChucVu = ChucVu;
        this.GioiTinh = GioiTinh;
        this.NgayTao = NgayTao;
        this.NgaySua = NgaySua;
        this.TrangThai = TrangThai;
    }

    public TaiKhoan(String IDTaiKhoan, String HoTen, boolean ChucVu) {
        this.IDTaiKhoan = IDTaiKhoan;
        this.HoTen = HoTen;
        this.ChucVu = ChucVu;
    }

  
    

    public String getIDTaiKhoan() {
        return IDTaiKhoan;
    }

    public void setIDTaiKhoan(String IDTaiKhoan) {
        this.IDTaiKhoan = IDTaiKhoan;
    }

    public String getTaiKhoan() {
        return TaiKhoan;
    }

    public void setTaiKhoan(String TaiKhoan) {
        this.TaiKhoan = TaiKhoan;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String HinhAnh) {
        this.HinhAnh = HinhAnh;
    }

    public boolean isChucVu() {
        return ChucVu;
    }

    public void setChucVu(boolean ChucVu) {
        this.ChucVu = ChucVu;
    }

    public boolean isGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(boolean GioiTinh) {
        this.GioiTinh = GioiTinh;
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
