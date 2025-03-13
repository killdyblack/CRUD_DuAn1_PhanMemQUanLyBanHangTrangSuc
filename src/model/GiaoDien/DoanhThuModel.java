/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.GiaoDien;

import java.util.Date;

/**
 *
 * @author WINDOWS10
 */
public class DoanhThuModel {
    private Date TuNgay ;
    private Date DenNgay ;
    private String HoTen ;
    private int NgayStart;
    private int ThangStart;
    private int NamStart;
    private int NgayEnd;
    private int ThangEnd;
    private int NamEnd;
    private double TongTienTruoc;
    private double TongTienSau;
    private double TongGiaGia;

    public double getTongTienTruoc() {
        return TongTienTruoc;
    }

    public void setTongTienTruoc(double TongTienTruoc) {
        this.TongTienTruoc = TongTienTruoc;
    }

    public double getTongTienSau() {
        return TongTienSau;
    }

    public void setTongTienSau(double TongTienSau) {
        this.TongTienSau = TongTienSau;
    }

    public double getTongGiaGia() {
        return TongGiaGia;
    }

    public void setTongGiaGia(double TongGiaGia) {
        this.TongGiaGia = TongGiaGia;
    }
    public int getNgayStart() {
        return NgayStart;
    }

    public void setNgayStart(int NgayStart) {
        this.NgayStart = NgayStart;
    }

    public int getThangStart() {
        return ThangStart;
    }

    public void setThangStart(int ThangStart) {
        this.ThangStart = ThangStart;
    }

    public int getNamStart() {
        return NamStart;
    }

    public void setNamStart(int NamStart) {
        this.NamStart = NamStart;
    }

    public int getNgayEnd() {
        return NgayEnd;
    }

    public void setNgayEnd(int NgayEnd) {
        this.NgayEnd = NgayEnd;
    }

    public int getThangEnd() {
        return ThangEnd;
    }

    public void setThangEnd(int ThangEnd) {
        this.ThangEnd = ThangEnd;
    }

    public int getNamEnd() {
        return NamEnd;
    }

    public void setNamEnd(int NamEnd) {
        this.NamEnd = NamEnd;
    }

    public DoanhThuModel(Date TuNgay, Date DenNgay, String HoTen) {
        this.TuNgay = TuNgay;
        this.DenNgay = DenNgay;
        this.HoTen = HoTen;
    }

    public DoanhThuModel() {
    }

    public Date getTuNgay() {
        return TuNgay;
    }

    public void setTuNgay(Date TuNgay) {
        this.TuNgay = TuNgay;
    }

    public Date getDenNgay() {
        return DenNgay;
    }

    public void setDenNgay(Date DenNgay) {
        this.DenNgay = DenNgay;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }
    
}
