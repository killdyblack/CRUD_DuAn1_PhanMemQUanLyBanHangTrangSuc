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
import java.awt.Image;
import java.awt.Window;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import view.until.hopthoai.Notification;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import model.SanPham;
import repository.SanPham.repoChiTietSanPham;
import service.CapNhatSanPham.CapNhatSanPhamSv;
import service.taikhoan.ServiceTaiKhoan;
import until.validate.ValidateData;
import view.banhang.GiaoDienBanHang;
import view.main.Main;
import view.until.combobox.ComboBoxSuggestion;

/**
 *
 * @author HUNGpYN
 */
public class CapNhatSanPham extends javax.swing.JDialog {

    private List<SanPham> lstSp = new ArrayList<>();
    private CapNhatSanPhamSv capNhatSanPhamSv = new CapNhatSanPhamSv();
    private repoChiTietSanPham rpChiTietSanPham = new repoChiTietSanPham();
    private service.taikhoan.ServiceTaiKhoan stk;
    private Color color2 = Color.decode("#101820");
    private Color color1 = Color.decode("#FEE715");
    private String selectedID;
    private GiaoDienSanPham gdsp = new GiaoDienSanPham();
    private boolean check;
    private Main main;

    private until.validate.ValidateData vld = new ValidateData();

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public CapNhatSanPham(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setFont();
        updateComboBoxes();
    }

    public void setSelectedID(String id) {
        this.selectedID = id;
        fillToTable();
    }

    void fillToTable() {
        if (selectedID == null || selectedID.isEmpty()) {
            System.out.println("selectedID is not set.");
            return;
        }

        // Khởi tạo DecimalFormat để loại bỏ phần thập phân
        DecimalFormat decimalFormat = new DecimalFormat("0");

        // Khởi tạo SimpleDateFormat để định dạng ngày tháng năm
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        lstSp = rpChiTietSanPham.getByID(selectedID);
        for (SanPham sp : lstSp) {
            // Định dạng số tiền để loại bỏ phần thập phân
            String giaChiTiet = decimalFormat.format(sp.getGiaChiTiet());
            txt_GiaBan.setText(giaChiTiet);
            // Số lượng tồn kho
            txt_TonKho.setText(String.valueOf(sp.getSoLuongTonKho()));
            // Định dạng kích thước đá
            txt_KichThuocDa.setText(String.valueOf(sp.getKichThuocDa()));

            // Thông tin mã trang sức
            txt_MaTrangSuc.setText(sp.getIDSanPham());

            // Định dạng ngày kiểm định
            String ngayKiemDinh = dateFormat.format(sp.getIDKIemDinh().getNgayKiemDinh());
            txt_NgayKiemDinh.setText(ngayKiemDinh);

            // Số lượng đá quý và tên trang sức
            txt_SoLuongDa.setText(String.valueOf(sp.getSoLuongDaQuy()));
            txt_TenTrangSuc.setText(sp.getTenSanPham());

            // Tỷ lệ và trọng lượng
            txt_TyLe.setText(String.valueOf(sp.getSoLuongTonKho()));
            txt_TyLe.setText(String.valueOf(sp.getIDChatLieu().getTyLe()));
            txt_TrongLuong.setText(String.valueOf(sp.getTrongLuong()));

            // Cập nhật lựa chọn giới tính
            if (sp.isGioiTinh()) {
                rdoNam.setSelected(true);
            } else {
                rdoNu.setSelected(true);
            }

            // Cập nhật trạng thái
            if (sp.isTrangThai()) {
                rdoKinhDoanh.setSelected(true);
            } else {
                rdoNgungKinhDoanh.setSelected(true);
            }

            // Cập nhật combobox kích thước
            for (int i = 0; i < cbos_Size.getItemCount(); i++) {
                if (cbos_Size.getItemAt(i) != null && cbos_Size.getItemAt(i).toString().equals(String.valueOf(sp.getIDSize().getSoSize()))) {
                    cbos_Size.setSelectedIndex(i);
                }
            }

            // Cập nhật hình ảnh sản phẩm
            if (sp.getHinhAnhSanPham() != null) {
                ImageIcon imageIcon = new ImageIcon(sp.getHinhAnhSanPham());
                Image image = imageIcon.getImage(); // Chuyển đổi về đối tượng Image
                Image scaledImage = image.getScaledInstance(lbl_HinhAnh.getWidth() - 2, lbl_HinhAnh.getHeight() - 2, Image.SCALE_SMOOTH); // Thay đổi kích thước ảnh
                imageIcon = new ImageIcon(scaledImage);
                lbl_HinhAnh.setIcon(imageIcon);
                lbl_HinhAnh.putClientProperty("imagepath", sp.getHinhAnhSanPham());
            } else {
                lbl_HinhAnh.setIcon(null);
            }

            // Cập nhật các combobox khác
            updateComboBox(cbo_ChatLieu, sp.getIDChatLieu().getTenChatLieu());
            updateComboBox(cbo_KiemDinh, sp.getIDKIemDinh().getDonViKiemDinh());
            updateComboBox(cbo_LoaiDa1, sp.getIDDaQuy().getTenDaQuy());
            updateComboBox(cbo_MauSac, sp.getIDMauSac().getChiTietMauSac());
            updateComboBox(cbo_NhaCungCap, sp.getIDNhaCungCap().getTenNhaCungCap());
            updateComboBox(cbo_PhanLoai, sp.getIDPhanLoai().getTenPhanLoai());
        }
    }

