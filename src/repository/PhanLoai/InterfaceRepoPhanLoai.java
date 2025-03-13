/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.PhanLoai;

import java.util.List;
import model.PhanLoai;

/**
 *
 * @author WINDOWS10
 */
public interface InterfaceRepoPhanLoai {
    public List<PhanLoai> getAll();
    public boolean updatePhanLoai(PhanLoai phanLoai);
    public boolean addPhanLoai(PhanLoai pl);
}
