/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.khuyenmai;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import model.GiamGia;
import model.GiaoDien.GiaoDienKhuyenMaiModel;
import model.Voucher;
import raven.modal.ModalDialog;
import raven.modal.Toast;
import raven.modal.component.SimpleModalBorder;
import repository.KhuyenMai.KhuyenMaiRepository;
import service.observer.Observer;
import until.validate.ValidateData;
import view.banhang.GiaoDienBanHang;
import view.form.JTableHeader;
import view.main.Main;
import view.until.hopthoai.NotificationJPanel;
import view.until.sampletable.CheckBoxTableHeaderRenderer;
import view.until.sampletable.TableHeaderAlignment;

public class GiaoDienKhuyenMai extends javax.swing.JPanel implements Observer {

    private KhuyenMaiRepository rpkm = new KhuyenMaiRepository();
    private service.KhuyenMai.GiaoDienKhuyenMaiService qlKm = new service.KhuyenMai.GiaoDienKhuyenMaiService();
    private repository.KhuyenMai.KhuyenMaiRepository rpKM = new repository.KhuyenMai.KhuyenMaiRepository();
    private Main main;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    private Color color2 = Color.decode("#101820");// thanden
    private Color color1 = Color.decode("#FEE715"); //mau vang
    private static GiaoDienKhuyenMai instance;