    void UpdateData() {
        String link = (String) lbl_HinhAnh.getClientProperty("imagepath");
        System.out.println(link +" Đường Dẫ");
        // Gọi phương thức update với tất cả các tham số, bao gồm soLuongTonKho
        int check = rpChiTietSanPham.update(
                txt_MaTrangSuc.getText().trim(),
                capNhatSanPhamSv.checkCboPhanLoai(cbo_PhanLoai),
                capNhatSanPhamSv.checkCboKiemDinh(cbo_KiemDinh),
                capNhatSanPhamSv.checkCboMauSac(cbo_MauSac),
                capNhatSanPhamSv.checkCboSize(cbos_Size),
                txt_TenTrangSuc.getText().trim(),
                rdoNam.isSelected(),
                capNhatSanPhamSv.checkCboChatLieu(cbo_ChatLieu),
                Integer.parseInt(txt_TonKho.getText().trim()), // Sử dụng giá trị tồn kho đã lấy
                Float.parseFloat(txt_GiaBan.getText().trim()), // Có thể cần thay đổi tùy vào cách xử lý
                "", // idGiamGia nếu không có giá trị
                capNhatSanPhamSv.checkCboNhaCungCap(cbo_NhaCungCap),
                Integer.parseInt(txt_SoLuongDa.getText().trim()), // Có thể cần thay đổi tùy vào cách xử lý
                Float.parseFloat(txt_KichThuocDa.getText().trim()),
                Float.parseFloat(txt_TrongLuong.getText().trim()),
                link, // Hình ảnh nếu không có giá trị
                capNhatSanPhamSv.checkCboLoaiDa(cbo_LoaiDa1),
                rdoKinhDoanh.isSelected()
        );
    }

