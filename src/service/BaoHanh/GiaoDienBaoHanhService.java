/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.BaoHanh;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.BaoHanh;
import model.KhachHang;
import model.SanPham;
import repository.KhachHang.repoKhachHang;

/**
 *
 * @author nguyentrikhoi
 */
public class GiaoDienBaoHanhService implements GiaoDienBaoHanhServicInterface {

    private repository.KhachHang.repoKhachHang rpKh = new repoKhachHang();
    private DefaultTableModel model;
    private repository.BaoHanh.repoBaoHanh rpBH = new repository.BaoHanh.repoBaoHanh();

    @Override
    public void fillToTable(JTable tbl) {
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        int stt = 1;
        for (BaoHanh bh : rpBH.getAll()) {
            String idBaoHanh = bh.getIDBaoHanh();
            KhachHang kh = bh.getIDKhachHang();
            String tenKhachHang = kh != null ? kh.getHoTen() : "";
            String soDienThoai = kh != null ? kh.getSoDienThoai() : "";
            String diaChi = kh != null ? kh.getDiaChi() : "";
            SanPham sp = bh.getIDSanPham();
            String tenSanPham = sp != null ? sp.getTenSanPham() : "";
            String ghiChu = bh.getGhiChu();
            Date ngayTao = bh.getNgayYeuCau();
            String formattedDate1 = sdf.format(ngayTao);
            boolean trangThai = bh.isTrangThai();

            model.addRow(new Object[]{stt++, idBaoHanh, tenKhachHang, soDienThoai, diaChi, tenSanPham, ghiChu, formattedDate1, trangThai ? "Đã Trả" : "Đã Nhận"});
        }
    }

    @Override
    public void addBaoHanh(JTextField TenKH, String SeriSp, String IDSp, String IDHDCT, JTextArea GhiChu, JTextField NgayTao, JRadioButton rdo1) {

        String IDKH = null;
        for (KhachHang kh : rpKh.getAll()) {
            if (TenKH.getText().trim().equals(kh.getHoTen())) {
                IDKH = kh.getIDKhachHang();
                break;
            }
        }

        if (IDKH == null) {
            return;
        }

        java.sql.Date NgayTaoSql = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            dateFormat.setLenient(false);
            java.util.Date utilDateStart1 = dateFormat.parse(NgayTao.getText().trim());
            NgayTaoSql = new java.sql.Date(utilDateStart1.getTime());
        } catch (ParseException e) {
            return;
        }

        rpBH.themBaoHanh(
                IDKH, SeriSp, IDSp,
                IDHDCT,
                NgayTaoSql,
                GhiChu.getText(),
                rdo1.isSelected());
    }

    @Override
    public void CapNhatBaoHanh(JTextField idBaoHanh, JTextArea GhiChuArea, JRadioButton rdo) {
        String idBaoHanhText = idBaoHanh.getText().trim();
        String ghiChuText = GhiChuArea.getText().trim();
        boolean trangThai = rdo.isSelected() ? true : false;

        if (idBaoHanhText.isEmpty() || idBaoHanhText.length() != 6) {
            JOptionPane.showMessageDialog(null, "ID Bảo Hành không hợp lệ. Vui lòng nhập ID có độ dài 6 ký tự.");
            return;
        }

        if (ghiChuText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ghi Chú không được để trống.");
            return;
        }
        if (ghiChuText.length() > 100) {
            JOptionPane.showMessageDialog(null, "Ghi Chú quá dài. Vui lòng nhập ít hơn 100 ký tự.");
            return;
        }

        boolean isSuccess = rpBH.capNhatBaoHanh(idBaoHanhText, ghiChuText, trangThai);
        if (isSuccess) {
            JOptionPane.showMessageDialog(null, "Cập Nhật bảo hành thành công.");
        } else {
            JOptionPane.showMessageDialog(null, "Cập Nhật bảo hành thất bại.");
        }
    }

    @Override
    public void fillByCondition(JTable tbl, JTextField TuNgay, JTextField ToiNgay, JTextField SDT) {
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        model.setRowCount(0);
        java.sql.Date TuNgaySQL = null;
        java.sql.Date ToiNgaySQL = null;

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            dateFormat.setLenient(false);
            java.util.Date utilDateStart1 = dateFormat.parse(TuNgay.getText().trim());
            java.util.Date utilDateEnd1 = dateFormat.parse(ToiNgay.getText().trim());

            if (utilDateStart1.after(utilDateEnd1)) {
                JOptionPane.showMessageDialog(null, "Ngày bắt đầu không được lớn hơn ngày kết thúc.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            TuNgaySQL = new java.sql.Date(utilDateStart1.getTime());
            ToiNgaySQL = new java.sql.Date(utilDateEnd1.getTime());
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Ngày không hợp lệ. Vui lòng nhập ngày theo định dạng dd-MM-yyyy.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String phone = SDT.getText().trim();
        if (!phone.matches("\\d{10,11}")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ. Vui lòng nhập số điện thoại có 10 hoặc 11 chữ số.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int stt = 1;
        for (BaoHanh bh : rpBH.getDanhSachSanPhamBaoHanhByDateAndPhone(TuNgaySQL, ToiNgaySQL, phone)) {
            String idBaoHanh = bh.getIDBaoHanh();
            KhachHang kh = bh.getIDKhachHang();
            String tenKhachHang = kh != null ? kh.getHoTen() : "";
            String soDienThoai = kh != null ? kh.getSoDienThoai() : "";
            String diaChi = kh != null ? kh.getDiaChi() : "";
            SanPham sp = bh.getIDSanPham();
            String tenSanPham = sp != null ? sp.getTenSanPham() : "";
            String ghiChu = bh.getGhiChu();
            Date ngayTao = bh.getNgayYeuCau();
            String formattedDate1 = sdf.format(ngayTao);
            boolean trangThai = bh.isTrangThai();

            model.addRow(new Object[]{stt++, idBaoHanh, tenKhachHang, soDienThoai, diaChi, tenSanPham, ghiChu, formattedDate1, trangThai ? "Đã Trả" : "Đã Nhận"});
        }
    }

}