    static {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("view.until.sampletable.themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacLightLaf.setup();
    }

    public GiaoDienKhuyenMai() {
        initComponents();
        setFont();
        qlKm.fillToTable(tbl_Voucher);
        init();
        qlKm.fillTableGiamGia(tbl_GiamGia);
        qlKm.fillCbo(cbosMaGiamGia);
        qlKm.fillTableGiamGiaSP(tbl_SPGiamGia);

    }

    private void init() {
        panel_SPGiamGia.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:25;"
                + "background:$Table.background");
        pnl_ThemGiamGia.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:25;"
                + "background:$Table.background");
        TableKhuyenMai tkm = new TableKhuyenMai();
        tkm.init(tbl_SPGiamGia, scrollSPGG);
        tkm.init(tbl_GiamGia, scroll_GiamGia);
        tbl_SPGiamGia.getColumnModel().getColumn(0).setHeaderRenderer(new CheckBoxTableHeaderRenderer(tbl_SPGiamGia, 0));
        tbl_SPGiamGia.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(tbl_SPGiamGia));
        tbl_GiamGia.getTableHeader().setDefaultRenderer(new JTableHeader(tbl_GiamGia));

        //voucher
        tkm.init(tbl_Voucher, scrollVC);
        tbl_Voucher.getTableHeader().setDefaultRenderer(new JTableHeader(tbl_Voucher));
    }

    void setFont() {
        txt_NgayBatDau.setText("dd - MM - yyyy");
        txt_NgayKetThuc.setText("dd - MM - yyyy");

        txt_NgayBatDauG.setText("dd - MM - yyyy");
        txt_NgayKetThucG.setText("dd - MM - yyyy");
        btn_TimKiemV.setColor1(color2);
        btn_Them.setColor1(color2);
        btn_CapNhat.setColor1(color2);
        btn_LamMoi.setColor1(color2);
        btn_Them.setForeground(color1);
        btn_LamMoi.setForeground(color1);
        btn_CapNhat.setForeground(color1);

        //Giảm Giá
        btn_TimKiemSP.setColor1(color2);
        btn_ThemSPGiamGia.setColor1(color2);
        btn_XoaSPGiamGia.setColor1(color2);
        btn_ThemSPGiamGia.setForeground(color1);
        btn_XoaSPGiamGia.setForeground(color1);

    }

    public void lamMoi() {
        lbl_MaVoucher.setText("");
        txt_TenVourcher.setText("");
        txt_TyLe.setText("");
        txt_NgayBatDau.setText("dd - MM - yyyy");
        txt_NgayKetThuc.setText("dd - MM - yyyy");
        rdo_HoatDong.setSelected(true);

    }

    public Voucher redFrom() {
        boolean trangThai = rdo_HoatDong.isSelected();
        if (!rdo_HoatDong.isSelected()) {
            trangThai = false;

        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        String stringBatDau = txt_NgayBatDau.getText().trim();
        String stringKetThuc = txt_NgayKetThuc.getText().trim();

        Date dateBatDau = null;
        Date dateKetThuc = null;
        try {
            dateBatDau = dateFormat.parse(stringBatDau);
            dateKetThuc = dateFormat.parse(stringKetThuc);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

        float tyLe;
        tyLe = Float.parseFloat(txt_TyLe.getText().trim());

        return new Voucher(lbl_MaVoucher.getText().trim(), txt_TenVourcher.getText().trim(), tyLe, dateBatDau, dateKetThuc, trangThai);
    }

    public static GiaoDienKhuyenMai getInstance() {
        if (instance == null) {
            instance = new GiaoDienKhuyenMai();
        }
        return instance;
    }

    public void clickHienThi() {
        int index = tbl_Voucher.getSelectedRow();
        if (index > -1) {
            String selectedID = tbl_Voucher.getValueAt(index, 1).toString();
            List<Voucher> vouchers = rpKM.getAllByID(selectedID);
            if (vouchers != null && !vouchers.isEmpty()) {
                Voucher vc = vouchers.get(0);
                String ngayBatDauFormatted = sdf.format(vc.getNgayBatDau());
                String ngayKetThucFormatted = sdf.format(vc.getNgayKetThuc());
                lbl_MaVoucher.setText(vc.getIDVoucher());
                txt_TenVourcher.setText(vc.getTenVoucher());
                txt_TyLe.setText(String.valueOf(vc.getTyLe()));
                txt_NgayBatDau.setText(ngayBatDauFormatted);
                txt_NgayKetThuc.setText(ngayKetThucFormatted);
                if (vc.isTrangThai()) {
                    rdo_HoatDong.setSelected(true);
                    rdo_DungHoatDong.setSelected(false);
                } else {
                    rdo_HoatDong.setSelected(false);
                    rdo_DungHoatDong.setSelected(true);
                }
            } else {
                System.out.println("Không tìm thấy voucher với ID: " + selectedID);
            }
            vouchers.clear();
        } else {
            System.out.println("Không có hàng nào được chọn trong bảng Voucher.");
        }
    }

    public void lamMoiGG() {
        txt_MaGiamGia.setText("");
        txt_TenGiamGia.setText("");
        txt_TyLeGiamGia.setText("");
        txt_NgayBatDauG.setText("dd - MM - yyyy");
        txt_NgayKetThucG.setText("dd - MM - yyyy");
        btnHD.setSelected(true);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        dateChooserStart = new com.raven.datechooser.DateChooser();
        dateChooserEnd = new com.raven.datechooser.DateChooser();
        dateChooser1 = new com.raven.datechooser.DateChooser();
        dateChooser2 = new com.raven.datechooser.DateChooser();
        tabbedPaneCustom1 = new view.until.tabbedpane.TabbedPaneCustom();
        jPanel1 = new javax.swing.JPanel();
        txt_NgayBatDau = new view.until.textfield.TextFieldSuggestion();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_timKiem = new view.until.textfield.TextFieldSuggestion();
        btn_TimKiemV = new view.until.button.Button();
        btn_LamMoi = new view.until.button.Button();
        btn_CapNhat = new view.until.button.Button();
        btn_Them = new view.until.button.Button();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_TenVourcher = new view.until.textfield.TextFieldSuggestion();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_TyLe = new view.until.textfield.TextFieldSuggestion();
        txt_NgayKetThuc = new view.until.textfield.TextFieldSuggestion();
        jLabel7 = new javax.swing.JLabel();
        scrollVC = new javax.swing.JScrollPane();
        tbl_Voucher = new javax.swing.JTable();
        lbl_MaVoucher = new view.until.textfield.TextFieldSuggestion();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        rdo_DungHoatDong = new javax.swing.JRadioButton();
        rdo_HoatDong = new javax.swing.JRadioButton();
        btn_LamMoiTimKiem = new view.until.button.Button();
        jPanel2 = new javax.swing.JPanel();
        panel_SPGiamGia = new javax.swing.JPanel();
        scrollSPGG = new javax.swing.JScrollPane();
        tbl_SPGiamGia = new javax.swing.JTable();
        cbosMaGiamGia = new view.until.combobox.ComboBoxSuggestion();
        jLabel13 = new javax.swing.JLabel();
        btn_TimKiemSP = new view.until.button.Button();
        jLabel12 = new javax.swing.JLabel();
        lbl_tilte = new javax.swing.JLabel();
        txt_LoaiTS = new view.until.textfield.TextFieldSuggestion();
        btn_ThemSPGiamGia = new view.until.button.Button();
        btn_XoaSPGiamGia = new view.until.button.Button();
        btn_LamMoiGiamGia = new view.until.button.Button();
        pnl_ThemGiamGia = new javax.swing.JPanel();
        btn_Them1 = new view.until.button.Button();
        btn_CapNhat1 = new view.until.button.Button();
        btn_LamMoi1 = new view.until.button.Button();
        btnKT = new javax.swing.JRadioButton();
        btnHD = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_NgayKetThucG = new view.until.textfield.TextFieldSuggestion();
        txt_NgayBatDauG = new view.until.textfield.TextFieldSuggestion();
        jLabel14 = new javax.swing.JLabel();
        txt_MaGiamGia = new view.until.textfield.TextFieldSuggestion();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_TyLeGiamGia = new view.until.textfield.TextFieldSuggestion();
        jLabel3 = new javax.swing.JLabel();
        scroll_GiamGia = new javax.swing.JScrollPane();
        tbl_GiamGia = new javax.swing.JTable();
        txt_maGiamGiaTimKiem = new view.until.textfield.TextFieldSuggestion();
        cbo__trangThai = new view.until.combobox.ComboBoxSuggestion();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        btn_TimKiemSP1 = new view.until.button.Button();
        txt_TenGiamGia = new view.until.textfield.TextFieldSuggestion();
        jLabel19 = new javax.swing.JLabel();
        btn_LamMoiGiamGia1 = new view.until.button.Button();

        dateChooserStart.setTextRefernce(txt_NgayBatDau);

        dateChooserEnd.setTextRefernce(txt_NgayKetThuc);

        dateChooser1.setTextRefernce(txt_NgayBatDauG);

        dateChooser2.setTextRefernce(txt_NgayKetThucG);

        tabbedPaneCustom1.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel9.setText("Trạng Thái:");

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Danh Sách Voucher");

        txt_timKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_timKiemActionPerformed(evt);
            }
        });

        btn_TimKiemV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/search.png"))); // NOI18N
        btn_TimKiemV.setColor1(new java.awt.Color(16, 24, 32));
        btn_TimKiemV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_TimKiemVMouseClicked(evt);
            }
        });
        btn_TimKiemV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemVActionPerformed(evt);
            }
        });

        btn_LamMoi.setText("Làm Mới");
        btn_LamMoi.setColor1(new java.awt.Color(16, 24, 32));
        btn_LamMoi.setColor2(new java.awt.Color(254, 231, 21));
        btn_LamMoi.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btn_LamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiActionPerformed(evt);
            }
        });

        btn_CapNhat.setText("Cập Nhật");
        btn_CapNhat.setColor1(new java.awt.Color(16, 24, 32));
        btn_CapNhat.setColor2(new java.awt.Color(254, 231, 21));
        btn_CapNhat.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btn_CapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CapNhatActionPerformed(evt);
            }
        });

        btn_Them.setText("Thêm");
        btn_Them.setColor1(new java.awt.Color(16, 24, 32));
        btn_Them.setColor2(new java.awt.Color(254, 231, 21));
        btn_Them.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel1.setText("Mã Voucher:");

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel2.setText("Tên Voucher:");

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel4.setText("Ngày Bắt Đầu:");

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel5.setText("Ngày Kết Thúc:");

        txt_TyLe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_TyLeActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel7.setText("Tỷ Lệ:");

        scrollVC.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        tbl_Voucher.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Voucher", "Tên Voucher", "Tỷ Lệ", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_Voucher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_VoucherMouseClicked(evt);
            }
        });
        scrollVC.setViewportView(tbl_Voucher);
        if (tbl_Voucher.getColumnModel().getColumnCount() > 0) {
            tbl_Voucher.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        lbl_MaVoucher.setEditable(false);
        lbl_MaVoucher.setFocusable(false);
        lbl_MaVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbl_MaVoucherActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel18.setText("Mã Voucher");

        buttonGroup1.add(rdo_DungHoatDong);
        rdo_DungHoatDong.setText("Kết Thúc");
        rdo_DungHoatDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_DungHoatDongActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdo_HoatDong);
        rdo_HoatDong.setSelected(true);
        rdo_HoatDong.setText("Hoạt Động");

        btn_LamMoiTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/lammoi.png"))); // NOI18N
        btn_LamMoiTimKiem.setColor1(new java.awt.Color(16, 24, 32));
        btn_LamMoiTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_LamMoiTimKiemMouseClicked(evt);
            }
        });
        btn_LamMoiTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(scrollVC)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txt_timKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_LamMoiTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_TimKiemV, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(39, 39, 39))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbl_MaVoucher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_NgayBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_TyLe, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(85, 85, 85)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addComponent(jLabel9))
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_NgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rdo_HoatDong)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(rdo_DungHoatDong))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txt_TenVourcher, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(285, 285, 285)
                        .addComponent(btn_LamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(btn_CapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(231, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbl_MaVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txt_TenVourcher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_TyLe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(rdo_HoatDong)
                    .addComponent(rdo_DungHoatDong))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_NgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txt_NgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_LamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_CapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_TimKiemV, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_LamMoiTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_timKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(scrollVC, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)))
                .addContainerGap())
        );

        tabbedPaneCustom1.addTab("Voucher", jPanel1);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        scrollSPGG.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        tbl_SPGiamGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "SELECT", "STT", "Mã Giảm Giá", "Mã Sản Phẩm", "Tên Phân Loại", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Tỷ Lệ Giảm Giá (%)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_SPGiamGia.getTableHeader().setReorderingAllowed(false);
        scrollSPGG.setViewportView(tbl_SPGiamGia);
        if (tbl_SPGiamGia.getColumnModel().getColumnCount() > 0) {
            tbl_SPGiamGia.getColumnModel().getColumn(0).setMaxWidth(50);
            tbl_SPGiamGia.getColumnModel().getColumn(1).setMaxWidth(50);
        }

        cbosMaGiamGia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất Cả" }));

        jLabel13.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel13.setText("Loại Trang Sức");

        btn_TimKiemSP.setBorder(null);
        btn_TimKiemSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/search.png"))); // NOI18N
        btn_TimKiemSP.setColor1(new java.awt.Color(16, 24, 32));
        btn_TimKiemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemSPActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Trang Sức Giảm Giá");

        lbl_tilte.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        lbl_tilte.setText("Mã Giảm Giá");

        btn_ThemSPGiamGia.setText("Thêm");
        btn_ThemSPGiamGia.setColor1(new java.awt.Color(16, 24, 32));
        btn_ThemSPGiamGia.setColor2(new java.awt.Color(254, 231, 21));
        btn_ThemSPGiamGia.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btn_ThemSPGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemSPGiamGiaActionPerformed(evt);
            }
        });

        btn_XoaSPGiamGia.setText("Xóa");
        btn_XoaSPGiamGia.setColor1(new java.awt.Color(16, 24, 32));
        btn_XoaSPGiamGia.setColor2(new java.awt.Color(254, 231, 21));
        btn_XoaSPGiamGia.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btn_XoaSPGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaSPGiamGiaActionPerformed(evt);
            }
        });

        btn_LamMoiGiamGia.setBorder(null);
        btn_LamMoiGiamGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/lammoi.png"))); // NOI18N
        btn_LamMoiGiamGia.setColor1(new java.awt.Color(16, 24, 32));
        btn_LamMoiGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiGiamGiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_SPGiamGiaLayout = new javax.swing.GroupLayout(panel_SPGiamGia);
        panel_SPGiamGia.setLayout(panel_SPGiamGiaLayout);
        panel_SPGiamGiaLayout.setHorizontalGroup(
            panel_SPGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_SPGiamGiaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_SPGiamGiaLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(panel_SPGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_LoaiTS, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(59, 59, 59)
                .addGroup(panel_SPGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_tilte)
                    .addGroup(panel_SPGiamGiaLayout.createSequentialGroup()
                        .addComponent(cbosMaGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(btn_LamMoiGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_TimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 195, Short.MAX_VALUE)
                .addComponent(btn_ThemSPGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(btn_XoaSPGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
            .addGroup(panel_SPGiamGiaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollSPGG)
                .addContainerGap())
        );
        panel_SPGiamGiaLayout.setVerticalGroup(
            panel_SPGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_SPGiamGiaLayout.createSequentialGroup()
                .addGroup(panel_SPGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_SPGiamGiaLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(panel_SPGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(lbl_tilte))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_SPGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbosMaGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_LoaiTS, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panel_SPGiamGiaLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(panel_SPGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_ThemSPGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_XoaSPGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_SPGiamGiaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel_SPGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_TimKiemSP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_LamMoiGiamGia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollSPGG, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );

        pnl_ThemGiamGia.setBackground(new java.awt.Color(255, 255, 255));

        btn_Them1.setText("Thêm");
        btn_Them1.setColor1(new java.awt.Color(16, 24, 32));
        btn_Them1.setColor2(new java.awt.Color(254, 231, 21));
        btn_Them1.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btn_Them1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Them1ActionPerformed(evt);
            }
        });

        btn_CapNhat1.setText("Cập Nhật");
        btn_CapNhat1.setColor1(new java.awt.Color(16, 24, 32));
        btn_CapNhat1.setColor2(new java.awt.Color(254, 231, 21));
        btn_CapNhat1.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btn_CapNhat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CapNhat1ActionPerformed(evt);
            }
        });

        btn_LamMoi1.setText("Làm Mới");
        btn_LamMoi1.setColor1(new java.awt.Color(16, 24, 32));
        btn_LamMoi1.setColor2(new java.awt.Color(254, 231, 21));
        btn_LamMoi1.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btn_LamMoi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoi1ActionPerformed(evt);
            }
        });

        buttonGroup2.add(btnKT);
        btnKT.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btnKT.setText("Kết Thúc");

        buttonGroup2.add(btnHD);
        btnHD.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btnHD.setSelected(true);
        btnHD.setText("Hoạt Động");
        btnHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHDActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel11.setText("Trạng Thái:");

        jLabel15.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel15.setText("Ngày Kết Thúc:");

        txt_NgayKetThucG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_NgayKetThucGActionPerformed(evt);
            }
        });

        txt_NgayBatDauG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_NgayBatDauGActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel14.setText("Ngày Bắt Đầu:");

        txt_MaGiamGia.setEditable(false);
        txt_MaGiamGia.setFocusable(false);
        txt_MaGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_MaGiamGiaActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel10.setText("Tỷ Lệ:");

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel8.setText("Tên Giảm Giá:");

        txt_TyLeGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_TyLeGiamGiaActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel3.setText("Mã Giảm Giá:");

        tbl_GiamGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Giảm Giá", "Tên Giảm Giá", "Tỷ Lệ (%)", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_GiamGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_GiamGiaMouseClicked(evt);
            }
        });
        scroll_GiamGia.setViewportView(tbl_GiamGia);
        if (tbl_GiamGia.getColumnModel().getColumnCount() > 0) {
            tbl_GiamGia.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        cbo__trangThai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất Cả", "Hoạt Động", "Ngừng Hoạt Động" }));
        cbo__trangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo__trangThaiActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel16.setText("Mã Giảm Giá:");

        jLabel17.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel17.setText("Trạng Thái:");

        btn_TimKiemSP1.setBorder(null);
        btn_TimKiemSP1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/search.png"))); // NOI18N
        btn_TimKiemSP1.setColor1(new java.awt.Color(16, 24, 32));
        btn_TimKiemSP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemSP1ActionPerformed(evt);
            }
        });

        txt_TenGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_TenGiamGiaActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel19.setText("Danh Sách Mã Giảm Giá");

        btn_LamMoiGiamGia1.setBorder(null);
        btn_LamMoiGiamGia1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/lammoi.png"))); // NOI18N
        btn_LamMoiGiamGia1.setColor1(new java.awt.Color(16, 24, 32));
        btn_LamMoiGiamGia1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiGiamGia1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_ThemGiamGiaLayout = new javax.swing.GroupLayout(pnl_ThemGiamGia);
        pnl_ThemGiamGia.setLayout(pnl_ThemGiamGiaLayout);
        pnl_ThemGiamGiaLayout.setHorizontalGroup(
            pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_ThemGiamGiaLayout.createSequentialGroup()
                .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_ThemGiamGiaLayout.createSequentialGroup()
                        .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_ThemGiamGiaLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel14)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel8))
                                    .addGroup(pnl_ThemGiamGiaLayout.createSequentialGroup()
                                        .addComponent(btn_LamMoi1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(52, 52, 52)
                                        .addComponent(btn_CapNhat1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(55, 55, 55)
                                        .addComponent(btn_Them1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnl_ThemGiamGiaLayout.createSequentialGroup()
                                    .addGap(137, 137, 137)
                                    .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txt_TyLeGiamGia, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                                        .addGroup(pnl_ThemGiamGiaLayout.createSequentialGroup()
                                            .addComponent(btnHD)
                                            .addGap(27, 27, 27)
                                            .addComponent(btnKT))
                                        .addComponent(txt_MaGiamGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_TenGiamGia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(pnl_ThemGiamGiaLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txt_NgayBatDauG, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt_NgayKetThucG, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_ThemGiamGiaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel15))
                        .addGap(346, 346, 346)))
                .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_ThemGiamGiaLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addGroup(pnl_ThemGiamGiaLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txt_maGiamGiaTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(35, 35, 35)
                        .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addGroup(pnl_ThemGiamGiaLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(cbo__trangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_LamMoiGiamGia1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_TimKiemSP1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(100, Short.MAX_VALUE))
                    .addGroup(pnl_ThemGiamGiaLayout.createSequentialGroup()
                        .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scroll_GiamGia, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
                            .addGroup(pnl_ThemGiamGiaLayout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        pnl_ThemGiamGiaLayout.setVerticalGroup(
            pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_ThemGiamGiaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_ThemGiamGiaLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_MaGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txt_TenGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel17)
                    .addGroup(pnl_ThemGiamGiaLayout.createSequentialGroup()
                        .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnl_ThemGiamGiaLayout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_maGiamGiaTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbo__trangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btn_LamMoiGiamGia1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_TimKiemSP1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addComponent(jLabel19)))
                .addGap(6, 6, 6)
                .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_ThemGiamGiaLayout.createSequentialGroup()
                        .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_TyLeGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(12, 12, 12)
                        .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txt_NgayBatDauG, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_NgayKetThucG, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(12, 12, 12)
                        .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(btnHD)
                            .addComponent(btnKT))
                        .addGap(18, 18, 18)
                        .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Them1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_CapNhat1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_LamMoi1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(scroll_GiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_SPGiamGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_ThemGiamGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnl_ThemGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 299, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel_SPGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabbedPaneCustom1.addTab("Giảm Giá", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPaneCustom1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabbedPaneCustom1, javax.swing.GroupLayout.PREFERRED_SIZE, 771, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_TimKiemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemSPActionPerformed
        GiaoDienKhuyenMaiModel gdmd = new GiaoDienKhuyenMaiModel();
        String tenPL = txt_LoaiTS.getText().trim();
        boolean found = false;
        for (GiamGia gg : rpKM.getAllGiamGia()) {
            if (gg.getIDGIamGia().equals(cbosMaGiamGia.getSelectedItem())) {
                gdmd.setMaGiamGiaGiamGia(gg.getIDGIamGia());
                found = true;
                break;
            }
        }
        if (!found) {
            gdmd.setMaGiamGiaGiamGia("");
        }
        if (!tenPL.isEmpty() && tenPL != null) {
            gdmd.setLoaiTrangSuc(tenPL);
        }
        qlKm.SearchGiamGiaSp(tbl_SPGiamGia, gdmd);

    }//GEN-LAST:event_btn_TimKiemSPActionPerformed

    private void btn_Them1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Them1ActionPerformed

        if (!vld.checkIsEmpty(txt_MaGiamGia.getText())) {
            NotificationJPanel panel = new NotificationJPanel(this, NotificationJPanel.Type.WARNING, NotificationJPanel.Location.CENTER, "Mã Giảm Giá Đã Tồn Tại Vui Lòng Làm Mới");
            panel.showNotification();
            return;
        }
        if (vld.checkIsEmpty(txt_TenGiamGia.getText())
                || vld.checkIsEmpty(txt_TyLeGiamGia.getText())
                || txt_NgayBatDauG.getText().equalsIgnoreCase("dd - MM - yyyy")
                || txt_NgayKetThucG.getText().equalsIgnoreCase("dd - MM - yyyy")) {
            NotificationJPanel panel = new NotificationJPanel(this, NotificationJPanel.Type.WARNING, NotificationJPanel.Location.CENTER, "Không Để Trống Dữ Liệu");
            panel.showNotification();
            return;
        }
        if (!vld.checkNumber(txt_TyLeGiamGia.getText(), true)) {
            NotificationJPanel panel = new NotificationJPanel(this, NotificationJPanel.Type.WARNING, NotificationJPanel.Location.CENTER, "Tỷ Lệ Là Số Và Lớn Hơn 0");
            panel.showNotification();
            return;
        }
        if (!vld.isEndDateValid(txt_NgayBatDauG.getText(), txt_NgayKetThucG.getText())) {
            NotificationJPanel panel = new NotificationJPanel(this, NotificationJPanel.Type.WARNING, NotificationJPanel.Location.CENTER, "Ngày Kết Thúc Phải Lớn Hơn Ngày Bắt Đầu");
            panel.showNotification();
            return;
        }
        if (vld.checkContaintGiamGia(null, txt_TenGiamGia.getText())) {
            NotificationJPanel panel = new NotificationJPanel(this, NotificationJPanel.Type.WARNING, NotificationJPanel.Location.CENTER, "Tên Giảm Giá Đã Tồn Tại");
            panel.showNotification();
            return;
        }
        qlKm.addNewGG(txt_TenGiamGia, txt_TyLeGiamGia, txt_NgayBatDauG, txt_NgayKetThucG, btnHD, btnHD, txt_MaGiamGia);
        NotificationJPanel panel = new NotificationJPanel(this, NotificationJPanel.Type.SUCCESS, NotificationJPanel.Location.CENTER, "Thêm Mới Giảm Giá Thành Công");
        panel.showNotification();
        lamMoiGG();
        qlKm.fillTableGiamGia(tbl_GiamGia);
    }//GEN-LAST:event_btn_Them1ActionPerformed

    private void btn_CapNhat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CapNhat1ActionPerformed
        int i = tbl_GiamGia.getSelectedRow();
        if (i < 0) {
            NotificationJPanel panel = new NotificationJPanel(this, NotificationJPanel.Type.WARNING, NotificationJPanel.Location.CENTER, "Chọn Giảm Giá Cần Cập Nhật");
            panel.showNotification();
            return;
        }
        if (vld.checkIsEmpty(txt_TenGiamGia.getText())
                || vld.checkIsEmpty(txt_TyLeGiamGia.getText())
                || txt_NgayBatDauG.getText().equalsIgnoreCase("dd - MM - yyyy")
                || txt_NgayKetThucG.getText().equalsIgnoreCase("dd - MM - yyyy")) {
            NotificationJPanel panel = new NotificationJPanel(this, NotificationJPanel.Type.WARNING, NotificationJPanel.Location.CENTER, "Không Để Trống Dữ Liệu");
            panel.showNotification();
            return;
        }
        if (!vld.checkNumber(txt_TyLeGiamGia.getText(), true)) {
            NotificationJPanel panel = new NotificationJPanel(this, NotificationJPanel.Type.WARNING, NotificationJPanel.Location.CENTER, "Tỷ Lệ Là Số Và Lớn Hơn 0");
            panel.showNotification();
            return;
        }
        if (!vld.isEndDateValid(txt_NgayBatDauG.getText(), txt_NgayKetThucG.getText())) {
            NotificationJPanel panel = new NotificationJPanel(this, NotificationJPanel.Type.WARNING, NotificationJPanel.Location.CENTER, "Ngày Kết Thúc Phải Lớn Hơn Ngày Bắt Đầu");
            panel.showNotification();
            return;
        }
        if (vld.checkContaintGiamGia(txt_MaGiamGia.getText(), txt_TenGiamGia.getText())) {
            NotificationJPanel panel = new NotificationJPanel(this, NotificationJPanel.Type.WARNING, NotificationJPanel.Location.CENTER, "Tên Giảm Giá Đã Tồn Tại");
            panel.showNotification();
            return;
        }
        if (vld.checkIsEmpty(txt_MaGiamGia.getText())) {
            NotificationJPanel panel = new NotificationJPanel(this, NotificationJPanel.Type.WARNING, NotificationJPanel.Location.CENTER, "Giảm Giá Không Tồn Tại Ấn Thêm Mới");
            panel.showNotification();
            return;
        }
        qlKm.updateGG(txt_TenGiamGia, txt_TyLeGiamGia, txt_NgayBatDauG, txt_NgayKetThucG, btnHD, btnHD, txt_MaGiamGia);
        NotificationJPanel panel = new NotificationJPanel(this, NotificationJPanel.Type.SUCCESS, NotificationJPanel.Location.CENTER, "Cập Nhật Giảm Giá Thành Công");
        panel.showNotification();
        lamMoiGG();
        qlKm.fillTableGiamGia(tbl_GiamGia);
    }//GEN-LAST:event_btn_CapNhat1ActionPerformed

    private void btn_LamMoi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoi1ActionPerformed
        lamMoiGG();
    }//GEN-LAST:event_btn_LamMoi1ActionPerformed

    private void btnHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHDActionPerformed

    private void txt_NgayKetThucGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_NgayKetThucGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NgayKetThucGActionPerformed

    private void txt_NgayBatDauGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_NgayBatDauGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NgayBatDauGActionPerformed

    private void txt_MaGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_MaGiamGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_MaGiamGiaActionPerformed

    private void txt_TyLeGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_TyLeGiamGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_TyLeGiamGiaActionPerformed

    private void cbo__trangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo__trangThaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo__trangThaiActionPerformed

    private void btn_TimKiemSP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemSP1ActionPerformed
        GiaoDienKhuyenMaiModel gdmd = new GiaoDienKhuyenMaiModel();
        String mgg = txt_maGiamGiaTimKiem.getText().trim();
        if (!mgg.isEmpty() && mgg != null) {
            gdmd.setMaGiamGiaGiamGia(mgg);
        }
        gdmd.setTrangThai(cbo__trangThai.getSelectedIndex());
        qlKm.SearchGiamGiaGG(tbl_GiamGia, gdmd);

    }//GEN-LAST:event_btn_TimKiemSP1ActionPerformed

    private void btn_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiActionPerformed
        lamMoi();
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_LamMoiActionPerformed

    private void btn_CapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CapNhatActionPerformed
        int i = tbl_Voucher.getSelectedRow();
        if (i < 0) {
            NotificationJPanel panel = new NotificationJPanel(this, NotificationJPanel.Type.WARNING, NotificationJPanel.Location.CENTER, "Chọn Voucher Cần Cập Nhật");
            panel.showNotification();
            return;
        }
        if (vld.checkIsEmpty(txt_TenVourcher.getText())
                || vld.checkIsEmpty(txt_TyLe.getText())
                || txt_NgayBatDau.getText().equalsIgnoreCase("dd - MM - yyyy")
                || txt_NgayKetThuc.getText().equalsIgnoreCase("dd - MM - yyyy")) {
            NotificationJPanel panel = new NotificationJPanel(this, NotificationJPanel.Type.WARNING, NotificationJPanel.Location.CENTER, "Không Để Trống Dữ Liệu");
            panel.showNotification();
            return;
        }
        if (!vld.checkNumber(txt_TyLe.getText(), true)) {
            NotificationJPanel panel = new NotificationJPanel(this, NotificationJPanel.Type.WARNING, NotificationJPanel.Location.CENTER, "Tỷ Lệ Là Số Và Lớn Hơn 0");
            panel.showNotification();
            return;
        }
        if (!vld.isEndDateValid(txt_NgayBatDau.getText(), txt_NgayKetThuc.getText())) {
            NotificationJPanel panel = new NotificationJPanel(this, NotificationJPanel.Type.WARNING, NotificationJPanel.Location.CENTER, "Ngày Kết Thúc Phải Lớn Hơn Ngày Bắt Đầu");
            panel.showNotification();
            return;
        }
        if (vld.checkContaintVoucher(lbl_MaVoucher.getText(), txt_TenVourcher.getText())) {
            NotificationJPanel panel = new NotificationJPanel(this, NotificationJPanel.Type.WARNING, NotificationJPanel.Location.CENTER, "Tên Voucher Đã Tồn Tại");
            panel.showNotification();
            return;
        }
        qlKm.suaVourcher(redFrom());
        NotificationJPanel panel = new NotificationJPanel(this, NotificationJPanel.Type.SUCCESS, NotificationJPanel.Location.CENTER, "Cập Nhật Voucher Thành Công");
        panel.showNotification();
        lamMoi();

        GiaoDienKhuyenMai gdnv = GiaoDienKhuyenMai.getInstance();
        if (gdnv != null) {
            gdnv.update();
        }

    }//GEN-LAST:event_btn_CapNhatActionPerformed

    private ValidateData vld = new ValidateData();
    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        if (!vld.checkIsEmpty(lbl_MaVoucher.getText())) {
            NotificationJPanel panel = new NotificationJPanel(this, NotificationJPanel.Type.WARNING, NotificationJPanel.Location.CENTER, "Mã Voucher Đã Tồn Tại Vui Lòng Làm Mới");
            panel.showNotification();
            return;
        }

        if (vld.checkIsEmpty(txt_TenVourcher.getText())
                || vld.checkIsEmpty(txt_TyLe.getText())
                || txt_NgayBatDau.getText().equalsIgnoreCase("dd - MM - yyyy")
                || txt_NgayKetThuc.getText().equalsIgnoreCase("dd - MM - yyyy")) {
            NotificationJPanel panel = new NotificationJPanel(this, NotificationJPanel.Type.WARNING, NotificationJPanel.Location.CENTER, "Không Để Trống Dữ Liệu");
            panel.showNotification();
            return;
        }
        if (!vld.checkNumber(txt_TyLe.getText(), true)) {
            NotificationJPanel panel = new NotificationJPanel(this, NotificationJPanel.Type.WARNING, NotificationJPanel.Location.CENTER, "Tỷ Lệ Là Số Và Lớn Hơn 0");
            panel.showNotification();
            return;
        }
        if (!vld.isEndDateValid(txt_NgayBatDau.getText(), txt_NgayKetThuc.getText())) {
            NotificationJPanel panel = new NotificationJPanel(this, NotificationJPanel.Type.WARNING, NotificationJPanel.Location.CENTER, "Ngày Kết Thúc Phải Lớn Hơn Ngày Bắt Đầu");
            panel.showNotification();
            return;
        }
        if (vld.checkContaintVoucher(null, txt_TenVourcher.getText())) {
            NotificationJPanel panel = new NotificationJPanel(this, NotificationJPanel.Type.WARNING, NotificationJPanel.Location.CENTER, "Tên Voucher Đã Tồn Tại");
            panel.showNotification();
            return;
        }
        qlKm.themVourcher(redFrom());
        NotificationJPanel panel = new NotificationJPanel(this, NotificationJPanel.Type.SUCCESS, NotificationJPanel.Location.CENTER, "Thêm Mới Voucher Thành Công");
        panel.showNotification();
        lamMoi();
        GiaoDienKhuyenMai gdnv = GiaoDienKhuyenMai.getInstance();
        if (gdnv != null) {
            gdnv.update();
        }
    }//GEN-LAST:event_btn_ThemActionPerformed

    private void txt_TyLeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_TyLeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_TyLeActionPerformed

    private void txt_timKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_timKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_timKiemActionPerformed

    private void btn_TimKiemVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemVActionPerformed
        // chưa làm
    }//GEN-LAST:event_btn_TimKiemVActionPerformed

    private void btn_TimKiemVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_TimKiemVMouseClicked
        qlKm.fillToTableByID(tbl_Voucher, txt_timKiem.getText().trim());
    }//GEN-LAST:event_btn_TimKiemVMouseClicked

    private void tbl_GiamGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_GiamGiaMouseClicked

        qlKm.fillWhenClick(tbl_GiamGia, txt_TenGiamGia, txt_TyLeGiamGia, txt_NgayBatDauG, txt_NgayKetThucG, btnHD, btnKT, txt_MaGiamGia);
    }//GEN-LAST:event_tbl_GiamGiaMouseClicked

    private void btn_ThemSPGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemSPGiamGiaActionPerformed
        ThemSanPhamGiamGia tgg = new ThemSanPhamGiamGia(main, true);
        tgg.setVisible(true);
    }//GEN-LAST:event_btn_ThemSPGiamGiaActionPerformed

    private void btn_XoaSPGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaSPGiamGiaActionPerformed
        List<String> selectedRows = qlKm.checkSelectedRowsTB1(tbl_SPGiamGia);
        System.out.println("Số lượng sản phẩm được chọn: " + selectedRows.size());
        if (selectedRows.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không có sản phẩm nào được chọn.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        boolean success = rpkm.updateNullIDGiamGiaForMultipleProducts(selectedRows);

        if (success) {
            JOptionPane.showMessageDialog(null, "Cập nhật giảm giá thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cập nhật giảm giá thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        qlKm.fillTableGiamGiaSP(tbl_SPGiamGia);
        GiaoDienBanHang gdbh = GiaoDienBanHang.getInstance();
        if (gdbh != null) {
            gdbh.update();
        }

    }//GEN-LAST:event_btn_XoaSPGiamGiaActionPerformed

    private void lbl_MaVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbl_MaVoucherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_MaVoucherActionPerformed

    private void rdo_DungHoatDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_DungHoatDongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_DungHoatDongActionPerformed

    private void tbl_VoucherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_VoucherMouseClicked
        // TODO add your handling code here:
        clickHienThi();
    }//GEN-LAST:event_tbl_VoucherMouseClicked

    private void txt_TenGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_TenGiamGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_TenGiamGiaActionPerformed

    private void btn_LamMoiTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_LamMoiTimKiemMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_LamMoiTimKiemMouseClicked

    private void btn_LamMoiTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiTimKiemActionPerformed
        // TODO add your handling code here:
        txt_timKiem.setText("");
        qlKm.fillToTable(tbl_Voucher);
    }//GEN-LAST:event_btn_LamMoiTimKiemActionPerformed

    private void btn_LamMoiGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiGiamGiaActionPerformed
        // TODO add your handling code here:
        cbosMaGiamGia.setSelectedIndex(0);
        txt_LoaiTS.setText("");
        qlKm.fillTableGiamGiaSP(tbl_SPGiamGia);
    }//GEN-LAST:event_btn_LamMoiGiamGiaActionPerformed

    private void btn_LamMoiGiamGia1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiGiamGia1ActionPerformed
        // TODO add your handling code here:
        cbo__trangThai.setSelectedIndex(0);
        txt_MaGiamGia.setText("");
        qlKm.fillTableGiamGia(tbl_GiamGia);
    }//GEN-LAST:event_btn_LamMoiGiamGia1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton btnHD;
    private javax.swing.JRadioButton btnKT;
    private view.until.button.Button btn_CapNhat;
    private view.until.button.Button btn_CapNhat1;
    private view.until.button.Button btn_LamMoi;
    private view.until.button.Button btn_LamMoi1;
    private view.until.button.Button btn_LamMoiGiamGia;
    private view.until.button.Button btn_LamMoiGiamGia1;
    private view.until.button.Button btn_LamMoiTimKiem;
    private view.until.button.Button btn_Them;
    private view.until.button.Button btn_Them1;
    private view.until.button.Button btn_ThemSPGiamGia;
    private view.until.button.Button btn_TimKiemSP;
    private view.until.button.Button btn_TimKiemSP1;
    private view.until.button.Button btn_TimKiemV;
    private view.until.button.Button btn_XoaSPGiamGia;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private view.until.combobox.ComboBoxSuggestion cbo__trangThai;
    private view.until.combobox.ComboBoxSuggestion cbosMaGiamGia;
    private com.raven.datechooser.DateChooser dateChooser1;
    private com.raven.datechooser.DateChooser dateChooser2;
    private com.raven.datechooser.DateChooser dateChooserEnd;
    private com.raven.datechooser.DateChooser dateChooserStart;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private view.until.textfield.TextFieldSuggestion lbl_MaVoucher;
    private javax.swing.JLabel lbl_tilte;
    private javax.swing.JPanel panel_SPGiamGia;
    private javax.swing.JPanel pnl_ThemGiamGia;
    private javax.swing.JRadioButton rdo_DungHoatDong;
    private javax.swing.JRadioButton rdo_HoatDong;
    private javax.swing.JScrollPane scrollSPGG;
    private javax.swing.JScrollPane scrollVC;
    private javax.swing.JScrollPane scroll_GiamGia;
    private view.until.tabbedpane.TabbedPaneCustom tabbedPaneCustom1;
    private javax.swing.JTable tbl_GiamGia;
    private javax.swing.JTable tbl_SPGiamGia;
    private javax.swing.JTable tbl_Voucher;
    private view.until.textfield.TextFieldSuggestion txt_LoaiTS;
    private view.until.textfield.TextFieldSuggestion txt_MaGiamGia;
    private view.until.textfield.TextFieldSuggestion txt_NgayBatDau;
    private view.until.textfield.TextFieldSuggestion txt_NgayBatDauG;
    private view.until.textfield.TextFieldSuggestion txt_NgayKetThuc;
    private view.until.textfield.TextFieldSuggestion txt_NgayKetThucG;
    private view.until.textfield.TextFieldSuggestion txt_TenGiamGia;
    private view.until.textfield.TextFieldSuggestion txt_TenVourcher;
    private view.until.textfield.TextFieldSuggestion txt_TyLe;
    private view.until.textfield.TextFieldSuggestion txt_TyLeGiamGia;
    private view.until.textfield.TextFieldSuggestion txt_maGiamGiaTimKiem;
    private view.until.textfield.TextFieldSuggestion txt_timKiem;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update() {
        qlKm.fillTableGiamGiaSP(tbl_SPGiamGia);
        qlKm.fillToTable(tbl_Voucher);
        tbl_Voucher.revalidate();
        tbl_Voucher.repaint();
    }
}
