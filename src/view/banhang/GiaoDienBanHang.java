/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.banhang;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JOptionPane;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import repository.KhachHang.repoKhachHang;
import repository.KhuyenMai.KhuyenMaiRepository;
import repository.SanPham.repoChiTietSanPham;
import repository.hoadonchitiet.RepositoryHoaDonChiTiet;
import view.main.Main;
import view.until.swing.event.EventItem;
import view.until.swing.form.MainForm;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.GiaoDien.DoanhThuModel;
import model.GiaoDien.GiaoDienSanPhamModel;
import model.HoaDon;
import model.HoaDonChiTiet;
import model.KhachHang;
import model.SanPham;
import model.Voucher;
import repository.hoadon.RepositoryHoaDon;
import service.banhang.BanHangService;
import service.observer.Observer;
import until.validate.ValidateData;
import view.banhang.cell.TableActionCellEditor;
import view.banhang.cell.TableActionCellRender;
import view.banhang.cell.TableActionEvent;
import view.banhang.spinner.EventCellInputChange;
import view.banhang.spinner.QtyCellEditor;
import view.dangnhap.DangNhapView;
import view.form.JTableHeader;
import view.khachhang.ThemMoiKhachHang;
import view.khuyenmai.TableKhuyenMai;
import view.until.hopthoai.NotificationJPanel;

/**
 *
 * @author HUNGpYN
 */
public class GiaoDienBanHang extends javax.swing.JPanel implements Observer {

    private Main main;
    private Color color2 = Color.decode("#101820");// thanden
    private Color color1 = Color.decode("#FEE715"); //mau vang
    private repoChiTietSanPham rpct = new repoChiTietSanPham();
    private MainForm mainForm;
    private static GiaoDienBanHang instance;

//service ban  hang
    service.banhang.BanHangService bhs = new BanHangService();
    //repo hóa đơn
    private RepositoryHoaDon rhd = new RepositoryHoaDon();
    //repo hóa đơn chi tiết
    private repository.hoadonchitiet.RepositoryHoaDonChiTiet rhdct = new RepositoryHoaDonChiTiet();
    //repo khách hàng
    private repository.KhachHang.repoKhachHang rpkh = new repoKhachHang();
    //repo voucher
    private repository.KhuyenMai.KhuyenMaiRepository kmrp = new KhuyenMaiRepository();

    //repo SanPham;
    private repoChiTietSanPham rpsp = new repoChiTietSanPham();
    // validate dữ liệu
    private ValidateData vld = new ValidateData();

    //lấy id hóa đơn chờ
    private String IDHoaDonCho;
    // tạo id sản phẩm
    private String IDSanPham;

    // tạo biến số lượng
    private int soLuong;

