package view.form;

import com.raven.chart.ModelChart;
import java.awt.Color;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.GiaoDien.DoanhThuModel;
import model.HoaDon;
import repository.hoadon.RepositoryHoaDon;
import service.DoanhThu.DoanhThusv;
import view.form.JTableHeader;
import view.khuyenmai.TableKhuyenMai;

public class Form_1 extends javax.swing.JPanel {

    private RepositoryHoaDon rphd = new RepositoryHoaDon();
    private DoanhThusv svdt = new DoanhThusv();

    public Form_1() {
        initComponents();
        setOpaque(false);
        lineChart.addLegend("Tổng Doanh Thu", new Color(255, 0, 0), new Color(255, 102, 102)); // Đỏ
        lineChart.addLegend("Doanh Thu Thực", new Color(0, 0, 255), new Color(102, 153, 255)); // Xanh dương
        lineChart.addLegend("Giảm Giá", new Color(255, 255, 0), new Color(255, 255, 153)); // Vàng

        init();
        getAll();
        // set font cho bảng
        TableKhuyenMai tkm = new TableKhuyenMai();
        tkm.init(tbl_ThongKe, scrollThongKe);
        tbl_ThongKe.getTableHeader().setDefaultRenderer(new JTableHeader(tbl_ThongKe));
    }

