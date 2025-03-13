/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.NhaCungCap;

import java.util.List;
import model.NhaCungCap;

/**
 *
 * @author HUNGpYN
 */
public interface NhaCungCapInterface {
        public List<NhaCungCap> getAll();

    public int creat();

    public int updateNhaCungCap(NhaCungCap ncc);

    public int delete();
    
    public boolean addNhaCungCap(NhaCungCap nhaCungCap);
}
