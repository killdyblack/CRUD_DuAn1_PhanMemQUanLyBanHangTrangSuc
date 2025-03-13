/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.DoanhThu;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.GiaoDien.DoanhThuModel;
import model.HoaDon;
import repository.hoadon.RepositoryHoaDon;
import static view.form.Form_1.formatCurrency;

/**
 *
 * @author WINDOWS10
 */
public class DoanhThusv implements DoanhThuInterface {

    private RepositoryHoaDon rphd = new RepositoryHoaDon();

    @Override
    public void mdChartThang() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mdChartNam() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void fillByCondition(DoanhThuModel dtmd, JTable tbl) {
        if (dtmd == null || tbl == null) {
            JOptionPane.showMessageDialog(null, "DoanhThuModel hoặc JTable không được khởi tạo.");
            return;
        }
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        List<HoaDon> hoaDonList = rphd.getDoanhThuTheoKhoangThoiGian(dtmd);
        if (hoaDonList == null) {
            JOptionPane.showMessageDialog(null, "Không thể lấy danh sách hóa đơn.");
            return;
        }
        int i = 0;
        for (HoaDon hd : hoaDonList) {
            if (hd.getIdTaiKhoan() != null) {
                model.addRow(new Object[]{
                    i++,
                    hd.getIdTaiKhoan().getIDTaiKhoan(),
                    hd.getIdTaiKhoan().getHoTen(),
                    hd.getSoLuongDon(),
                    formatCurrency(hd.getGiamGiaSanPham()),
                    formatCurrency(hd.getTongTienSau()),
                    formatCurrency(hd.getTongTienTRuoc())
                });
            }
        }
    }

    @Override
    public DoanhThuModel TimKiemTheoNgay(JTextField txtNgayBatDau, JTextField txtNgayKetThuc, JComboBox cbo_NhanVien) {
        DoanhThuModel dtmd = new DoanhThuModel();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            LocalDate dateStart = LocalDate.parse(txtNgayBatDau.getText(), formatter);
            LocalDate dateEnd = LocalDate.parse(txtNgayKetThuc.getText(), formatter);
            if (dateEnd.isBefore(dateStart)) {
                JOptionPane.showMessageDialog(null, "Ngày kết thúc phải lớn hơn hoặc bằng ngày bắt đầu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            YearMonth yearMonth = YearMonth.of(dateStart.getYear(), dateStart.getMonthValue());
            int daysInMonth = yearMonth.lengthOfMonth();
            dtmd.setNgayStart(dateStart.getDayOfMonth());
            dtmd.setNgayEnd(daysInMonth);
            dtmd.setThangStart(dateStart.getMonthValue());
            dtmd.setThangEnd(dateStart.getMonthValue());
            dtmd.setNamStart(dateStart.getYear());
            dtmd.setNamEnd(dateStart.getYear());
            dtmd.setHoTen(cbo_NhanVien.getSelectedItem().toString());
            return dtmd;
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Ngày không hợp lệ. Vui lòng kiểm tra định dạng ngày.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return null;
        }
    }

}
