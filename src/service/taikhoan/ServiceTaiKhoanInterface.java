/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.taikhoan;

import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import model.GiaoDien.GiaoDienNhanVienModel;
import model.TaiKhoan;

/**
 *
 * @author HUNGpYN
 */
public interface ServiceTaiKhoanInterface {
    public void fillToTableCheck(JTable tbl, GiaoDienNhanVienModel gdnvm);
    public void fillToTable(JTable tbl);
    public void doubleCicled(JTable tbl);
    public void chonAnh(JLabel label);
}