    public static String formatCurrency(double amount) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return currencyFormat.format(amount);
    }

    public void getAll() {
        // Kiểm tra tbl_ThongKe và cbo_NhanVien không phải là null
        if (tbl_ThongKe == null || cbo_NhanVien == null) {
            JOptionPane.showMessageDialog(null, "Bảng hoặc ComboBox không được khởi tạo.");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) tbl_ThongKe.getModel();
        model.setRowCount(0);

        // Lấy danh sách hóa đơn
        List<HoaDon> hoaDonList = rphd.getAllDT();
        if (hoaDonList == null) {
            JOptionPane.showMessageDialog(null, "Không thể lấy danh sách hóa đơn.");
            return;
        }

        cbo_NhanVien.addItem("Tất Cả");
        int i = 0;
        for (HoaDon hd : hoaDonList) {
            if (hd.getIdTaiKhoan() != null) {
                cbo_NhanVien.addItem(hd.getIdTaiKhoan().getHoTen());

                model.addRow(new Object[]{i++,
                    hd.getIdTaiKhoan().getIDTaiKhoan(),
                    hd.getIdTaiKhoan().getHoTen(),
                    hd.getSoLuongDon(),
                    formatCurrency(hd.getGiamGiaSanPham()),
                    formatCurrency(hd.getTongTienSau()),
                    formatCurrency(hd.getTongTienTRuoc())
                });
            }
        }
    }

    private void init() {
        // Thêm các chú thích vào biểu đồ
        lineChart.clear();

        // Tạo danh sách tháng
        String[] months = {
            "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6",
            "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"
        };

        // Khởi tạo dữ liệu cho tất cả các tháng
        double[][] data = new double[months.length][3];
        for (int i = 0; i < months.length; i++) {
            data[i] = new double[]{0, 0, 0}; // Mặc định là [0,0,0]
        }

        // Giả sử danh sách dữ liệu được truyền vào từ phương thức `findByDate`
        List<DoanhThuModel> doanhThuList = rphd.getDoanhThuByMonthAndYear();

        // Cập nhật dữ liệu từ danh sách vào mảng
        for (DoanhThuModel dt : doanhThuList) {
            int monthIndex = dt.getThangEnd() - 1; // Chuyển đổi từ tháng (1-12) thành chỉ số mảng (0-11)
            if (monthIndex >= 0 && monthIndex < data.length) {
                data[monthIndex] = new double[]{
                    dt.getTongTienTruoc(),
                    dt.getTongTienSau(),
                    dt.getTongGiaGia()
                };
            }
            lbl_DonVi.setText("Năm " + dt.getNamEnd());
        }

        // Thêm dữ liệu vào biểu đồ
        lineChart.addData(new ModelChart("", new double[]{0, 0, 0}));
        for (int i = 0; i < months.length; i++) {
            lineChart.addData(new ModelChart(months[i], data[i]));
        }

        // Bắt đầu hiển thị biểu đồ
        lineChart.start();
    }

    private void fillTheoThang(DoanhThuModel dtmd) {
        if (!rphd.findByDate(dtmd).isEmpty()) {
            lineChart.clear();
            int month = dtmd.getThangEnd();
            int year = dtmd.getNamEnd();
            lbl_DonVi.setText(dtmd.getHoTen() + " Tháng " + month);
            YearMonth yearMonth = YearMonth.of(year, month);
            int daysInMonth = yearMonth.lengthOfMonth();
            // Tạo mảng chứa dữ liệu cho biểu đồ
            double[][] data = new double[daysInMonth][3]; // Số ngày trong tháng, mỗi ngày có 3 giá trị
            // Khởi tạo dữ liệu cho tất cả các ngày
            for (int i = 0; i < daysInMonth; i++) {
                data[i] = new double[]{0, 0, 0}; // Mặc định là [0,0,0]
            }
            // Cập nhật dữ liệu từ danh sách vào mảng
            for (DoanhThuModel dt : rphd.findByDate(dtmd)) {
                int dayIndex = dt.getNgayEnd() - 1; // Chuyển đổi từ ngày (1-30/31) thành chỉ số mảng (0-29/30)
                if (dayIndex >= 0 && dayIndex < data.length) {
                    data[dayIndex] = new double[]{
                        dt.getTongTienTruoc(),
                        dt.getTongTienSau(),
                        dt.getTongGiaGia()
                    };
                }
            }
            String[] days = new String[daysInMonth];
            for (int i = 0; i < daysInMonth; i++) {
                days[i] = "" + (i + 1);
            }
            lineChart.addData(new ModelChart("", new double[]{0, 0, 0}));
            for (int i = 0; i < daysInMonth; i++) {
                lineChart.addData(new ModelChart(days[i], data[i]));
            }
            lineChart.start();
        } else {
            init();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new com.raven.datechooser.DateChooser();
        dateChooser2 = new com.raven.datechooser.DateChooser();
        roundPanel2 = new com.raven.swing.RoundPanel();
        scrollThongKe = new javax.swing.JScrollPane();
        tbl_ThongKe = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        cbo_NhanVien = new view.until.combobox.ComboBoxSuggestion();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_TuNgay = new view.until.textfield.TextFieldSuggestion();
        txt_DenNgay = new view.until.textfield.TextFieldSuggestion();
        jLabel8 = new javax.swing.JLabel();
        button1 = new view.until.button.Button();
        button2 = new view.until.button.Button();
        roundPanel3 = new com.raven.swing.RoundPanel();
        lineChart = new com.raven.chart.LineChart();
        lbl_DonVi = new javax.swing.JLabel();

        dateChooser1.setTextRefernce(txt_TuNgay);

        dateChooser2.setTextRefernce(txt_DenNgay);

        roundPanel2.setBackground(new java.awt.Color(255, 255, 255));

        scrollThongKe.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        tbl_ThongKe.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tbl_ThongKe.setModel(new javax.swing.table.DefaultTableModel(
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
                "STT", "Mã Nhân Viên", "Tên Nhân Viên", "Số Lượng Đơn", "Giảm Giá", "Doanh Thu Thực", "Tổng Doanh Thu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollThongKe.setViewportView(tbl_ThongKe);
        if (tbl_ThongKe.getColumnModel().getColumnCount() > 0) {
            tbl_ThongKe.getColumnModel().getColumn(5).setResizable(false);
        }

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel5.setText("Bảng Thống Kê");

        jLabel6.setText("Nhân Viên");

        jLabel7.setText("Từ Ngày");

        jLabel8.setText("Đến Ngày");

        button1.setText("Tìm Kiếm");
        button1.setColor1(new java.awt.Color(16, 24, 32));
        button1.setColor2(new java.awt.Color(254, 231, 21));
        button1.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        button2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/excel.png"))); // NOI18N
        button2.setColor1(new java.awt.Color(16, 24, 32));

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5))
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(roundPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(cbo_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(46, 46, 46)
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_TuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(41, 41, 41)
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel2Layout.createSequentialGroup()
                                .addComponent(txt_DenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8))))
                .addContainerGap(243, Short.MAX_VALUE))
            .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(roundPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, 1089, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbo_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_TuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_DenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addContainerGap(399, Short.MAX_VALUE))
            .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                    .addGap(106, 106, 106)
                    .addComponent(scrollThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        roundPanel3.setBackground(new java.awt.Color(51, 51, 51));

        lbl_DonVi.setBackground(new java.awt.Color(255, 255, 255));
        lbl_DonVi.setForeground(new java.awt.Color(255, 255, 255));
        lbl_DonVi.setText("           ");

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel3Layout.createSequentialGroup()
                        .addComponent(lbl_DonVi)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lineChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_DonVi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lineChart, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(roundPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        DoanhThuModel dtmd = new DoanhThuModel();
        fillTheoThang(svdt.TimKiemTheoNgay(txt_TuNgay, txt_DenNgay, cbo_NhanVien));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            LocalDate dateStart = LocalDate.parse(txt_TuNgay.getText(), formatter);
            LocalDate dateEnd = LocalDate.parse(txt_DenNgay.getText(), formatter);
            if (dateEnd.isBefore(dateStart)) {
                JOptionPane.showMessageDialog(null, "Ngày kết thúc phải lớn hơn hoặc bằng ngày bắt đầu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            YearMonth yearMonth = YearMonth.of(dateStart.getYear(), dateStart.getMonthValue());
            int daysInMonth = yearMonth.lengthOfMonth();
            dtmd.setNgayStart(dateStart.getDayOfMonth());
            dtmd.setNgayEnd(daysInMonth);
            dtmd.setThangStart(dateStart.getMonthValue());
            dtmd.setThangEnd(dateStart.getMonthValue());
            dtmd.setNamStart(dateStart.getYear());
            dtmd.setNamEnd(dateStart.getYear());
            dtmd.setHoTen(cbo_NhanVien.getSelectedItem().toString());
            svdt.fillByCondition(dtmd, tbl_ThongKe);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Ngày không hợp lệ. Vui lòng kiểm tra định dạng ngày.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }


    }//GEN-LAST:event_button1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.until.button.Button button1;
    private view.until.button.Button button2;
    private view.until.combobox.ComboBoxSuggestion cbo_NhanVien;
    private com.raven.datechooser.DateChooser dateChooser1;
    private com.raven.datechooser.DateChooser dateChooser2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel lbl_DonVi;
    private com.raven.chart.LineChart lineChart;
    private com.raven.swing.RoundPanel roundPanel2;
    private com.raven.swing.RoundPanel roundPanel3;
    private javax.swing.JScrollPane scrollThongKe;
    private javax.swing.JTable tbl_ThongKe;
    private view.until.textfield.TextFieldSuggestion txt_DenNgay;
    private view.until.textfield.TextFieldSuggestion txt_TuNgay;
    // End of variables declaration//GEN-END:variables
}
