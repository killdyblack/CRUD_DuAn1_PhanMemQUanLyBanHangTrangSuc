/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.taikhoan;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import model.GiaoDien.GiaoDienNhanVienModel;
import model.TaiKhoan;
import repository.taikhoan.RepositoryTaiKhoan;
import view.main.Main;
import view.nhanvien.CapNhatNhanVien;

public class ServiceTaiKhoan implements ServiceTaiKhoanInterface {

    private RepositoryTaiKhoan rptk = new RepositoryTaiKhoan();
    private Main main;

    @Override
    public void fillToTable(JTable tbl) {
        DefaultTableModel tableModel = (DefaultTableModel) tbl.getModel();
        tableModel.setRowCount(0);
        int STT = 1;
        for (TaiKhoan tk : rptk.getAll()) {
            tableModel.addRow(new Object[]{STT++, tk.getIDTaiKhoan(), tk.getHoTen(), tk.getTaiKhoan(), tk.getMatKhau(), tk.isGioiTinh() ? "Nam" : "Nữ",
                tk.getSoDienThoai(), tk.isChucVu() ? "Quản Lí" : "Nhân Viên", tk.isTrangThai() ? "Làm Việc" : "Nghỉ Việc"});
        }
    }

    public void fillToTableCheck(JTable tbl, GiaoDienNhanVienModel gdnvm) {
        DefaultTableModel tableModel = (DefaultTableModel) tbl.getModel();
        tableModel.setRowCount(0);
        int STT = 1;
        for (TaiKhoan tk : rptk.fillToCheck(tbl, gdnvm)) {
            tableModel.addRow(new Object[]{STT++,tk.getIDTaiKhoan(), tk.getHoTen(), tk.getTaiKhoan(), tk.getMatKhau(), tk.isGioiTinh() ? "Nam" : "Nữ",
                tk.getSoDienThoai(), tk.isChucVu() ? "Quản Lí" : "Nhân Viên", tk.isTrangThai() ? "Làm Việc" : "Nghỉ Việc"});
        }
    }

    @Override
    public void doubleCicled(JTable tbl) {
        tbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = tbl.getSelectedRow();
                    if (row >= 0) {
                        for (TaiKhoan tk : rptk.getAll()) {
                            if (tk.getIDTaiKhoan().equalsIgnoreCase(tbl.getValueAt(row, 1).toString())) {
                                CapNhatNhanVien cnnv = new CapNhatNhanVien(main, true);
                                cnnv.setData(tk);
                                cnnv.setVisible(true);
                                break;
                            }

                        }

                    }
                }
            }

        });
    }

    @Override
    public void chonAnh(JLabel label) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setDialogTitle("Chọn Ảnh");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String fileName = selectedFile.getName();
            Path projectDir = Paths.get("src", "images");
            Path targetPath = projectDir.resolve(fileName);

            try {
                // Tạo thư mục nếu nó không tồn tại
                Files.createDirectories(projectDir);
                // Sao chép tệp đến thư mục của dự án
                Files.copy(selectedFile.toPath(), targetPath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);

                // Đọc ảnh từ tệp
                BufferedImage img = ImageIO.read(selectedFile);

                if (img != null) {
                    Image scaledImage = img.getScaledInstance(label.getWidth() - 2, label.getHeight() - 2, Image.SCALE_SMOOTH);
                    label.setIcon(new ImageIcon(scaledImage));
                    label.setHorizontalAlignment(JLabel.CENTER);

                    // Cập nhật đường dẫn ảnh đã lưu trong dự án
                    String link = targetPath.toString();
                    label.putClientProperty("imagepath", link);
                } else {
                    System.err.println("File đã chọn không phải là ảnh hợp lệ.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
