package service.CapNhatSanPham;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import repository.DaQuy.DaQuyRepository;
import repository.NhaCungCap.NhaCungCapRepository;
import repository.PhanLoai.PhanLoaiRepo;
import repository.Size.SizeRepository;
import repository.chatlieu.ChatLieuRepository;
import repository.kiemdinh.KiemDinhRepository;
import repository.mausac.MauSacRepository;
import view.until.combobox.ComboBoxSuggestion;
import model.KiemDinh;
import model.ChatLieu;
import model.DaQuy;
import model.MauSac;
import model.PhanLoai;
import model.NhaCungCap;
import model.Size;

public class CapNhatSanPhamSv implements InterfaceCapNhatSanPhamSv {

    private KiemDinhRepository rpkd = new KiemDinhRepository();
    private DaQuyRepository rpdq = new DaQuyRepository();
    private SizeRepository rpsz = new SizeRepository();
    private NhaCungCapRepository rpncc = new NhaCungCapRepository();
    private PhanLoaiRepo rppl = new PhanLoaiRepo();
    private MauSacRepository rpms = new MauSacRepository();
    private ChatLieuRepository rpcl = new ChatLieuRepository();

    private List<KiemDinh> lstKiemDinh = new ArrayList<>();
    private List<ChatLieu> lstChatLieus = new ArrayList<>();
    private List<DaQuy> lstDaQuys = new ArrayList<>();
    private List<MauSac> lstMauSacs = new ArrayList<>();
    private List<NhaCungCap> lstNhaCungCaps = new ArrayList<>();
    private List<Size> lstSizes = new ArrayList<>();
    private List<PhanLoai> lstPhanLoais = new ArrayList<>();

    @Override
    public String addCboKiemDinh(ComboBoxSuggestion cbos) {
        if (cbos == null) {
            return "ComboBox không được khởi tạo.";
        }
        lstKiemDinh = rpkd.getAll();
        if (lstKiemDinh == null) {
            lstKiemDinh = new ArrayList<>();
        }
        cbos.removeAllItems();
        for (KiemDinh kiemDinh : lstKiemDinh) {
            if (kiemDinh != null) {
                cbos.addItem(kiemDinh.getDonViKiemDinh());

            }
        }
        return "Danh sách kiểm định đã được thêm vào ComboBox";
    }

    @Override
    public String addCboSize(ComboBoxSuggestion cbos) {
        if (cbos == null) {
            return "ComboBox không được khởi tạo.";
        }
        lstSizes = rpsz.getAll();
        if (lstSizes == null) {
            lstSizes = new ArrayList<>(); // Khởi tạo danh sách nếu nó là null
        }
        cbos.removeAllItems();
        for (Size size : lstSizes) {
            if (size != null) {
                cbos.addItem(size.getSoSize());
            }
        }
        return "Danh sách kích thước đã được thêm vào ComboBox";
    }

    @Override
    public String addCboPhanLoai(ComboBoxSuggestion cbos) {
        if (cbos == null) {
            return "ComboBox không được khởi tạo.";
        }
        lstPhanLoais = rppl.getAll();
        if (lstPhanLoais == null) {
            lstPhanLoais = new ArrayList<>(); 
        }
        cbos.removeAllItems();
        for (PhanLoai phanLoai : lstPhanLoais) {
            if (phanLoai != null) {
                cbos.addItem(phanLoai.getTenPhanLoai());
            }
        }
        return "Danh sách phân loại đã được thêm vào ComboBox";
    }

    @Override
    public String addCboChatLieu(ComboBoxSuggestion cbos) {
        if (cbos == null) {
            return "ComboBox không được khởi tạo.";
        }
        lstChatLieus = rpcl.getAll();
        if (lstChatLieus == null) {
            lstChatLieus = new ArrayList<>(); // Khởi tạo danh sách nếu nó là null
        }
        cbos.removeAllItems();
        for (ChatLieu chatLieu : lstChatLieus) {
            if (chatLieu != null) {
                cbos.addItem(chatLieu.getTenChatLieu());
            }
        }
        return "Danh sách chất liệu đã được thêm vào ComboBox";
    }

