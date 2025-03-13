package service.banhang;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.BaoHanh;
import model.HoaDon;
import model.HoaDonChiTiet;
import model.KhachHang;
import model.PhanLoai;
import model.SanPham;
import model.TaiKhoan;
import model.Voucher;
import repository.KhuyenMai.KhuyenMaiRepository;
import repository.PhanLoai.PhanLoaiRepo;
import repository.SanPham.repoChiTietSanPham;
import repository.hoadon.RepositoryHoaDon;
import repository.hoadonchitiet.RepositoryHoaDonChiTiet;
import repository.taikhoan.RepositoryTaiKhoan;
import view.banhang.HoaDonChiTietDialog;
import view.main.Main;

/**
 *
 * @author HUNGpYN
 */
public class BanHangService implements BanHangServiceInteface {

    private RepositoryHoaDonChiTiet rpct = new RepositoryHoaDonChiTiet();
    private PhanLoaiRepo plr = new PhanLoaiRepo();
    private RepositoryHoaDon rhd = new RepositoryHoaDon();
    private repository.SanPham.repoChiTietSanPham rpctsp = new repoChiTietSanPham();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    private Main main;
    private HoaDonChiTietDialog hdct;
    private RepositoryTaiKhoan rptk = new RepositoryTaiKhoan();

    // Add combobox
    @Override
    public void addComboBox(JComboBox cbo) {
        plr = new PhanLoaiRepo();
        for (PhanLoai pl : plr.getAll()) {
            cbo.addItem(pl.getTenPhanLoai());
        }
    }

    // format kiểu tiền của việt nam
    // Phương thức định dạng giá trị kiểu double thành tiền VND
    @Override
    public String formatToVND(double amount) {
        BigDecimal bigDecimalAmount = BigDecimal.valueOf(amount);
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        currencyFormat.setMaximumFractionDigits(0); // Loại bỏ phần thập phân
        return currencyFormat.format(bigDecimalAmount);
    }

    //format về kieu double
    @Override
    public double formatDouble(String currencyStr) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        try {
            Number number = currencyFormat.parse(currencyStr);
            return number.doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0.0; // Trả về giá trị mặc định nếu có lỗi
        }
    }
    // hiển thị lịch sử hóa đơn đã thanh toán

    @Override
    public void fillToTableLichSu(JTable tbl) {
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);

        // Danh sách lưu trữ các mã hóa đơn đã xử lý
        List<String> processedInvoiceIds = new ArrayList<>();
        int stt = 0;

        for (HoaDonChiTiet ct : rpct.getAll()) {
            if (ct.getIDHoaDon().isTrangThai() == true) {
                String maHoaDon = ct.getIDHoaDon().getIDHoaDon();
                double tongTien = 0;

                // Kiểm tra nếu mã hóa đơn đã được xử lý chưa
                if (!processedInvoiceIds.contains(maHoaDon)) {
                    String ngayTaoHoaDon = sdf.format(ct.getIDHoaDon().getNgayTao());
                    String tenNhanVien = ct.getIDHoaDon().getIdTaiKhoan().getHoTen();
                    String tenKhachHang = ct.getIDHoaDon().getIdKhachHang().getHoTen();
                    tongTien = ct.getIDHoaDon().getTongTienSau();

                    // Định dạng số tiền
                    String formattedTongTien = formatToVND(tongTien);
                    stt++;

                    // Thêm thông tin hóa đơn vào bảng
                    model.addRow(new Object[]{
                        stt,
                        maHoaDon,
                        ngayTaoHoaDon,
                        tenNhanVien,
                        tenKhachHang,
                        formattedTongTien,
                        ct.getIDHoaDon().isTrangThai() ? "Đã Thanh Toán" : "Chưa Thanh Toán"
                    });

                    // Đánh dấu mã hóa đơn đã được xử lý
                    processedInvoiceIds.add(maHoaDon);
                }
            }
        }

    }

    @Override
    public void fillHoaDonCho(JTable tbl) {
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        String tongThanhToan;
        int STT = 1;
        for (HoaDon hd : rhd.getAll()) {
            tongThanhToan = formatToVND(hd.getTongTienTRuoc());
            model.addRow(new Object[]{STT++, hd.getIDHoaDon(), hd.getNgayTao(), tongThanhToan, hd.isTrangThai() ? "Đã Thanh Toán" : "Chờ Thanh Toán"});
        }
    }

