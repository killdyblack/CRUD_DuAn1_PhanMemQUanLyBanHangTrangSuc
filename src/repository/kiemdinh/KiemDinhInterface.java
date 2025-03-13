/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.kiemdinh;

import java.util.List;
import model.KiemDinh;

/**
 *
 * @author HUNGpYN
 */
public interface KiemDinhInterface {

    public List<KiemDinh> getAll();

    public int creat();

    public int updateKiemDinh(KiemDinh kd);

    public int delete();
    public boolean addKiemDinh(KiemDinh kiemDinh);
}
