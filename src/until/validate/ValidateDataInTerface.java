/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package until.validate;

import javax.swing.JTextField;

/**
 *
 * @author HUNGpYN
 */
public interface ValidateDataInTerface {

    //Sản Phẩm Validate
    public boolean checkSanPham(String text, String ten);

    public boolean checkNumber(String text, boolean mustBePositive);

    public boolean checkNguyenDuong(String text, boolean mustBePositive);

    public boolean checkIsEmpty(String text);

    public boolean checkPhone(String Text);

    public boolean checkMail(String email);

    public boolean checkContainPL(String id, String text);

    public boolean checkContainCL(String id, String text);

    public boolean checkContainSize(String id, String text);

    public boolean checkContainCungCap(String id, String text);

    public boolean checkContainMau(String id, String text);

    public boolean checkContainDaQuy(String id, String text);

    public boolean checkContainKiemDinh(String id, String text);

    //Nhân Viên Validate
    public boolean checkContainNhanVien(String id, String sdt, String taikhoan);

    //Khách Hàng
    public boolean checkContainKhachHang(String id, String Sdt);

    // so sánh ngày bắt đầu và kết thúc
    public boolean isEndDateValid(String startDateStr, String endDateStr);

    public boolean checkContaintVoucher(String ID, String ten);
    
     public boolean isDateValid(JTextField txt_Ngay);
}
