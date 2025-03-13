/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.nhanvien;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.UIManager;
import model.GiaoDien.GiaoDienNhanVienModel;
import service.observer.Observer;
import service.taikhoan.ServiceTaiKhoan;
import view.form.JTableHeader;
import view.khuyenmai.TableKhuyenMai;
import view.main.Main;

/**
 *
 * @author HUNGpYN
 */
public class GiaoDienNhanVien extends javax.swing.JPanel implements Observer {

    private Color color2 = Color.decode("#101820"); // Thanden
    private Color color1 = Color.decode("#FEE715"); // Mau vang
    private ServiceTaiKhoan stk; // Khai báo làm final để không thay đổi thể hiện
    private static GiaoDienNhanVien instance;
    private Main main;

    static {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("view.until.sampletable.themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacLightLaf.setup();
    }

    private GiaoDienNhanVien() {
        stk = new ServiceTaiKhoan(); // Khởi tạo ServiceTaiKhoan
        initComponents();
        stk.fillToTable(tbl_NhanVien); // Đổ dữ liệu vào bảng ngay khi khởi tạo
        stk.doubleCicled(tbl_NhanVien);
        init();
    }

    private void init() {
        TableKhuyenMai tkm = new TableKhuyenMai();
        tkm.init(tbl_NhanVien, scrollNV);
        tbl_NhanVien.getTableHeader().setDefaultRenderer(new JTableHeader(tbl_NhanVien));
        tbl_NhanVien.getTableHeader().setReorderingAllowed(false);
        lbl_DanhSach.setForeground(color1);
        panel_ThemMoi.setBackground(color2);
        btn_TimKiem.setColor1(color2);
        btn_TimKiem.setColor2(color1);
    }

    public static GiaoDienNhanVien getInstance() {
        if (instance == null) {
            instance = new GiaoDienNhanVien();
        }
        return instance;
    }

    public JTable tbl() {
        return tbl_NhanVien;
    }

    @Override
    public void update() {
        stk.fillToTable(tbl_NhanVien);
        tbl_NhanVien.revalidate();
        tbl_NhanVien.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_ThemMoi = new javax.swing.JPanel();
        lbl_DanhSach = new javax.swing.JLabel();
        btn_TaoMoi = new view.until.button.Button();
        txt_TimKiem = new view.until.textfield.TextFieldSuggestion();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbos_GioITinh = new view.until.combobox.ComboBoxSuggestion();
        jLabel3 = new javax.swing.JLabel();
        cbox_TrangThai = new view.until.combobox.ComboBoxSuggestion();
        btn_TimKiem = new view.until.button.Button();
        jLabel5 = new javax.swing.JLabel();
        scrollNV = new javax.swing.JScrollPane();
        tbl_NhanVien = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        btn_Excel1 = new view.until.button.Button();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1013, 612));

        panel_ThemMoi.setPreferredSize(new java.awt.Dimension(372, 64));

        lbl_DanhSach.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        lbl_DanhSach.setText("Danh Sách Nhân Viên");

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
                .addGap(20, 20, 20)
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

        txt_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_TimKiemActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel4.setText("Họ và tên");

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel2.setText("Giới Tính");

        cbos_GioITinh.setEditable(false);
        cbos_GioITinh.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất Cả", "Nam", "Nữ" }));
        cbos_GioITinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbos_GioITinhActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel3.setText("Trạng Thái");

        cbox_TrangThai.setEditable(false);
        cbox_TrangThai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất Cả", "Làm Việc", "Nghỉ Việc" }));
        cbox_TrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbox_TrangThaiActionPerformed(evt);
            }
        });

        btn_TimKiem.setText("Tìm Kiếm");
        btn_TimKiem.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btn_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel5.setText("Danh Sách Nhân viên");

        scrollNV.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        tbl_NhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Nhân Viên", "Họ Và Tên", "Tài Khoản", "Mật Khẩu", "Giới Tính", "Điện Thoại", "Chức Vụ", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollNV.setViewportView(tbl_NhanVien);
        if (tbl_NhanVien.getColumnModel().getColumnCount() > 0) {
            tbl_NhanVien.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        btn_Excel1.setBorder(null);
        btn_Excel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/lammoi.png"))); // NOI18N
        btn_Excel1.setColor1(new java.awt.Color(16, 24, 32));
        btn_Excel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Excel1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_ThemMoi, javax.swing.GroupLayout.DEFAULT_SIZE, 1013, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(101, 101, 101)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(cbos_GioITinh, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(116, 116, 116)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbox_TrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_Excel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(btn_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(180, 180, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1)
                            .addComponent(scrollNV))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel_ThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbox_TrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbos_GioITinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_Excel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollNV, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents
    
    private void btn_TaoMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TaoMoiActionPerformed
        // TODO add your handling code here:
        ThemMoiNhanVien tmnv = new ThemMoiNhanVien(main, true);
        tmnv.setVisible(true);
    }//GEN-LAST:event_btn_TaoMoiActionPerformed

    private void txt_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_TimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_TimKiemActionPerformed

    private void cbos_GioITinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbos_GioITinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbos_GioITinhActionPerformed

    private void cbox_TrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbox_TrangThaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbox_TrangThaiActionPerformed

    private void btn_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemActionPerformed
        GiaoDienNhanVienModel gdnvmd = new GiaoDienNhanVienModel();
        String ten = txt_TimKiem.getText().trim();
        if (!ten.isEmpty()) {
            gdnvmd.setHoTen(ten);
        }
        gdnvmd.setGioiTinh(cbos_GioITinh.getSelectedIndex());
        gdnvmd.setTrangThai(cbox_TrangThai.getSelectedIndex());
        stk.fillToTableCheck(tbl_NhanVien, gdnvmd);
    }//GEN-LAST:event_btn_TimKiemActionPerformed

    private void btn_Excel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Excel1ActionPerformed
        // TODO add your handling code here:
        cbos_GioITinh.setSelectedIndex(0);
        cbox_TrangThai.setSelectedIndex(0);
        txt_TimKiem.setText("");
        stk.fillToTable(tbl_NhanVien);
    }//GEN-LAST:event_btn_Excel1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.until.button.Button btn_Excel1;
    private view.until.button.Button btn_TaoMoi;
    private view.until.button.Button btn_TimKiem;
    private view.until.combobox.ComboBoxSuggestion cbos_GioITinh;
    private view.until.combobox.ComboBoxSuggestion cbox_TrangThai;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbl_DanhSach;
    private javax.swing.JPanel panel_ThemMoi;
    private javax.swing.JScrollPane scrollNV;
    private javax.swing.JTable tbl_NhanVien;
    private view.until.textfield.TextFieldSuggestion txt_TimKiem;
    // End of variables declaration//GEN-END:variables
}