    public void updateComboBox(ComboBoxSuggestion cbos, String valueToSelect) {
        boolean isSelected = capNhatSanPhamSv.checkCbo(cbos, valueToSelect);
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

//Font Màu
    void setFont() {
        setLocationRelativeTo(null);
        lbl_CapNhat.setForeground(color1);
        pnl_CapNhat.setBackground(color2);
        btn_ThemAnh.setColor1(Color.LIGHT_GRAY);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        pnl_CapNhat = new javax.swing.JPanel();
        lbl_CapNhat = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txt_TenTrangSuc = new view.until.textfield.TextFieldSuggestion();
        jLabel2 = new javax.swing.JLabel();
        txt_GiaBan = new view.until.textfield.TextFieldSuggestion();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_TyLe = new view.until.textfield.TextFieldSuggestion();
        jLabel5 = new javax.swing.JLabel();
        txt_SoLuongDa = new view.until.textfield.TextFieldSuggestion();
        jLabel6 = new javax.swing.JLabel();
        txt_KichThuocDa = new view.until.textfield.TextFieldSuggestion();
        txt_TrongLuong = new view.until.textfield.TextFieldSuggestion();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbos_Size = new view.until.combobox.ComboBoxSuggestion();
        cbo_PhanLoai = new view.until.combobox.ComboBoxSuggestion();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbo_ChatLieu = new view.until.combobox.ComboBoxSuggestion();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cbo_LoaiDa1 = new view.until.combobox.ComboBoxSuggestion();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cbo_MauSac = new view.until.combobox.ComboBoxSuggestion();
        btn_ThemAnh = new view.until.button.Button();
        jLabel16 = new javax.swing.JLabel();
        cbo_NhaCungCap = new view.until.combobox.ComboBoxSuggestion();
        jLabel15 = new javax.swing.JLabel();
        btn_Huy = new view.until.button.Button();
        btn_CapNhat = new view.until.button.Button();
        cbo_KiemDinh = new view.until.combobox.ComboBoxSuggestion();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txt_NgayKiemDinh = new view.until.textfield.TextFieldSuggestion();
        lbl_HinhAnh = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        rdoKinhDoanh = new javax.swing.JRadioButton();
        rdoNgungKinhDoanh = new javax.swing.JRadioButton();
        txt_TonKho = new view.until.textfield.TextFieldSuggestion();
        txt_MaTrangSuc = new view.until.textfield.TextFieldSuggestion();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(780, 850));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_CapNhat.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        lbl_CapNhat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_CapNhat.setText("Cập Nhật Trang Sức");

        javax.swing.GroupLayout pnl_CapNhatLayout = new javax.swing.GroupLayout(pnl_CapNhat);
        pnl_CapNhat.setLayout(pnl_CapNhatLayout);
        pnl_CapNhatLayout.setHorizontalGroup(
            pnl_CapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_CapNhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnl_CapNhatLayout.setVerticalGroup(
            pnl_CapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_CapNhatLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbl_CapNhat)
                .addGap(10, 10, 10))
        );

