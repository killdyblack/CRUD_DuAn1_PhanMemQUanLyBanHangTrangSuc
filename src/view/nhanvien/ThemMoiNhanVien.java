/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view.nhanvien;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.UIManager;
import model.TaiKhoan;
import repository.taikhoan.RepositoryTaiKhoan;
import service.taikhoan.ServiceTaiKhoan;
import until.validate.ValidateData;
import view.until.hopthoai.Notification;

/**
 *
 * @author HUNGpYN
 */
public class ThemMoiNhanVien extends javax.swing.JDialog {

    private Color color2 = Color.decode("#101820");// thanden
    private Color color1 = Color.decode("#FEE715"); //mau vang
    private ServiceTaiKhoan stk;
    private repository.taikhoan.RepositoryTaiKhoan rpt;
    private GiaoDienNhanVien gdnv;
    private ValidateData vld = new ValidateData();

    public ThemMoiNhanVien(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setFont();
    }

    void setFont() {
        lbl_ThemMoi.setForeground(color1);
        pnl_ThemMoi.setBackground(color2);
        setLocationRelativeTo(null);
        btn_ThemAnh.setColor1(Color.LIGHT_GRAY);
        txt_NgaySinh.setText("dd - MM - yyyy");

    }

    TaiKhoan readForm() {
        TaiKhoan tk = new TaiKhoan();
        tk.setHoTen(txt_HoVaTen.getText().trim());
        tk.setTaiKhoan(txt_TaiKhoan.getText().trim());
        tk.setMatKhau(txt_MatKhau.getText().trim());
        tk.setDiaChi(txt_DiaChi.getText().trim());
        tk.setEmail(txt_Email.getText().trim());
        tk.setSoDienThoai(txt_SoDienThoai.getText().trim());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = null;
        try {
            utilDate = dateFormat.parse(txt_NgaySinh.getText().trim());
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            tk.setNgaySinh(sqlDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String link = (String) lbl_HinhAnh.getClientProperty("imagepath");
        tk.setHinhAnh(link);
        if (rdo_Nam.isSelected()) {
            tk.setGioiTinh(true);
        } else {
            tk.setGioiTinh(false);
        }
        if (rdo_QuanLi.isSelected()) {
            tk.setChucVu(true);
        } else {
            tk.setChucVu(false);
        }
        return tk;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new com.raven.datechooser.DateChooser();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_DiaChi = new javax.swing.JTextArea();
        pnl_ThemMoi = new javax.swing.JPanel();
        lbl_ThemMoi = new javax.swing.JLabel();
        rdo_NghiViec = new javax.swing.JRadioButton();
        rdo_NhanVien = new javax.swing.JRadioButton();
        btn_ThemAnh = new view.until.button.Button();
        btn_Huy = new view.until.button.Button();
        rdo_LamViec = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        btn_Luu = new view.until.button.Button();
        rdo_QuanLi = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_SoDienThoai = new view.until.textfield.TextFieldSuggestion();
        jLabel3 = new javax.swing.JLabel();
        txt_HoVaTen = new view.until.textfield.TextFieldSuggestion();
        jLabel4 = new javax.swing.JLabel();
        lbl_HinhAnh = new javax.swing.JLabel();
        txt_MatKhau = new view.until.textfield.TextFieldSuggestion();
        jLabel5 = new javax.swing.JLabel();
        txt_Email = new view.until.textfield.TextFieldSuggestion();
        jLabel6 = new javax.swing.JLabel();
        txt_NgaySinh = new view.until.textfield.TextFieldSuggestion();
        rdo_Nam = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        rdo_Nu = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        txt_TaiKhoan = new view.until.textfield.TextFieldSuggestion();

        dateChooser1.setForeground(new java.awt.Color(51, 51, 51));
        dateChooser1.setTextRefernce(txt_NgaySinh);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setText("Trạng Thái");

        txt_DiaChi.setColumns(20);
        txt_DiaChi.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        txt_DiaChi.setRows(5);
        jScrollPane2.setViewportView(txt_DiaChi);

        lbl_ThemMoi.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        lbl_ThemMoi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_ThemMoi.setText("Thêm Mới Nhân Viên");

        javax.swing.GroupLayout pnl_ThemMoiLayout = new javax.swing.GroupLayout(pnl_ThemMoi);
        pnl_ThemMoi.setLayout(pnl_ThemMoiLayout);
        pnl_ThemMoiLayout.setHorizontalGroup(
            pnl_ThemMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_ThemMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnl_ThemMoiLayout.setVerticalGroup(
            pnl_ThemMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_ThemMoiLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbl_ThemMoi)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        rdo_NghiViec.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        rdo_NghiViec.setText("Nghỉ Việc");
        rdo_NghiViec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_NghiViecActionPerformed(evt);
            }
        });

        rdo_NhanVien.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        rdo_NhanVien.setSelected(true);
        rdo_NhanVien.setText("Nhân Viên");

        btn_ThemAnh.setText("Thêm Ảnh");
        btn_ThemAnh.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btn_ThemAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemAnhActionPerformed(evt);
            }
        });

        btn_Huy.setText("Đóng");
        btn_Huy.setColor1(new java.awt.Color(16, 24, 32));
        btn_Huy.setColor2(new java.awt.Color(254, 231, 21));
        btn_Huy.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btn_Huy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HuyActionPerformed(evt);
            }
        });

        rdo_LamViec.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        rdo_LamViec.setSelected(true);
        rdo_LamViec.setText("Làm Việc");

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel1.setText("Họ Và Tên:");

        btn_Luu.setText("Lưu");
        btn_Luu.setColor1(new java.awt.Color(16, 24, 32));
        btn_Luu.setColor2(new java.awt.Color(254, 231, 21));
        btn_Luu.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btn_Luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LuuActionPerformed(evt);
            }
        });

        rdo_QuanLi.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        rdo_QuanLi.setText("Quản Lí");

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel10.setText("Địa Chỉ");

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel2.setText("Tài Khoản");

        txt_SoDienThoai.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        txt_SoDienThoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SoDienThoaiActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel3.setText("Mật khẩu");

        txt_HoVaTen.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        txt_HoVaTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_HoVaTenActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel4.setText("Số Điện Thoại");

        lbl_HinhAnh.setText("     ");

        txt_MatKhau.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        txt_MatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_MatKhauActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel5.setText("Email");

        txt_Email.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        txt_Email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_EmailActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel6.setText("Ngày Sinh");

        txt_NgaySinh.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        txt_NgaySinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_NgaySinhActionPerformed(evt);
            }
        });

        rdo_Nam.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        rdo_Nam.setSelected(true);
        rdo_Nam.setText("Nam");

        jLabel7.setText("Chức Vụ");

        rdo_Nu.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        rdo_Nu.setText("Nữ");

        jLabel8.setText("Giới Tính");

        txt_TaiKhoan.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_ThemMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_Huy, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(88, 88, 88)
                                .addComponent(btn_Luu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(rdo_QuanLi)
                                        .addGap(27, 27, 27)
                                        .addComponent(rdo_NhanVien)))
                                .addGap(66, 66, 66)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(rdo_LamViec)
                                        .addGap(29, 29, 29)
                                        .addComponent(rdo_NghiViec))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(btn_ThemAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(lbl_HinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txt_NgaySinh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                                            .addComponent(txt_TaiKhoan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txt_SoDienThoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(72, 72, 72)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txt_Email, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(txt_MatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGap(3, 3, 3)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel8)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(rdo_Nam)
                                                .addGap(85, 85, 85)
                                                .addComponent(rdo_Nu)))
                                        .addGap(9, 9, 9))))
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_HoVaTen, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnl_ThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbl_HinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_ThemAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_HoVaTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rdo_Nam)
                                    .addComponent(rdo_Nu))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_MatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_NgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_TaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_SoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdo_QuanLi)
                            .addComponent(rdo_NhanVien)
                            .addComponent(rdo_LamViec)
                            .addComponent(rdo_NghiViec))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Huy, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Luu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rdo_NghiViecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_NghiViecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_NghiViecActionPerformed

    private void btn_ThemAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemAnhActionPerformed
        stk = new ServiceTaiKhoan();
        stk.chonAnh(lbl_HinhAnh);
    }//GEN-LAST:event_btn_ThemAnhActionPerformed

    private void btn_HuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HuyActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btn_HuyActionPerformed

    private void btn_LuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LuuActionPerformed
        // TODO add your handling code here:
        if (vld.checkIsEmpty(txt_Email.getText())
                || vld.checkIsEmpty(txt_DiaChi.getText())
                || vld.checkIsEmpty(txt_HoVaTen.getText())
                || vld.checkIsEmpty(txt_TaiKhoan.getText())
                || vld.checkIsEmpty(txt_MatKhau.getText())
                || vld.checkIsEmpty(txt_SoDienThoai.getText()) || txt_NgaySinh.getText().equalsIgnoreCase("dd - MM - yyyy")) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Không Để Trống Dữ Liệu");
            panel.showNotification();
            return;

        }
        if (vld.checkContainNhanVien(null,txt_SoDienThoai.getText(), txt_TaiKhoan.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Tài Khoản Hoặc Số Điện Thoại Trùng Với nhân Viên Khác");
            panel.showNotification();
            return;
        }
        if (!vld.checkMail(txt_Email.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Email phải có định dạng 'example@example.com'");
            panel.showNotification();
            return;
        }
        if (!vld.checkPhone(txt_SoDienThoai.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Số Điện Thoại Phải Có 10 Chữ Số Và Bắt Đầu Bằng Số 0");
            panel.showNotification();
            return;
        }
        String link = (String) lbl_HinhAnh.getClientProperty("imagepath");
        if (link == null) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Chưa Chọn Ảnh");
            panel.showNotification();
            return;
        }
        RepositoryTaiKhoan rtk = new RepositoryTaiKhoan();
        rtk.creat(readForm()); // Thực hiện thêm dữ liệu vào cơ sở dữ liệu
        Notification panel = new Notification(this, Notification.Type.SUCCESS, Notification.Location.CENTER, "Thêm Thành Công");
        panel.showNotification();
        // Lấy thể hiện của GiaoDienNhanVien
        GiaoDienNhanVien gdnv = GiaoDienNhanVien.getInstance();
        if (gdnv != null) {
            gdnv.update();
        }

    }//GEN-LAST:event_btn_LuuActionPerformed

    private void txt_SoDienThoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SoDienThoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_SoDienThoaiActionPerformed

    private void txt_HoVaTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_HoVaTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_HoVaTenActionPerformed

    private void txt_MatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_MatKhauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_MatKhauActionPerformed

    private void txt_EmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_EmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_EmailActionPerformed

    private void txt_NgaySinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_NgaySinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NgaySinhActionPerformed

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
                ThemMoiNhanVien dialog = new ThemMoiNhanVien(new javax.swing.JFrame(), true);
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
    private view.until.button.Button btn_ThemAnh;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private com.raven.datechooser.DateChooser dateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_HinhAnh;
    private javax.swing.JLabel lbl_ThemMoi;
    private javax.swing.JPanel pnl_ThemMoi;
    private javax.swing.JRadioButton rdo_LamViec;
    private javax.swing.JRadioButton rdo_Nam;
    private javax.swing.JRadioButton rdo_NghiViec;
    private javax.swing.JRadioButton rdo_NhanVien;
    private javax.swing.JRadioButton rdo_Nu;
    private javax.swing.JRadioButton rdo_QuanLi;
    private javax.swing.JTextArea txt_DiaChi;
    private view.until.textfield.TextFieldSuggestion txt_Email;
    private view.until.textfield.TextFieldSuggestion txt_HoVaTen;
    private view.until.textfield.TextFieldSuggestion txt_MatKhau;
    private view.until.textfield.TextFieldSuggestion txt_NgaySinh;
    private view.until.textfield.TextFieldSuggestion txt_SoDienThoai;
    private view.until.textfield.TextFieldSuggestion txt_TaiKhoan;
    // End of variables declaration//GEN-END:variables
}
