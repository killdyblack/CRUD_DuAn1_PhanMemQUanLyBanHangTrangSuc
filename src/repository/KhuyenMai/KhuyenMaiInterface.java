/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.KhuyenMai;

import java.util.List;
import model.Voucher;

/**
 *
 * @author nguyentrikhoi
 */
public interface KhuyenMaiInterface {
    public List<Voucher> getAll();

    public int creat(Voucher vc);

    public int update(Voucher vc);
}
