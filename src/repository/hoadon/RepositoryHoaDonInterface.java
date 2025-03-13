/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.hoadon;

import java.util.List;
import model.GiaoDien.DoanhThuModel;
import model.HoaDon;
import model.TaiKhoan;

/**
 *
 * @author HUNGpYN
 */
public interface RepositoryHoaDonInterface {

    public List<HoaDon> getAll();

    public int create(String tk);

    public int delete(String text);

    public int update(HoaDon hd);

    public int updateTichDiem(String IDKhachHang, String IDHoaDon);

    //phúc
    public List<HoaDon> getAllDT();

    public List<HoaDon> getDoanhThuTheoKhoangThoiGian(DoanhThuModel dtmd);

    public List<DoanhThuModel> findByDate(DoanhThuModel dtmd);

    public List<DoanhThuModel> getDoanhThuByMonthAndYear();
}