        jPanel1.add(pnl_CapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 777, -1));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel1.setText("Mã Trang Sức");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(231, 57, -1, -1));

        txt_TenTrangSuc.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        jPanel1.add(txt_TenTrangSuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(499, 80, 242, 33));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel2.setText("Tên Trang Sức");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 57, -1, -1));

        txt_GiaBan.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        jPanel1.add(txt_GiaBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, 216, 33));

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel3.setText("Giá Bán");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(231, 125, -1, -1));

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel4.setText("Giới Tính");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 125, -1, -1));

        txt_TyLe.setEditable(false);
        txt_TyLe.setFocusable(false);
        txt_TyLe.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        jPanel1.add(txt_TyLe, new org.netbeans.lib.awtextra.AbsoluteConstraints(499, 438, 242, 33));

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel5.setText("Tồn Kho");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(231, 199, -1, -1));

        txt_SoLuongDa.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        jPanel1.add(txt_SoLuongDa, new org.netbeans.lib.awtextra.AbsoluteConstraints(499, 296, 242, 33));

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel6.setText("Số Lượng Đá Quý");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 273, -1, -1));

        txt_KichThuocDa.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        jPanel1.add(txt_KichThuocDa, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 296, 216, 33));

        txt_TrongLuong.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        jPanel1.add(txt_TrongLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(499, 222, 242, 33));

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel8.setText("Trọng Lượng");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 199, -1, -1));

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel7.setText("Kích Thước Đá Quý");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(231, 273, -1, -1));

        cbos_Size.setEditable(false);
        cbos_Size.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        jPanel1.add(cbos_Size, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 364, 216, -1));

        cbo_PhanLoai.setEditable(false);
        cbo_PhanLoai.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        jPanel1.add(cbo_PhanLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(499, 364, 242, -1));

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel10.setText("Phân Loại");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 341, -1, -1));

        jLabel9.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel9.setText("Size");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(231, 341, -1, -1));

        cbo_ChatLieu.setEditable(false);
        cbo_ChatLieu.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        cbo_ChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_ChatLieuActionPerformed(evt);
            }
        });
        jPanel1.add(cbo_ChatLieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 438, 216, -1));

        jLabel12.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel12.setText("Chất Liệu");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(231, 415, -1, -1));

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel11.setText("Tỉ Lệ chất Liệu");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 415, -1, -1));

        cbo_LoaiDa1.setEditable(false);
        cbo_LoaiDa1.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        jPanel1.add(cbo_LoaiDa1, new org.netbeans.lib.awtextra.AbsoluteConstraints(499, 513, 242, -1));

        jLabel13.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel13.setText("Loại Đá");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 490, -1, -1));

        jLabel14.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel14.setText("Màu Sắc");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(231, 490, -1, -1));

        cbo_MauSac.setEditable(false);
        cbo_MauSac.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        jPanel1.add(cbo_MauSac, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 513, 216, -1));

        btn_ThemAnh.setText("Chọn Ảnh");
        btn_ThemAnh.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btn_ThemAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemAnhActionPerformed(evt);
            }
        });
        jPanel1.add(btn_ThemAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 288, 107, 28));

        jLabel16.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel16.setText("Trạng Thái");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(231, 564, -1, -1));

        cbo_NhaCungCap.setEditable(false);
        jPanel1.add(cbo_NhaCungCap, new org.netbeans.lib.awtextra.AbsoluteConstraints(499, 587, 242, 33));

        jLabel15.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel15.setText("Nhà Cung Cấp");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 564, -1, -1));

        btn_Huy.setText("Đóng");
        btn_Huy.setColor1(new java.awt.Color(16, 24, 32));
        btn_Huy.setColor2(new java.awt.Color(254, 231, 21));
        btn_Huy.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btn_Huy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HuyActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Huy, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 706, 100, 31));

        btn_CapNhat.setText("Cập Nhật");
        btn_CapNhat.setColor1(new java.awt.Color(16, 24, 32));
        btn_CapNhat.setColor2(new java.awt.Color(254, 231, 21));
        btn_CapNhat.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btn_CapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CapNhatActionPerformed(evt);
            }
        });
        jPanel1.add(btn_CapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(446, 706, 100, 31));

        cbo_KiemDinh.setEditable(false);
        cbo_KiemDinh.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        cbo_KiemDinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_KiemDinhActionPerformed(evt);
            }
        });
        jPanel1.add(cbo_KiemDinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(499, 655, 242, -1));

        jLabel17.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel17.setText("Kiểm Định");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 632, -1, -1));

        jLabel18.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel18.setText("Ngày Kiểm Định");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(231, 632, -1, -1));

        txt_NgayKiemDinh.setEditable(false);
        txt_NgayKiemDinh.setFocusable(false);
        txt_NgayKiemDinh.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        txt_NgayKiemDinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_NgayKiemDinhActionPerformed(evt);
            }
        });
        jPanel1.add(txt_NgayKiemDinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 655, 216, 33));
        jPanel1.add(lbl_HinhAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 57, 174, 219));

        buttonGroup1.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");
        jPanel1.add(rdoNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(499, 155, -1, -1));

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");
        jPanel1.add(rdoNu, new org.netbeans.lib.awtextra.AbsoluteConstraints(612, 154, -1, -1));

        buttonGroup2.add(rdoKinhDoanh);
        rdoKinhDoanh.setText("Kinh Doanh");
        jPanel1.add(rdoKinhDoanh, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 587, -1, -1));

        buttonGroup2.add(rdoNgungKinhDoanh);
        rdoNgungKinhDoanh.setText("Ngừng Kinh Doanh");
        jPanel1.add(rdoNgungKinhDoanh, new org.netbeans.lib.awtextra.AbsoluteConstraints(328, 587, -1, -1));

        txt_TonKho.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        jPanel1.add(txt_TonKho, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 222, 216, 33));

        txt_MaTrangSuc.setEditable(false);
        txt_MaTrangSuc.setFocusable(false);
        txt_MaTrangSuc.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        jPanel1.add(txt_MaTrangSuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 80, 216, 33));

        jLabel19.setForeground(new java.awt.Color(204, 204, 204));
        jLabel19.setText("VNĐ");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 147, -1, 40));

        jLabel20.setForeground(new java.awt.Color(153, 153, 153));
        jLabel20.setText("mm");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(422, 297, 30, 30));

        jLabel21.setForeground(new java.awt.Color(153, 153, 153));
        jLabel21.setText("%");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 440, -1, 30));

        jLabel22.setForeground(new java.awt.Color(153, 153, 153));
        jLabel22.setText("Carat");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 220, -1, 40));

        jLabel23.setForeground(new java.awt.Color(153, 153, 153));
        jLabel23.setText("Viên");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 290, -1, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 777, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 764, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_HuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HuyActionPerformed
        Window window = SwingUtilities.getWindowAncestor(this);
        window.dispose();
    }//GEN-LAST:event_btn_HuyActionPerformed

    private void btn_CapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CapNhatActionPerformed
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
        if (!vld.checkNumber(txt_GiaBan.getText().trim(), true)) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Giá Bán Phải Là Số Và Lớn Hơn 0");
            panel.showNotification();
            return;
        }

        if (!vld.checkNumber(txt_KichThuocDa.getText().trim(), false)) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Kích Thước Đá Phải Là Số");
            panel.showNotification();
            return;

        }
        if (!vld.checkNguyenDuong(txt_SoLuongDa.getText().trim(), false)) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Số Lương Đá Phải Là Số Nguyên Dương");
            panel.showNotification();
            return;

        }
        if (!vld.checkNguyenDuong(txt_TonKho.getText().trim(), false)) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Tồn Kho Phải Là Số Nguyên Dương");
            panel.showNotification();
            return;

        }
        if (!vld.checkNumber(txt_TrongLuong.getText().trim(), true)) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Trọng Lượng Phải Là Số Và Lớn Hơn 0");
            panel.showNotification();
            return;

        }
        System.out.println(lbl_HinhAnh.getIcon());
        if (lbl_HinhAnh.getIcon() == null) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Chưa Chọn Ảnh");
            panel.showNotification();
            return;
        }
        if (vld.checkSanPham(txt_MaTrangSuc.getText().trim(), txt_TenTrangSuc.getText())) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "Tên Sản Phẩm Đã Tồn Tại");
            panel.showNotification();
            return;
        }
        UpdateData();
        Notification panel = new Notification(this, Notification.Type.SUCCESS, Notification.Location.CENTER, "Cập Nhật Trang Sức Thành Công");
        panel.showNotification();
        GiaoDienSanPham gdnv = GiaoDienSanPham.getInstance();
        if (gdnv != null) {
            gdnv.update();
        }
        GiaoDienBanHang gdbh = GiaoDienBanHang.getInstance();
        if (gdbh != null) {
            gdbh.update();
        }

    }//GEN-LAST:event_btn_CapNhatActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
