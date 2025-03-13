/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package until.validate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextField;
import model.ChatLieu;
import model.DaQuy;
import model.GiamGia;
import model.KhachHang;
import model.KiemDinh;
import model.MauSac;
import model.NhaCungCap;
import model.PhanLoai;
import model.SanPham;
import model.Size;
import model.TaiKhoan;
import model.Voucher;
import repository.DaQuy.DaQuyRepository;
import repository.KhachHang.repoKhachHang;
import repository.NhaCungCap.NhaCungCapRepository;
import repository.PhanLoai.PhanLoaiRepo;
import repository.SanPham.repoChiTietSanPham;
import repository.Size.SizeRepository;
import repository.chatlieu.ChatLieuRepository;
import repository.giamgia.RepoGiamGia;
import repository.kiemdinh.KiemDinhRepository;
import repository.mausac.MauSacRepository;
import repository.taikhoan.RepositoryTaiKhoan;

/**
 *
 * @author HUNGpYN
 */
public class ValidateData implements ValidateDataInTerface {

    private repository.SanPham.repoChiTietSanPham rpct = new repoChiTietSanPham();

    @Override
    public boolean checkSanPham(String text, String ten) {

        for (SanPham sp : rpct.getAll()) {
            if (sp.getIDSanPham().equalsIgnoreCase(text)) {
                continue;
            }
            if (sp.getTenSanPham().equalsIgnoreCase(ten.trim())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkNumber(String text, boolean mustBePositive) {
        try {
            double num = Double.parseDouble(text);
            return mustBePositive ? num > 0 : num >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public boolean checkIsEmpty(String text) {
        return text.trim().isEmpty();
    }

    @Override
    public boolean checkPhone(String text) {
        String regex = "^0\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();

    }

    @Override
    public boolean checkMail(String email) {
        // Phương thức kiểm tra tính hợp lệ của địa chỉ email
        // Biểu thức chính quy cho địa chỉ email
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        // Tạo đối tượng Pattern từ regex
        Pattern pattern = Pattern.compile(regex);

        // Tạo đối tượng Matcher từ chuỗi cần kiểm tra
        Matcher matcher = pattern.matcher(email);

        // Trả về true nếu chuỗi khớp với regex, false nếu không khớp
        return matcher.matches();
    }

    @Override
    public boolean checkContainPL(String id, String text) {
        PhanLoaiRepo plr = new PhanLoaiRepo();
        for (PhanLoai pl : plr.getAll()) {
            if (pl.getIDPhanLoai().equalsIgnoreCase(id)) {
                continue;
            }
            if (pl.getTenPhanLoai().equalsIgnoreCase(text)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkContainCL(String id, String text) {
        ChatLieuRepository clr = new ChatLieuRepository();
        for (ChatLieu cl : clr.getAll()) {
            if (cl.getIDChatLieu().equalsIgnoreCase(id)) {
                continue;
            }
            if (cl.getTenChatLieu().equalsIgnoreCase(text)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkContainSize(String id, String text) {
        SizeRepository srp = new SizeRepository();
        for (Size s : srp.getAll()) {
            if (s.getIDSize().equalsIgnoreCase(id)) {
                continue;
            }
            if (s.getSoSize() == Integer.parseInt(text)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkNguyenDuong(String text, boolean mustBePositive) {
        try {
            int num = Integer.parseInt(text);
            return mustBePositive ? num > 0 : num >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public boolean checkContainCungCap(String id, String text) {
        NhaCungCapRepository ncr = new NhaCungCapRepository();
        for (NhaCungCap nc : ncr.getAll()) {
            if (nc.getIDNhaCungCap().equalsIgnoreCase(id)) {
                continue;
            }
            if (nc.getTenNhaCungCap().equalsIgnoreCase(text)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkContainMau(String id, String text) {
        MauSacRepository msr = new MauSacRepository();
        for (MauSac mauSac : msr.getAll()) {
            if (mauSac.getIDMauSac().equalsIgnoreCase(id)) {
                continue;
            }
            if (mauSac.getChiTietMauSac().equalsIgnoreCase(text)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkContainDaQuy(String id, String text) {
        DaQuyRepository dqr = new DaQuyRepository();
        for (DaQuy dq : dqr.getAll()) {
            if (dq.getIDDaQuy().equalsIgnoreCase(id)) {
                continue;
            }
            if (dq.getTenDaQuy().equalsIgnoreCase(text)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkContainKiemDinh(String id, String text) {
        KiemDinhRepository kdr = new KiemDinhRepository();
        for (KiemDinh kiemDinh : kdr.getAll()) {
            if (kiemDinh.getIDKiemDinh().equalsIgnoreCase(id)) {
                continue;
            }
            if (kiemDinh.getDonViKiemDinh().equalsIgnoreCase(text)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkContainNhanVien(String id, String sdt, String taikhoan) {
        RepositoryTaiKhoan tk = new RepositoryTaiKhoan();
        for (TaiKhoan nv : tk.getAll()) {
            if (nv.getIDTaiKhoan().equalsIgnoreCase(id)) {
                continue;
            }
            if (nv.getTaiKhoan().equalsIgnoreCase(taikhoan) || nv.getSoDienThoai().equalsIgnoreCase(sdt)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkContainKhachHang(String id, String Sdt) {
        repoKhachHang rpk = new repoKhachHang();
        for (KhachHang khachHang : rpk.getAll()) {
            for (KhachHang kh : rpk.getAll()) {
                if (kh.getIDKhachHang().equalsIgnoreCase(id)) {
                    continue;
                }
                if (kh.getSoDienThoai().equalsIgnoreCase(Sdt)) {
                    return true;
                }
            }
        }
        return false;
    }

    // So Sánh ngày Bắt Đầu Và Kết Thúc
    @Override
    public boolean isEndDateValid(String startDateStr, String endDateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            LocalDate startDate = LocalDate.parse(startDateStr, formatter);
            LocalDate endDate = LocalDate.parse(endDateStr, formatter);

            return !endDate.isBefore(startDate);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    //check tồn tài của voucher
    @Override
    public boolean checkContaintVoucher(String ID, String ten) {
        repository.KhuyenMai.KhuyenMaiRepository kmr = new repository.KhuyenMai.KhuyenMaiRepository();
        for (Voucher vc : kmr.getAll()) {
            if (vc.getIDVoucher().equalsIgnoreCase(ID)) {
                continue;
            }
            if (vc.getTenVoucher().equalsIgnoreCase(ten)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkContaintGiamGia(String ID, String ten) {
        RepoGiamGia rgg = new RepoGiamGia();
        for (GiamGia gg : rgg.getAll()) {
            if (gg.getIDGIamGia().equalsIgnoreCase(ID)) {
                continue;
            }
            if (gg.getTenMaGiamGia().equalsIgnoreCase(ten)) {
                return true;
            }
        }
        return false;
    }
@Override
    public boolean isDateValid(JTextField txt_Ngay) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date selectedDate = sdf.parse(txt_Ngay.getText().trim());
            Date currentDate = new Date();

            // So sánh ngày chọn với ngày hiện tại
            if (selectedDate.before(sdf.parse(sdf.format(currentDate)))) {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
