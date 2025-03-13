/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.dangnhap;


import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import model.TaiKhoan;
import repository.taikhoan.RepositoryTaiKhoan;

/**
 *
 * @author HUNGpYN
 */
public class DangNhap implements DangNhapInteface {

    private RepositoryTaiKhoan rptk = new RepositoryTaiKhoan();

    @Override
    public void showPassword(JPasswordField show, JLabel disable, JLabel enabled) {
        show.setEchoChar((char) 0);
        disable.setVisible(false);
        disable.setEnabled(false);
        enabled.setVisible(true);
        enabled.setEnabled(true);
    }

    @Override
    public void hidePassword(JPasswordField show, JLabel disable, JLabel enabled) {
        show.setEchoChar((char) 8226);
        disable.setVisible(true);
        disable.setEnabled(true);
        enabled.setVisible(false);
        enabled.setEnabled(false);
    }

    @Override
    public TaiKhoan CheckPassword(JTextField Password, JTextField Account) {
        String inputPassword = Password.getText().trim();
        String inputAccount = Account.getText().trim();

        for (TaiKhoan tk : rptk.getAll()) {
            if (inputPassword.equals(tk.getMatKhau()) && inputAccount.equals(tk.getTaiKhoan())) {
                return tk;
            }
        }
        return null;
    }

    @Override
    public TaiKhoan ForgortPassword(JTextField Account, JTextField SDT, JTextField Email) {
        String inputAcount = Account.getText().trim();
        String inputEmail = Email.getText().trim();
        String inputSDT = SDT.getText().trim();
        for (TaiKhoan tk : rptk.getAll()) {
            if (inputAcount.equals(tk.getTaiKhoan()) && inputEmail.equals(tk.getEmail()) && inputSDT.equals(tk.getSoDienThoai())) {
                return tk;
            }
        }
        return null;
    }
}
