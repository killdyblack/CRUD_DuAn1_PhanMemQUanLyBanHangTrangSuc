package view.form;

import com.formdev.flatlaf.FlatClientProperties;
import view.until.sampletable.*;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author RAVEN
 */
public class JTableHeader implements TableCellRenderer {

    private final TableCellRenderer oldHeaderRenderer;
    private final TableCellRenderer oldCellRenderer;

    public JTableHeader(JTable table) {
        this.oldHeaderRenderer = table.getTableHeader().getDefaultRenderer();
        this.oldCellRenderer = table.getDefaultRenderer(Object.class);
        table.setDefaultRenderer(Object.class, new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int row, int column) {
                JLabel label = (JLabel) oldCellRenderer.getTableCellRendererComponent(jtable, o, bln, bln1, row, column);
                label.setHorizontalAlignment(getAlignment(column));
                return label;
            }
        });
         table.setBorder(BorderFactory.createEmptyBorder()); // Loại bỏ viền ngoài của bảng
        table.getTableHeader().setBorder(BorderFactory.createEmptyBorder());
    }

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int row, int column) {
        JLabel label = (JLabel) oldHeaderRenderer.getTableCellRendererComponent(jtable, o, bln, bln1, row, column);
        label.setHorizontalAlignment(getAlignment(column));
        return label;
    }

    protected int getAlignment(int column) {
        if (column == 0) {
            return SwingConstants.CENTER;
        }
        return SwingConstants.LEADING;
    }
}
