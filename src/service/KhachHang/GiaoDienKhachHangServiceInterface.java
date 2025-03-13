package service.KhachHang;


import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.GiaoDien.GiaoDienKhachHangModel;
import model.KhachHang;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author nguyentrikhoi
 */
public interface GiaoDienKhachHangServiceInterface {
    public void fillToTable(JTable tbl);
    public void themKhachHang(KhachHang ThemMoiKH);
    public void fillToCheck(JTable tbl, GiaoDienKhachHangModel gdmd);
    public void fillToUpdate(JTextField txtMaKH,JTextField txtTen,JTextField txtTichDiem,JTextField txtSDT,JTextField txtEmail,JTextArea DiaChir,JRadioButton rdoHoatDong ,JRadioButton NHoatDong ,String str) ;
        public void update(JTextField txtMaKH,JTextField txtTen,JTextField txtSDT,JTextField txtEmail,JTextArea DiaChir,JRadioButton rdoHoatDong);
}
