package view.banhang.spinner;

import java.awt.Component;
import java.text.DecimalFormat;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.text.DefaultFormatter;
import model.HoaDonChiTiet;

public class QtyCellEditor extends DefaultCellEditor {

    private EventCellInputChange event;
    private JSpinner input;

    private JTable table;
    private int row;
    private HoaDonChiTiet item;

    public QtyCellEditor(EventCellInputChange event) {
        super(new JCheckBox());
        this.event = event;
        input = new JSpinner();
        SpinnerNumberModel numberModel = (SpinnerNumberModel) input.getModel();
        numberModel.setMinimum(1);
        JSpinner.NumberEditor editor = (JSpinner.NumberEditor) input.getEditor();
        DefaultFormatter formatter = (DefaultFormatter) editor.getTextField().getFormatter();
        formatter.setCommitsOnValidEdit(true);
        editor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
        input.addChangeListener((ChangeEvent e) -> {
            inputChange();
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        super.getTableCellEditorComponent(table, value, isSelected, row, column);
        this.table = table;
        this.row = row;

        Object obj = table.getValueAt(row, 0);
        if (obj instanceof HoaDonChiTiet) {
            this.item = (HoaDonChiTiet) obj;
        } else {
            this.item = null;
        }

        int qty = Integer.parseInt(value.toString());
        input.setValue(qty);
        input.setEnabled(false);
        enable();
        return input;
    }

    private void enable() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                    input.setEnabled(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public Object getCellEditorValue() {
        return input.getValue();
    }

    private void inputChange() {
        int qty = Integer.parseInt(input.getValue().toString());
        if (item != null && qty != item.getSoLUongSanPHam()) {
            DecimalFormat df = new DecimalFormat("#,##0.##");
            item.setSoLUongSanPHam(qty);
            double total = item.getGiaSanPham() * qty;
            table.setValueAt(df.format(total) + " VND", row, 8);
            event.inputChanged();
        }
    }
}
