/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.sanpham;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import model.GiaoDien.GiaoDienSanPhamModel;
import model.SanPham;
import repository.SanPham.repoChiTietSanPham;
import service.GiaDienSanPhamService;
import service.observer.Observer;
import view.form.JTableHeader;
import view.khuyenmai.TableKhuyenMai;
import view.main.Main;

/**
 *
 * @author HUNGpYN
 */
public class GiaoDienSanPham extends javax.swing.JPanel implements Observer {

    private static GiaoDienSanPham instance;
    private List<SanPham> spList = new ArrayList<>();
    private repository.SanPham.repoChiTietSanPham rpsp = new repoChiTietSanPham();
    private GiaoDienSanPhamModel mdgd = new GiaoDienSanPhamModel();
    private GiaDienSanPhamService gdspsv = new GiaDienSanPhamService();
    private DefaultTableModel model;
    private Color color2 = Color.decode("#101820");// thanden
    private Color color1 = Color.decode("#FEE715"); //mau vang
    private String selectedID = null;
    private CapNhatSanPham cpspCapNhatSanPham;
    private ThemMoiSanPham tpspMoiSanPham;
    private Main main;

    //add flat
    static {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("view.until.sampletable.themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacLightLaf.setup();
    }

    public GiaoDienSanPham() {
        initComponents();
        init();
        fillToTable();
        List<String> tenPhanLoais = gdspsv.fillTocbo();
        for (String ten : tenPhanLoais) {
            cbosLoaiTrangSuc.addItem(ten);
        }
        tbl_SanPham.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Kiểm tra nhấp chuột hai lần
                    int row = tbl_SanPham.rowAtPoint(e.getPoint()); // Lấy chỉ số hàng được nhấp
                    if (row >= 0 && row < tbl_SanPham.getRowCount()) {
                        selectedID = (String) tbl_SanPham.getValueAt(row, tbl_SanPham.getColumnModel().getColumnIndex("Mã Trang Sức")).toString();
                        if (selectedID != null) {
                            cpspCapNhatSanPham = new CapNhatSanPham(main, true);
                            cpspCapNhatSanPham.setSelectedID(selectedID);
                            cpspCapNhatSanPham.setVisible(true);

                        } else {
                            JOptionPane.showMessageDialog(null, "Mã Trang Sức không hợp lệ.");
                        }
                    }
                }
            }
        });

    }

    private void init() {
        lbl_DanhSach.setForeground(color1);
        panel_ThemMoi.setBackground(color2);
        btn_TimKiem.setColor1(color2);
        btn_TimKiem.setColor2(color1);

        TableKhuyenMai tkm = new TableKhuyenMai();
        tkm.init(tbl_SanPham, scrollSP);
        tbl_SanPham.getTableHeader().setDefaultRenderer(new JTableHeader(tbl_SanPham));
        tbl_SanPham.getTableHeader().setReorderingAllowed(false);
    }

    public static GiaoDienSanPham getInstance() {
        if (instance == null) {
            instance = new GiaoDienSanPham();
        }
        return instance;
    }

    public String getSelectedID() {
        return selectedID;
    }

    void fillToTable() {
        // Khởi tạo DecimalFormatSymbols để cấu hình định dạng tiền tệ
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');

        // Tạo đối tượng DecimalFormat với mẫu định dạng
        DecimalFormat decimalFormat = new DecimalFormat("#,##0", symbols);

        // Lấy danh sách sản phẩm từ repository
        spList = rpsp.getAll();

        // Khởi tạo mô hình bảng và đặt lại số lượng hàng về 0 để xóa dữ liệu cũ
        model = (DefaultTableModel) tbl_SanPham.getModel();
        model.setRowCount(0);

        // Duyệt qua danh sách sản phẩm và thêm dữ liệu vào mô hình bảng
        int STT = 1;
        for (SanPham sp : spList) {
            // Lấy tên phân loại hoặc "N/A" nếu không có
            String tenPhanLoai = (sp.getIDPhanLoai() != null) ? sp.getIDPhanLoai().getTenPhanLoai() : "N/A";

            // Xác định trạng thái hoạt động
            String trangThai = sp.isTrangThai() ? "Kinh Doanh" : "Ngừng Kinh Doanh";

            // Định dạng giá chi tiết và số lượng tồn kho
            String giaChiTiet = decimalFormat.format(sp.getGiaChiTiet()) + " VNĐ"; // Định dạng số tiền và thêm "VNĐ"
            String soLuongTonKho = (sp.getSoLuongTonKho() > 0) ? String.valueOf(sp.getSoLuongTonKho()) : "Hết Hàng";

            // Tạo mảng đối tượng cho hàng trong bảng
            Object[] rowObject = {
                STT++,
                sp.getIDSanPham(),
                sp.getTenSanPham(),
                tenPhanLoai,
                sp.isGioiTinh() ? "Nam" : "Nữ",
                giaChiTiet,
                soLuongTonKho,
                trangThai
            };

            // Thêm hàng vào mô hình bảng
            model.addRow(rowObject);
        }

        // Cập nhật mô hình cho bảng (có thể không cần thiết nếu bạn chỉ thay đổi dữ liệu trong mô hình)
        tbl_SanPham.setModel(model);
    }

    void fillTOCheck(GiaoDienSanPhamModel gdspmd
    ) {
        // Khởi tạo DecimalFormatSymbols để cấu hình định dạng tiền tệ
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');

        // Tạo đối tượng DecimalFormat với mẫu định dạng
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);

        // Lấy danh sách sản phẩm từ repository
        spList = rpsp.getAllWithConditional(gdspmd);

        // Khởi tạo mô hình bảng và đặt lại số lượng hàng về 0 để xóa dữ liệu cũ
        model = (DefaultTableModel) tbl_SanPham.getModel();
        model.setRowCount(0);

        // Duyệt qua danh sách sản phẩm và thêm dữ liệu vào mô hình bảng
        int STT = 1;
        for (SanPham sp : spList) {
            // Lấy tên phân loại hoặc "N/A" nếu không có
            String tenPhanLoai = (sp.getIDPhanLoai() != null) ? sp.getIDPhanLoai().getTenPhanLoai() : "N/A";

            // Xác định trạng thái hoạt động
            String trangThai = sp.isTrangThai() ? "Kinh Doanh" : "Ngừng Kinh Doanh";

            // Định dạng giá chi tiết và số lượng tồn kho
            String giaChiTiet = decimalFormat.format(sp.getGiaChiTiet()) + " đ"; // Định dạng số tiền và thêm "VNĐ"
            String soLuongTonKho = (sp.getSoLuongTonKho() > 0) ? String.valueOf(sp.getSoLuongTonKho()) : "Hết Hàng";

            // Tạo mảng đối tượng cho hàng trong bảng
            Object[] rowObject = {
                STT++,
                sp.getIDSanPham(),
                sp.getTenSanPham(),
                tenPhanLoai,
                sp.isGioiTinh() ? "Nam" : "Nữ",
                giaChiTiet,
                soLuongTonKho,
                trangThai
            };

            // Thêm hàng vào mô hình bảng
            model.addRow(rowObject);
        }

        // Cập nhật mô hình cho bảng (có thể không cần thiết nếu bạn chỉ thay đổi dữ liệu trong mô hình)
        tbl_SanPham.setModel(model);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_ThemMoi = new javax.swing.JPanel();
        lbl_DanhSach = new javax.swing.JLabel();
        btn_TaoMoi = new view.until.button.Button();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbosTrangThai = new view.until.combobox.ComboBoxSuggestion();
        cbosLoaiTrangSuc = new view.until.combobox.ComboBoxSuggestion();
        cbosGioiTinh = new view.until.combobox.ComboBoxSuggestion();
        jLabel4 = new javax.swing.JLabel();
        txt_TimKiem = new view.until.textfield.TextFieldSuggestion();
        btn_TimKiem = new view.until.button.Button();
        jSeparator1 = new javax.swing.JSeparator();
        btn_Excel1 = new view.until.button.Button();
        scrollSP = new javax.swing.JScrollPane();
        tbl_SanPham = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        lbl_DanhSach.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        lbl_DanhSach.setText("Danh Sách Trang Sức");

        btn_TaoMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/add.png"))); // NOI18N
        btn_TaoMoi.setText("Tạo Mới");
        btn_TaoMoi.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btn_TaoMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TaoMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_ThemMoiLayout = new javax.swing.GroupLayout(panel_ThemMoi);
        panel_ThemMoi.setLayout(panel_ThemMoiLayout);
        panel_ThemMoiLayout.setHorizontalGroup(
            panel_ThemMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ThemMoiLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lbl_DanhSach)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_TaoMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        panel_ThemMoiLayout.setVerticalGroup(
            panel_ThemMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ThemMoiLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(panel_ThemMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_DanhSach)
                    .addComponent(btn_TaoMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel1.setText("Loại Trang Sức");

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel2.setText("Giới Tính");

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel3.setText("Trạng Thái");

        cbosTrangThai.setEditable(false);
        cbosTrangThai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất Cả", "Kinh Doanh", "Ngừng Kinh Doanh" }));
        cbosTrangThai.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cbosTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbosTrangThaiActionPerformed(evt);
            }
        });

        cbosLoaiTrangSuc.setEditable(false);
        cbosLoaiTrangSuc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất Cả" }));
        cbosLoaiTrangSuc.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cbosLoaiTrangSuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbosLoaiTrangSucActionPerformed(evt);
            }
        });

        cbosGioiTinh.setEditable(false);
        cbosGioiTinh.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất Cả", "Nam", "Nữ" }));
        cbosGioiTinh.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cbosGioiTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbosGioiTinhActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel4.setText("Tên Trang Sức");

        txt_TimKiem.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txt_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_TimKiemActionPerformed(evt);
            }
        });

        btn_TimKiem.setText("Tìm Kiếm");
        btn_TimKiem.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btn_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemActionPerformed(evt);
            }
        });

        btn_Excel1.setBorder(null);
        btn_Excel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/lammoi.png"))); // NOI18N
        btn_Excel1.setColor1(new java.awt.Color(16, 24, 32));
        btn_Excel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Excel1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbosLoaiTrangSuc, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(62, 62, 62)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbosGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(63, 63, 63)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(75, 75, 75))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbosTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addComponent(btn_Excel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(btn_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbosLoaiTrangSuc, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbosGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_Excel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbosTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        scrollSP.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 0, 0, 0));

        tbl_SanPham.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tbl_SanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Trang Sức", "Tên Trang Sức", "Phân Loại", "Giới Tính", "Giá Bán", "Tồn Kho", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollSP.setViewportView(tbl_SanPham);
        if (tbl_SanPham.getColumnModel().getColumnCount() > 0) {
            tbl_SanPham.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel5.setText("Danh Sách Trang Sức");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_ThemMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollSP)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel_ThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollSP, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbosTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbosTrangThaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbosTrangThaiActionPerformed

    private void cbosLoaiTrangSucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbosLoaiTrangSucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbosLoaiTrangSucActionPerformed

    private void cbosGioiTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbosGioiTinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbosGioiTinhActionPerformed

    private void btn_TaoMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TaoMoiActionPerformed
        tpspMoiSanPham = new ThemMoiSanPham(main, true);
        tpspMoiSanPham.setVisible(true);
    }//GEN-LAST:event_btn_TaoMoiActionPerformed

    private void txt_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_TimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_TimKiemActionPerformed

    private void btn_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemActionPerformed

        GiaoDienSanPhamModel GiaoDienSanPhamModel = new GiaoDienSanPhamModel();
        String searchText = txt_TimKiem.getText().trim();
        if (!searchText.isEmpty()) {
            GiaoDienSanPhamModel.setTenTrangSuc(txt_TimKiem.getText().trim());
        }
        GiaoDienSanPhamModel.setLoaiTrangSuc(cbosLoaiTrangSuc.getSelectedItem().toString());
        GiaoDienSanPhamModel.setTrangThai(cbosTrangThai.getSelectedIndex());
        GiaoDienSanPhamModel.setGioiTinh(cbosGioiTinh.getSelectedIndex());
        spList.clear();
        fillTOCheck(GiaoDienSanPhamModel);
    }//GEN-LAST:event_btn_TimKiemActionPerformed

    private void btn_Excel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Excel1ActionPerformed
        // TODO add your handling code here:
        cbosGioiTinh.setSelectedIndex(0);
        cbosLoaiTrangSuc.setSelectedIndex(0);
        cbosTrangThai.setSelectedIndex(0);
        txt_TimKiem.setText("");
    }//GEN-LAST:event_btn_Excel1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.until.button.Button btn_Excel1;
    private view.until.button.Button btn_TaoMoi;
    private view.until.button.Button btn_TimKiem;
    private view.until.combobox.ComboBoxSuggestion cbosGioiTinh;
    private view.until.combobox.ComboBoxSuggestion cbosLoaiTrangSuc;
    private view.until.combobox.ComboBoxSuggestion cbosTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbl_DanhSach;
    private javax.swing.JPanel panel_ThemMoi;
    private javax.swing.JScrollPane scrollSP;
    private javax.swing.JTable tbl_SanPham;
    private view.until.textfield.TextFieldSuggestion txt_TimKiem;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update() {
        fillToTable();
        tbl_SanPham.revalidate();
        tbl_SanPham.repaint();
    }
}
