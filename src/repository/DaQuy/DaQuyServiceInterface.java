/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.DaQuy;

import java.util.List;
import model.DaQuy;

/**
 *
 * @author HUNGpYN
 */
public interface DaQuyServiceInterface {

    public List<DaQuy> getAll();

    public int creat(DaQuy dq);

    public int update(DaQuy dq);

    public int delete(DaQuy dq);
}
