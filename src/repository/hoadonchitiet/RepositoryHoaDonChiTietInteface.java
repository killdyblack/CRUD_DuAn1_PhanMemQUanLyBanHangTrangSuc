/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.hoadonchitiet;

import java.util.List;
import model.HoaDonChiTiet;

/**
 *
 * @author HUNGpYN
 */
public interface RepositoryHoaDonChiTietInteface {
    public List<HoaDonChiTiet> getAll();
    public int create(HoaDonChiTiet ct);
    public int update (HoaDonChiTiet ct);
    public int delete(String text);
     public List<HoaDonChiTiet> getWithConditions(java.sql.Date startDate, java.sql.Date endDate, String idTaiKhoan);
}
