/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.banhang;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author HUNGpYN
 */
public interface BanHangServiceInteface {

    public void addComboBox(JComboBox cbo);

    public void addVoucher(JComboBox cbo);

    public String formatToVND(double amount);

    // fill lên lịch sử bán hàng
    public void fillToTableLichSu(JTable tbl);

    public void fillHoaDonCho(JTable tbl);

    public void fillToGioHang(JTable tbl, String text);

    public void fillToTableNoReset(JTable table);

    public void deleteHoaDon(String IDHoaDon);

    public int capNhatSoLuong(String IDHoaDon, String IDSanPham, int soLuong);

    public void doubleClick(JTable tbl);
    public double formatDouble(String currencyStr);
    public void fillToCbo(JComboBox cbo);
    public void fillByCondition(JTable tbl, JTextField TuNgay, JTextField ToiNgay, String IDNhanVien) ;
}
