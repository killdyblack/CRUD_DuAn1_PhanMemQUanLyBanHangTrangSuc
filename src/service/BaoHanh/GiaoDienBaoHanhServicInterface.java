/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.BaoHanh;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author nguyentrikhoi
 */
public interface GiaoDienBaoHanhServicInterface {
    public void fillToTable(JTable tbl);
    public void addBaoHanh(JTextField TenKH,String SeriSp,String IDSp,String IDHDCT,JTextArea GhiChu, JTextField NgayTao, JRadioButton rdo1);
    public void CapNhatBaoHanh(JTextField idBaoHanh, JTextArea GhiChuArea,JRadioButton rdo);
    public void fillByCondition(JTable tbl, JTextField TuNgay, JTextField ToiNgay,JTextField SDT) ;
}
