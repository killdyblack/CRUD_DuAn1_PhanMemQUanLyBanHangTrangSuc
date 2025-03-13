/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view.sanpham;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Color;
import java.awt.Font;
import javax.swing.UIManager;
import repository.SanPham.repoChiTietSanPham;
import service.CapNhatSanPham.CapNhatSanPhamSv;
import service.taikhoan.ServiceTaiKhoan;
import until.validate.ValidateData;
import view.banhang.GiaoDienBanHang;
import view.form.JTableHeader;
import view.khuyenmai.TableKhuyenMai;
import view.until.hopthoai.Notification;

/**
 *
 * @author HUNGpYN
 */
public class ThemMoiSanPham extends javax.swing.JDialog {

    private service.taikhoan.ServiceTaiKhoan stk;
    private CapNhatSanPhamSv capNhatSanPhamSv = new CapNhatSanPhamSv();
    private repoChiTietSanPham rpChiTietSanPham = new repoChiTietSanPham();
    private Color color2 = Color.decode("#101820");// thanden
    private Color color1 = Color.decode("#FEE715"); //mau vang

    private ValidateData vld = new ValidateData();

    public ThemMoiSanPham(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        lbl_ThemMoi.setForeground(color1);
        pnl_ThemMoi.setBackground(color2);
        setLocationRelativeTo(null);
        btn_ThemAnh.setColor1(Color.LIGHT_GRAY);
        updateComboBoxes();
        fillAllTable();
        init();
    }

    // add flat
    private void init() {
        TableKhuyenMai tkm = new TableKhuyenMai();
        tkm.init(tbl_PhanLoai, scrollPL);
        tbl_PhanLoai.getTableHeader().setDefaultRenderer(new JTableHeader(tbl_PhanLoai));
        tbl_PhanLoai.getTableHeader().setReorderingAllowed(false);

        tkm.init(tbl_ChatLieu, scrollCL);
        tbl_ChatLieu.getTableHeader().setDefaultRenderer(new JTableHeader(tbl_ChatLieu));
        tbl_ChatLieu.getTableHeader().setReorderingAllowed(false);

        tkm.init(tbl_Size, scrollSZ);
        tbl_Size.getTableHeader().setDefaultRenderer(new JTableHeader(tbl_Size));
        tbl_Size.getTableHeader().setReorderingAllowed(false);

        tkm.init(tbl_NhaCungCAp, scrollCC);
        tbl_NhaCungCAp.getTableHeader().setDefaultRenderer(new JTableHeader(tbl_NhaCungCAp));
        tbl_NhaCungCAp.getTableHeader().setReorderingAllowed(false);

        tkm.init(tbl_DaQuy, scrollDQ);
        tbl_DaQuy.getTableHeader().setDefaultRenderer(new JTableHeader(tbl_DaQuy));
        tbl_DaQuy.getTableHeader().setReorderingAllowed(false);

        tkm.init(tbl_MauSac, scrollM);
        tbl_MauSac.getTableHeader().setDefaultRenderer(new JTableHeader(tbl_MauSac));
        tbl_MauSac.getTableHeader().setReorderingAllowed(false);

        tkm.init(tbl_KiemDinh, scrollKD);
        tbl_KiemDinh.getTableHeader().setDefaultRenderer(new JTableHeader(tbl_KiemDinh));
        tbl_KiemDinh.getTableHeader().setReorderingAllowed(false);

        txt_NgayKD.setText("dd - MM - yyyy");
    }