    @Override
    public String addCboMauSac(ComboBoxSuggestion cbos) {
        if (cbos == null) {
            return "ComboBox không được khởi tạo.";
        }
        lstMauSacs = rpms.getAll();
        if (lstMauSacs == null) {
            lstMauSacs = new ArrayList<>(); // Khởi tạo danh sách nếu nó là null
        }
        cbos.removeAllItems();
        for (MauSac mauSac : lstMauSacs) {
            if (mauSac != null) {
                cbos.addItem(mauSac.getChiTietMauSac());
            }
        }
        return "Danh sách màu sắc đã được thêm vào ComboBox";
    }

    @Override
    public String addCboLoaiDa(ComboBoxSuggestion cbos) {
        if (cbos == null) {
            return "ComboBox không được khởi tạo.";
        }
        lstDaQuys = rpdq.getAll();
        if (lstDaQuys == null) {
            lstDaQuys = new ArrayList<>();
        }
        cbos.removeAllItems();
        for (DaQuy daQuy : lstDaQuys) {
            if (daQuy != null) {
                cbos.addItem(daQuy.getTenDaQuy());
            }
        }
        return "Danh sách loại đá đã được thêm vào ComboBox";
    }

    @Override
    public String addCboNhaCungCap(ComboBoxSuggestion cbos) {
        if (cbos == null) {
            return "ComboBox không được khởi tạo.";
        }
        lstNhaCungCaps = rpncc.getAll();
        if (lstNhaCungCaps == null) {
            lstNhaCungCaps = new ArrayList<>(); // Khởi tạo danh sách nếu nó là null
        }
        cbos.removeAllItems();
        for (NhaCungCap nhaCungCap : lstNhaCungCaps) {
            if (nhaCungCap != null) {
                cbos.addItem(nhaCungCap.getTenNhaCungCap());
            }
        }
        return "Danh sách nhà cung cấp đã được thêm vào ComboBox";
    }

    @Override
    public boolean checkCbo(ComboBoxSuggestion cbos, String str) {
        if (cbos == null || str == null) {
            return false;
        }
        for (int i = 0; i < cbos.getItemCount(); i++) {
            if (cbos.getItemAt(i) != null && cbos.getItemAt(i).toString().equals(str)) {
                cbos.setSelectedItem(str);
                return true;
            }
        }
        return false;
    }

    @Override
    public String checkCboKiemDinh(ComboBoxSuggestion cbos) {
        if (cbos == null || cbos.getSelectedItem() == null) {
            return null;
        }
        String selectedItem = cbos.getSelectedItem().toString().trim();
        lstKiemDinh = rpkd.getAll();
        for (KiemDinh kiemDinh : lstKiemDinh) {
            if (selectedItem.equalsIgnoreCase(kiemDinh.getDonViKiemDinh().trim())) {
                return kiemDinh.getIDKiemDinh();
            }
        }
        return null;
    }

    @Override
    public String checkCboSize(ComboBoxSuggestion cbos) {
        if (cbos == null || cbos.getSelectedItem() == null) {
            return null;
        }
        String selectedItem = cbos.getSelectedItem().toString().trim();
        lstSizes = rpsz.getAll();
        for (Size size : lstSizes) {
            if (selectedItem.equalsIgnoreCase(String.valueOf(size.getSoSize()).trim())) {
                return size.getIDSize();
            }
        }

        return null;
    }

    @Override
    public String checkCboPhanLoai(ComboBoxSuggestion cbos) {
        if (cbos == null || cbos.getSelectedItem() == null) {
            return null;
        }
        String selectedItem = cbos.getSelectedItem().toString().trim();
        lstPhanLoais = rppl.getAll();
        for (PhanLoai phanLoai : lstPhanLoais) {
            if (selectedItem.equalsIgnoreCase(phanLoai.getTenPhanLoai().trim())) {
                return phanLoai.getIDPhanLoai();
            }
        }
        return null;
    }

