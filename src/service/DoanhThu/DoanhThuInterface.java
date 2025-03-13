/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.DoanhThu;

import com.raven.chart.ModelChart;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import model.GiaoDien.DoanhThuModel;

/**
 *
 * @author WINDOWS10
 */
public interface DoanhThuInterface {
    public void mdChartThang();
    public void mdChartNam();
    public void fillByCondition(DoanhThuModel dtmd,JTable tbl);
    public DoanhThuModel TimKiemTheoNgay(JTextField txtNgayBatDau, JTextField txtNgayKetThuc, JComboBox cbo_NhanVien);
}
