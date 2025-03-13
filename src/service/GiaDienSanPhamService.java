/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import java.util.List;
import model.PhanLoai;
import repository.PhanLoai.PhanLoaiRepo;

/**
 *
 * @author WINDOWS10
 */
public class GiaDienSanPhamService implements GiaoDienSanPhamServiceInterface{
    private List<PhanLoai> lstpl = new ArrayList<>();
    private PhanLoaiRepo rppl = new PhanLoaiRepo();
    @Override
    public List<String> fillTocbo() {
        lstpl = rppl.getAll();
        List<String> tenPhanLoais = new ArrayList<>();
        for (PhanLoai pl : lstpl) {
            tenPhanLoais.add(pl.getTenPhanLoai());
        }
        return tenPhanLoais;
    }
    
}