    @Override
    public String checkCboChatLieu(ComboBoxSuggestion cbos) {
        if (cbos == null || cbos.getSelectedItem() == null) {
            return null;
        }
        String selectedItem = cbos.getSelectedItem().toString().trim();
        lstChatLieus = rpcl.getAll();
        for (ChatLieu chatLieu : lstChatLieus) {
            if (selectedItem.equalsIgnoreCase(chatLieu.getTenChatLieu().trim())) {
                return chatLieu.getIDChatLieu();
            }
        }
        return null;
    }

    @Override
    public String checkCboMauSac(ComboBoxSuggestion cbos) {
        if (cbos == null || cbos.getSelectedItem() == null) {
            return null;
        }
        String selectedItem = cbos.getSelectedItem().toString().trim();
        lstMauSacs = rpms.getAll();
        for (MauSac mauSac : lstMauSacs) {
            if (selectedItem.equalsIgnoreCase(mauSac.getChiTietMauSac().trim())) {
                return mauSac.getIDMauSac();
            }
        }
        return null;
    }

    @Override
    public String checkCboLoaiDa(ComboBoxSuggestion cbos) {
        if (cbos == null || cbos.getSelectedItem() == null) {
            return null;
        }
        String selectedItem = cbos.getSelectedItem().toString().trim();
        lstDaQuys = rpdq.getAll();
        for (DaQuy daQuy : lstDaQuys) {
            if (selectedItem.equalsIgnoreCase(daQuy.getTenDaQuy().trim())) {
                return daQuy.getIDDaQuy();
            }
        }
        return null;
    }

    @Override
    public String checkCboNhaCungCap(ComboBoxSuggestion cbos) {
        if (cbos == null || cbos.getSelectedItem() == null) {
            return null;
        }
        String selectedItem = cbos.getSelectedItem().toString().trim();
        lstNhaCungCaps = rpncc.getAll();
        for (NhaCungCap nhaCungCap : lstNhaCungCaps) {
            if (selectedItem.equalsIgnoreCase(nhaCungCap.getTenNhaCungCap().trim())) {
                return nhaCungCap.getIDNhaCungCap();
            }
        }
        return null;
    }

    @Override
    public void fillToPhanLoaiTbl(JTable tbl) {
        lstPhanLoais = rppl.getAll();
        DefaultTableModel tableModel = (DefaultTableModel) tbl.getModel();
        tableModel.setRowCount(0);
        int STT = 1;
        for (PhanLoai pl : rppl.getAll()) {
            tableModel.addRow(new Object[]{STT++, pl.getIDPhanLoai(), pl.getTenPhanLoai(), pl.isTrangThai() ? "Kinh Doanh" : "Ngừng Kinh Doanh"});
        }
    }

    @Override
    public void fillToKiemDinhTbl(JTable tbl) {
        lstKiemDinh = rpkd.getAll();
        DefaultTableModel tableModel = (DefaultTableModel) tbl.getModel();
        tableModel.setRowCount(0);
        int STT = 1;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        for (KiemDinh kd : lstKiemDinh) {
            java.sql.Date sqlDate = kd.getNgayKiemDinh();
            String formattedDate = dateFormat.format(sqlDate);

            tableModel.addRow(new Object[]{
                STT++,
                kd.getIDKiemDinh(),
                kd.getDonViKiemDinh(),
                formattedDate,
                kd.isTrangThai() ? "Hoạt Động" : "Ngừng Hoạt Động"
            });
        }
    }

    @Override
    public void fillToChatLieuTbl(JTable tbl) {
        lstChatLieus = rpcl.getAll();
        DefaultTableModel tableModel = (DefaultTableModel) tbl.getModel();
        tableModel.setRowCount(0);
        int STT = 1;
        for (ChatLieu cl : rpcl.getAll()) {
            tableModel.addRow(new Object[]{STT++, cl.getIDChatLieu(), cl.getTenChatLieu(), cl.getTyLe(), cl.isTrangThai() ? "Kinh Doanh" : "Ngừng Kinh Doanh"});
        }
    }

