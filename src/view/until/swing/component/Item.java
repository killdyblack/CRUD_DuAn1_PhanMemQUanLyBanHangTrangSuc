/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.until.swing.component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import model.GiamGia;
import model.SanPham;
import repository.SanPham.repoChiTietSanPham;
import service.banhang.BanHangService;

public class Item extends javax.swing.JPanel {

    private repository.SanPham.repoChiTietSanPham rpct = new repoChiTietSanPham();
    private boolean selected;
    private BanHangService bhs = new BanHangService();

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        repaint();
    }

    public Item() {
        initComponents();
        setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR) {
        });
    }
    private SanPham sp;

    public void setData(SanPham sp) {
        this.sp = sp;
        txt_MaSP.setText(sp.getTenSanPham());

        double giaChiTiet = sp.getGiaChiTiet();
        lbl_GiaBan.setText(bhs.formatToVND(giaChiTiet)); // Sử dụng phương thức formatToVND để định dạng giá bán

        if (sp.getIDGiamGia().getIDGIamGia() != null) {
            lbl_GiaBan.setText(String.format("<html><strike>%s</strike></html>", bhs.formatToVND(giaChiTiet)));
            lbl_GiaBan.setForeground(Color.GRAY);
            double giaGiam = giaChiTiet - (giaChiTiet * sp.getIDGiamGia().getTyLeGiamGia() / 100);
            System.out.println("GiaGiam:" + giaGiam);
            txt_GiaGiam.setText(bhs.formatToVND(giaGiam)); // Sử dụng phương thức formatToVND để định dạng giá giảm
        } else {
            txt_GiaGiam.setText("");
        }
        txt_SoLuong.setText(sp.getSoLuongTonKho() == 0 ? "Hết hàng" : String.valueOf(sp.getSoLuongTonKho()));
        if (sp.getHinhAnhSanPham() == null) {
            anh.setImage(new ImageIcon(getClass().getResource("/Icon/noimage.png")));
        } else {
            ImageIcon imageIcon = new ImageIcon(sp.getHinhAnhSanPham());
            Image image = imageIcon.getImage(); // Chuyển đổi về đối tượng Image
            Image scaledImage = image.getScaledInstance(anh.getWidth() - 2, anh.getHeight() - 2, Image.SCALE_SMOOTH); // Thay đổi kích thước ảnh
            imageIcon = new ImageIcon(scaledImage);
            anh.setImage(imageIcon);
        }
        // Đảm bảo cập nhật giao diện
        this.revalidate();
        this.repaint();
    }

    @Override
    public void paint(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(242, 242, 242));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        if (selected) {
            g2.setColor(new Color(94, 156, 255));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        }
        g2.dispose();
        super.paint(grphcs);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_MaSP = new javax.swing.JLabel();
        anh = new view.until.swing.PictureBox();
        txt_GiaGiam = new javax.swing.JLabel();
        txt_SoLuong = new javax.swing.JLabel();
        lbl_GiaBan = new javax.swing.JLabel();

        txt_MaSP.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_MaSP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_MaSP.setText("SP001");

        txt_GiaGiam.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txt_GiaGiam.setForeground(new java.awt.Color(255, 51, 51));
        txt_GiaGiam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_GiaGiam.setText("250.0000");

        txt_SoLuong.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_SoLuong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_SoLuong.setText("10");

        lbl_GiaBan.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lbl_GiaBan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_GiaBan.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_SoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
            .addComponent(lbl_GiaBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_MaSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(anh, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txt_GiaGiam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(txt_MaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(anh, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_SoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_GiaBan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_GiaGiam)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.until.swing.PictureBox anh;
    private javax.swing.JLabel lbl_GiaBan;
    private javax.swing.JLabel txt_GiaGiam;
    private javax.swing.JLabel txt_MaSP;
    private javax.swing.JLabel txt_SoLuong;
    // End of variables declaration//GEN-END:variables
}
