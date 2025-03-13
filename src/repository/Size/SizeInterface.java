/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.Size;

import java.util.List;
import model.Size;

/**
 *
 * @author HUNGpYN
 */
public interface SizeInterface {
    public List<Size> getAll();
    public int creat(Size s);
    public int update(Size s);
    public int delete(Size s);
}