    @Override
    public void fillToSizeTbl(JTable tbl) {
        lstSizes = rpsz.getAll();
        DefaultTableModel tableModel = (DefaultTableModel) tbl.getModel();
        int STT = 1;
        tableModel.setRowCount(0);
        for (Size sz : rpsz.getAll()) {
            tableModel.addRow(new Object[]{STT++, sz.getIDSize(), sz.getSoSize(), sz.isTrangThai() ? "Kinh Doanh" : "Ngừng Kinh Doanh"});

        }
    }

    @Override
    public void fillToNhaCungCapTbl(JTable tbl) {
        lstNhaCungCaps = rpncc.getAll();
        DefaultTableModel tableModel = (DefaultTableModel) tbl.getModel();
        tableModel.setRowCount(0);
        int STT = 1;
        for (NhaCungCap ncc : rpncc.getAll()) {
            tableModel.addRow(new Object[]{STT++, ncc.getIDNhaCungCap(), ncc.getTenNhaCungCap(), ncc.getEmail(), ncc.getSoDienThoai(), ncc.getDiaChi(), ncc.isTrangThai() ? "Hoạt Động" : "Ngừng Hoạt Động"});
        }
    }

    @Override
    public void fillToMauSacTbl(JTable tbl) {
        lstMauSacs = rpms.getAll();
        DefaultTableModel tableModel = (DefaultTableModel) tbl.getModel();
        tableModel.setRowCount(0);
        int STT = 1;
        for (MauSac ms : rpms.getAll()) {
            tableModel.addRow(new Object[]{STT++, ms.getIDMauSac(), ms.getChiTietMauSac(), ms.isTrangThai() ? "Kinh Doanh" : "Ngừng Kinh Doanh"});
        }
    }

    @Override
    public void fillToDaQuyTbl(JTable tbl) {
        lstDaQuys = rpdq.getAll();
        DefaultTableModel tableModel = (DefaultTableModel) tbl.getModel();
        tableModel.setRowCount(0);
        int STT = 1;
        for (DaQuy dq : rpdq.getAll()) {
            tableModel.addRow(new Object[]{STT++, dq.getIDDaQuy(), dq.getTenDaQuy(), dq.isTrangThai() ? "Kinh Doanh" : "Ngừng Kinh Doanh"});
        }
    }

    @Override
    public void getDataPhanLoai(JTable tbl, JTextField txtID, JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2) {
        int i = tbl.getSelectedRow();
        if (i >= 0) {
            txtID.setText(tbl.getValueAt(i, 1).toString());
            txtTen.setText(tbl.getValueAt(i, 2).toString());
            if (tbl.getValueAt(i, 3).toString().equalsIgnoreCase("Kinh Doanh")) {
                rdo1.setSelected(true);
            } else {
                rdo2.setSelected(true);
            }
        }
    }

    @Override
    public void getDataKiemDinh(JTable tbl, JTextField txtID, JTextField txtTen, JTextField txtNgayKiemDinh, JRadioButton rdo1, JRadioButton rdo2) {
        int i = tbl.getSelectedRow();
        if (i >= 0) {
            txtID.setText(tbl.getValueAt(i, 1).toString());
            txtTen.setText(tbl.getValueAt(i, 2).toString());
            txtNgayKiemDinh.setText(tbl.getValueAt(i, 3).toString());
            if (tbl.getValueAt(i, 4).toString().equalsIgnoreCase("Hoạt Động")) {
                rdo1.setSelected(true);
            } else {
                rdo2.setSelected(true);
            }
        }
    }

