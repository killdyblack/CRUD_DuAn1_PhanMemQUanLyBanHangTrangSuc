/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.KhuyenMai;

import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import model.GiamGia;
import model.GiaoDien.GiaoDienKhuyenMaiModel;
import model.GiaoDien.ThemGGModel;
import model.SanPham;
import model.Voucher;

/**
 *
 * @author nguyentrikhoi
 */
public interface RepoKhuyenMaiInterface {

    public List<Voucher> getAll();

    public int creat(Voucher vc);

    public int update(Voucher vc);

    public List<Voucher> getAllByID(String str);

    public List<GiamGia> getAllGiamGia();

    public List<GiamGia> getAllByIDGiamGia(String str);

    public List<GiamGia> SearchGiamGiaGG(GiaoDienKhuyenMaiModel gdkmm);

    public List<SanPham> SearchGiamGiaSP(GiaoDienKhuyenMaiModel gdkmm);

    public List<SanPham> fillTableGiamGiaSP();

    public boolean addGiamGia(GiamGia gg);

    public boolean updateGiamGia(GiamGia gg, String ID);

    public List<SanPham> SearchTSP(ThemGGModel tggm);

    public boolean updateIDGiamGiaForMultipleProducts(List<String> idSanPhams, String idGiamGia);

    public boolean updateNullIDGiamGiaForMultipleProducts(List<String> idSanPhams);
}
