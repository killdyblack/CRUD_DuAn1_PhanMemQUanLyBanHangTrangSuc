/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.KhuyenMai;

import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import model.GiaoDien.GiaoDienKhuyenMaiModel;
import model.GiaoDien.ThemGGModel;
import model.Voucher;
import view.until.combobox.ComboBoxSuggestion;

/**
 *
 * @author nguyentrikhoi
 */
public interface GiaoDienKhuyenMaiServiceInterface {
    public void fillToTable(JTable tbl);
    public void themVourcher(Voucher vc);
    public void suaVourcher(Voucher vc);
    public void fillToTableByID(JTable tbl,String str);
    public void fillTableGiamGia(JTable tbl);
    public void SearchGiamGiaGG(JTable tbl,GiaoDienKhuyenMaiModel gdkmm);
    public void SearchGiamGiaSp(JTable tbl,GiaoDienKhuyenMaiModel gdkmm);
    public String fillCbo(ComboBoxSuggestion cbos);
    public void fillTableGiamGiaSP(JTable tbl);
    public void fillWhenClick(JTable tbl,JTextField TenGG, JTextField TyleField, JTextField Ngaybd , JTextField NgayKt, JRadioButton btn1,JRadioButton btn2,JTextField MaGG);
    public void addNewGG(JTextField TenGG, JTextField TyleField, JTextField Ngaybd , JTextField NgayKt, JRadioButton btn1,JRadioButton btn2,JTextField MaGG);
    public void updateGG(JTextField TenGG, JTextField TyleField, JTextField Ngaybd , JTextField NgayKt, JRadioButton btn1,JRadioButton btn2,JTextField MaGG);
    public void SearchTSp(JTable tbl,ThemGGModel tmd);
    public String fillCbo2(ComboBoxSuggestion cbos);
    public List<String> checkSelectedRows(JTable table);
    public List<String> checkSelectedRowsTB1(JTable table);
}