    static {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("view.until.sampletable.themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacLightLaf.setup();
    }

    public GiaoDienBanHang() {
        initComponents();
        init();
        setTableGioHang();
        bhs.fillToTableLichSu(tbl_LichSuHoaDon);
        bhs.fillHoaDonCho(tbl_HoaDonCho);
        bhs.fillToGioHang(tbl_GioHang, getIDHoaDonCho());
        bhs.addVoucher(cbo_Voucher);
        setDataThanhToan();
        lbl_GioHangHoaDon.setText(getIDHoaDonCho());
        bhs.doubleClick(tbl_LichSuHoaDon);
        bhs.fillToCbo(cbo_NhanVien);
    }

    private void init() {
        mainForm = new MainForm();
        SanPhamPanel.setLayout(new BorderLayout());
        SanPhamPanel.add(mainForm);
        testData();

        bhs.addComboBox(cbo_PhanLoai);
        setLastRow();
        TableKhuyenMai tkm = new TableKhuyenMai();
        tkm.init(tbl_LichSuHoaDon, scrollLSHD);
        tbl_LichSuHoaDon.getTableHeader().setDefaultRenderer(new JTableHeader(tbl_LichSuHoaDon));
        tkm.init(tbl_HoaDonCho, scrollHDC);
        tbl_HoaDonCho.getTableHeader().setDefaultRenderer(new JTableHeader(tbl_HoaDonCho));

        tkm.init(tbl_GioHang, scrollGH);
        tbl_GioHang.getTableHeader().setDefaultRenderer(new JTableHeader(tbl_GioHang));

        //set Table Hóa Đơn chờ và giỏ hàng
        setTableHoaDonCho();
        setTableGioHang();

    }
    int index;

    private void setTableHoaDonCho() {
        // cài đặt lại nút xóa của jtable
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void Delete(int row) {
                if (tbl_HoaDonCho.isEditing()) {
                    tbl_HoaDonCho.getCellEditor().stopCellEditing();
                }
                DefaultTableModel model = (DefaultTableModel) tbl_HoaDonCho.getModel();

                bhs.deleteHoaDon(tbl_HoaDonCho.getValueAt(row, 1).toString());
                model.removeRow(row);
                NotificationJPanel panel = new NotificationJPanel(Panel_HoaDon, NotificationJPanel.Type.SUCCESS, NotificationJPanel.Location.CENTER, "Xóa Hóa Đơn Thành Công");
                panel.showNotification();
                bhs.fillToGioHang(tbl_GioHang, getIDHoaDonCho());
                setDataThanhToan();
                if (getInstance() != null) {
                    update();
                }
            }
        };
        tbl_HoaDonCho.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender());
        tbl_HoaDonCho.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(event));
    }

    private void setTableGioHang() {

        TableActionEvent event = new TableActionEvent() {
            @Override
            public void Delete(int row) {
                if (tbl_GioHang.isEditing()) {
                    tbl_GioHang.getCellEditor().stopCellEditing();
                }
                DefaultTableModel model = (DefaultTableModel) tbl_GioHang.getModel();
                int slBan = Integer.valueOf(tbl_GioHang.getValueAt(row, 7).toString());
                String IDSanPhamBan = tbl_GioHang.getValueAt(row, 3).toString();
                System.out.println(IDSanPhamBan);
                System.out.println(slBan);
                int tonKhoHienTai = getTonKhoHienTai(IDSanPhamBan);
                SanPham sp = new SanPham();
                sp.setIDSanPham(IDSanPhamBan);
                sp.setSoLuongTonKho(tonKhoHienTai + slBan);

                rhdct.delete(tbl_GioHang.getValueAt(row, 2).toString());

                rpsp.updateTonKho(sp);
                model.removeRow(row);
                bhs.fillToTableNoReset(tbl_HoaDonCho);
                NotificationJPanel panel = new NotificationJPanel(Panel_HoaDon, NotificationJPanel.Type.SUCCESS, NotificationJPanel.Location.CENTER, "Xóa Sản Phảm Ở Giỏ Hàng Thành Công");
                panel.showNotification();
                setDataThanhToan();

                // tạo lại mainform
                if (getInstance() != null) {
                    update();
                }

            }
        };

        tbl_GioHang.getColumnModel().getColumn(9).setCellRenderer(new TableActionCellRender());
        tbl_GioHang.getColumnModel().getColumn(9).setCellEditor(new TableActionCellEditor(event));

        tbl_GioHang.getColumnModel().getColumn(7).setCellEditor(new QtyCellEditor(new EventCellInputChange() {
            @Override
            public void inputChanged() {

            }
        }));
        tbl_GioHang.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(SwingConstants.CENTER);
                return this;
            }
        });

    }

    public int getTonKhoHienTai(String text) {
        int tonKhoHienTai = 0;
        for (SanPham sp : rpsp.getAll()) {
            if (sp.getIDSanPham().equalsIgnoreCase(text)) {
                tonKhoHienTai = sp.getSoLuongTonKho();
            }
        }
        return tonKhoHienTai;
    }

    private int lastRow = -1;

    // set bảng luôn luôn ở hàng cuối
    private void setLastRow() {
        DefaultTableModel model = (DefaultTableModel) tbl_HoaDonCho.getModel();
        tbl_HoaDonCho.setRowSelectionAllowed(true); // Đảm bảo rằng chọn hàng là được phép

        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int rowCount = model.getRowCount();

                // Chỉ thực hiện thay đổi lựa chọn khi bảng có hàng
                if (rowCount > 0) {
                    // Chọn lại dòng trước đó nếu còn hợp lệ
                    if (lastRow >= 0 && lastRow < rowCount) {
                        tbl_HoaDonCho.setRowSelectionInterval(lastRow, lastRow);
                    } else {
                        // Nếu dòng trước đó không còn hợp lệ, chọn hàng cuối cùng
                        tbl_HoaDonCho.setRowSelectionInterval(rowCount - 1, rowCount - 1);
                    }
                } else {
                    // Nếu không có hàng nào, xóa lựa chọn
                    tbl_HoaDonCho.clearSelection();
                }
            }
        });
        DefaultTableModel modelGH = (DefaultTableModel) tbl_GioHang.getModel();

        modelGH.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int rowCount = modelGH.getRowCount();
                if (rowCount > 0) {
                    tbl_GioHang.setRowSelectionInterval(rowCount - 1, rowCount - 1);
                }
            }
        });
    }

    private void testData() {
        mainForm.setEvent(new EventItem() {
            @Override
            public void itemClick(Component com, SanPham sp) {
                IDSanPham = sp.getIDSanPham();
                if (sp.getSoLuongTonKho() == 0) {
                    NotificationJPanel panel = new NotificationJPanel(Panel_HoaDon, NotificationJPanel.Type.WARNING, NotificationJPanel.Location.CENTER, "Sản Phẩm Hiện Hết Hàng");
                    panel.showNotification();
                    return;
                }
                String input = JOptionPane.showInputDialog("Nhập số lượng");
                if (input == "" || input.trim().isEmpty()) {
                    return;
                }
                try {
                    soLuong = Integer.parseInt(input);
                    if (tbl_HoaDonCho.getSelectedRow() <0) {
                        NotificationJPanel panel = new NotificationJPanel(Panel_HoaDon, NotificationJPanel.Type.WARNING, NotificationJPanel.Location.CENTER, "Chưa Có Hoá Đơn Tạo Không Thể Thêm");
                        panel.showNotification();
                        return;
                    }
                    if (soLuong > sp.getSoLuongTonKho()) {
                        NotificationJPanel panel = new NotificationJPanel(Panel_HoaDon, NotificationJPanel.Type.WARNING, NotificationJPanel.Location.CENTER, "Nhập quá số lượng tồn kho");
                        panel.showNotification();
                        setDataThanhToan();
                        return;

                    }
                    if (!vld.checkNguyenDuong(input, true)) {
                        NotificationJPanel panel = new NotificationJPanel(Panel_HoaDon, NotificationJPanel.Type.WARNING, NotificationJPanel.Location.CENTER, "Số Lượng Phải Là Số Nguyên Dương");
                        panel.showNotification();
                        return;
                    }
                    if (bhs.capNhatSoLuong(getIDHoaDonCho(), IDSanPham, soLuong) == 1) {

                        NotificationJPanel panel = new NotificationJPanel(Panel_HoaDon, NotificationJPanel.Type.SUCCESS, NotificationJPanel.Location.CENTER, "Thêm Trang Sức Vào Giỏ Hàng Thành Công");
                        panel.showNotification();
                        bhs.fillToGioHang(tbl_GioHang, getIDHoaDonCho());
                        bhs.fillToTableNoReset(tbl_HoaDonCho);
                        setDataThanhToan();
                        if (getInstance() != null) {
                            update();
                        }
                        System.out.println(soLuong + " số lượng cập nhật");
                        return;

                    }
                    if (readForm() != null) {
                        rhdct.create(readForm());
                        bhs.fillToGioHang(tbl_GioHang, getIDHoaDonCho());
                        bhs.fillToTableNoReset(tbl_HoaDonCho);
                        sp.setSoLuongTonKho(sp.getSoLuongTonKho() - soLuong);
                        rpsp.updateTonKho(sp);
                        NotificationJPanel panel = new NotificationJPanel(Panel_HoaDon, NotificationJPanel.Type.SUCCESS, NotificationJPanel.Location.CENTER, "Thêm Trang Sức Vào Giỏ Hàng Thành Công");
                        panel.showNotification();
                        setDataThanhToan();
                        if (getInstance() != null) {
                            update();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                }
                mainForm.setSelected(com);
            }
        });
        setDataSP();
    }

    private void setDataSP() {
        for (SanPham sp : rpct.getAll()) {
            if (sp.isTrangThai()) {
                mainForm.addItem(new SanPham(sp.getIDSanPham(), sp.getTenSanPham(), sp.getSoLuongTonKho(), sp.getGiaChiTiet(), sp.getIDGiamGia(), sp.getHinhAnhSanPham()));
            }
        }
    }

    // Lấy Id của hóa đơn chờ để xóa
    public String getIDHoaDonCho() {
        int i = tbl_HoaDonCho.getSelectedRow();
        if (i == -1 && tbl_HoaDonCho.getRowCount() > 0) {
            // Nếu không có hàng nào được chọn và bảng không rỗng, chọn hàng cuối cùng
            i = tbl_HoaDonCho.getRowCount() - 1;
        }

        if (i > -1) {
            IDHoaDonCho = (String) tbl_HoaDonCho.getValueAt(i, 1);
        }
        return IDHoaDonCho;
    }

    // Lấy ID Sản Phẩm để insert vào hóa đơn chi tiết
    public String getIDSanPham() {
        return IDSanPham;
    }

    // đọc dữ liệu hóa đơn chi tiết
    HoaDonChiTiet readForm() {
        SanPham sp = new SanPham();
        sp.setIDSanPham(getIDSanPham());
        HoaDon hd = new HoaDon();
        hd.setIDHoaDon(getIDHoaDonCho());
        return new HoaDonChiTiet(sp, hd, soLuong);
    }

    @Override
    public void update() {
        mainForm.clearItems(); // Xóa các item cũ
        setDataSP();           // Thêm các item mới
        SanPhamPanel.revalidate();
        SanPhamPanel.repaint();
    }

    public static GiaoDienBanHang getInstance() {
        if (instance == null) {
            instance = new GiaoDienBanHang();
        }
        return instance;
    }
    // lấy tổng tiền của hóa đơn
    double tongTien;

    public double getTongTien() {
        for (HoaDon hd : rhd.getAll()) {
            if (hd.getIDHoaDon().equalsIgnoreCase(getIDHoaDonCho())) {
                tongTien = hd.getTongTienTRuoc();
                break;
            }

        }
        return tongTien;
    }
    //set Data Dữ Liệu Thanh Toán
    private int tichDiem = 0;
    private double chietKhau = 0;
    private double thanhToan = 0;
    private int tichDiemBanDau = 0;

    void setDataThanhToan() {
        lbl_MaHoaDon.setText(getIDHoaDonCho());
        lbl_TongTien.setText(bhs.formatToVND(getTongTien()));

        // Thiết lập giá trị thanh toán ban đầu
        updateThanhToan();
        ckb_SuDung.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // Khi checkbox được chọn
                    // Khi checkbox bị bỏ chọn
                    tichDiem = tichDiemBanDau; // Khôi phục giá trị tích điểm ban đầu
                    lbl_TichDiem.setText(String.valueOf(tichDiem));
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    tichDiem = 0; // Đặt giá trị tích điểm về 0 khi checkbox được chọn
                }
                // Cập nhật thanh toán sau khi thay đổi tích điểm
                updateThanhToan();
            }
        });

        // Thêm ItemListener cho cbo_Voucher
        cbo_Voucher.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // Xử lý khi một voucher được chọn
                    for (Voucher vc : kmrp.getAll()) {
                        if (vc.getIDVoucher().equalsIgnoreCase(cbo_Voucher.getSelectedItem().toString())) {
                            chietKhau = getTongTien() * vc.getTyLe() / 100;
                            lbl_ChietKhau.setText(bhs.formatToVND(chietKhau));
                            break;
                        }
                    }
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    // Khi voucher bị bỏ chọn
                    chietKhau = 0; // Đặt chiết khấu về 0
                    lbl_ChietKhau.setText(bhs.formatToVND(chietKhau));
                }
                // Cập nhật thanh toán sau khi thay đổi chiết khấu
                updateThanhToan();
            }
        });
    }

    void updateThanhToan() {
        // Đảm bảo các giá trị cần thiết đã được cập nhật
        double tongTien = getTongTien(); // Cập nhật tổng tiền hóa đơn mới nhất
        double tichDiem = this.tichDiem; // Sử dụng giá trị tích điểm hiện tại
        double chietKhau = this.chietKhau; // Sử dụng giá trị chiết khấu hiện tại

        // Tính toán số tiền thanh toán
        thanhToan = tongTien - tichDiem - chietKhau;

        // Cập nhật nhãn hiển thị số tiền thanh toán
        lbl_ThanhToan.setText(bhs.formatToVND(thanhToan));
        System.out.println("Tong Tien: " + tongTien);
        System.out.println("Tich Diem: " + tichDiem);
        System.out.println("Chiet Khau: " + chietKhau);
        System.out.println("Thanh Toan: " + thanhToan);
    }

    void lamMoi() {
        lbl_HoTen.setText("");
        lbl_TichDiem.setText("");
        lbl_MaHoaDon.setText("");
        lbl_ThanhToan.setText("");
        lbl_GioHangHoaDon.setText("");
        lbl_TongTien.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new com.raven.datechooser.DateChooser();
        dateChooser2 = new com.raven.datechooser.DateChooser();
        tabbedPaneCustom1 = new view.until.tabbedpane.TabbedPaneCustom();
        Panel_HoaDon = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cbo_PhanLoai = new view.until.combobox.ComboBoxSuggestion();
        jLabel6 = new javax.swing.JLabel();
        txt_TenTrangSuc = new view.until.textfield.TextFieldSuggestion();
        button1 = new view.until.button.Button();
        button2 = new view.until.button.Button();
        jLabel7 = new javax.swing.JLabel();
        SanPhamPanel = new view.until.swing.Background();
        jLabel1 = new javax.swing.JLabel();
        btn_TaoHoaDon = new view.until.button.Button();
        background3 = new view.until.swing.Background();
        scrollHDC = new javax.swing.JScrollPane();
        tbl_HoaDonCho = new javax.swing.JTable();
        background1 = new view.until.swing.Background();
        scrollGH = new javax.swing.JScrollPane();
        tbl_GioHang = new javax.swing.JTable();
        background2 = new view.until.swing.Background();
        txt_SoDienThoai = new view.until.textfield.TextFieldSuggestion();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lbl_HoTen = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lbl_TichDiem = new javax.swing.JLabel();
        ckb_SuDung = new javax.swing.JCheckBox();
        btn_TimKhachHang = new view.until.button.Button();
        tbn_ThemKhachHang = new view.until.button.Button();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        lbl_MaHoaDon = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lbl_TongTien = new javax.swing.JLabel();
        lbl_ChietKhau = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lbl_ThanhToan = new javax.swing.JLabel();
        btn_ThanhToan = new view.until.button.Button();
        btn_Huy = new view.until.button.Button();
        cbo_Voucher = new view.until.combobox.ComboBoxSuggestion();
        jLabel8 = new javax.swing.JLabel();
        lbl_GioHangHoaDon = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Panel_LichSu = new javax.swing.JPanel();
        panel_ThongTin = new javax.swing.JPanel();
        lbl_ThongTin = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbo_NhanVien = new view.until.combobox.ComboBoxSuggestion();
        txt_BatDau = new view.until.textfield.TextFieldSuggestion();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_KetThuc = new view.until.textfield.TextFieldSuggestion();
        btn_TimKiemHoaDon = new view.until.button.Button();
        btn_Excel = new view.until.button.Button();
        scrollLSHD = new javax.swing.JScrollPane();
        tbl_LichSuHoaDon = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();

        dateChooser1.setTextRefernce(txt_BatDau);

        dateChooser2.setTextRefernce(txt_KetThuc);

        tabbedPaneCustom1.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setText("Phân Loại");

        cbo_PhanLoai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất Cả" }));

        jLabel6.setText("Tên Trang Sức");

        button1.setText("Tìm Kiếm");
        button1.setColor1(new java.awt.Color(16, 24, 32));
        button1.setColor2(new java.awt.Color(254, 231, 21));
        button1.setDefaultCapable(false);
        button1.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        button2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/lammoi.png"))); // NOI18N
        button2.setColor1(new java.awt.Color(16, 24, 32));
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(cbo_PhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(58, 58, 58)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txt_TenTrangSuc, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbo_PhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_TenTrangSuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Hóa Đơn Chờ");

        javax.swing.GroupLayout SanPhamPanelLayout = new javax.swing.GroupLayout(SanPhamPanel);
        SanPhamPanel.setLayout(SanPhamPanelLayout);
        SanPhamPanelLayout.setHorizontalGroup(
            SanPhamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        SanPhamPanelLayout.setVerticalGroup(
            SanPhamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Trang Sức");

        btn_TaoHoaDon.setText("Tạo Hóa Đơn");
        btn_TaoHoaDon.setColor1(new java.awt.Color(16, 24, 32));
        btn_TaoHoaDon.setColor2(new java.awt.Color(254, 231, 21));
        btn_TaoHoaDon.setDefaultCapable(false);
        btn_TaoHoaDon.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btn_TaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TaoHoaDonActionPerformed(evt);
            }
        });

        scrollHDC.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tbl_HoaDonCho.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tbl_HoaDonCho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Hóa Đơn", "Ngày Tạo", "Tổng Tiền", "Trạng Thái", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_HoaDonCho.setRowHeight(40);
        tbl_HoaDonCho.getTableHeader().setReorderingAllowed(false);
        tbl_HoaDonCho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_HoaDonChoMouseClicked(evt);
            }
        });
        scrollHDC.setViewportView(tbl_HoaDonCho);
        if (tbl_HoaDonCho.getColumnModel().getColumnCount() > 0) {
            tbl_HoaDonCho.getColumnModel().getColumn(0).setMaxWidth(50);
            tbl_HoaDonCho.getColumnModel().getColumn(5).setMaxWidth(50);
        }

        javax.swing.GroupLayout background3Layout = new javax.swing.GroupLayout(background3);
        background3.setLayout(background3Layout);
        background3Layout.setHorizontalGroup(
            background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background3Layout.createSequentialGroup()
                .addComponent(scrollHDC)
                .addContainerGap())
        );
        background3Layout.setVerticalGroup(
            background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollHDC, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );

        scrollGH.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        tbl_GioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "", "STT", "Mã Hóa Đơn Chi Tiết", "Mã Sản Phẩm", "Tên Sản Phẩm", "Giá Bán", "Giảm Giá", "SL", "Tổng Tiền", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_GioHang.getTableHeader().setReorderingAllowed(false);
        scrollGH.setViewportView(tbl_GioHang);
        if (tbl_GioHang.getColumnModel().getColumnCount() > 0) {
            tbl_GioHang.getColumnModel().getColumn(0).setMinWidth(0);
            tbl_GioHang.getColumnModel().getColumn(0).setPreferredWidth(0);
            tbl_GioHang.getColumnModel().getColumn(0).setMaxWidth(0);
            tbl_GioHang.getColumnModel().getColumn(1).setMaxWidth(30);
            tbl_GioHang.getColumnModel().getColumn(2).setMinWidth(0);
            tbl_GioHang.getColumnModel().getColumn(2).setPreferredWidth(0);
            tbl_GioHang.getColumnModel().getColumn(2).setMaxWidth(0);
            tbl_GioHang.getColumnModel().getColumn(3).setMinWidth(0);
            tbl_GioHang.getColumnModel().getColumn(3).setMaxWidth(0);
            tbl_GioHang.getColumnModel().getColumn(4).setPreferredWidth(200);
            tbl_GioHang.getColumnModel().getColumn(7).setMaxWidth(50);
            tbl_GioHang.getColumnModel().getColumn(9).setMaxWidth(40);
        }

        javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
        background1.setLayout(background1Layout);
        background1Layout.setHorizontalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollGH, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        background1Layout.setVerticalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollGH, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                .addContainerGap())
        );

        txt_SoDienThoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SoDienThoaiActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel12.setText("Số Điện Thoại:");

        jLabel13.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel13.setText("Khách Hàng");

        jLabel15.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel15.setText("Tích Điểm:");

        lbl_TichDiem.setText("0");

        ckb_SuDung.setText("Sử Dụng");
        ckb_SuDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckb_SuDungActionPerformed(evt);
            }
        });

        btn_TimKhachHang.setBorder(null);
        btn_TimKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/searchsmall.png"))); // NOI18N
        btn_TimKhachHang.setColor1(new java.awt.Color(16, 24, 32));
        btn_TimKhachHang.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_TimKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKhachHangActionPerformed(evt);
            }
        });

        tbn_ThemKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/addsmall.png"))); // NOI18N
        tbn_ThemKhachHang.setColor1(new java.awt.Color(16, 24, 32));
        tbn_ThemKhachHang.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tbn_ThemKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbn_ThemKhachHangActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel10.setText("Mã Hóa Đơn:");

        jLabel16.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel16.setText("Tổng Tiền:");

        lbl_ChietKhau.setText("0");

        jLabel19.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel19.setText("Voucher:");

        jLabel20.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel20.setText("Thanh Toán:");

        jLabel21.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel21.setText("Chiết Khấu:");

        btn_ThanhToan.setText("Thanh Toán");
        btn_ThanhToan.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btn_ThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThanhToanActionPerformed(evt);
            }
        });

        btn_Huy.setText("Hủy");
        btn_Huy.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btn_Huy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HuyActionPerformed(evt);
            }
        });

        cbo_Voucher.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Không" }));

        javax.swing.GroupLayout background2Layout = new javax.swing.GroupLayout(background2);
        background2.setLayout(background2Layout);
        background2Layout.setHorizontalGroup(
            background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background2Layout.createSequentialGroup()
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel12)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel15)))
                .addGap(18, 18, 18)
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_SoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_HoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_TichDiem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background2Layout.createSequentialGroup()
                        .addComponent(btn_TimKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tbn_ThemKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(background2Layout.createSequentialGroup()
                        .addComponent(ckb_SuDung)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(background2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(background2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel20)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(background2Layout.createSequentialGroup()
                        .addComponent(btn_Huy, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                        .addGap(135, 135, 135)
                        .addComponent(btn_ThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)))
                .addGap(35, 35, 35))
            .addGroup(background2Layout.createSequentialGroup()
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(background2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbl_ThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, background2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbl_MaHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbo_Voucher, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                            .addComponent(lbl_ChietKhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_TongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(110, Short.MAX_VALUE))
        );
        background2Layout.setVerticalGroup(
            background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btn_TimKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_SoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(tbn_ThemKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lbl_HoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(background2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)))
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(background2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(ckb_SuDung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_TichDiem, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                        .addGap(7, 7, 7)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel10)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel16))
                    .addGroup(background2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addComponent(lbl_MaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_TongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(cbo_Voucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel21)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel20))
                    .addGroup(background2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addComponent(lbl_ChietKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_ThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_ThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(btn_Huy, javax.swing.GroupLayout.PREFERRED_SIZE, 31, Short.MAX_VALUE))
                .addGap(22, 22, 22))
        );

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel8.setText("Giỏ Hàng:");

        jLabel9.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel9.setText("Thanh Toán:");

        javax.swing.GroupLayout Panel_HoaDonLayout = new javax.swing.GroupLayout(Panel_HoaDon);
        Panel_HoaDon.setLayout(Panel_HoaDonLayout);
        Panel_HoaDonLayout.setHorizontalGroup(
            Panel_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_HoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGroup(Panel_HoaDonLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_TaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57))
                    .addComponent(background3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SanPhamPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(Panel_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_HoaDonLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addGap(0, 345, Short.MAX_VALUE))
                    .addGroup(Panel_HoaDonLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(Panel_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(background1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(Panel_HoaDonLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(lbl_GioHangHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_HoaDonLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(background2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );
        Panel_HoaDonLayout.setVerticalGroup(
            Panel_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_HoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Panel_HoaDonLayout.createSequentialGroup()
                        .addGroup(Panel_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_TaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(background3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Panel_HoaDonLayout.createSequentialGroup()
                        .addGroup(Panel_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(lbl_GioHangHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(background1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_HoaDonLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SanPhamPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(background2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        tabbedPaneCustom1.addTab("Bán Hàng", Panel_HoaDon);

        Panel_LichSu.setBackground(new java.awt.Color(255, 255, 255));

        lbl_ThongTin.setBackground(new java.awt.Color(255, 255, 255));
        lbl_ThongTin.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_ThongTin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_ThongTin.setText("Thông Tin Hóa Đơn");

        javax.swing.GroupLayout panel_ThongTinLayout = new javax.swing.GroupLayout(panel_ThongTin);
        panel_ThongTin.setLayout(panel_ThongTinLayout);
        panel_ThongTinLayout.setHorizontalGroup(
            panel_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_ThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel_ThongTinLayout.setVerticalGroup(
            panel_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_ThongTinLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_ThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel2.setText("Mã Nhân Viên");

        cbo_NhanVien.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất Cả" }));

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel3.setText("Từ Ngày");

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel4.setText("Đến Ngày");

        btn_TimKiemHoaDon.setText("Tìm Kiếm");
        btn_TimKiemHoaDon.setColor1(new java.awt.Color(16, 24, 32));
        btn_TimKiemHoaDon.setColor2(new java.awt.Color(254, 231, 21));
        btn_TimKiemHoaDon.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btn_TimKiemHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemHoaDonActionPerformed(evt);
            }
        });

        btn_Excel.setBorder(null);
        btn_Excel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/lammoi.png"))); // NOI18N
        btn_Excel.setColor1(new java.awt.Color(16, 24, 32));
        btn_Excel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ExcelActionPerformed(evt);
            }
        });

        scrollLSHD.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        tbl_LichSuHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Hóa Đơn", "Ngày Tạo", "Nhân Viên", "Khách Hàng", "Tổng Thanh Toán", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollLSHD.setViewportView(tbl_LichSuHoaDon);
        if (tbl_LichSuHoaDon.getColumnModel().getColumnCount() > 0) {
            tbl_LichSuHoaDon.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel11.setText("Danh Sách Hóa Dơn");

        javax.swing.GroupLayout Panel_LichSuLayout = new javax.swing.GroupLayout(Panel_LichSu);
        Panel_LichSu.setLayout(Panel_LichSuLayout);
        Panel_LichSuLayout.setHorizontalGroup(
            Panel_LichSuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_ThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(Panel_LichSuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollLSHD)
                .addContainerGap())
            .addGroup(Panel_LichSuLayout.createSequentialGroup()
                .addGroup(Panel_LichSuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_LichSuLayout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(Panel_LichSuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(cbo_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(93, 93, 93)
                        .addGroup(Panel_LichSuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txt_BatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(71, 71, 71)
                        .addGroup(Panel_LichSuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(Panel_LichSuLayout.createSequentialGroup()
                                .addComponent(txt_KetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(btn_TimKiemHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_Excel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(Panel_LichSuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11)))
                .addGap(133, 133, Short.MAX_VALUE))
        );
        Panel_LichSuLayout.setVerticalGroup(
            Panel_LichSuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_LichSuLayout.createSequentialGroup()
                .addComponent(panel_ThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(Panel_LichSuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_LichSuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Excel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Panel_LichSuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbo_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_BatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_KetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_TimKiemHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollLSHD, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

        tabbedPaneCustom1.addTab("Lịch Sử Bán Hàng", Panel_LichSu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPaneCustom1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPaneCustom1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    private void btn_TaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TaoHoaDonActionPerformed
        System.out.println(dnv.IDTaiKhoan);
        if (rhd.create(dnv.IDTaiKhoan) > 0) {
            NotificationJPanel panel = new NotificationJPanel(Panel_HoaDon, NotificationJPanel.Type.SUCCESS, NotificationJPanel.Location.CENTER, "Tạo Hóa Đơn Thành Công");
            panel.showNotification();
            bhs.fillHoaDonCho(tbl_HoaDonCho);
            bhs.fillToGioHang(tbl_GioHang, getIDHoaDonCho());
            lbl_GioHangHoaDon.setText(getIDHoaDonCho());
        } else {
            return;
        }


    }//GEN-LAST:event_btn_TaoHoaDonActionPerformed

    private void tbl_HoaDonChoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_HoaDonChoMouseClicked
        // TODO add your handling code here:
        bhs.fillToGioHang(tbl_GioHang, getIDHoaDonCho());
        setDataThanhToan();
        System.out.println(getIDSanPham() + "Clicked");
        lbl_GioHangHoaDon.setText(getIDHoaDonCho());
    }//GEN-LAST:event_tbl_HoaDonChoMouseClicked

    private void txt_SoDienThoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SoDienThoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_SoDienThoaiActionPerformed

    private void ckb_SuDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckb_SuDungActionPerformed

    }//GEN-LAST:event_ckb_SuDungActionPerformed


    private void btn_TimKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKhachHangActionPerformed
        boolean checkKH = true;
        for (KhachHang kh : rpkh.getAll()) {
            if (kh.getSoDienThoai().equalsIgnoreCase(txt_SoDienThoai.getText())) {
                lbl_HoTen.setText(kh.getHoTen());
                lbl_TichDiem.setText(String.valueOf(kh.getTichDiem()));
                tichDiemBanDau = kh.getTichDiem();
                checkKH = false;
                break;
            }
        }
        if (checkKH) {
            NotificationJPanel panel = new NotificationJPanel(Panel_HoaDon, NotificationJPanel.Type.WARNING, NotificationJPanel.Location.BOTTOM_RIGHT, "Không Tìm Thấy Khách Hàng");
            panel.showNotification();
        }
    }//GEN-LAST:event_btn_TimKhachHangActionPerformed

    private void tbn_ThemKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbn_ThemKhachHangActionPerformed
        // TODO add your handling code here:
        ThemMoiKhachHang tmkh = new ThemMoiKhachHang(main, true);
        tmkh.setVisible(true);
    }//GEN-LAST:event_tbn_ThemKhachHangActionPerformed

    private void btn_HuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HuyActionPerformed
        // TODO add your handling code here:
        bhs.deleteHoaDon(lbl_MaHoaDon.getText());
        bhs.fillHoaDonCho(tbl_HoaDonCho);
        bhs.fillToGioHang(tbl_GioHang, getIDHoaDonCho());
        if (getInstance() != null) {
            update();
        }
        lamMoi();
        NotificationJPanel panel = new NotificationJPanel(Panel_HoaDon, NotificationJPanel.Type.SUCCESS, NotificationJPanel.Location.CENTER, "Hủy Hóa Đơn Thành Công");
        panel.showNotification();
    }//GEN-LAST:event_btn_HuyActionPerformed

    private String getIDVoucher() {
        // Khởi tạo IDVoucher với giá trị null
        String IDVoucher = null;

        // Lặp qua tất cả các Voucher để tìm IDVoucher phù hợp
        for (Voucher vc : kmrp.getAll()) {
            if (vc.getIDVoucher().equalsIgnoreCase(cbo_Voucher.getSelectedItem().toString().trim())) {
                IDVoucher = vc.getIDVoucher();
                break; // Nếu tìm thấy, không cần tiếp tục lặp
            }
        }

        // Trả về IDVoucher nếu tìm thấy, nếu không trả về null
        return IDVoucher;
    }

    String idKhachHang;

    private String getIDKhachHang() {
        for (KhachHang kh : rpkh.getAll()) {
            if (kh.getSoDienThoai().equalsIgnoreCase(txt_SoDienThoai.getText())) {
                idKhachHang = kh.getIDKhachHang();
                return idKhachHang;
            }
        }
        return null;
    }
    private DangNhapView dnv = new DangNhapView();

    HoaDon readHoaDon() {
        HoaDon hd = new HoaDon();
        hd.setIDHoaDon(lbl_MaHoaDon.getText());

        Voucher vc = new Voucher();
        vc.setIDVoucher(getIDVoucher()); // Nếu cần, hãy cung cấp IDVoucher hợp lệ
        KhachHang kh = new KhachHang();
        kh.setIDKhachHang(getIDKhachHang());
        hd.setIdKhachHang(kh);
        hd.setIdVoucher(vc);
        // Sử dụng formatDouble để chuyển đổi chuỗi tiền tệ về kiểu double
        double tongTien = bhs.formatDouble(lbl_TongTien.getText());
        double thanhToan = bhs.formatDouble(lbl_ThanhToan.getText());

        hd.setTongTienTRuoc(tongTien);
        hd.setTongTienSau(thanhToan);

        return hd;
    }

    private void btn_ThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThanhToanActionPerformed
        // TODO add your handling code here:
        if (vld.checkIsEmpty(txt_SoDienThoai.getText())) {
            NotificationJPanel panel = new NotificationJPanel(Panel_HoaDon, NotificationJPanel.Type.WARNING, NotificationJPanel.Location.CENTER, "Chưa Nhập Số Điện Thoại");
            panel.showNotification();
            return;
        }
        if (rhd.update(readHoaDon()) > 0) {
            bhs.fillHoaDonCho(tbl_HoaDonCho);
            bhs.fillToGioHang(tbl_GioHang, null);
            bhs.fillToTableLichSu(tbl_LichSuHoaDon);
            if (ckb_SuDung.isSelected()) {
                rhd.updateTichDiem(getIDKhachHang(), getIDHoaDonCho());
            }
            lamMoi();
            NotificationJPanel panel = new NotificationJPanel(Panel_HoaDon, NotificationJPanel.Type.SUCCESS, NotificationJPanel.Location.CENTER, "Thanh Toán Thành Công");
            panel.showNotification();
        } else {
            NotificationJPanel panel = new NotificationJPanel(Panel_HoaDon, NotificationJPanel.Type.WARNING, NotificationJPanel.Location.CENTER, "Thanh Toán Thất Bại");
            panel.showNotification();
        }
        System.out.println(getIDVoucher());
    }//GEN-LAST:event_btn_ThanhToanActionPerformed

    private void btn_ExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ExcelActionPerformed
        // TODO add your handling code here:
        cbo_NhanVien.setSelectedIndex(1);
        bhs.fillToTableLichSu(tbl_LichSuHoaDon);
    }//GEN-LAST:event_btn_ExcelActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
        setDataSP();
        if (getInstance() != null) {
            update();
        }
        cbo_PhanLoai.setSelectedIndex(0);
        txt_TenTrangSuc.setText("");

    }//GEN-LAST:event_button2ActionPerformed

    GiaoDienSanPhamModel readModel() {
        GiaoDienSanPhamModel gdmd = new GiaoDienSanPhamModel();
        gdmd.setLoaiTrangSuc(cbo_PhanLoai.getSelectedItem().toString().trim());
        String tenTrangSuc = txt_TenTrangSuc.getText();
        if (!txt_TenTrangSuc.getText().isEmpty()) {
            tenTrangSuc = txt_TenTrangSuc.getText().trim();
        }
        gdmd.setTenTrangSuc(tenTrangSuc);
        return gdmd;
    }

    void setDataSpTimKiem() {
        mainForm.clearItems();
        for (SanPham sp : rpct.getAllWithConditional(readModel())) {
            if (sp.isTrangThai()) {
                mainForm.addItem(new SanPham(sp.getIDSanPham(), sp.getTenSanPham(), sp.getSoLuongTonKho(), sp.getGiaChiTiet(), sp.getIDGiamGia(), sp.getHinhAnhSanPham()));
            }
        }
    }
    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed

        if (getInstance() != null) {
            update();
        }
        setDataSpTimKiem();
    }//GEN-LAST:event_button1ActionPerformed

    private void btn_TimKiemHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemHoaDonActionPerformed
        if (cbo_NhanVien.getSelectedItem().toString().equals("Tất Cả")) {
            bhs.fillToTableLichSu(tbl_LichSuHoaDon);
        } else {
            bhs.fillByCondition(tbl_LichSuHoaDon, txt_BatDau, txt_BatDau, cbo_NhanVien.getSelectedItem().toString());

        }
    }//GEN-LAST:event_btn_TimKiemHoaDonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel_HoaDon;
    private javax.swing.JPanel Panel_LichSu;
    private view.until.swing.Background SanPhamPanel;
    private view.until.swing.Background background1;
    private view.until.swing.Background background2;
    private view.until.swing.Background background3;
    private view.until.button.Button btn_Excel;
    private view.until.button.Button btn_Huy;
    private view.until.button.Button btn_TaoHoaDon;
    private view.until.button.Button btn_ThanhToan;
    private view.until.button.Button btn_TimKhachHang;
    private view.until.button.Button btn_TimKiemHoaDon;
    private view.until.button.Button button1;
    private view.until.button.Button button2;
    private view.until.combobox.ComboBoxSuggestion cbo_NhanVien;
    private view.until.combobox.ComboBoxSuggestion cbo_PhanLoai;
    private view.until.combobox.ComboBoxSuggestion cbo_Voucher;
    private javax.swing.JCheckBox ckb_SuDung;
    private com.raven.datechooser.DateChooser dateChooser1;
    private com.raven.datechooser.DateChooser dateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbl_ChietKhau;
    private javax.swing.JLabel lbl_GioHangHoaDon;
    private javax.swing.JLabel lbl_HoTen;
    private javax.swing.JLabel lbl_MaHoaDon;
    private javax.swing.JLabel lbl_ThanhToan;
    private javax.swing.JLabel lbl_ThongTin;
    private javax.swing.JLabel lbl_TichDiem;
    private javax.swing.JLabel lbl_TongTien;
    private javax.swing.JPanel panel_ThongTin;
    private javax.swing.JScrollPane scrollGH;
    private javax.swing.JScrollPane scrollHDC;
    private javax.swing.JScrollPane scrollLSHD;
    private view.until.tabbedpane.TabbedPaneCustom tabbedPaneCustom1;
    private javax.swing.JTable tbl_GioHang;
    private javax.swing.JTable tbl_HoaDonCho;
    private javax.swing.JTable tbl_LichSuHoaDon;
    private view.until.button.Button tbn_ThemKhachHang;
    private view.until.textfield.TextFieldSuggestion txt_BatDau;
    private view.until.textfield.TextFieldSuggestion txt_KetThuc;
    private view.until.textfield.TextFieldSuggestion txt_SoDienThoai;
    private view.until.textfield.TextFieldSuggestion txt_TenTrangSuc;
    // End of variables declaration//GEN-END:variables
}
