/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.BaoHanh;

import java.sql.Date;
import java.util.List;
import model.BaoHanh;

/**
 *
 * @author nguyentrikhoi
 */
public interface InterfaceRepoBaoHanh {
    public List<BaoHanh> getAll();
    
    public List<BaoHanh> getSanPhamBaoHanh(String soDienThoaiBaoHanh) ;
    
    public int capNhat(BaoHanh bh);
    
    public boolean themBaoHanh(String idKhachHang,String SeriSp,String IDSanPham ,String idHoaDonChiTiet, java.sql.Date ngayYeuCau, String ghiChu, boolean trangThai);
    
    public boolean capNhatBaoHanh(String idBaoHanh, String ghiChu, boolean trangThai);
    public List<BaoHanh> getDanhSachSanPhamBaoHanhByDateAndPhone(java.sql.Date startDate, java.sql.Date endDate, String phone);
}
