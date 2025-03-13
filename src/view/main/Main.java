package view.main;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import view.event.EventMenu;
import view.form.Form;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.UIManager;
import raven.modal.Toast;
import service.observer.Subject;
import view.banhang.GiaoDienBanHang;
import view.baohanh.GiaoDienBaoHanh;
import view.dangnhap.DangNhapView;
import view.form.Form_1;
import view.khachhang.GiaoDienKhachHang;
import view.khuyenmai.GiaoDienKhuyenMai;
import view.nhanvien.GiaoDienNhanVien;
import view.sanpham.GiaoDienSanPham;

public class Main extends javax.swing.JFrame {

    private int cuaSo;
    private static Main currentInstance;
    private Subject subject;
    private GiaoDienNhanVien gdnv;
    private GiaoDienKhachHang gdkh;
    private GiaoDienSanPham gpsp;
    private GiaoDienKhuyenMai gdkm;
    public Main(int cuaSo) {
        this.cuaSo = cuaSo;
        subject = new Subject();
        gdkm = GiaoDienKhuyenMai.getInstance();
        gdnv = GiaoDienNhanVien.getInstance();
        gdkh = GiaoDienKhachHang.getInstance();
        subject.addObserver(gdnv);
        subject.addObserver(gdkm);
        subject.addObserver(gdkh);
        gpsp = GiaoDienSanPham.getInstance();
        subject.addObserver(gpsp);
        initComponents();
        simpleTitleBar1.init(this);
        getContentPane().setBackground(new Color(25, 25, 25));
        setBackground(new Color(0, 0, 0, 0));
        EventMenu event = new EventMenu() {
            @Override
           public void selected(int index) {
                if (index == 0) {
                    showForm(new Form_1());
                } else if (index == 1 && DangNhapView.roleDN) {
                    showForm(new GiaoDienSanPham().getInstance());
                } else if (index == 2) {
                    showForm(new GiaoDienBanHang());
                } else if (index == 3 && DangNhapView.roleDN) {
                    showForm(GiaoDienNhanVien.getInstance());
                } else if (index == 4) {
                    showForm(new GiaoDienKhachHang().getInstance());
                } else if (index == 5) {
                    showForm(new GiaoDienKhuyenMai().getInstance());
                }else if (index == 6) {
                    showForm(new GiaoDienBaoHanh());
                }else if (index == 8) {
                    System.out.println("Logout");
                } else {
                    showForm(new Form(index));
                    
                }
            }

        };
        menu1.initMenu(event);
        showForm(new Form_1());
        currentInstance = this;
    }

    public void showw() {
        showForm(new GiaoDienSanPham());
    }

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        if (aFlag && cuaSo == 1) {
            showForm(new GiaoDienSanPham());
        } else if (aFlag && cuaSo == 4) {
            showForm(new GiaoDienKhachHang());
        }else if (aFlag && cuaSo == 0) {
            showForm(new Form_1());
        }
    }

    private void showForm(Component com) {
        body.removeAll();
        body.add(com);
        body.revalidate();
        body.repaint();
    }

    public static void closeCurrentInstance() {
        if (currentInstance != null) {
            currentInstance.dispose();
            currentInstance = null;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new com.raven.swing.RoundPanel();
        simpleTitleBar1 = new com.raven.swing.javaswingdev.SimpleTitleBar();
        body = new javax.swing.JPanel();
        menu1 = new view.component.Menu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        roundPanel1.setBackground(new java.awt.Color(25, 25, 25));

        body.setOpaque(false);
        body.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, 1357, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(simpleTitleBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 1652, Short.MAX_VALUE)
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addComponent(simpleTitleBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(menu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main(0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private view.component.Menu menu1;
    private com.raven.swing.RoundPanel roundPanel1;
    private com.raven.swing.javaswingdev.SimpleTitleBar simpleTitleBar1;
    // End of variables declaration//GEN-END:variables
}
