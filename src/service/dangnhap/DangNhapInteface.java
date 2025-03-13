/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.dangnhap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import model.TaiKhoan;

/**
 *
 * @author HUNGpYN
 */
public interface DangNhapInteface {
    public void showPassword(JPasswordField show, JLabel disable, JLabel enabled);
    public void hidePassword(JPasswordField show, JLabel disable, JLabel enabled);
    public TaiKhoan CheckPassword(JTextField Password, JTextField Account);
    public TaiKhoan ForgortPassword(JTextField Account, JTextField SDT, JTextField Email);
}