    @Override
    public void getDataMauSac(JTable tbl, JTextField txtID, JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2) {
        int i = tbl.getSelectedRow();
        if (i >= 0) {
            txtID.setText(tbl.getValueAt(i, 1).toString());
            txtTen.setText(tbl.getValueAt(i, 2).toString());
            if (tbl.getValueAt(i, 3).toString().equalsIgnoreCase("Kinh Doanh")) {
                rdo1.setSelected(true);
            } else {
                rdo2.setSelected(true);
            }
        }
    }

    @Override
    public void getDataNhaCungCap(JTable tbl, JTextField txtID, JTextField txtTen, JTextField txtEmail, JTextField txtSDT, JTextArea txtDiaChi, JRadioButton rdo1, JRadioButton rdo2) {
        if (tbl == null || txtID == null || txtTen == null || txtEmail == null || txtSDT == null || txtDiaChi == null || rdo1 == null || rdo2 == null) {
            throw new IllegalArgumentException("One or more arguments are null");
        }

        int i = tbl.getSelectedRow();
        if (i >= 0) {
            Object id = tbl.getValueAt(i, 1);
            Object ten = tbl.getValueAt(i, 2);
            Object email = tbl.getValueAt(i, 3);
            Object sdt = tbl.getValueAt(i, 4);
            Object diaChi = tbl.getValueAt(i, 5);
            Object trangThai = tbl.getValueAt(i, 6);

            txtID.setText(id != null ? id.toString() : "");
            txtTen.setText(ten != null ? ten.toString() : "");
            txtEmail.setText(email != null ? email.toString() : "");
            txtSDT.setText(sdt != null ? sdt.toString() : "");
            txtDiaChi.setText(diaChi != null ? diaChi.toString() : "");

            if (trangThai != null && trangThai.toString().equalsIgnoreCase("Hoạt Động")) {
                rdo1.setSelected(true);
            } else {
                rdo2.setSelected(true);
            }
        }
    }

    @Override
    public void getDataChatLieu(JTable tbl, JTextField txtID, JTextField txtTen, JTextField txtTyLe, JRadioButton rdo1, JRadioButton rdo2) {
        int i = tbl.getSelectedRow();
        if (i >= 0) {
            txtID.setText(tbl.getValueAt(i, 1).toString());
            txtTen.setText(tbl.getValueAt(i, 2).toString());
            txtTyLe.setText(tbl.getValueAt(i, 3).toString());
            if (tbl.getValueAt(i, 4).toString().equalsIgnoreCase("Kinh Doanh")) {
                rdo1.setSelected(true);

            } else {
                rdo2.setSelected(true);
            }
        }
    }

    @Override
    public void getDataSize(JTable tbl, JTextField txtID, JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2) {
        int i = tbl.getSelectedRow();
        if (i >= 0) {
            txtID.setText(tbl.getValueAt(i, 1).toString());
            txtTen.setText(tbl.getValueAt(i, 2).toString());
            if (tbl.getValueAt(i, 3).toString().equalsIgnoreCase("Kinh Doanh")) {
                rdo1.setSelected(true);
            } else {
                rdo2.setSelected(true);
            }
        }
    }

    @Override
    public void getDataDaQuy(JTable tbl, JTextField txtID, JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2) {
        int i = tbl.getSelectedRow();
        if (i >= 0) {
            txtID.setText(tbl.getValueAt(i, 1).toString());
            txtTen.setText(tbl.getValueAt(i, 2).toString());
            if (tbl.getValueAt(i, 3).toString().equalsIgnoreCase("Kinh Doanh")) {
                rdo1.setSelected(true);
            } else {
                rdo2.setSelected(true);
            }
        }
    }

    @Override
    public void readFormPhanLoai(JTextField txtID, JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2) {
        for (PhanLoai pl : lstPhanLoais) {
            if (txtID.getText().equals(pl.getIDPhanLoai())) {
                pl.setTenPhanLoai(txtTen.getText().trim());
                pl.setTrangThai(rdo1.isSelected());
                rppl.updatePhanLoai(pl);
                return;
            }
        }
    }

