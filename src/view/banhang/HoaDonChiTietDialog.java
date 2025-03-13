package view.banhang;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import model.HoaDon;
import model.HoaDonChiTiet;
import service.banhang.BanHangService;
import view.form.JTableHeader;
import view.khuyenmai.TableKhuyenMai;

/**
 *
 * @author HUNGpYN
 */
public class HoaDonChiTietDialog extends javax.swing.JDialog {

    private Color color2 = Color.decode("#101820");// thanden
    private Color color1 = Color.decode("#FEE715"); //mau vang
    private BanHangService bhs = new BanHangService();
    private GiaoDienBanHang gdBanHang = new GiaoDienBanHang();
    private repository.hoadonchitiet.RepositoryHoaDonChiTiet rll = new repository.hoadonchitiet.RepositoryHoaDonChiTiet();
    
    public HoaDonChiTietDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
        setFont();
        
    }

    private void init() {
        setLocationRelativeTo(null);
        TableKhuyenMai tkm = new TableKhuyenMai();
        tkm.init(tbl_HDCT, scroll);
        tbl_HDCT.getTableHeader().setDefaultRenderer(new JTableHeader(tbl_HDCT));
        tbl_HDCT.getTableHeader().setReorderingAllowed(false);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowOpened(java.awt.event.WindowEvent e) {
                btn_Dong.requestFocus();
            }
        });
    }

    public JTable getJTable() {
        return tbl_HDCT;
    }

    void setFont() {
        lbl_Menu.setBackground(color2);
        lbl_thongtin.setForeground(color1);
        setLocationRelativeTo(null);
        setTitle("LuxuryStore");

    }

    public void setData(HoaDonChiTiet ct) {
        String voucher;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        // Lấy thông tin hóa đơn
        HoaDon hd = ct.getIDHoaDon();

        // Lấy thông tin khách hàng
        String maHoaDon = hd.getIDHoaDon();
        String khachHang = hd.getIdKhachHang().getHoTen();
        String ngayTao = sdf.format(hd.getNgayTao());

        // Xử lý thông tin giảm giá và thanh toán
        if (hd.getIdVoucher().getIDVoucher() != null) {
            voucher = hd.getIdVoucher().getTenVoucher();
            System.out.println(voucher);
        } else {
            voucher = "Không";
        }

        // Cập nhật giao diện
        lbl_MaHoaDon.setText(maHoaDon);
        lbl_HoTenKhachHang.setText(khachHang);
        lbl_NgayTaoHoaDon.setText(ngayTao);
        lbl_TenVoucher.setText(voucher);
        lbl_ChietKhau.setText(bhs.formatToVND(hd.getTongTienTRuoc() - hd.getTongTienSau()));
        lbl_TongTien.setText(bhs.formatToVND(hd.getTongTienTRuoc()));
        lbl_ThanhToan.setText(bhs.formatToVND(hd.getTongTienSau()));
    }
    
    public void fillDataChiTiet(String text) {
        DefaultTableModel model = (DefaultTableModel) tbl_HDCT.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ trong bảng
        int STT = 1; // Số thứ tự bắt đầu từ 1
        double tongTien; // Tổng tiền cho từng sản phẩm
        double giamGia; // Giảm giá cho từng sản phẩm

        // Lặp qua các chi tiết hóa đơn
        for (HoaDonChiTiet ct : rll.getAll()) {
            if (ct.getIDHoaDon().getIDHoaDon().equalsIgnoreCase(text)) {
                // Tính toán giảm giá và tổng tiền
                double giaSanPham = ct.getGiaSanPham();
                double giaSauGiamGia = ct.getGiaSauGiamGia();
                int soLuongSanPham = ct.getSoLUongSanPHam();

                if (giaSauGiamGia > 0) {
                    // Nếu có giá sau giảm giá
                    giamGia = (giaSanPham - giaSauGiamGia) * soLuongSanPham;
                    tongTien = giaSauGiamGia * soLuongSanPham;
                } else {
                    // Nếu không có giá sau giảm giá
                    giamGia = 0;
                    tongTien = giaSanPham * soLuongSanPham;
                    System.out.println(tongTien);
                }

                // Thêm hàng vào bảng
                model.addRow(new Object[]{
                    STT++, // STT
                    ct.getIDSanPham().getTenSanPham(), // Tên sản phẩm
                    soLuongSanPham, // Số lượng sản phẩm
                    bhs.formatToVND(giaSanPham), // Giá sản phẩm
                    bhs.formatToVND(giamGia), // Giảm giá
                    bhs.formatToVND(tongTien) // Tổng tiền
                });
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lbl_Menu = new javax.swing.JPanel();
        lbl_thongtin = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbl_MaHoaDon = new javax.swing.JLabel();
        lbl_NgayTaoHoaDon = new javax.swing.JLabel();
        lbl_HoTenKhachHang = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbl_TenVoucher = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbl_TongTien = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbl_ChietKhau = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lbl_ThanhToan = new javax.swing.JLabel();
        scroll = new javax.swing.JScrollPane();
        tbl_HDCT = new javax.swing.JTable();
        btn_Dong = new view.until.button.Button();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbl_Menu.setBackground(new java.awt.Color(16, 24, 32));

        lbl_thongtin.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        lbl_thongtin.setForeground(new java.awt.Color(254, 231, 21));
        lbl_thongtin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_thongtin.setText("Hóa Đơn Chi Tiết");

        javax.swing.GroupLayout lbl_MenuLayout = new javax.swing.GroupLayout(lbl_Menu);
        lbl_Menu.setLayout(lbl_MenuLayout);
        lbl_MenuLayout.setHorizontalGroup(
            lbl_MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_thongtin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        lbl_MenuLayout.setVerticalGroup(
            lbl_MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lbl_MenuLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lbl_thongtin)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel3.setText("Mã Hóa Đơn:");

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel4.setText(" Khách Hàng:");

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel5.setText("Ngày Tạo:");

        lbl_MaHoaDon.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        lbl_NgayTaoHoaDon.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        lbl_HoTenKhachHang.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel7.setText("Voucher Sử Dụng:");

        lbl_TenVoucher.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel10.setText("Tổng Tiền:");

        lbl_TongTien.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_TongTien.setForeground(new java.awt.Color(51, 51, 51));
        lbl_TongTien.setText("1000000000000");

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel8.setText("Chiết Khẩu:");

        lbl_ChietKhau.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        lbl_ChietKhau.setForeground(new java.awt.Color(255, 0, 0));
        lbl_ChietKhau.setText("1000000000000");

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel11.setText("Thanh Toán:");

        lbl_ThanhToan.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        lbl_ThanhToan.setForeground(new java.awt.Color(0, 0, 255));
        lbl_ThanhToan.setText("100000000000000000");

        tbl_HDCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Tên Trang Sức", "Số Lượng", "Giá Bán", "Giảm Giá", "Tổng Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scroll.setViewportView(tbl_HDCT);
        if (tbl_HDCT.getColumnModel().getColumnCount() > 0) {
            tbl_HDCT.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        btn_Dong.setText("Đóng");
        btn_Dong.setColor1(new java.awt.Color(16, 24, 32));
        btn_Dong.setColor2(new java.awt.Color(254, 231, 21));
        btn_Dong.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btn_Dong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_Menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel4))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbl_MaHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbl_NgayTaoHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbl_HoTenKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbl_TenVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jLabel2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbl_TongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbl_ChietKhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbl_ThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 275, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scroll)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(238, 238, 238)
                .addComponent(btn_Dong, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lbl_Menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lbl_MaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lbl_HoTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lbl_NgayTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lbl_TenVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lbl_TongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lbl_ChietKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11)
                    .addComponent(lbl_ThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btn_Dong, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 724, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_DongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DongActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btn_DongActionPerformed

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
                HoaDonChiTietDialog dialog = new HoaDonChiTietDialog(new javax.swing.JFrame(), true);
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
    private view.until.button.Button btn_Dong;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_ChietKhau;
    private javax.swing.JLabel lbl_HoTenKhachHang;
    private javax.swing.JLabel lbl_MaHoaDon;
    private javax.swing.JPanel lbl_Menu;
    private javax.swing.JLabel lbl_NgayTaoHoaDon;
    private javax.swing.JLabel lbl_TenVoucher;
    private javax.swing.JLabel lbl_ThanhToan;
    private javax.swing.JLabel lbl_TongTien;
    private javax.swing.JLabel lbl_thongtin;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable tbl_HDCT;
    // End of variables declaration//GEN-END:variables
}
