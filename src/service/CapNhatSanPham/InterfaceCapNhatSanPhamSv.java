/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.CapNhatSanPham;

import java.util.List;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.ChatLieu;
import model.DaQuy;
import model.KiemDinh;
import model.MauSac;
import model.NhaCungCap;
import model.PhanLoai;
import model.Size;
import view.until.combobox.ComboBoxSuggestion;

/**
 *
 * @author WINDOWS10
 */
public interface InterfaceCapNhatSanPhamSv {

    String addCboKiemDinh(ComboBoxSuggestion cbos);

    String addCboSize(ComboBoxSuggestion cbos);

    String addCboPhanLoai(ComboBoxSuggestion cbos);

    String addCboChatLieu(ComboBoxSuggestion cbos);

    String addCboMauSac(ComboBoxSuggestion cbos);

    String addCboLoaiDa(ComboBoxSuggestion cbos);

    String addCboNhaCungCap(ComboBoxSuggestion cbos);

    boolean checkCbo(ComboBoxSuggestion cbos, String str);


    String checkCboKiemDinh(ComboBoxSuggestion cbos);

    String checkCboSize(ComboBoxSuggestion cbos);

    String checkCboPhanLoai(ComboBoxSuggestion cbos);

    String checkCboChatLieu(ComboBoxSuggestion cbos);

    String checkCboMauSac(ComboBoxSuggestion cbos);

    String checkCboLoaiDa(ComboBoxSuggestion cbos);

    String checkCboNhaCungCap(ComboBoxSuggestion cbos);

    public void fillToPhanLoaiTbl(JTable tbl);

    public void fillToKiemDinhTbl(JTable tbl);

    public void fillToChatLieuTbl(JTable tbl);

    public void fillToSizeTbl(JTable tbl);

    public void fillToNhaCungCapTbl(JTable tbl);

    public void fillToMauSacTbl(JTable tbl);

    public void fillToDaQuyTbl(JTable tbl);
    
    public void getDataPhanLoai(JTable tbl, JTextField txtID,JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2);

    public void getDataKiemDinh(JTable tbl, JTextField txtID,JTextField txtTen,JTextField txtNgayKiemDinh, JRadioButton rdo1, JRadioButton rdo2);
   
    public void getDataMauSac(JTable tbl, JTextField txtID,JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2);
   
    public void getDataNhaCungCap(JTable tbl, JTextField txtID,JTextField txtTen,JTextField txtEmail,JTextField txtSDT,JTextArea txtDiaChi, JRadioButton rdo1, JRadioButton rdo2);
   
    public void getDataChatLieu(JTable tbl, JTextField txtID,JTextField txtTen,JTextField txtTyLe, JRadioButton rdo1, JRadioButton rdo2);
   
    public void getDataSize(JTable tbl, JTextField txtID,JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2);
   
    public void getDataDaQuy(JTable tbl, JTextField txtID,JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2);
    
    public void readFormPhanLoai(JTextField txtID,JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2);
    
    public void readFormMauSac(JTextField txtID, JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2) ;
    
    public void readFormKiemDinh(JTextField txtID, JTextField txtTen, JTextField txtNgayKiemDinh,JRadioButton rdo1, JRadioButton rdo2);
    
    public void readFormChatLieu(JTextField txtID, JTextField txtTen, JTextField txtTyle, JRadioButton rdo1, JRadioButton rdo2);
    
    public void readFormNhaCungCap(JTextField txtID, JTextField txtTen, JTextField txtEmail, JTextField txtSDT, JTextArea txtDiaChi, JRadioButton rdo1, JRadioButton rdo2) ;
    
    public void readFormSize(JTextField txtID, JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2) ;
    
    public void readFormDaQuy(JTextField txtID, JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2) ;
    
    public void clearList(String listName) ;
    
    public void InsertPhanLoai(JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2);
    
    public void InsertMauSac(JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2) ;
    
    public void InsertKiemDinh(JTextField txtTen, JTextField txtNgayKiemDinh,JRadioButton rdo1, JRadioButton rdo2);
    
    public void InsertChatLieu(JTextField txtTen, JTextField txtTyle, JRadioButton rdo1, JRadioButton rdo2);
    
    public void InsertNhaCungCap(JTextField txtTen, JTextField txtEmail, JTextField txtSDT, JTextArea txtDiaChi, JRadioButton rdo1, JRadioButton rdo2) ;
    
    public void InsertSize(JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2) ;
    
    public void InsertDaQuy(JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2) ;

}

