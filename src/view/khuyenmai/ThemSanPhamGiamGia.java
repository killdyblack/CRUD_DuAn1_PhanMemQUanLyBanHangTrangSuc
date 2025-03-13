/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view.khuyenmai;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Font;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import model.GiaoDien.GiaoDienKhuyenMaiModel;
import model.GiaoDien.ThemGGModel;
import repository.KhuyenMai.KhuyenMaiRepository;
import service.KhuyenMai.GiaoDienKhuyenMaiService;
import view.banhang.GiaoDienBanHang;
import view.until.sampletable.CheckBoxTableHeaderRenderer;
import view.until.sampletable.TableHeaderAlignment;

/**
 *
 * @author HUNGpYN
 */
public class ThemSanPhamGiamGia extends javax.swing.JDialog {

    private GiaoDienKhuyenMaiService gdkmsv = new GiaoDienKhuyenMaiService();
    private KhuyenMaiRepository rpkm = new KhuyenMaiRepository();

    /**
     * Creates new form ThemSanPhamGiamGia
     */
    public ThemSanPhamGiamGia(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
        setLocationRelativeTo(null);
        fillCbo();
        fillToCB02();
        fillToTb();
    }

    void fillToCB02() {
        gdkmsv.fillCbo2(cbo_LoaiTS_GG);
    }

    void fillToTb() {
        ThemGGModel tgg = new ThemGGModel();
        gdkmsv.SearchTSp(tbl_SanPham, tgg);
    }

    void fillCbo() {
        gdkmsv.fillCbo(cboThemSp_MGG);
    }