//    fill lại jtable kho rs lại hàng được chọn
    @Override
    public void fillToTableNoReset(JTable table) {
        int selectedRow = table.getSelectedRow(); // Lưu dòng hiện tại được chọn

        // Cập nhật bảng với dữ liệu mới
        fillHoaDonCho(table);

        // Chọn lại dòng trước đó nếu còn hợp lệ
        if (selectedRow >= 0 && selectedRow < table.getRowCount()) {
            table.setRowSelectionInterval(selectedRow, selectedRow);
        }
    }

    @Override
    public void fillToGioHang(JTable tbl, String text) {
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        int STT = 1;
        String tongTien;
        String giamGia;
        for (HoaDonChiTiet ct : rpct.getAll()) {
            if (ct.getIDHoaDon().getIDHoaDon().equalsIgnoreCase(text)) {
                if (ct.getGiaSauGiamGia() > 0) {
                    tongTien = formatToVND(ct.getGiaSauGiamGia() * ct.getSoLUongSanPHam());
                    giamGia = formatToVND(ct.getGiaSanPham() - ct.getGiaSauGiamGia());
                } else {
                    tongTien = formatToVND(ct.getGiaSanPham() * ct.getSoLUongSanPHam());
                    giamGia = "0";
                }

                model.addRow(new Object[]{
                    ct,
                    STT++,
                    ct.getIDHoaDonChiTiet(),
                    ct.getIDSanPham().getIDSanPham(),
                    ct.getIDSanPham().getTenSanPham(),
                    formatToVND(ct.getGiaSanPham()),
                    giamGia,
                    ct.getSoLUongSanPHam(),
                    tongTien
                });
            }
        }
    }

    @Override
    public void deleteHoaDon(String IDHoaDon) {
        List<HoaDonChiTiet> listHDCT = new ArrayList<>();
        // Lấy danh sách các chi tiết hóa đơn liên quan đến hóa đơn cần xóa
        for (HoaDonChiTiet ct : rpct.getAll()) {
            if (ct.getIDHoaDon().getIDHoaDon().equalsIgnoreCase(IDHoaDon)) {
                listHDCT.add(ct);
            }
        }

        // Cập nhật lại số lượng tồn kho cho các sản phẩm liên quan
        for (HoaDonChiTiet hdct : listHDCT) {
            SanPham sp = hdct.getIDSanPham();
            int soLuong = hdct.getSoLUongSanPHam();
            sp.setSoLuongTonKho(sp.getSoLuongTonKho() + soLuong); // Cộng lại số lượng tồn kho
            rpctsp.updateTonKho(sp);
        }

        // Xóa hóa đơn
        rhd.delete(IDHoaDon);
    }

    @Override
    public int capNhatSoLuong(String IDHoaDon, String IDSanPham, int soLuong) {
        boolean daCapNhat = false; // Cờ để theo dõi xem đã cập nhật hay chưa

        // Lấy tất cả các hóa đơn chi tiết
        List<HoaDonChiTiet> danhSachChiTiet = rpct.getAll();

        for (HoaDonChiTiet hdct : danhSachChiTiet) {
            // Kiểm tra điều kiện
            if (hdct.getIDHoaDon().getIDHoaDon().equalsIgnoreCase(IDHoaDon)
                    && hdct.getIDSanPham().getIDSanPham().equalsIgnoreCase(IDSanPham)) {

                // Cập nhật số lượng sản phẩm trong hóa đơn
                int slMoi = hdct.getSoLUongSanPHam() + soLuong;
                hdct.setSoLUongSanPHam(slMoi);

                // Cập nhật số lượng tồn kho của sản phẩm
                SanPham sp = hdct.getIDSanPham();
                int soLuongTonKhoMoi = sp.getSoLuongTonKho() - soLuong;

                if (soLuongTonKhoMoi < 0) {
                    // Xử lý nếu số lượng tồn kho không đủ

                    return 0;
                }
                sp.setSoLuongTonKho(soLuongTonKhoMoi);

                // Cập nhật vào cơ sở dữ liệu
                rpct.update(hdct);
                rpctsp.updateTonKho(sp);

                daCapNhat = true;
                break; // Kết thúc vòng lặp nếu đã cập nhật
            }
        }

        // Trả về kết quả
        if (daCapNhat) {
            return 1; // Thành công
        } else {
            return 0; // Không tìm thấy sản phẩm để cập nhật
        }
    }

    @Override
    public void addVoucher(JComboBox cbo) {
        repository.KhuyenMai.KhuyenMaiRepository kmrp = new KhuyenMaiRepository();
        for (Voucher vc : kmrp.getAll()) {
            cbo.addItem(vc.getIDVoucher());
        }
    }

    @Override
    public void doubleClick(JTable tbl) {
        tbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = tbl.getSelectedRow();
                    if (row >= 0) {

                        for (HoaDonChiTiet hd : rpct.getAll()) {
                            if (hd.getIDHoaDon().getIDHoaDon().equalsIgnoreCase(tbl.getValueAt(row, 1).toString())) {
                                hdct = new view.banhang.HoaDonChiTietDialog(main, true);
                                hdct.setData(hd);
                                hdct.fillDataChiTiet(tbl.getValueAt(row, 1).toString());
                                hdct.setVisible(true);
                                break;
                            }
                        }
                    }
                }
            }
        });
    }

    @Override
    public void fillToCbo(JComboBox cbo) {
        cbo.addItem("Tất Cả");
        for (TaiKhoan tk : rptk.getAll()) {
            cbo.addItem(tk.getIDTaiKhoan());
        }
    }

    @Override
    public void fillByCondition(JTable tbl, JTextField TuNgay, JTextField ToiNgay, String IDNhanVien) {
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
        String idTaiKhoan = IDNhanVien.trim();
        int stt = 1;
        List<HoaDonChiTiet> list = rpct.getWithConditions(TuNgaySQL, ToiNgaySQL, idTaiKhoan);
        model.setRowCount(0);

        // Danh sách lưu trữ các mã hóa đơn đã xử lý
        List<String> processedInvoiceIds = new ArrayList<>();

        for (HoaDonChiTiet ct : list) {
            if (ct.getIDHoaDon().isTrangThai() == true) {
                String maHoaDon = ct.getIDHoaDon().getIDHoaDon();
                double tongTien = 0;

                // Kiểm tra nếu mã hóa đơn đã được xử lý chưa
                if (!processedInvoiceIds.contains(maHoaDon)) {
                    String ngayTaoHoaDon = sdf.format(ct.getIDHoaDon().getNgayTao());
                    String tenNhanVien = ct.getIDHoaDon().getIdTaiKhoan().getHoTen();
                    String tenKhachHang = ct.getIDHoaDon().getIdKhachHang().getHoTen();
                    tongTien = ct.getIDHoaDon().getTongTienSau();

                    // Định dạng số tiền
                    String formattedTongTien = formatToVND(tongTien);
                    stt++;

                    // Thêm thông tin hóa đơn vào bảng
                    model.addRow(new Object[]{
                        stt,
                        maHoaDon,
                        ngayTaoHoaDon,
                        tenNhanVien,
                        tenKhachHang,
                        formattedTongTien,
                        ct.getIDHoaDon().isTrangThai() ? "Đã Thanh Toán" : "Chưa Thanh Toán"
                    });

                    // Đánh dấu mã hóa đơn đã được xử lý
                    processedInvoiceIds.add(maHoaDon);
                }
            }
        }
    }

}