    @Override
    public void readFormMauSac(JTextField txtID, JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2) {
        for (MauSac ms : lstMauSacs) {
            if (txtID.getText().equals(ms.getIDMauSac())) {
                ms.setChiTietMauSac(txtTen.getText().trim());
                ms.setTrangThai(rdo1.isSelected());
                rpms.update(ms);
                return;
            }
        }
    }

    @Override
    public void readFormKiemDinh(JTextField txtID, JTextField txtTen, JTextField txtNgayKiemDinh, JRadioButton rdo1, JRadioButton rdo2) {
        String id = txtID.getText().trim();
        String ten = txtTen.getText().trim();
        String ngayKiemDinhStr = txtNgayKiemDinh.getText().trim();

        // Chuyển đổi ngày từ chuỗi sang java.sql.Date
        java.sql.Date ngayKiemDinhSql = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); // Định dạng ngày
            java.util.Date ngayKiemDinhUtil = sdf.parse(ngayKiemDinhStr); // Phân tích chuỗi thành java.util.Date
            ngayKiemDinhSql = new java.sql.Date(ngayKiemDinhUtil.getTime()); // Chuyển đổi thành java.sql.Date
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }

        for (KiemDinh kd : lstKiemDinh) {
            if (id.equals(kd.getIDKiemDinh())) {
                kd.setDonViKiemDinh(ten);
                kd.setNgayKiemDinh(ngayKiemDinhSql);
                kd.setTrangThai(rdo1.isSelected());

                // Cập nhật đối tượng KiemDinh
                rpkd.updateKiemDinh(kd);
                return;
            }
        }
    }

    @Override
    public void readFormChatLieu(JTextField txtID, JTextField txtTen, JTextField txtTyle, JRadioButton rdo1, JRadioButton rdo2) {
        for (ChatLieu cl : rpcl.getAll()) {
            if (txtID.getText().equals(cl.getIDChatLieu())) {
                cl.setTenChatLieu(txtTen.getText().trim());
                cl.setTyLe(Float.parseFloat(txtTyle.getText().trim()));
                cl.setTrangThai(rdo1.isSelected());
                cl.setIDChatLieu(txtID.getText().trim());
                rpcl.update(cl);
                return;
            }
        }
    }

    @Override
    public void readFormNhaCungCap(JTextField txtID, JTextField txtTen, JTextField txtEmail, JTextField txtSDT, JTextArea txtDiaChi, JRadioButton rdo1, JRadioButton rdo2) {
        for (NhaCungCap ncc : lstNhaCungCaps) {
            if (txtID.getText().equals(ncc.getIDNhaCungCap())) {
                ncc.setTenNhaCungCap(txtTen.getText());
                ncc.setEmail(txtEmail.getText().trim());
                ncc.setDiaChi(txtDiaChi.getText().trim());
                ncc.setSoDienThoai(txtSDT.getText().trim());
                ncc.setTrangThai(rdo1.isSelected());
                rpncc.updateNhaCungCap(ncc);
                return;
            }
        }
    }

    @Override
    public void readFormSize(JTextField txtID, JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2) {
        for (Size sz : lstSizes) {
            if (txtID.getText().equals(sz.getIDSize())) {
                sz.setSoSize(Integer.valueOf(txtTen.getText().trim()));
                sz.setTrangThai(rdo1.isSelected());
                rpsz.update(sz);           
                return;
            }
        }
    }

    @Override
    public void readFormDaQuy(JTextField txtID, JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2) {
        for (DaQuy dq : lstDaQuys) {
            if (txtID.getText().equals(dq.getIDDaQuy())) {
                dq.setTenDaQuy(txtTen.getText().trim());
                dq.setTrangThai(rdo1.isSelected());
                rpdq.update(dq); 
                }
                return;
            }
    }

    @Override
    public void clearList(String listName) {
        switch (listName) {
            case "KiemDinh":
                lstKiemDinh.clear();
                break;
            case "ChatLieu":
                lstChatLieus.clear();
                break;
            case "DaQuy":
                lstDaQuys.clear();
                break;
            case "MauSac":
                lstMauSacs.clear();
                break;
            case "NhaCungCap":
                lstNhaCungCaps.clear();
                break;
            case "Size":
                lstSizes.clear();
                break;
            case "PhanLoai":
                lstPhanLoais.clear();
                break;
            default:
                System.out.println("Danh sách không hợp lệ.");
        }
    }

    private void showMessage(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void InsertPhanLoai(JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2) {
        String tenPhanLoai = txtTen.getText().trim();
        PhanLoai pl = new PhanLoai();
        pl.setTenPhanLoai(txtTen.getText().trim());
        pl.setTrangThai(rdo1.isSelected() ? true : false);
        rppl.addPhanLoai(pl);

    }

    @Override
    public void InsertMauSac(JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2) {
        String tenMauSac = txtTen.getText().trim();

        MauSac ms = new MauSac();
        ms.setChiTietMauSac(tenMauSac);
        ms.setTrangThai(rdo1.isSelected() ? true : false);
        rpms.addMauSac(ms);

    }

    @Override
    public void InsertKiemDinh(JTextField txtTen, JTextField txtNgayKiemDinh, JRadioButton rdo1, JRadioButton rdo2) {
        String tenKiemDinh = txtTen.getText().trim();
        String ngayKiemDinhStr = txtNgayKiemDinh.getText().trim();

        java.sql.Date ngayKiemDinhSql = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date ngayKiemDinhUtil = sdf.parse(ngayKiemDinhStr);
            ngayKiemDinhSql = new java.sql.Date(ngayKiemDinhUtil.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Tạo đối tượng KiemDinh nếu ngày hợp lệ
        KiemDinh kd = new KiemDinh();
        kd.setDonViKiemDinh(tenKiemDinh);
        kd.setNgayKiemDinh(ngayKiemDinhSql);
        kd.setTrangThai(rdo1.isSelected());

        // Thêm đối tượng vào danh sách
        rpkd.addKiemDinh(kd);
    }

    @Override
    public void InsertChatLieu(JTextField txtTen, JTextField txtTyle, JRadioButton rdo1, JRadioButton rdo2) {
        String tenChatLieu = txtTen.getText().trim();
        String tyle = txtTyle.getText().trim();
        ChatLieu cl = new ChatLieu();
        cl.setTenChatLieu(tenChatLieu);
        cl.setTyLe(Float.parseFloat(tyle));
        cl.setTrangThai(rdo1.isSelected() ? true : false);
        rpcl.creat(cl);

    }

    @Override
    public void InsertNhaCungCap(JTextField txtTen, JTextField txtEmail, JTextField txtSDT, JTextArea txtDiaChi, JRadioButton rdo1, JRadioButton rdo2) {
        String soDienThoai = txtSDT.getText().trim();
        NhaCungCap nc = new NhaCungCap();
        nc.setTenNhaCungCap(txtTen.getText().trim());
        nc.setSoDienThoai(soDienThoai);
        nc.setDiaChi(txtDiaChi.getText().trim());
        nc.setEmail(txtEmail.getText().trim());
        nc.setTrangThai(rdo1.isSelected());
        rpncc.addNhaCungCap(nc);

    }

    @Override
    public void InsertSize(JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2) {
        Size sz = new Size();
        int tenSize;
        tenSize = Integer.parseInt(txtTen.getText().trim());
        sz.setSoSize(tenSize);
        sz.setTrangThai(rdo1.isSelected());
        rpsz.creat(sz);
    }

    @Override
    public void InsertDaQuy(JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2) {
        String tenDaQuy = txtTen.getText().trim();

        DaQuy dq = new DaQuy();
        dq.setTenDaQuy(tenDaQuy);
        dq.setTrangThai(rdo1.isSelected() ? true : false);
        rpdq.creat(dq);
    }

}