    private void init() {
        TableKhuyenMai tkm = new TableKhuyenMai();
        tkm.init(tbl_SanPham, scroll);
        tbl_SanPham.getColumnModel().getColumn(0).setHeaderRenderer(new CheckBoxTableHeaderRenderer(tbl_SanPham, 0));
        tbl_SanPham.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(tbl_SanPham));
        tbl_SanPham.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(tbl_SanPham));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        scroll = new javax.swing.JScrollPane();
        tbl_SanPham = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_maSP_GG = new view.until.textfield.TextFieldSuggestion();
        jLabel4 = new javax.swing.JLabel();
        btn_TimKiemSP = new view.until.button.Button();
        cboThemSp_MGG = new view.until.combobox.ComboBoxSuggestion();
        cbo_LoaiTS_GG = new view.until.combobox.ComboBoxSuggestion();
        btn_Huy = new view.until.button.Button();
        btn_Luu = new view.until.button.Button();
        jLabel7 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        btn_TimKiemSP1 = new view.until.button.Button();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel5.setText("jLabel5");

        jLabel6.setText("jLabel6");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(16, 24, 32));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(254, 231, 21));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Thêm Giảm Giá");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        scroll.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        tbl_SanPham.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tbl_SanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "SELECT", "STT", "Mã Trang Sức", "Tên Trang Sức", "Loại Trang Sức"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_SanPham.getTableHeader().setReorderingAllowed(false);
        scroll.setViewportView(tbl_SanPham);
        if (tbl_SanPham.getColumnModel().getColumnCount() > 0) {
            tbl_SanPham.getColumnModel().getColumn(0).setMaxWidth(50);
            tbl_SanPham.getColumnModel().getColumn(1).setMaxWidth(50);
        }

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel1.setText("Chọn Mã Giảm Giá:");

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel3.setText("Mã Sản Phẩm:");

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel4.setText("Loại Trang Sức:");

        btn_TimKiemSP.setBorder(null);
        btn_TimKiemSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/search.png"))); // NOI18N
        btn_TimKiemSP.setColor1(new java.awt.Color(16, 24, 32));
        btn_TimKiemSP.setColor2(new java.awt.Color(254, 231, 21));
        btn_TimKiemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemSPActionPerformed(evt);
            }
        });

        cboThemSp_MGG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboThemSp_MGGActionPerformed(evt);
            }
        });

        btn_Huy.setText("Hủy");
        btn_Huy.setColor1(new java.awt.Color(16, 24, 32));
        btn_Huy.setColor2(new java.awt.Color(254, 231, 21));
        btn_Huy.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btn_Huy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HuyActionPerformed(evt);
            }
        });

        btn_Luu.setText("Lưu");
        btn_Luu.setColor1(new java.awt.Color(16, 24, 32));
        btn_Luu.setColor2(new java.awt.Color(254, 231, 21));
        btn_Luu.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btn_Luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LuuActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel7.setText("Danh Sách Trang Sức");

        btn_TimKiemSP1.setBorder(null);
        btn_TimKiemSP1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/lammoi.png"))); // NOI18N
        btn_TimKiemSP1.setColor1(new java.awt.Color(16, 24, 32));
        btn_TimKiemSP1.setColor2(new java.awt.Color(254, 231, 21));
        btn_TimKiemSP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemSP1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scroll)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_maSP_GG, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(56, 56, 56)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(cbo_LoaiTS_GG, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(25, 25, 25)
                                        .addComponent(btn_TimKiemSP1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_TimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(45, 45, 45)
                                .addComponent(cboThemSp_MGG, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 629, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addComponent(btn_Huy, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(btn_Luu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboThemSp_MGG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(30, 30, 30)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(btn_TimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(430, 430, 430))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_maSP_GG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbo_LoaiTS_GG, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn_TimKiemSP1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Luu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Huy, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_TimKiemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemSPActionPerformed
        ThemGGModel tgg = new ThemGGModel();
        if (txt_maSP_GG.getText().trim() != null && !txt_maSP_GG.getText().trim().isEmpty()) {
            tgg.setMaTrangSuc(txt_maSP_GG.getText().trim());
        }
        if (cbo_LoaiTS_GG.getSelectedItem().toString().trim() != null && !cbo_LoaiTS_GG.getSelectedItem().toString().trim().isEmpty() && cbo_LoaiTS_GG.getSelectedItem().toString().trim() != "Tất Cả") {
            tgg.setLoaiTrangSuc(cbo_LoaiTS_GG.getSelectedItem().toString().trim());
        }
        gdkmsv.SearchTSp(tbl_SanPham, tgg);

    }//GEN-LAST:event_btn_TimKiemSPActionPerformed

    private void btn_LuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LuuActionPerformed

        List<String> selectedRows = gdkmsv.checkSelectedRows(tbl_SanPham);
        System.out.println(selectedRows.size());
        if (selectedRows.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không có sản phẩm nào được chọn.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String selectedDiscountCode = cboThemSp_MGG.getSelectedItem().toString();
        if (selectedDiscountCode == null || selectedDiscountCode.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn mã giảm giá.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        boolean success = rpkm.updateIDGiamGiaForMultipleProducts(selectedRows, selectedDiscountCode);

        if (success) {
            JOptionPane.showMessageDialog(null, "Cập nhật giảm giá thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cập nhật giảm giá thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        GiaoDienKhuyenMai gdnv = GiaoDienKhuyenMai.getInstance();
        if (gdnv != null) {
            gdnv.update();
        }
        GiaoDienBanHang gdbh = GiaoDienBanHang.getInstance();
        if (gdbh != null) {
            gdbh.update();
        }

        this.dispose();
    }//GEN-LAST:event_btn_LuuActionPerformed

    private void btn_HuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HuyActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_HuyActionPerformed

    private void cboThemSp_MGGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboThemSp_MGGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboThemSp_MGGActionPerformed

    private void btn_TimKiemSP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemSP1ActionPerformed
        // TODO add your handling code here:
        txt_maSP_GG.setText("");
        cboThemSp_MGG.setSelectedIndex(0);
    }//GEN-LAST:event_btn_TimKiemSP1ActionPerformed

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
                ThemSanPhamGiamGia dialog = new ThemSanPhamGiamGia(new javax.swing.JFrame(), true);
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
    private view.until.button.Button btn_Huy;
    private view.until.button.Button btn_Luu;
    private view.until.button.Button btn_TimKiemSP;
    private view.until.button.Button btn_TimKiemSP1;
    private view.until.combobox.ComboBoxSuggestion cboThemSp_MGG;
    private view.until.combobox.ComboBoxSuggestion cbo_LoaiTS_GG;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable tbl_SanPham;
    private view.until.textfield.TextFieldSuggestion txt_maSP_GG;
    // End of variables declaration//GEN-END:variables
}