//        Main.closeCurrentInstance();
//        Main newMain = new Main(1);
//        newMain.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void btn_ThemAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemAnhActionPerformed
        stk = new ServiceTaiKhoan();
        stk.chonAnh(lbl_HinhAnh);
    }//GEN-LAST:event_btn_ThemAnhActionPerformed

    private void cbo_ChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_ChatLieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_ChatLieuActionPerformed

    private void txt_NgayKiemDinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_NgayKiemDinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NgayKiemDinhActionPerformed

    private void cbo_KiemDinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_KiemDinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_KiemDinhActionPerformed

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
                CapNhatSanPham dialog = new CapNhatSanPham(new javax.swing.JFrame(), true);
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
    private view.until.button.Button btn_CapNhat;
    private view.until.button.Button btn_Huy;
    private view.until.button.Button btn_ThemAnh;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private view.until.combobox.ComboBoxSuggestion cbo_ChatLieu;
    private view.until.combobox.ComboBoxSuggestion cbo_KiemDinh;
    private view.until.combobox.ComboBoxSuggestion cbo_LoaiDa1;
    private view.until.combobox.ComboBoxSuggestion cbo_MauSac;
    private view.until.combobox.ComboBoxSuggestion cbo_NhaCungCap;
    private view.until.combobox.ComboBoxSuggestion cbo_PhanLoai;
    private view.until.combobox.ComboBoxSuggestion cbos_Size;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_CapNhat;
    private javax.swing.JLabel lbl_HinhAnh;
    private javax.swing.JPanel pnl_CapNhat;
    private javax.swing.JRadioButton rdoKinhDoanh;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNgungKinhDoanh;
    private javax.swing.JRadioButton rdoNu;
    private view.until.textfield.TextFieldSuggestion txt_GiaBan;
    private view.until.textfield.TextFieldSuggestion txt_KichThuocDa;
    private view.until.textfield.TextFieldSuggestion txt_MaTrangSuc;
    private view.until.textfield.TextFieldSuggestion txt_NgayKiemDinh;
    private view.until.textfield.TextFieldSuggestion txt_SoLuongDa;
    private view.until.textfield.TextFieldSuggestion txt_TenTrangSuc;
    private view.until.textfield.TextFieldSuggestion txt_TonKho;
    private view.until.textfield.TextFieldSuggestion txt_TrongLuong;
    private view.until.textfield.TextFieldSuggestion txt_TyLe;
    // End of variables declaration//GEN-END:variables
}
