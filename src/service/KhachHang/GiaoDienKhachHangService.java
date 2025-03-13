/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.KhachHang;

import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.GiaoDien.GiaoDienKhachHangModel;
import model.KhachHang;
import repository.KhachHang.repoKhachHang;

/**
 *
 * @author nguyentrikhoi
 */
public class GiaoDienKhachHangService implements GiaoDienKhachHangServiceInterface {

    private DefaultTableModel model;
    private repository.KhachHang.repoKhachHang rpKH = new repoKhachHang();
  

    @Override
    public void fillToTable(JTable tbl) {
        model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        int STT = 1;
        for (KhachHang kh : rpKH.getAll()) {
            model.addRow(new Object[]{STT++,kh.getIDKhachHang(), kh.getHoTen(), kh.getSoDienThoai(), kh.getEmail(), kh.getDiaChi(), kh.getTichDiem(), kh.isTrangThai()?"Hoạt Động" : "Ngừng Hoạt Động"});
        }

    }
    @Override
    public void fillToUpdate(JTextField txtMaKH,JTextField txtTen,JTextField txtTichDiem,JTextField txtSDT,JTextField txtEmail,JTextArea DiaChir,JRadioButton rdoHoatDong ,JRadioButton NHoatDong ,String str) {
        for (KhachHang kh : rpKH.getAllByID(str)) {
           txtMaKH.setText(kh.getIDKhachHang());
           txtTen.setText(kh.getHoTen());
           txtTichDiem.setText(String.valueOf(kh.getTichDiem()));
           txtEmail.setText(kh.getEmail());
           DiaChir.setText(kh.getDiaChi());
           txtSDT.setText(kh.getSoDienThoai());
            if (kh.isTrangThai()) {
                rdoHoatDong.setSelected(true);
            }else{
                rdoHoatDong.setSelected(true);
            }           
        }

    }

    @Override
    public void themKhachHang(KhachHang kh) {
            rpKH.creat(kh);
    }

    @Override
    public void fillToCheck(JTable tbl, GiaoDienKhachHangModel gdmd) {
        model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        for (KhachHang kh : rpKH.getAllWithConditional(gdmd)) {
            model.addRow(new Object[]{kh.getIDKhachHang(), kh.getHoTen(), kh.getSoDienThoai(), kh.getEmail(), kh.getDiaChi(), kh.getTichDiem(), kh.isTrangThai()?"Hoạt Động" : "Ngừng Hoạt Động"});
        }
    }
    @Override
    public void update(JTextField txtMaKH,JTextField txtTen,JTextField txtSDT,JTextField txtEmail,JTextArea DiaChir,JRadioButton rdoHoatDong){
           KhachHang kh = new KhachHang();
           kh.setDiaChi(DiaChir.getText().trim());
           kh.setEmail(txtEmail.getText().trim());
           kh.setHoTen(txtTen.getText().trim());
           kh.setSoDienThoai(txtSDT.getText().trim());
           kh.setTrangThai(rdoHoatDong.isSelected()?true:false);
           kh.setIDKhachHang(txtMaKH.getText().trim());
           rpKH.update(kh);
    }
}
