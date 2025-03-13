/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.giamgia;

import java.util.List;
import model.GiamGia;

/**
 *
 * @author WINDOWS10
 */
public interface InterfaceRepoGiamGia {
    public List<GiamGia> getAll();

    public int creat();

    public int update();

    public int delete();
}