    public void updateComboBoxes() {
        if (capNhatSanPhamSv == null) {
            System.err.println("capNhatSanPhamSv is null.");
            return;
        }

        if (cbo_KiemDinh != null) {
            capNhatSanPhamSv.addCboKiemDinh(cbo_KiemDinh);
        } else {
            System.err.println("cboKiemDinh is null.");
        }

        if (cbos_Size != null) {
            capNhatSanPhamSv.addCboSize(cbos_Size);
        } else {
            System.err.println("cboSize is null.");
        }

        if (cbo_PhanLoai != null) {
            capNhatSanPhamSv.addCboPhanLoai(cbo_PhanLoai);
        } else {
            System.err.println("cboPhanLoai is null.");
        }

        if (cbo_ChatLieu != null) {
            capNhatSanPhamSv.addCboChatLieu(cbo_ChatLieu);
        } else {
            System.err.println("cboChatLieu is null.");
        }

        if (cbo_MauSac != null) {
            capNhatSanPhamSv.addCboMauSac(cbo_MauSac);
        } else {
            System.err.println("cboMauSac is null.");
        }

        if (cbo_LoaiDa1 != null) {
            capNhatSanPhamSv.addCboLoaiDa(cbo_LoaiDa1);
        } else {
            System.err.println("cboLoaiDa is null.");
        }

        if (cbo_NhaCungCap != null) {
            capNhatSanPhamSv.addCboNhaCungCap(cbo_NhaCungCap);
        } else {
            System.err.println("cboNhaCungCap is null.");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableDark2 = new view.until.table.TableDark();
        buttonGroup6 = new javax.swing.ButtonGroup();
        buttonGroup7 = new javax.swing.ButtonGroup();
        buttonGroup8 = new javax.swing.ButtonGroup();
        buttonGroup9 = new javax.swing.ButtonGroup();
        dateChooser1 = new com.raven.datechooser.DateChooser();
        buttonGroup10 = new javax.swing.ButtonGroup();
        tabbedPaneCustom1 = new view.until.tabbedpane.TabbedPaneCustom();
        panel_ThemSanPham = new javax.swing.JPanel();
        pnl_ThemMoi = new javax.swing.JPanel();
        lbl_ThemMoi = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_TenTrangSuc = new view.until.textfield.TextFieldSuggestion();
        jLabel3 = new javax.swing.JLabel();
        txt_GiaBan = new view.until.textfield.TextFieldSuggestion();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_TrongLuong = new view.until.textfield.TextFieldSuggestion();
        jLabel6 = new javax.swing.JLabel();
        txt_TonKho = new view.until.textfield.TextFieldSuggestion();
        jLabel7 = new javax.swing.JLabel();
        txt_SoLuongDa = new view.until.textfield.TextFieldSuggestion();
        jLabel8 = new javax.swing.JLabel();
        txt_KichThuocDa = new view.until.textfield.TextFieldSuggestion();
        jLabel9 = new javax.swing.JLabel();
        cbos_Size = new view.until.combobox.ComboBoxSuggestion();
        jLabel10 = new javax.swing.JLabel();
        cbo_PhanLoai = new view.until.combobox.ComboBoxSuggestion();
        jLabel11 = new javax.swing.JLabel();
        cbo_KiemDinh = new view.until.combobox.ComboBoxSuggestion();
        jLabel12 = new javax.swing.JLabel();
        cbo_MauSac = new view.until.combobox.ComboBoxSuggestion();
        jLabel13 = new javax.swing.JLabel();
        cbo_ChatLieu = new view.until.combobox.ComboBoxSuggestion();
        jLabel14 = new javax.swing.JLabel();
        cbo_LoaiDa1 = new view.until.combobox.ComboBoxSuggestion();
        jLabel15 = new javax.swing.JLabel();
        cbo_NhaCungCap = new view.until.combobox.ComboBoxSuggestion();
        jLabel16 = new javax.swing.JLabel();
        btn_ThemAnh = new view.until.button.Button();
        btnHuy = new view.until.button.Button();
        btnLuu = new view.until.button.Button();
        lbl_HinhAnh = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        rdoKinhDoanh = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        Panel_ThemThuocTinh = new javax.swing.JPanel();
        materialTabbed1 = new view.until.tabbedpane.MaterialTabbed();
        PanelPhanLoai = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_MaPhanLoai = new view.until.textfield.TextFieldSuggestion();
        jLabel17 = new javax.swing.JLabel();
        txt_TenPhanLoai = new view.until.textfield.TextFieldSuggestion();
        jLabel18 = new javax.swing.JLabel();
        btnUpdatePL = new view.until.button.Button();
        btnAddPL = new view.until.button.Button();
        rdo_HoatDongPL = new javax.swing.JRadioButton();
        rdo_NgungHoatDongPL = new javax.swing.JRadioButton();
        jLabel37 = new javax.swing.JLabel();
        scrollPL = new javax.swing.JScrollPane();
        tbl_PhanLoai = new javax.swing.JTable();
        btn_LamMoiPL = new view.until.button.Button();
        PanelChatLieu = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        txt_MaChatLieu = new view.until.textfield.TextFieldSuggestion();
        jLabel21 = new javax.swing.JLabel();
        txt_TyLe = new view.until.textfield.TextFieldSuggestion();
        jLabel22 = new javax.swing.JLabel();
        txt_TenChatLieu = new view.until.textfield.TextFieldSuggestion();
        jLabel23 = new javax.swing.JLabel();
        btn_CapNhatCL = new view.until.button.Button();
        btn_ThemCL = new view.until.button.Button();
        rdo_HoatDong_CL = new javax.swing.JRadioButton();
        rdo_NHoatDong_CL = new javax.swing.JRadioButton();
        jLabel46 = new javax.swing.JLabel();
        scrollCL = new javax.swing.JScrollPane();
        tbl_ChatLieu = new javax.swing.JTable();
        btn_LamMoiCL = new view.until.button.Button();
        PanelSize = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        txt_SoSize = new view.until.textfield.TextFieldSuggestion();
        txt_MaSize = new view.until.textfield.TextFieldSuggestion();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        btn_UpdateSize = new view.until.button.Button();
        btn_ThemS = new view.until.button.Button();
        rdo_HSZ = new javax.swing.JRadioButton();
        rdo_NSZ = new javax.swing.JRadioButton();
        scrollSZ = new javax.swing.JScrollPane();
        tbl_Size = new javax.swing.JTable();
        jLabel47 = new javax.swing.JLabel();
        btn_LamMoiS = new view.until.button.Button();
        PanelNhaCungCap = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        txt_SoDienThoai = new view.until.textfield.TextFieldSuggestion();
        jLabel28 = new javax.swing.JLabel();
        txt_MaNhaCungCap = new view.until.textfield.TextFieldSuggestion();
        jLabel29 = new javax.swing.JLabel();
        txt_Email = new view.until.textfield.TextFieldSuggestion();
        jLabel30 = new javax.swing.JLabel();
        bnt_ThemNhaCungCap = new view.until.button.Button();
        btn_CapNhatNhaCungCap = new view.until.button.Button();
        jLabel31 = new javax.swing.JLabel();
        txt_TenNhaCungCap = new view.until.textfield.TextFieldSuggestion();
        jLabel32 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtA_DiaChi = new javax.swing.JTextArea();
        rdo_HNCC = new javax.swing.JRadioButton();
        rdo_NNCC = new javax.swing.JRadioButton();
        scrollCC = new javax.swing.JScrollPane();
        tbl_NhaCungCAp = new javax.swing.JTable();
        jLabel48 = new javax.swing.JLabel();
        tbn_LamMoiNhaCC = new view.until.button.Button();
        PanelMauSac = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        txt_MaMau = new view.until.textfield.TextFieldSuggestion();
        jLabel34 = new javax.swing.JLabel();
        txt_TenMau = new view.until.textfield.TextFieldSuggestion();
        btn_UpdateMS = new view.until.button.Button();
        btn_AddMS = new view.until.button.Button();
        jLabel38 = new javax.swing.JLabel();
        rdo_HMS = new javax.swing.JRadioButton();
        rdo_NMS = new javax.swing.JRadioButton();
        jLabel49 = new javax.swing.JLabel();
        scrollM = new javax.swing.JScrollPane();
        tbl_MauSac = new javax.swing.JTable();
        btn_LamMoiMS = new view.until.button.Button();
        PanelDaQuy = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        txt_MaDQ = new view.until.textfield.TextFieldSuggestion();
        txt_TenDQ = new view.until.textfield.TextFieldSuggestion();
        jLabel41 = new javax.swing.JLabel();
        btn_CapNhatDaQuy = new view.until.button.Button();
        btn_DaQuy_them = new view.until.button.Button();
        jLabel50 = new javax.swing.JLabel();
        rdo_HDQ = new javax.swing.JRadioButton();
        rdo_NDQ = new javax.swing.JRadioButton();
        scrollDQ = new javax.swing.JScrollPane();
        tbl_DaQuy = new javax.swing.JTable();
        btn_CapNhatKD1 = new view.until.button.Button();
        PanelKiemDinh = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        txt_MaKD = new view.until.textfield.TextFieldSuggestion();
        jLabel43 = new javax.swing.JLabel();
        txt_DonViKD = new view.until.textfield.TextFieldSuggestion();
        btn_CapNhatKd = new view.until.button.Button();
        bnt_Them_KiemDinh = new view.until.button.Button();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        txt_NgayKD = new view.until.textfield.TextFieldSuggestion();
        scrollKD = new javax.swing.JScrollPane();
        tbl_KiemDinh = new javax.swing.JTable();
        jLabel51 = new javax.swing.JLabel();
        rdo_HKD = new javax.swing.JRadioButton();
        rdo_NKD = new javax.swing.JRadioButton();
        btn_LamMoi = new view.until.button.Button();

        tableDark2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tableDark2);

        dateChooser1.setForeground(new java.awt.Color(102, 102, 102));
        dateChooser1.setTextRefernce(txt_NgayKD);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        tabbedPaneCustom1.setBackground(new java.awt.Color(255, 255, 255));
        tabbedPaneCustom1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tabbedPaneCustom1.setSelectedColor(new java.awt.Color(255, 255, 0));

        panel_ThemSanPham.setBackground(new java.awt.Color(255, 255, 255));
        panel_ThemSanPham.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_ThemMoi.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        lbl_ThemMoi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_ThemMoi.setText("Thêm Mới Trang Sức");

        javax.swing.GroupLayout pnl_ThemMoiLayout = new javax.swing.GroupLayout(pnl_ThemMoi);
        pnl_ThemMoi.setLayout(pnl_ThemMoiLayout);
        pnl_ThemMoiLayout.setHorizontalGroup(
            pnl_ThemMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_ThemMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnl_ThemMoiLayout.setVerticalGroup(
            pnl_ThemMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_ThemMoiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_ThemMoi)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        panel_ThemSanPham.add(pnl_ThemMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 733, -1));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel2.setText("Tên Trang Sức");
        panel_ThemSanPham.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 57, -1, -1));

        txt_TenTrangSuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_TenTrangSucActionPerformed(evt);
            }
        });
        panel_ThemSanPham.add(txt_TenTrangSuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 80, 444, 33));

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel3.setText("Giá Bán");
        panel_ThemSanPham.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 128, -1, -1));
        panel_ThemSanPham.add(txt_GiaBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(243, 151, 183, 33));

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel4.setText("Giới Tính");
        panel_ThemSanPham.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(469, 128, -1, -1));

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel5.setText("Tồn Kho");
        panel_ThemSanPham.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 199, -1, -1));
        panel_ThemSanPham.add(txt_TrongLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(481, 294, 202, 33));

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel6.setText("Số Lượng Đá Quý");
        panel_ThemSanPham.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(475, 199, -1, -1));
        panel_ThemSanPham.add(txt_TonKho, new org.netbeans.lib.awtextra.AbsoluteConstraints(243, 222, 183, 33));

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel7.setText("Kích Thước Đá Quý");
        panel_ThemSanPham.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 270, -1, -1));
        panel_ThemSanPham.add(txt_SoLuongDa, new org.netbeans.lib.awtextra.AbsoluteConstraints(481, 222, 202, 33));

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel8.setText("Trọng Lượng");
        panel_ThemSanPham.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(475, 270, -1, -1));
        panel_ThemSanPham.add(txt_KichThuocDa, new org.netbeans.lib.awtextra.AbsoluteConstraints(243, 294, 183, 33));

        jLabel9.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel9.setText("Size");
        panel_ThemSanPham.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 342, -1, -1));

        cbos_Size.setEditable(false);
        panel_ThemSanPham.add(cbos_Size, new org.netbeans.lib.awtextra.AbsoluteConstraints(243, 365, 183, -1));

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel10.setText("Phân Loại");
        panel_ThemSanPham.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(475, 342, -1, -1));

        cbo_PhanLoai.setEditable(false);
        panel_ThemSanPham.add(cbo_PhanLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(481, 365, 202, -1));

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel11.setText("Màu Sắc");
        panel_ThemSanPham.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 413, -1, -1));

        cbo_KiemDinh.setEditable(false);
        panel_ThemSanPham.add(cbo_KiemDinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(243, 578, 438, -1));

        jLabel12.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel12.setText("Chất Liệu");
        panel_ThemSanPham.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(475, 413, -1, -1));

        cbo_MauSac.setEditable(false);
        panel_ThemSanPham.add(cbo_MauSac, new org.netbeans.lib.awtextra.AbsoluteConstraints(243, 436, 183, -1));

        jLabel13.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel13.setText("Loại Đá");
        panel_ThemSanPham.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 484, -1, -1));

        cbo_ChatLieu.setEditable(false);
        panel_ThemSanPham.add(cbo_ChatLieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(481, 436, 202, -1));

        jLabel14.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel14.setText("Kiểm Định");
        panel_ThemSanPham.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 555, -1, -1));

        cbo_LoaiDa1.setEditable(false);
        panel_ThemSanPham.add(cbo_LoaiDa1, new org.netbeans.lib.awtextra.AbsoluteConstraints(243, 507, 183, -1));

        jLabel15.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel15.setText("Nhà Cung Cấp");
        panel_ThemSanPham.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 623, -1, -1));

        cbo_NhaCungCap.setEditable(false);
        panel_ThemSanPham.add(cbo_NhaCungCap, new org.netbeans.lib.awtextra.AbsoluteConstraints(243, 646, 438, -1));

        jLabel16.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel16.setText("Trạng Thái");
        panel_ThemSanPham.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(475, 484, -1, -1));

        btn_ThemAnh.setText("Chọn Ảnh");
        btn_ThemAnh.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btn_ThemAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemAnhActionPerformed(evt);
            }
        });
        panel_ThemSanPham.add(btn_ThemAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 296, 107, 28));

        btnHuy.setText("Đóng");
        btnHuy.setColor1(new java.awt.Color(16, 24, 12));
        btnHuy.setColor2(new java.awt.Color(254, 231, 21));
        btnHuy.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btnHuy.setPreferredSize(new java.awt.Dimension(100, 31));
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });
        panel_ThemSanPham.add(btnHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 707, 90, -1));

        btnLuu.setText("Lưu");
        btnLuu.setColor1(new java.awt.Color(16, 24, 12));
        btnLuu.setColor2(new java.awt.Color(254, 231, 21));
        btnLuu.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btnLuu.setPreferredSize(new java.awt.Dimension(100, 31));
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        panel_ThemSanPham.add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(417, 707, 90, -1));
        panel_ThemSanPham.add(lbl_HinhAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 60, 175, 228));

        buttonGroup1.add(rdoNam);
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");
        panel_ThemSanPham.add(rdoNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(475, 151, -1, -1));

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");
        panel_ThemSanPham.add(rdoNu, new org.netbeans.lib.awtextra.AbsoluteConstraints(598, 151, -1, -1));

        buttonGroup2.add(rdoKinhDoanh);
        rdoKinhDoanh.setSelected(true);
        rdoKinhDoanh.setText("Kinh Doanh");
        panel_ThemSanPham.add(rdoKinhDoanh, new org.netbeans.lib.awtextra.AbsoluteConstraints(481, 513, -1, -1));

        buttonGroup2.add(jRadioButton4);
        jRadioButton4.setText("Ngừng Kinh Doanh");
        panel_ThemSanPham.add(jRadioButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(575, 513, -1, -1));

        jLabel20.setForeground(new java.awt.Color(153, 153, 153));
        jLabel20.setText("VNĐ");
        panel_ThemSanPham.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 160, -1, -1));

        jLabel35.setForeground(new java.awt.Color(153, 153, 153));
        jLabel35.setText("mm");
        panel_ThemSanPham.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 300, -1, -1));

        jLabel36.setForeground(new java.awt.Color(153, 153, 153));
        jLabel36.setText("Carat");
        panel_ThemSanPham.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 300, -1, -1));

        tabbedPaneCustom1.addTab("Trang Sức", panel_ThemSanPham);

        Panel_ThemThuocTinh.setBackground(new java.awt.Color(255, 255, 255));

        materialTabbed1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        PanelPhanLoai.setBackground(new java.awt.Color(255, 255, 255));
        PanelPhanLoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PanelPhanLoaiMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel1.setText("Mã Phân Loại");

        txt_MaPhanLoai.setEditable(false);
        txt_MaPhanLoai.setFocusable(false);

        jLabel17.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel17.setText("Tên Phân Loại");

        txt_TenPhanLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_TenPhanLoaiActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel18.setText("Trạng Thái");

        btnUpdatePL.setText("Cập Nhật");
        btnUpdatePL.setColor1(new java.awt.Color(16, 24, 32));
        btnUpdatePL.setColor2(new java.awt.Color(254, 231, 21));
        btnUpdatePL.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btnUpdatePL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdatePLActionPerformed(evt);
            }
        });

        btnAddPL.setText("Thêm");
        btnAddPL.setColor1(new java.awt.Color(16, 24, 32));
        btnAddPL.setColor2(new java.awt.Color(254, 231, 21));
        btnAddPL.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btnAddPL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPLActionPerformed(evt);
            }
        });

        buttonGroup3.add(rdo_HoatDongPL);
        rdo_HoatDongPL.setSelected(true);
        rdo_HoatDongPL.setText("Kinh Doanh");

        buttonGroup3.add(rdo_NgungHoatDongPL);
        rdo_NgungHoatDongPL.setText("Ngừng Kinh Doanh");

        jLabel37.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel37.setText("Danh Sách Phân Loại");

        scrollPL.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tbl_PhanLoai.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tbl_PhanLoai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Mã Phân Loại", "Tên Phân Loại", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_PhanLoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_PhanLoaiMouseClicked(evt);
            }
        });
        scrollPL.setViewportView(tbl_PhanLoai);
        if (tbl_PhanLoai.getColumnModel().getColumnCount() > 0) {
            tbl_PhanLoai.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        btn_LamMoiPL.setText("Làm Mới");
        btn_LamMoiPL.setColor1(new java.awt.Color(16, 24, 32));
        btn_LamMoiPL.setColor2(new java.awt.Color(254, 231, 21));
        btn_LamMoiPL.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btn_LamMoiPL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiPLActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelPhanLoaiLayout = new javax.swing.GroupLayout(PanelPhanLoai);
        PanelPhanLoai.setLayout(PanelPhanLoaiLayout);
        PanelPhanLoaiLayout.setHorizontalGroup(
            PanelPhanLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPhanLoaiLayout.createSequentialGroup()
                .addGroup(PanelPhanLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelPhanLoaiLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PanelPhanLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelPhanLoaiLayout.createSequentialGroup()
                                .addComponent(jLabel37)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 608, Short.MAX_VALUE))
                            .addComponent(scrollPL, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(PanelPhanLoaiLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(PanelPhanLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel17)
                            .addComponent(jLabel1)
                            .addGroup(PanelPhanLoaiLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(PanelPhanLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(PanelPhanLoaiLayout.createSequentialGroup()
                                        .addComponent(rdo_HoatDongPL)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdo_NgungHoatDongPL))
                                    .addComponent(txt_MaPhanLoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_TenPhanLoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(PanelPhanLoaiLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_LamMoiPL, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97)
                .addComponent(btnUpdatePL, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addComponent(btnAddPL, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(139, 139, 139))
        );
        PanelPhanLoaiLayout.setVerticalGroup(
            PanelPhanLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPhanLoaiLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_MaPhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_TenPhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelPhanLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdo_HoatDongPL)
                    .addComponent(rdo_NgungHoatDongPL))
                .addGap(34, 34, 34)
                .addGroup(PanelPhanLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdatePL, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddPL, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_LamMoiPL, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPL, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        materialTabbed1.addTab("Phân Loại", PanelPhanLoai);

        PanelChatLieu.setBackground(new java.awt.Color(255, 255, 255));
        PanelChatLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PanelChatLieuMouseClicked(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel19.setText("Mã Chất Liệu");

        txt_MaChatLieu.setEditable(false);
        txt_MaChatLieu.setFocusable(false);
        txt_MaChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_MaChatLieuActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel21.setText("Tên Chất Liệu");

        jLabel22.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel22.setText("Tỷ Lệ");

        jLabel23.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel23.setText("Trạng Thái");

        btn_CapNhatCL.setText("Cập Nhật");
        btn_CapNhatCL.setColor1(new java.awt.Color(16, 24, 32));
        btn_CapNhatCL.setColor2(new java.awt.Color(254, 231, 21));
        btn_CapNhatCL.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btn_CapNhatCL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CapNhatCLActionPerformed(evt);
            }
        });

        btn_ThemCL.setText("Thêm");
        btn_ThemCL.setColor1(new java.awt.Color(16, 24, 32));
        btn_ThemCL.setColor2(new java.awt.Color(254, 231, 21));
        btn_ThemCL.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btn_ThemCL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemCLActionPerformed(evt);
            }
        });

        buttonGroup4.add(rdo_HoatDong_CL);
        rdo_HoatDong_CL.setSelected(true);
        rdo_HoatDong_CL.setText("Kinh Doanh");

        buttonGroup4.add(rdo_NHoatDong_CL);
        rdo_NHoatDong_CL.setText("Ngừng Kinh Doanh");

        jLabel46.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel46.setText("Danh Sách Chất Liệu");

        scrollCL.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 0, 0, 0));

        tbl_ChatLieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Chất Liệu", "Tên Chất Liệu", "Tỷ Lệ (%)", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_ChatLieu.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tbl_ChatLieuAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tbl_ChatLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_ChatLieuMouseClicked(evt);
            }
        });
        scrollCL.setViewportView(tbl_ChatLieu);
        if (tbl_ChatLieu.getColumnModel().getColumnCount() > 0) {
            tbl_ChatLieu.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        btn_LamMoiCL.setText("Làm Mới");
        btn_LamMoiCL.setColor1(new java.awt.Color(16, 24, 32));
        btn_LamMoiCL.setColor2(new java.awt.Color(254, 231, 21));
        btn_LamMoiCL.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btn_LamMoiCL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiCLActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelChatLieuLayout = new javax.swing.GroupLayout(PanelChatLieu);
        PanelChatLieu.setLayout(PanelChatLieuLayout);
        PanelChatLieuLayout.setHorizontalGroup(
            PanelChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelChatLieuLayout.createSequentialGroup()
                .addGroup(PanelChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelChatLieuLayout.createSequentialGroup()
                        .addGroup(PanelChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelChatLieuLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(PanelChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel23)
                                    .addGroup(PanelChatLieuLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(PanelChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(PanelChatLieuLayout.createSequentialGroup()
                                                .addComponent(rdo_HoatDong_CL)
                                                .addGap(49, 49, 49)
                                                .addComponent(rdo_NHoatDong_CL))
                                            .addComponent(txt_TyLe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txt_TenChatLieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txt_MaChatLieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addGroup(PanelChatLieuLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 421, Short.MAX_VALUE))
                    .addGroup(PanelChatLieuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollCL)))
                .addContainerGap())
            .addGroup(PanelChatLieuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_LamMoiCL, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84)
                .addComponent(btn_CapNhatCL, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addComponent(btn_ThemCL, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(153, 153, 153))
        );
        PanelChatLieuLayout.setVerticalGroup(
            PanelChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelChatLieuLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_MaChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_TenChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_TyLe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdo_HoatDong_CL)
                    .addComponent(rdo_NHoatDong_CL))
                .addGap(18, 18, 18)
                .addGroup(PanelChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_CapNhatCL, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ThemCL, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_LamMoiCL, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel46)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollCL, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                .addContainerGap())
        );

        materialTabbed1.addTab("Chất Liệu", PanelChatLieu);

        PanelSize.setBackground(new java.awt.Color(255, 255, 255));
        PanelSize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PanelSizeMouseClicked(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel24.setText("Số Size");

        txt_SoSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SoSizeActionPerformed(evt);
            }
        });

        txt_MaSize.setEditable(false);
        txt_MaSize.setFocusable(false);

        jLabel25.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel25.setText("Mã Size");

        jLabel26.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel26.setText("Trạng Thái");

        btn_UpdateSize.setText("Cập Nhật");
        btn_UpdateSize.setColor1(new java.awt.Color(16, 24, 32));
        btn_UpdateSize.setColor2(new java.awt.Color(254, 231, 21));
        btn_UpdateSize.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btn_UpdateSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UpdateSizeActionPerformed(evt);
            }
        });

        btn_ThemS.setText("Thêm");
        btn_ThemS.setColor1(new java.awt.Color(16, 24, 32));
        btn_ThemS.setColor2(new java.awt.Color(254, 231, 21));
        btn_ThemS.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btn_ThemS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemSActionPerformed(evt);
            }
        });

        buttonGroup5.add(rdo_HSZ);
        rdo_HSZ.setSelected(true);
        rdo_HSZ.setText("Kinh Doanh");

        buttonGroup5.add(rdo_NSZ);
        rdo_NSZ.setText("Ngừng Kinh Doanh");

        scrollSZ.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        tbl_Size.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Mã Size", "Số Size", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_Size.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_SizeMouseClicked(evt);
            }
        });
        scrollSZ.setViewportView(tbl_Size);
        if (tbl_Size.getColumnModel().getColumnCount() > 0) {
            tbl_Size.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jLabel47.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel47.setText("Danh Sách Size");

        btn_LamMoiS.setText("Làm Mới");
        btn_LamMoiS.setColor1(new java.awt.Color(16, 24, 32));
        btn_LamMoiS.setColor2(new java.awt.Color(254, 231, 21));
        btn_LamMoiS.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btn_LamMoiS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelSizeLayout = new javax.swing.GroupLayout(PanelSize);
        PanelSize.setLayout(PanelSizeLayout);
        PanelSizeLayout.setHorizontalGroup(
            PanelSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSizeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollSZ, javax.swing.GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(PanelSizeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel47)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(PanelSizeLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(PanelSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelSizeLayout.createSequentialGroup()
                        .addGroup(PanelSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26)
                            .addGroup(PanelSizeLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(PanelSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(PanelSizeLayout.createSequentialGroup()
                                        .addComponent(rdo_HSZ)
                                        .addGap(32, 32, 32)
                                        .addComponent(rdo_NSZ))
                                    .addComponent(txt_SoSize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_MaSize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PanelSizeLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_LamMoiS, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addComponent(btn_UpdateSize, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)))
                .addComponent(btn_ThemS, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(153, 153, 153))
        );
        PanelSizeLayout.setVerticalGroup(
            PanelSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSizeLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_MaSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_SoSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdo_HSZ)
                    .addComponent(rdo_NSZ))
                .addGap(35, 35, 35)
                .addGroup(PanelSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_ThemS, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_UpdateSize, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_LamMoiS, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(jLabel47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollSZ, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        materialTabbed1.addTab("Size", PanelSize);

        PanelNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        PanelNhaCungCap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PanelNhaCungCapMouseClicked(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel27.setText("Mã Nhà Cung Cấp");

        txt_SoDienThoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SoDienThoaiActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel28.setText("Số Điện Thoại");

        txt_MaNhaCungCap.setEditable(false);
        txt_MaNhaCungCap.setFocusable(false);

        jLabel29.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel29.setText("Email");

        txt_Email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_EmailActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel30.setText("Địa Chỉ");

        bnt_ThemNhaCungCap.setText("Thêm");
        bnt_ThemNhaCungCap.setColor1(new java.awt.Color(16, 24, 32));
        bnt_ThemNhaCungCap.setColor2(new java.awt.Color(254, 231, 21));
        bnt_ThemNhaCungCap.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        bnt_ThemNhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnt_ThemNhaCungCapActionPerformed(evt);
            }
        });

        btn_CapNhatNhaCungCap.setText("Cập Nhật");
        btn_CapNhatNhaCungCap.setColor1(new java.awt.Color(16, 24, 32));
        btn_CapNhatNhaCungCap.setColor2(new java.awt.Color(254, 231, 21));
        btn_CapNhatNhaCungCap.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btn_CapNhatNhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CapNhatNhaCungCapActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel31.setText("Tên Nhà Cung Cấp");

        jLabel32.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel32.setText("Trạng Thái");

        txtA_DiaChi.setColumns(20);
        txtA_DiaChi.setRows(5);
        jScrollPane1.setViewportView(txtA_DiaChi);

        buttonGroup6.add(rdo_HNCC);
        rdo_HNCC.setSelected(true);
        rdo_HNCC.setText("Hoạt Động");

        buttonGroup6.add(rdo_NNCC);
        rdo_NNCC.setText("Ngừng Hoạt Đông");

        scrollCC.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        tbl_NhaCungCAp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Nhà Cung Cấp", "Nhà Cung Cấp", "Số Điện Thoại", "Email", "Địa Chỉ", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_NhaCungCAp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_NhaCungCApMouseClicked(evt);
            }
        });
        scrollCC.setViewportView(tbl_NhaCungCAp);
        if (tbl_NhaCungCAp.getColumnModel().getColumnCount() > 0) {
            tbl_NhaCungCAp.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jLabel48.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel48.setText("Danh Sách Nhà Cung Cấp");

        tbn_LamMoiNhaCC.setText("Làm Mới");
        tbn_LamMoiNhaCC.setColor1(new java.awt.Color(16, 24, 32));
        tbn_LamMoiNhaCC.setColor2(new java.awt.Color(254, 231, 21));
        tbn_LamMoiNhaCC.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        tbn_LamMoiNhaCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbn_LamMoiNhaCCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelNhaCungCapLayout = new javax.swing.GroupLayout(PanelNhaCungCap);
        PanelNhaCungCap.setLayout(PanelNhaCungCapLayout);
        PanelNhaCungCapLayout.setHorizontalGroup(
            PanelNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelNhaCungCapLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(PanelNhaCungCapLayout.createSequentialGroup()
                .addGroup(PanelNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelNhaCungCapLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollCC))
                    .addGroup(PanelNhaCungCapLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(PanelNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelNhaCungCapLayout.createSequentialGroup()
                                .addGroup(PanelNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanelNhaCungCapLayout.createSequentialGroup()
                                        .addGroup(PanelNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel31)
                                            .addGroup(PanelNhaCungCapLayout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addGroup(PanelNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(txt_MaNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(txt_Email, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(txt_TenNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(PanelNhaCungCapLayout.createSequentialGroup()
                                        .addGroup(PanelNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel29)
                                            .addGroup(PanelNhaCungCapLayout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addGroup(PanelNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel32)
                                                    .addGroup(PanelNhaCungCapLayout.createSequentialGroup()
                                                        .addGap(6, 6, 6)
                                                        .addComponent(rdo_HNCC)
                                                        .addGap(45, 45, 45)
                                                        .addComponent(rdo_NNCC)))))
                                        .addGap(94, 94, 94)))
                                .addGap(8, 8, 8)
                                .addGroup(PanelNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel28)
                                    .addComponent(jLabel30)
                                    .addGroup(PanelNhaCungCapLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(PanelNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txt_SoDienThoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(57, 57, 57))
                            .addGroup(PanelNhaCungCapLayout.createSequentialGroup()
                                .addGroup(PanelNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel27)
                                    .addGroup(PanelNhaCungCapLayout.createSequentialGroup()
                                        .addGap(101, 101, 101)
                                        .addComponent(tbn_LamMoiNhaCC, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(58, 58, 58)
                                        .addComponent(btn_CapNhatNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(53, 53, 53)
                                        .addComponent(bnt_ThemNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        PanelNhaCungCapLayout.setVerticalGroup(
            PanelNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelNhaCungCapLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_MaNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PanelNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelNhaCungCapLayout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_SoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(PanelNhaCungCapLayout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_TenNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdo_NNCC)
                            .addComponent(rdo_HNCC))))
                .addGap(18, 18, 18)
                .addGroup(PanelNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_CapNhatNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bnt_ThemNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbn_LamMoiNhaCC, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(jLabel48)
                .addGap(8, 8, 8)
                .addComponent(scrollCC, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                .addContainerGap())
        );

        materialTabbed1.addTab("Nhà Cung Cấp", PanelNhaCungCap);

        PanelMauSac.setBackground(new java.awt.Color(255, 255, 255));
        PanelMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PanelMauSacMouseClicked(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel33.setText("Mã Màu");

        txt_MaMau.setEditable(false);
        txt_MaMau.setFocusable(false);
        txt_MaMau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_MaMauActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel34.setText("Tên Màu");

        btn_UpdateMS.setText("Cập Nhật");
        btn_UpdateMS.setColor1(new java.awt.Color(16, 24, 32));
        btn_UpdateMS.setColor2(new java.awt.Color(254, 231, 21));
        btn_UpdateMS.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btn_UpdateMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UpdateMSActionPerformed(evt);
            }
        });

        btn_AddMS.setText("Thêm");
        btn_AddMS.setColor1(new java.awt.Color(16, 24, 32));
        btn_AddMS.setColor2(new java.awt.Color(254, 231, 21));
        btn_AddMS.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btn_AddMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AddMSActionPerformed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel38.setText("Trạng Thái");

        buttonGroup7.add(rdo_HMS);
        rdo_HMS.setSelected(true);
        rdo_HMS.setText("Kinh Doanh");

        buttonGroup7.add(rdo_NMS);
        rdo_NMS.setText("Ngừng Kinh Doanh");

        jLabel49.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel49.setText("Danh Sách Màu");

        scrollM.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        tbl_MauSac.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Mã Màu", "Màu", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_MauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_MauSacMouseClicked(evt);
            }
        });
        scrollM.setViewportView(tbl_MauSac);
        if (tbl_MauSac.getColumnModel().getColumnCount() > 0) {
            tbl_MauSac.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        btn_LamMoiMS.setText("Làm Mới");
        btn_LamMoiMS.setColor1(new java.awt.Color(16, 24, 32));
        btn_LamMoiMS.setColor2(new java.awt.Color(254, 231, 21));
        btn_LamMoiMS.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btn_LamMoiMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiMSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelMauSacLayout = new javax.swing.GroupLayout(PanelMauSac);
        PanelMauSac.setLayout(PanelMauSacLayout);
        PanelMauSacLayout.setHorizontalGroup(
            PanelMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMauSacLayout.createSequentialGroup()
                .addGroup(PanelMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelMauSacLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollM))
                    .addGroup(PanelMauSacLayout.createSequentialGroup()
                        .addGroup(PanelMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelMauSacLayout.createSequentialGroup()
                                .addGap(118, 118, 118)
                                .addComponent(btn_LamMoiMS, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(65, 65, 65)
                                .addComponent(btn_UpdateMS, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)
                                .addComponent(btn_AddMS, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelMauSacLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(PanelMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel34)
                                    .addComponent(jLabel33)
                                    .addComponent(jLabel38)
                                    .addGroup(PanelMauSacLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(PanelMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(PanelMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(txt_TenMau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(PanelMauSacLayout.createSequentialGroup()
                                                    .addComponent(rdo_HMS)
                                                    .addGap(26, 26, 26)
                                                    .addComponent(rdo_NMS)))
                                            .addComponent(txt_MaMau, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(PanelMauSacLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel49)))
                        .addGap(0, 178, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PanelMauSacLayout.setVerticalGroup(
            PanelMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMauSacLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_MaMau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_TenMau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdo_HMS)
                    .addComponent(rdo_NMS))
                .addGap(18, 18, 18)
                .addGroup(PanelMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_AddMS, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_UpdateMS, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_LamMoiMS, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jLabel49)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollM, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE))
        );

        materialTabbed1.addTab("Màu Sắc", PanelMauSac);

        PanelDaQuy.setBackground(new java.awt.Color(255, 255, 255));
        PanelDaQuy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PanelDaQuyMouseClicked(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel39.setText("Mã Đá Quý");

        jLabel40.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel40.setText("Tên Đá Quý");

        txt_MaDQ.setEditable(false);
        txt_MaDQ.setFocusable(false);

        jLabel41.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel41.setText("Trạng Thái");

        btn_CapNhatDaQuy.setText("Cập Nhật");
        btn_CapNhatDaQuy.setColor1(new java.awt.Color(16, 24, 32));
        btn_CapNhatDaQuy.setColor2(new java.awt.Color(254, 231, 21));
        btn_CapNhatDaQuy.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btn_CapNhatDaQuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CapNhatDaQuyActionPerformed(evt);
            }
        });

        btn_DaQuy_them.setText("Thêm");
        btn_DaQuy_them.setColor1(new java.awt.Color(16, 24, 32));
        btn_DaQuy_them.setColor2(new java.awt.Color(254, 231, 21));
        btn_DaQuy_them.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btn_DaQuy_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DaQuy_themActionPerformed(evt);
            }
        });

        jLabel50.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel50.setText("Danh Sách Đá Quý");

        buttonGroup10.add(rdo_HDQ);
        rdo_HDQ.setSelected(true);
        rdo_HDQ.setText("Kinh doanh");

        buttonGroup10.add(rdo_NDQ);
        rdo_NDQ.setText("Ngưng Kinh Doanh");

        scrollDQ.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        tbl_DaQuy.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Mã Đá Quý", "Tên Đá Quý", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_DaQuy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_DaQuyMouseClicked(evt);
            }
        });
        scrollDQ.setViewportView(tbl_DaQuy);
        if (tbl_DaQuy.getColumnModel().getColumnCount() > 0) {
            tbl_DaQuy.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        btn_CapNhatKD1.setText("Làm Mới");
        btn_CapNhatKD1.setColor1(new java.awt.Color(16, 24, 32));
        btn_CapNhatKD1.setColor2(new java.awt.Color(254, 231, 21));
        btn_CapNhatKD1.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btn_CapNhatKD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CapNhatKD1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelDaQuyLayout = new javax.swing.GroupLayout(PanelDaQuy);
        PanelDaQuy.setLayout(PanelDaQuyLayout);
        PanelDaQuyLayout.setHorizontalGroup(
            PanelDaQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDaQuyLayout.createSequentialGroup()
                .addGroup(PanelDaQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDaQuyLayout.createSequentialGroup()
                        .addGroup(PanelDaQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelDaQuyLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addGroup(PanelDaQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel41)
                                    .addComponent(jLabel40)
                                    .addComponent(jLabel39)
                                    .addGroup(PanelDaQuyLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(PanelDaQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelDaQuyLayout.createSequentialGroup()
                                                .addComponent(rdo_HDQ)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                                .addComponent(rdo_NDQ))
                                            .addComponent(txt_MaDQ, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txt_TenDQ, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addGroup(PanelDaQuyLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel50)))
                        .addGap(0, 441, Short.MAX_VALUE))
                    .addGroup(PanelDaQuyLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollDQ, javax.swing.GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(PanelDaQuyLayout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(btn_CapNhatKD1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(btn_CapNhatDaQuy, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(btn_DaQuy_them, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelDaQuyLayout.setVerticalGroup(
            PanelDaQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDaQuyLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_MaDQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_TenDQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel41)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelDaQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdo_HDQ)
                    .addComponent(rdo_NDQ))
                .addGap(18, 18, 18)
                .addGroup(PanelDaQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_CapNhatDaQuy, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_DaQuy_them, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_CapNhatKD1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jLabel50)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollDQ, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        materialTabbed1.addTab("Đá Quý", PanelDaQuy);

        PanelKiemDinh.setBackground(new java.awt.Color(255, 255, 255));
        PanelKiemDinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PanelKiemDinhMouseClicked(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel42.setText("Mã Kiểm Định");

        txt_MaKD.setEditable(false);
        txt_MaKD.setFocusable(false);

        jLabel43.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel43.setText("Đơn Vị");

        btn_CapNhatKd.setText("Cập Nhật");
        btn_CapNhatKd.setColor1(new java.awt.Color(16, 24, 32));
        btn_CapNhatKd.setColor2(new java.awt.Color(254, 231, 21));
        btn_CapNhatKd.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btn_CapNhatKd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CapNhatKdActionPerformed(evt);
            }
        });

        bnt_Them_KiemDinh.setText("Thêm");
        bnt_Them_KiemDinh.setColor1(new java.awt.Color(16, 24, 32));
        bnt_Them_KiemDinh.setColor2(new java.awt.Color(254, 231, 21));
        bnt_Them_KiemDinh.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        bnt_Them_KiemDinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnt_Them_KiemDinhActionPerformed(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel44.setText("Trạng Thái");

        jLabel45.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel45.setText("Ngày Kiểm Định ");

        txt_NgayKD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_NgayKDActionPerformed(evt);
            }
        });

        scrollKD.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        tbl_KiemDinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Kiểm Định", "Đơn Vị Kiểm Định", "Ngày Kiểm Định", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_KiemDinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_KiemDinhMouseClicked(evt);
            }
        });
        scrollKD.setViewportView(tbl_KiemDinh);
        if (tbl_KiemDinh.getColumnModel().getColumnCount() > 0) {
            tbl_KiemDinh.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jLabel51.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel51.setText("Danh Sách Đơn Vị Kiểm Định");

        buttonGroup9.add(rdo_HKD);
        rdo_HKD.setSelected(true);
        rdo_HKD.setText("Hoạt Động");

        buttonGroup9.add(rdo_NKD);
        rdo_NKD.setText("Ngừng Hoạt Động");

        btn_LamMoi.setText("Làm Mới");
        btn_LamMoi.setColor1(new java.awt.Color(16, 24, 32));
        btn_LamMoi.setColor2(new java.awt.Color(254, 231, 21));
        btn_LamMoi.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btn_LamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelKiemDinhLayout = new javax.swing.GroupLayout(PanelKiemDinh);
        PanelKiemDinh.setLayout(PanelKiemDinhLayout);
        PanelKiemDinhLayout.setHorizontalGroup(
            PanelKiemDinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelKiemDinhLayout.createSequentialGroup()
                .addGroup(PanelKiemDinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelKiemDinhLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollKD, javax.swing.GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE))
                    .addGroup(PanelKiemDinhLayout.createSequentialGroup()
                        .addGroup(PanelKiemDinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelKiemDinhLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel51))
                            .addGroup(PanelKiemDinhLayout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addGroup(PanelKiemDinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel43)
                                    .addComponent(jLabel42)
                                    .addComponent(jLabel44)
                                    .addComponent(jLabel45)
                                    .addGroup(PanelKiemDinhLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(PanelKiemDinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(PanelKiemDinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txt_DonViKD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(PanelKiemDinhLayout.createSequentialGroup()
                                                    .addComponent(rdo_HKD)
                                                    .addGap(31, 31, 31)
                                                    .addComponent(rdo_NKD))
                                                .addComponent(txt_MaKD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(txt_NgayKD, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(PanelKiemDinhLayout.createSequentialGroup()
                                .addGap(111, 111, 111)
                                .addComponent(btn_LamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62)
                                .addComponent(btn_CapNhatKd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(72, 72, 72)
                                .addComponent(bnt_Them_KiemDinh, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PanelKiemDinhLayout.setVerticalGroup(
            PanelKiemDinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelKiemDinhLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_MaKD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_DonViKD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_NgayKD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel44)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelKiemDinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdo_HKD)
                    .addComponent(rdo_NKD))
                .addGap(29, 29, 29)
                .addGroup(PanelKiemDinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_CapNhatKd, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bnt_Them_KiemDinh, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_LamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jLabel51)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollKD, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        materialTabbed1.addTab("Kiểm Định", PanelKiemDinh);

        javax.swing.GroupLayout Panel_ThemThuocTinhLayout = new javax.swing.GroupLayout(Panel_ThemThuocTinh);
        Panel_ThemThuocTinh.setLayout(Panel_ThemThuocTinhLayout);
        Panel_ThemThuocTinhLayout.setHorizontalGroup(
            Panel_ThemThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_ThemThuocTinhLayout.createSequentialGroup()
                .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        Panel_ThemThuocTinhLayout.setVerticalGroup(
            Panel_ThemThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tabbedPaneCustom1.addTab("Thuộc Tính", Panel_ThemThuocTinh);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabbedPaneCustom1, javax.swing.GroupLayout.PREFERRED_SIZE, 737, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPaneCustom1, javax.swing.GroupLayout.PREFERRED_SIZE, 804, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdatePLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdatePLActionPerformed
        int i = tbl_PhanLoai.getSelectedRow();
        if (i < 0) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Chọn Phân Loại Cần Cập Nhật");
            panel.showNotification();
            return;
        }
        if (vld.checkIsEmpty(txt_TenPhanLoai.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Không Để Trống Dữ Liệu");
            panel.showNotification();
            return;
        }
        if (vld.checkContainPL(txt_MaPhanLoai.getText(), txt_TenPhanLoai.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Phân Loại Đã Tồn Tại");
            panel.showNotification();
            return;
        }

        capNhatSanPhamSv.readFormPhanLoai(txt_MaPhanLoai, txt_TenPhanLoai, rdo_HoatDongPL, rdo_NgungHoatDongPL);
        Notification panel = new Notification(this, Notification.Type.SUCCESS, Notification.Location.CENTER, "Cập Nhật Phân Loại Thành Công");
        panel.showNotification();
        txt_MaPhanLoai.setText("");
        txt_TenPhanLoai.setText("");
        rdo_HoatDongPL.setSelected(true);
        tbl_PhanLoai.clearSelection();
        capNhatSanPhamSv.clearList("PhanLoai");
        capNhatSanPhamSv.fillToPhanLoaiTbl(tbl_PhanLoai);
    }//GEN-LAST:event_btnUpdatePLActionPerformed

    private void txt_TenPhanLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_TenPhanLoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_TenPhanLoaiActionPerformed

    private void btn_CapNhatCLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CapNhatCLActionPerformed
        int i = tbl_ChatLieu.getSelectedRow();
        if (i < 0) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Chọn Chất Liệu Cần Cập Nhật");
            panel.showNotification();
            return;
        }
        if (vld.checkIsEmpty(txt_TenChatLieu.getText()) || vld.checkIsEmpty(txt_TyLe.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Không Để Trống Dữ Liệu");
            panel.showNotification();
            return;
        }
        if (vld.checkContainCL(txt_MaChatLieu.getText(), txt_TenChatLieu.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Chất Liệu Đã Tồn Tại");
            panel.showNotification();
            return;
        }
        if (!vld.checkNumber(txt_TyLe.getText(), true)) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Tỷ Lệ Phải Là Số Và Lớn Hơn Không");
            panel.showNotification();
            return;
        }
        capNhatSanPhamSv.readFormChatLieu(txt_MaChatLieu, txt_TenChatLieu, txt_TyLe, rdo_HoatDong_CL, rdo_NHoatDong_CL);
        capNhatSanPhamSv.clearList("ChatLieu");
        capNhatSanPhamSv.fillToChatLieuTbl(tbl_ChatLieu);
        Notification panel = new Notification(this, Notification.Type.SUCCESS, Notification.Location.CENTER, "Cập Nhật Chất Liệu Thành Công");
        panel.showNotification();

        txt_MaChatLieu.setText("");
        txt_TenChatLieu.setText("");
        txt_TyLe.setText("");
        rdo_HoatDong_CL.setSelected(true);
        tbl_ChatLieu.clearSelection();

    }//GEN-LAST:event_btn_CapNhatCLActionPerformed

    private void btn_UpdateSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpdateSizeActionPerformed
        int i = tbl_Size.getSelectedRow();
        if (i < 0) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Chọn Size Cần Cập Nhật");
            panel.showNotification();
            return;
        }
        if (vld.checkIsEmpty(txt_SoSize.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Không Được Để Trống Size");
            panel.showNotification();
            return;
        }
        if (vld.checkContainSize(txt_MaSize.getText(), txt_SoSize.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Số Size Đã Tồn Tại");
            panel.showNotification();
            return;
        }
        if (!vld.checkNguyenDuong(txt_SoSize.getText(), true)) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Size Là Số Nguyên Dương Và Lớn Hơn 0");
            panel.showNotification();
            return;
        }

        capNhatSanPhamSv.readFormSize(txt_MaSize, txt_SoSize, rdo_HSZ, rdo_NSZ);
        Notification panel = new Notification(this, Notification.Type.SUCCESS, Notification.Location.CENTER, "Cập Nhật Size Thành Công");
        panel.showNotification();
        txt_MaSize.setText("");
        txt_SoSize.setText("");
        rdo_HSZ.setSelected(true);
        tbl_Size.clearSelection();
        capNhatSanPhamSv.clearList("Size");
        capNhatSanPhamSv.fillToSizeTbl(tbl_Size);
    }//GEN-LAST:event_btn_UpdateSizeActionPerformed

    private void btn_ThemSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemSActionPerformed
        if (!vld.checkIsEmpty(txt_MaSize.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Mã Size Đã Tồn Tại Vui Lòng Làm Mới");
            panel.showNotification();
            return;
        }
        if (vld.checkIsEmpty(txt_SoSize.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Không Được Để Trống Size");
            panel.showNotification();
            return;
        }
        if (vld.checkContainSize(null, txt_SoSize.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Số Size Đã Tồn Tại");
            panel.showNotification();
            return;
        }
        if (!vld.checkNguyenDuong(txt_SoSize.getText(), true)) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Size Là Số Nguyên Dương Và Lớn Hơn 0");
            panel.showNotification();
            return;
        }

        capNhatSanPhamSv.InsertSize(txt_SoSize, rdo_HSZ, rdo_NSZ);
        Notification panel = new Notification(this, Notification.Type.SUCCESS, Notification.Location.CENTER, "Thêm Mới Size Thành Công");
        panel.showNotification();
        txt_MaSize.setText("");
        txt_SoSize.setText("");
        rdo_HSZ.setSelected(true);
        capNhatSanPhamSv.clearList("Size");
        capNhatSanPhamSv.fillToSizeTbl(tbl_Size);
    }//GEN-LAST:event_btn_ThemSActionPerformed

    private void txt_EmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_EmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_EmailActionPerformed

    private void btn_CapNhatDaQuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CapNhatDaQuyActionPerformed
        int i = tbl_DaQuy.getSelectedRow();
        if (i < 0) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Chọn Đá Quý Cần Cập Nhật");
            panel.showNotification();
            return;
        }
        if (vld.checkIsEmpty(txt_TenDQ.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Không Để Trống Dữ Liệu");
            panel.showNotification();
            return;
        }
        if (vld.checkContainDaQuy(txt_MaDQ.getText(), txt_TenDQ.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Tên Đá Quý Đã Tồn Tại");
            panel.showNotification();
            return;
        }
        capNhatSanPhamSv.readFormDaQuy(txt_MaDQ, txt_TenDQ, rdo_HDQ, rdo_NDQ);
        Notification panel = new Notification(this, Notification.Type.SUCCESS, Notification.Location.CENTER, "Cập Nhật Nhà  Thành Công");
        panel.showNotification();
        txt_MaDQ.setText("");
        txt_TenDQ.setText("");
        rdo_HDQ.setSelected(true);
        tbl_DaQuy.clearSelection();
        capNhatSanPhamSv.clearList("Da Quy");
        capNhatSanPhamSv.fillToDaQuyTbl(tbl_DaQuy);
    }//GEN-LAST:event_btn_CapNhatDaQuyActionPerformed

    private void btn_CapNhatKdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CapNhatKdActionPerformed
        int i = tbl_KiemDinh.getSelectedRow();
        if (i < 0) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Chọn Đơn Vị Kiểm Định Cần Cập Nhật");
            panel.showNotification();
            return;
        }
        if (vld.checkIsEmpty(txt_DonViKD.getText()) || txt_NgayKD.getText().equalsIgnoreCase("dd - MM - yyyy")) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Không Để Trống Dữ Liệu");
            panel.showNotification();
            return;
        }
        if (vld.checkContainKiemDinh(txt_MaKD.getText(), txt_DonViKD.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Đơn Vị Kiểm Định Đã Tồn Tại");
            panel.showNotification();
            return;
        }
        capNhatSanPhamSv.readFormKiemDinh(txt_MaKD, txt_DonViKD, txt_NgayKD, rdo_HKD, rdo_NKD);
        Notification panel = new Notification(this, Notification.Type.SUCCESS, Notification.Location.CENTER, "Cập Nhật Đơn Vị Kiểm Định Thành Công");
        panel.showNotification();
        txt_MaKD.setText("");
        txt_DonViKD.setText("");
        txt_NgayKD.setText("dd - MM - yyyy");
        rdo_HKD.setSelected(true);
        tbl_KiemDinh.clearSelection();
        capNhatSanPhamSv.clearList("KiemDinh");
        capNhatSanPhamSv.fillToKiemDinhTbl(tbl_KiemDinh);

        capNhatSanPhamSv.clearList("Kiem Dinh");
        capNhatSanPhamSv.fillToKiemDinhTbl(tbl_KiemDinh);
    }//GEN-LAST:event_btn_CapNhatKdActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        if (vld.checkIsEmpty(txt_TenTrangSuc.getText())
                || vld.checkIsEmpty(txt_GiaBan.getText())
                || vld.checkIsEmpty(txt_KichThuocDa.getText())
                || vld.checkIsEmpty(txt_TonKho.getText())
                || vld.checkIsEmpty(txt_TrongLuong.getText())
                || vld.checkIsEmpty(txt_SoLuongDa.getText())) {

            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Không Để Trống Dữ Liệu");
            panel.showNotification();
            return;
        }
        if (!vld.checkNumber(txt_GiaBan.getText(), true)) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Giá Bán Phải Là Số Và Lớn Hơn 0");
            panel.showNotification();
            return;
        }

        if (!vld.checkNumber(txt_KichThuocDa.getText(), false)) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Kích Thước Đá Phải Là Số");
            panel.showNotification();
            return;

        }
        if (!vld.checkNguyenDuong(txt_SoLuongDa.getText(), false)) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Số Lương Đá Phải Là Số Nguyên Dương");
            panel.showNotification();
            return;

        }
        if (!vld.checkNguyenDuong(txt_TonKho.getText(), false)) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Tồn Kho  Phải Là Số Nguyên Dương");
            panel.showNotification();
            return;

        }
        if (!vld.checkNumber(txt_TrongLuong.getText(), true)) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Trọng Lượng Phải Là Số Và Lớn Hơn 0");
            panel.showNotification();
            return;

        }
        String link = (String) lbl_HinhAnh.getClientProperty("imagepath");
        if (link == null) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Chưa Chọn Ảnh");
            panel.showNotification();
            return;
        }
        if (vld.checkSanPham(null, txt_TenTrangSuc.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Tên Sản Phẩm Đã Tồn Tại");
            panel.showNotification();
            return;
        }
        rpChiTietSanPham.add(capNhatSanPhamSv.checkCboPhanLoai(cbo_PhanLoai),
                capNhatSanPhamSv.checkCboKiemDinh(cbo_KiemDinh),
                capNhatSanPhamSv.checkCboMauSac(cbo_MauSac),
                capNhatSanPhamSv.checkCboSize(cbos_Size), txt_TenTrangSuc.getText().trim(),
                rdoNam.isSelected() ? true : false, capNhatSanPhamSv.checkCboChatLieu(cbo_ChatLieu),
                Integer.parseInt(txt_TonKho.getText().trim()), Float.parseFloat(txt_GiaBan.getText().trim()), "GG0001",
                capNhatSanPhamSv.checkCboNhaCungCap(cbo_NhaCungCap), Integer.parseInt(txt_SoLuongDa.getText().trim()),
                Float.parseFloat(txt_KichThuocDa.getText().trim()), Float.parseFloat(txt_TrongLuong.getText().trim()), link,
                capNhatSanPhamSv.checkCboLoaiDa(cbo_LoaiDa1), rdoKinhDoanh.isSelected() ? true : false);

        Notification panel = new Notification(this, Notification.Type.SUCCESS, Notification.Location.CENTER, "Thêm Mới Trang Sức Thành Công");
        panel.showNotification();

        GiaoDienSanPham gdnv = GiaoDienSanPham.getInstance();
        if (gdnv != null) {
            gdnv.update();
        }
        GiaoDienBanHang gdbh = GiaoDienBanHang.getInstance();
        if (gdbh != null) {
            gdbh.update();
        }

        this.dispose();

    }//GEN-LAST:event_btnLuuActionPerformed
    public void fillAllTable() {
        capNhatSanPhamSv.fillToChatLieuTbl(tbl_ChatLieu);
        capNhatSanPhamSv.fillToPhanLoaiTbl(tbl_PhanLoai);
        capNhatSanPhamSv.fillToSizeTbl(tbl_Size);
        capNhatSanPhamSv.fillToDaQuyTbl(tbl_DaQuy);
        capNhatSanPhamSv.fillToMauSacTbl(tbl_MauSac);
        capNhatSanPhamSv.fillToNhaCungCapTbl(tbl_NhaCungCAp);
        capNhatSanPhamSv.fillToKiemDinhTbl(tbl_KiemDinh);
    }

    public void getDataOfTable() {
        capNhatSanPhamSv.getDataPhanLoai(tbl_PhanLoai, txt_MaPhanLoai, txt_TenPhanLoai, rdo_HoatDongPL, rdo_NgungHoatDongPL);

        capNhatSanPhamSv.getDataChatLieu(tbl_ChatLieu, txt_MaChatLieu, txt_TenChatLieu, txt_TyLe, rdo_HoatDong_CL, rdo_NHoatDong_CL);

        capNhatSanPhamSv.getDataDaQuy(tbl_DaQuy, txt_MaDQ, txt_TenDQ, rdo_HDQ, rdo_NDQ);

        capNhatSanPhamSv.getDataKiemDinh(tbl_KiemDinh, txt_MaKD, txt_DonViKD, txt_NgayKD, rdo_HKD, rdo_NKD);

        capNhatSanPhamSv.getDataMauSac(tbl_MauSac, txt_MaMau, txt_TenMau, rdo_HMS, rdo_NMS);

        capNhatSanPhamSv.getDataNhaCungCap(tbl_NhaCungCAp, txt_MaNhaCungCap, txt_TenNhaCungCap, txt_Email, txt_SoDienThoai, txtA_DiaChi, rdo_HNCC, rdo_NNCC);

        capNhatSanPhamSv.getDataSize(tbl_Size, txt_MaSize, txt_SoSize, rdo_HSZ, rdo_HSZ);

    }
    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed

        this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
//        Main.closeCurrentInstance();
//        Main newMain = new Main(1);
//        newMain.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void PanelPhanLoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelPhanLoaiMouseClicked

    }//GEN-LAST:event_PanelPhanLoaiMouseClicked

    private void PanelChatLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelChatLieuMouseClicked
        txt_MaChatLieu.setText("");
        txt_TenChatLieu.setText("");
        txt_TyLe.setText("");
    }//GEN-LAST:event_PanelChatLieuMouseClicked

    private void PanelSizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelSizeMouseClicked
        txt_MaSize.setText("");
        txt_SoSize.setText("");
    }//GEN-LAST:event_PanelSizeMouseClicked

    private void PanelNhaCungCapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelNhaCungCapMouseClicked
        txt_MaNhaCungCap.setText("");
        txt_TenNhaCungCap.setText("");
        txt_Email.setText("");
        txt_SoDienThoai.setText("");
        txtA_DiaChi.setText("");
    }//GEN-LAST:event_PanelNhaCungCapMouseClicked

    private void PanelMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelMauSacMouseClicked
        txt_MaMau.setText("");
        txt_TenMau.setText("");
    }//GEN-LAST:event_PanelMauSacMouseClicked

    private void PanelKiemDinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelKiemDinhMouseClicked
        txt_MaKD.setText("");
        txt_DonViKD.setText("");
        txt_NgayKD.setText("dd - MM - yyyy");
    }//GEN-LAST:event_PanelKiemDinhMouseClicked

    private void PanelDaQuyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelDaQuyMouseClicked
        txt_MaDQ.setText("");
        txt_TenDQ.setText("");
    }//GEN-LAST:event_PanelDaQuyMouseClicked

    private void btn_CapNhatNhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CapNhatNhaCungCapActionPerformed
        int i = tbl_NhaCungCAp.getSelectedRow();
        if (i < 0) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Chọn Nhà Cung Cấp Cần Cập Nhật");
            panel.showNotification();
            return;
        }
        if (vld.checkIsEmpty(txt_TenNhaCungCap.getText())
                || vld.checkIsEmpty(txt_Email.getText())
                || vld.checkIsEmpty(txtA_DiaChi.getText())
                || vld.checkIsEmpty(txt_SoDienThoai.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Không Để Trống Dữ Liệu");
            panel.showNotification();
            return;

        }
        if (vld.checkContainCungCap(txt_MaNhaCungCap.getText(), txt_TenNhaCungCap.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Nhà Cung Cấp Đã Tồn Tại");
            panel.showNotification();
            return;
        }
        if (!vld.checkMail(txt_Email.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Email Phải Có Dạng 'example@example.com'");
            panel.showNotification();
            return;
        }
        if (!vld.checkPhone(txt_SoDienThoai.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Số Điện Thoại Phải Có 10 Chữ Số Và Bắt Đầu Bằng Số 0");
            panel.showNotification();
            return;
        }
        capNhatSanPhamSv.readFormNhaCungCap(txt_MaNhaCungCap, txt_TenNhaCungCap, txt_Email, txt_SoDienThoai, txtA_DiaChi, rdo_HNCC, rdo_NNCC);
        Notification panel = new Notification(this, Notification.Type.SUCCESS, Notification.Location.CENTER, "Cập Nhật Nhà Cung Cấp Thành Công");
        panel.showNotification();
        txt_MaNhaCungCap.setText("");
        txt_TenNhaCungCap.setText("");
        txt_Email.setText("");
        txt_SoDienThoai.setText("");
        txtA_DiaChi.setText("");
        rdo_HNCC.setSelected(true);
        tbl_NhaCungCAp.clearSelection();
        capNhatSanPhamSv.clearList("NhaCungCap");
        capNhatSanPhamSv.fillToNhaCungCapTbl(tbl_NhaCungCAp);
    }//GEN-LAST:event_btn_CapNhatNhaCungCapActionPerformed

    private void btn_UpdateMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpdateMSActionPerformed
        int i = tbl_MauSac.getSelectedRow();
        if (i < 0) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Chọn Màu Sắc Cần Cập Nhật");
            panel.showNotification();
            return;
        }
        if (vld.checkIsEmpty(txt_TenMau.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Không Để Trống Dữ Liệu");
            panel.showNotification();
            return;
        }
        if (vld.checkContainMau(txt_MaPhanLoai.getText(), txt_TenMau.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Màu Sắc Đã Tồn Tại");
            panel.showNotification();
            return;
        }
        capNhatSanPhamSv.readFormMauSac(txt_MaMau, txt_TenMau, rdo_HMS, rdo_NMS);
        Notification panel = new Notification(this, Notification.Type.SUCCESS, Notification.Location.CENTER, "Cập Nhật Màu Sắc Cấp Thành Công");
        panel.showNotification();
        txt_MaMau.setText("");
        txt_TenMau.setText("");
        rdo_HMS.setSelected(true);
        tbl_MauSac.clearSelection();
        capNhatSanPhamSv.clearList("MauSac");
        capNhatSanPhamSv.fillToMauSacTbl(tbl_MauSac);
    }//GEN-LAST:event_btn_UpdateMSActionPerformed

    private void txt_NgayKDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_NgayKDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NgayKDActionPerformed

    private void btnAddPLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPLActionPerformed

        if (vld.checkIsEmpty(txt_TenPhanLoai.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Không Để Trống Dữ Liệu");
            panel.showNotification();
            return;
        }
        if (!txt_MaPhanLoai.getText().isEmpty()) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Mã Phân Loại Đã Tồn Tại Vui Lòng Làm Mới");
            panel.showNotification();
            return;
        }
        if (vld.checkContainPL(null, txt_TenPhanLoai.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Phân Loại Đã Tồn Tại");
            panel.showNotification();
            return;
        }

        capNhatSanPhamSv.InsertPhanLoai(txt_TenPhanLoai, rdo_HoatDongPL, rdo_NgungHoatDongPL);
        Notification panel = new Notification(this, Notification.Type.SUCCESS, Notification.Location.CENTER, "Thêm Mới Phân Loại Thành Công");
        panel.showNotification();
        txt_MaPhanLoai.setText("");
        txt_TenPhanLoai.setText("");
        rdo_HoatDongPL.setSelected(true);
        capNhatSanPhamSv.clearList("PhanLoai");
        capNhatSanPhamSv.fillToPhanLoaiTbl(tbl_PhanLoai);
    }//GEN-LAST:event_btnAddPLActionPerformed

    private void btn_ThemCLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemCLActionPerformed
        if (vld.checkIsEmpty(txt_TenChatLieu.getText()) || vld.checkIsEmpty(txt_TyLe.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Không Để Trống Dữ Liệu");
            panel.showNotification();
            return;
        }

        if (vld.checkContainCL(null, txt_TenChatLieu.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Chất Liệu Đã Tồn Tại");
            panel.showNotification();
            return;
        }
        if (!vld.checkNumber(txt_TyLe.getText(), true)) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Tỷ Lệ Phải Là Số Và Lớn Hơn Không");
            panel.showNotification();
            return;
        }
        if (!txt_MaChatLieu.getText().isEmpty()) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Mã Chất Liệu Đã Tồn Tại Vui Lòng Làm Mới");
            panel.showNotification();
            return;
        }
        capNhatSanPhamSv.InsertChatLieu(txt_TenChatLieu, txt_TyLe, rdo_HoatDong_CL, rdo_NHoatDong_CL);
        Notification panel = new Notification(this, Notification.Type.SUCCESS, Notification.Location.CENTER, "Thêm Mới Chất Liệu Thành Công");
        panel.showNotification();

        txt_MaChatLieu.setText("");
        txt_TenChatLieu.setText("");
        txt_TyLe.setText("");
        rdo_HoatDong_CL.setSelected(true);
        capNhatSanPhamSv.clearList("ChatLieu");
        capNhatSanPhamSv.fillToChatLieuTbl(tbl_ChatLieu);
    }//GEN-LAST:event_btn_ThemCLActionPerformed

    private void bnt_ThemNhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnt_ThemNhaCungCapActionPerformed
        if (!vld.checkIsEmpty(txt_MaNhaCungCap.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Mã Nhà Cung Cấp Đã Tồn Tại Vui Lòng Làm Mới");
            panel.showNotification();
            return;
        }
        if (vld.checkIsEmpty(txt_TenNhaCungCap.getText())
                || vld.checkIsEmpty(txt_Email.getText())
                || vld.checkIsEmpty(txtA_DiaChi.getText())
                || vld.checkIsEmpty(txt_SoDienThoai.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Không Để Trống Dữ Liệu");
            panel.showNotification();
            return;

        }
        if (vld.checkContainCungCap(null, txt_TenNhaCungCap.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Nhà Cung Cấp Đã Tồn Tại");
            panel.showNotification();
            return;
        }
        if (!vld.checkMail(txt_Email.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Email Phải Có Dạng 'example@example.com'");
            panel.showNotification();
            return;
        }
        if (!vld.checkPhone(txt_SoDienThoai.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Số Điện Thoại Phải Có 10 Chữ Số Và Bắt Đầu Bằng Số 0");
            panel.showNotification();
            return;
        }
        capNhatSanPhamSv.InsertNhaCungCap(txt_TenNhaCungCap, txt_Email, txt_SoDienThoai, txtA_DiaChi, rdo_HNCC, rdo_NNCC);
        Notification panel = new Notification(this, Notification.Type.SUCCESS, Notification.Location.CENTER, "Thêm Mới Nhà Cung Cấp Thành Công");
        panel.showNotification();
        txt_MaNhaCungCap.setText("");
        txt_TenNhaCungCap.setText("");
        txt_Email.setText("");
        txt_SoDienThoai.setText("");
        txtA_DiaChi.setText("");
        rdo_HNCC.setSelected(true);
        capNhatSanPhamSv.clearList("NhaCungCap");
        capNhatSanPhamSv.fillToNhaCungCapTbl(tbl_NhaCungCAp);
    }//GEN-LAST:event_bnt_ThemNhaCungCapActionPerformed

    private void btn_AddMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AddMSActionPerformed

        if (!vld.checkIsEmpty(txt_MaMau.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Mã Màu Đã Tồn Tại Vui Lòng Làm Mới");
            panel.showNotification();
            return;
        }
        if (vld.checkIsEmpty(txt_TenMau.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Không Để Trống Dữ Liệu");
            panel.showNotification();
            return;
        }
        if (vld.checkContainMau(null, txt_TenMau.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Màu Sắc Đã Tồn Tại");
            panel.showNotification();
            return;
        }
        capNhatSanPhamSv.InsertMauSac(txt_TenMau, rdo_HMS, rdo_NMS);
        Notification panel = new Notification(this, Notification.Type.SUCCESS, Notification.Location.CENTER, "Thêm Mới Màu Sắc Cấp Thành Công");
        panel.showNotification();
        txt_MaMau.setText("");
        txt_TenMau.setText("");
        rdo_HMS.setSelected(true);
        capNhatSanPhamSv.clearList("MauSac");
        capNhatSanPhamSv.fillToMauSacTbl(tbl_MauSac);
    }//GEN-LAST:event_btn_AddMSActionPerformed

    private void btn_DaQuy_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DaQuy_themActionPerformed

        if (!vld.checkIsEmpty(txt_MaDQ.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Mã Đá Quý Đã Tồn Tại Vui Lòng Làm Mới");
            panel.showNotification();
            return;
        }

        if (vld.checkIsEmpty(txt_TenDQ.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Không Để Trống Dữ Liệu");
            panel.showNotification();
            return;
        }
        if (vld.checkContainDaQuy(null, txt_TenDQ.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Tên Đá Quý Đã Tồn Tại");
            panel.showNotification();
            return;
        }
        capNhatSanPhamSv.InsertDaQuy(txt_TenDQ, rdo_HDQ, rdo_NDQ);
        Notification panel = new Notification(this, Notification.Type.SUCCESS, Notification.Location.CENTER, "Thêm Mới Đá Quý Thành Công");
        panel.showNotification();
        txt_MaDQ.setText("");
        txt_TenDQ.setText("");
        rdo_HDQ.setSelected(true);
        capNhatSanPhamSv.clearList("DaQuy");
        capNhatSanPhamSv.fillToDaQuyTbl(tbl_DaQuy);
    }//GEN-LAST:event_btn_DaQuy_themActionPerformed

    private void bnt_Them_KiemDinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnt_Them_KiemDinhActionPerformed
        if (!vld.checkIsEmpty(txt_MaKD.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Mã Kiểm Định Đã Tồn Tại Vui Lòng Làm Mới");
            panel.showNotification();
            return;
        }
        if (vld.checkIsEmpty(txt_DonViKD.getText()) || txt_NgayKD.getText().equalsIgnoreCase("dd - MM - yyyy")) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Không Để Trống Dữ Liệu");
            panel.showNotification();
            return;
        }
        if (vld.checkContainKiemDinh(null, txt_DonViKD.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Đơn Vị Kiểm Định Đã Tồn Tại");
            panel.showNotification();
            return;
        }
        capNhatSanPhamSv.InsertKiemDinh(txt_DonViKD, txt_NgayKD, rdo_HKD, rdo_NKD);
        Notification panel = new Notification(this, Notification.Type.SUCCESS, Notification.Location.CENTER, "Thêm Mới Đơn Vị Kiểm Định Thành Công");
        panel.showNotification();
        txt_MaKD.setText("");
        txt_DonViKD.setText("");
        txt_NgayKD.setText("dd - MM - yyyy");
        rdo_HKD.setSelected(true);
        capNhatSanPhamSv.clearList("KiemDinh");
        capNhatSanPhamSv.fillToKiemDinhTbl(tbl_KiemDinh);
    }//GEN-LAST:event_bnt_Them_KiemDinhActionPerformed

    private void btn_ThemAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemAnhActionPerformed
        stk = new ServiceTaiKhoan();
        stk.chonAnh(lbl_HinhAnh);
    }//GEN-LAST:event_btn_ThemAnhActionPerformed

    private void txt_TenTrangSucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_TenTrangSucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_TenTrangSucActionPerformed

    private void tbl_PhanLoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_PhanLoaiMouseClicked
        // TODO add your handling code here:
        capNhatSanPhamSv.getDataPhanLoai(tbl_PhanLoai, txt_MaPhanLoai, txt_TenPhanLoai, rdo_HoatDongPL, rdo_NgungHoatDongPL);
    }//GEN-LAST:event_tbl_PhanLoaiMouseClicked

    private void tbl_ChatLieuAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tbl_ChatLieuAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_ChatLieuAncestorAdded

    private void tbl_ChatLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_ChatLieuMouseClicked
        // TODO add your handling code here:
        capNhatSanPhamSv.getDataChatLieu(tbl_ChatLieu, txt_MaChatLieu, txt_TenChatLieu, txt_TyLe, rdo_HoatDong_CL, rdo_NHoatDong_CL);
    }//GEN-LAST:event_tbl_ChatLieuMouseClicked

    private void tbl_SizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_SizeMouseClicked
        // TODO add your handling code here:
        capNhatSanPhamSv.getDataSize(tbl_Size, txt_MaSize, txt_SoSize, rdo_HSZ, rdo_HSZ);
    }//GEN-LAST:event_tbl_SizeMouseClicked

    private void tbl_NhaCungCApMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_NhaCungCApMouseClicked
        // TODO add your handling code here:
        capNhatSanPhamSv.getDataNhaCungCap(tbl_NhaCungCAp, txt_MaNhaCungCap, txt_TenNhaCungCap, txt_Email, txt_SoDienThoai, txtA_DiaChi, rdo_HNCC, rdo_NNCC);
    }//GEN-LAST:event_tbl_NhaCungCApMouseClicked

    private void txt_MaMauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_MaMauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_MaMauActionPerformed

    private void tbl_MauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_MauSacMouseClicked
        // TODO add your handling code here:
        capNhatSanPhamSv.getDataMauSac(tbl_MauSac, txt_MaMau, txt_TenMau, rdo_HMS, rdo_NMS);
    }//GEN-LAST:event_tbl_MauSacMouseClicked

    private void tbl_DaQuyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_DaQuyMouseClicked
        // TODO add your handling code here:
        capNhatSanPhamSv.getDataDaQuy(tbl_DaQuy, txt_MaDQ, txt_TenDQ, rdo_HDQ, rdo_NDQ);

    }//GEN-LAST:event_tbl_DaQuyMouseClicked

    private void tbl_KiemDinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_KiemDinhMouseClicked

        capNhatSanPhamSv.getDataKiemDinh(tbl_KiemDinh, txt_MaKD, txt_DonViKD, txt_NgayKD, rdo_HKD, rdo_NKD);        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_KiemDinhMouseClicked

    private void txt_SoSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SoSizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_SoSizeActionPerformed

    private void txt_SoDienThoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SoDienThoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_SoDienThoaiActionPerformed

    private void btn_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiActionPerformed
        // TODO add your handling code here:
        txt_MaKD.setText("");
        txt_DonViKD.setText("");
        txt_NgayKD.setText("dd - MM - yyyy");
        rdo_HKD.setSelected(true);
        tbl_KiemDinh.clearSelection();
    }//GEN-LAST:event_btn_LamMoiActionPerformed

    private void btn_CapNhatKD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CapNhatKD1ActionPerformed
        txt_MaDQ.setText("");
        txt_TenDQ.setText("");
        rdo_HDQ.setSelected(true);
        tbl_DaQuy.clearSelection();
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_CapNhatKD1ActionPerformed

    private void btn_LamMoiMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiMSActionPerformed
        // TODO add your handling code here:
        txt_MaMau.setText("");
        txt_TenMau.setText("");
        rdo_HMS.setSelected(true);
        tbl_MauSac.clearSelection();
    }//GEN-LAST:event_btn_LamMoiMSActionPerformed

    private void tbn_LamMoiNhaCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbn_LamMoiNhaCCActionPerformed
        txt_MaNhaCungCap.setText("");
        txt_TenNhaCungCap.setText("");
        txt_Email.setText("");
        txt_SoDienThoai.setText("");
        txtA_DiaChi.setText("");
        rdo_HNCC.setSelected(true);
        tbl_NhaCungCAp.clearSelection();
        // TODO add your handling code here:
    }//GEN-LAST:event_tbn_LamMoiNhaCCActionPerformed

    private void btn_LamMoiSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiSActionPerformed
        // TODO add your handling code here:
        txt_MaSize.setText("");
        txt_SoSize.setText("");
        rdo_HSZ.setSelected(true);
        tbl_Size.clearSelection();
    }//GEN-LAST:event_btn_LamMoiSActionPerformed

    private void btn_LamMoiCLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiCLActionPerformed
        // TODO add your handling code here:
        txt_MaChatLieu.setText("");
        txt_TenChatLieu.setText("");
        txt_TyLe.setText("");
        rdo_HoatDong_CL.setSelected(true);
        tbl_ChatLieu.clearSelection();
    }//GEN-LAST:event_btn_LamMoiCLActionPerformed

    private void btn_LamMoiPLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiPLActionPerformed
        // TODO add your handling code here:
        txt_MaPhanLoai.setText("");
        txt_TenPhanLoai.setText("");
        rdo_HoatDongPL.setSelected(true);
        tbl_PhanLoai.clearSelection();
    }//GEN-LAST:event_btn_LamMoiPLActionPerformed

    private void txt_MaChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_MaChatLieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_MaChatLieuActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("view.until.sampletable.themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacLightLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ThemMoiSanPham dialog = new ThemMoiSanPham(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelChatLieu;
    private javax.swing.JPanel PanelDaQuy;
    private javax.swing.JPanel PanelKiemDinh;
    private javax.swing.JPanel PanelMauSac;
    private javax.swing.JPanel PanelNhaCungCap;
    private javax.swing.JPanel PanelPhanLoai;
    private javax.swing.JPanel PanelSize;
    private javax.swing.JPanel Panel_ThemThuocTinh;
    private view.until.button.Button bnt_ThemNhaCungCap;
    private view.until.button.Button bnt_Them_KiemDinh;
    private view.until.button.Button btnAddPL;
    private view.until.button.Button btnHuy;
    private view.until.button.Button btnLuu;
    private view.until.button.Button btnUpdatePL;
    private view.until.button.Button btn_AddMS;
    private view.until.button.Button btn_CapNhatCL;
    private view.until.button.Button btn_CapNhatDaQuy;
    private view.until.button.Button btn_CapNhatKD1;
    private view.until.button.Button btn_CapNhatKd;
    private view.until.button.Button btn_CapNhatNhaCungCap;
    private view.until.button.Button btn_DaQuy_them;
    private view.until.button.Button btn_LamMoi;
    private view.until.button.Button btn_LamMoiCL;
    private view.until.button.Button btn_LamMoiMS;
    private view.until.button.Button btn_LamMoiPL;
    private view.until.button.Button btn_LamMoiS;
    private view.until.button.Button btn_ThemAnh;
    private view.until.button.Button btn_ThemCL;
    private view.until.button.Button btn_ThemS;
    private view.until.button.Button btn_UpdateMS;
    private view.until.button.Button btn_UpdateSize;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup10;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.ButtonGroup buttonGroup8;
    private javax.swing.ButtonGroup buttonGroup9;
    private view.until.combobox.ComboBoxSuggestion cbo_ChatLieu;
    private view.until.combobox.ComboBoxSuggestion cbo_KiemDinh;
    private view.until.combobox.ComboBoxSuggestion cbo_LoaiDa1;
    private view.until.combobox.ComboBoxSuggestion cbo_MauSac;
    private view.until.combobox.ComboBoxSuggestion cbo_NhaCungCap;
    private view.until.combobox.ComboBoxSuggestion cbo_PhanLoai;
    private view.until.combobox.ComboBoxSuggestion cbos_Size;
    private com.raven.datechooser.DateChooser dateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbl_HinhAnh;
    private javax.swing.JLabel lbl_ThemMoi;
    private view.until.tabbedpane.MaterialTabbed materialTabbed1;
    private javax.swing.JPanel panel_ThemSanPham;
    private javax.swing.JPanel pnl_ThemMoi;
    private javax.swing.JRadioButton rdoKinhDoanh;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JRadioButton rdo_HDQ;
    private javax.swing.JRadioButton rdo_HKD;
    private javax.swing.JRadioButton rdo_HMS;
    private javax.swing.JRadioButton rdo_HNCC;
    private javax.swing.JRadioButton rdo_HSZ;
    private javax.swing.JRadioButton rdo_HoatDongPL;
    private javax.swing.JRadioButton rdo_HoatDong_CL;
    private javax.swing.JRadioButton rdo_NDQ;
    private javax.swing.JRadioButton rdo_NHoatDong_CL;
    private javax.swing.JRadioButton rdo_NKD;
    private javax.swing.JRadioButton rdo_NMS;
    private javax.swing.JRadioButton rdo_NNCC;
    private javax.swing.JRadioButton rdo_NSZ;
    private javax.swing.JRadioButton rdo_NgungHoatDongPL;
    private javax.swing.JScrollPane scrollCC;
    private javax.swing.JScrollPane scrollCL;
    private javax.swing.JScrollPane scrollDQ;
    private javax.swing.JScrollPane scrollKD;
    private javax.swing.JScrollPane scrollM;
    private javax.swing.JScrollPane scrollPL;
    private javax.swing.JScrollPane scrollSZ;
    private view.until.tabbedpane.TabbedPaneCustom tabbedPaneCustom1;
    private view.until.table.TableDark tableDark2;
    private javax.swing.JTable tbl_ChatLieu;
    private javax.swing.JTable tbl_DaQuy;
    private javax.swing.JTable tbl_KiemDinh;
    private javax.swing.JTable tbl_MauSac;
    private javax.swing.JTable tbl_NhaCungCAp;
    private javax.swing.JTable tbl_PhanLoai;
    private javax.swing.JTable tbl_Size;
    private view.until.button.Button tbn_LamMoiNhaCC;
    private javax.swing.JTextArea txtA_DiaChi;
    private view.until.textfield.TextFieldSuggestion txt_DonViKD;
    private view.until.textfield.TextFieldSuggestion txt_Email;
    private view.until.textfield.TextFieldSuggestion txt_GiaBan;
    private view.until.textfield.TextFieldSuggestion txt_KichThuocDa;
    private view.until.textfield.TextFieldSuggestion txt_MaChatLieu;
    private view.until.textfield.TextFieldSuggestion txt_MaDQ;
    private view.until.textfield.TextFieldSuggestion txt_MaKD;
    private view.until.textfield.TextFieldSuggestion txt_MaMau;
    private view.until.textfield.TextFieldSuggestion txt_MaNhaCungCap;
    private view.until.textfield.TextFieldSuggestion txt_MaPhanLoai;
    private view.until.textfield.TextFieldSuggestion txt_MaSize;
    private view.until.textfield.TextFieldSuggestion txt_NgayKD;
    private view.until.textfield.TextFieldSuggestion txt_SoDienThoai;
    private view.until.textfield.TextFieldSuggestion txt_SoLuongDa;
    private view.until.textfield.TextFieldSuggestion txt_SoSize;
    private view.until.textfield.TextFieldSuggestion txt_TenChatLieu;
    private view.until.textfield.TextFieldSuggestion txt_TenDQ;
    private view.until.textfield.TextFieldSuggestion txt_TenMau;
    private view.until.textfield.TextFieldSuggestion txt_TenNhaCungCap;
    private view.until.textfield.TextFieldSuggestion txt_TenPhanLoai;
    private view.until.textfield.TextFieldSuggestion txt_TenTrangSuc;
    private view.until.textfield.TextFieldSuggestion txt_TonKho;
    private view.until.textfield.TextFieldSuggestion txt_TrongLuong;
    private view.until.textfield.TextFieldSuggestion txt_TyLe;
    // End of variables declaration//GEN-END:variables
}
