/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.DaQuy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.DaQuy;
import until.jdbc;

/**
 *
 * @author HUNGpYN
 */
public class DaQuyRepository implements DaQuyServiceInterface {

    private Connection con = null;
    private PreparedStatement pre = null;
    private ResultSet res = null;
    private String sql = null;

    @Override
    public List<DaQuy> getAll() {
        List<DaQuy> list = new ArrayList<>();
        sql = "Select * from DaQuy";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            res = pre.executeQuery();
            while (res.next()) {
                DaQuy dq = new DaQuy();
                dq.setIDDaQuy(res.getString("IDDaQuy"));
                dq.setTenDaQuy(res.getString("TenDaQuy"));
                dq.setTrangThai(res.getBoolean("TrangThai"));
                list.add(dq);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int creat(DaQuy dq) {
        sql = "INSERT INTO DaQuy(TenDaQuy, TrangThai) VALUES(?,?)";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            pre.setString(1, dq.getTenDaQuy());
            pre.setBoolean(2, dq.isTrangThai());
            return pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
   public int update(DaQuy dq) {
    sql = "UPDATE DaQuy SET TenDaQuy = ?, TrangThai = ? WHERE IDDaQuy = ?";
    try {
        con = jdbc.getConnection();
        pre = con.prepareStatement(sql);
        pre.setString(1, dq.getTenDaQuy());
        pre.setBoolean(2, dq.isTrangThai());
        pre.setString(3, dq.getIDDaQuy());
        return pre.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
        return 0;
    }
}

    @Override
    public int delete(DaQuy dq) {
        sql = "EXEC UpdateDaQuy ?, ?, ?, ?";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            pre.setString(1, dq.getIDDaQuy());
            pre.setString(2, dq.getTenDaQuy());
            pre.setInt(4, 0);
            return pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
