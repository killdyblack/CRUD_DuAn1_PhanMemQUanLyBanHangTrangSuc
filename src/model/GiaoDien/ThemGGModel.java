/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.GiaoDien;

/**
 *
 * @author WINDOWS10
 */
public class ThemGGModel {
    private String MaTrangSuc ;
    private String LoaiTrangSuc ;

    public ThemGGModel() {
    }

    public ThemGGModel(String MaTrangSuc, String LoaiTrangSuc) {
        this.MaTrangSuc = MaTrangSuc;
        this.LoaiTrangSuc = LoaiTrangSuc;
    }

    public String getMaTrangSuc() {
        return MaTrangSuc;
    }

    public void setMaTrangSuc(String MaTrangSuc) {
        this.MaTrangSuc = MaTrangSuc;
    }

    public String getLoaiTrangSuc() {
        return LoaiTrangSuc;
    }

    public void setLoaiTrangSuc(String LoaiTrangSuc) {
        this.LoaiTrangSuc = LoaiTrangSuc;
    }
    
}
