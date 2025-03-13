package view.until.textarea;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicTextAreaUI;

public class TextAreaSuggestionUI extends BasicTextAreaUI {

    private JTextArea textarea;
    private Border border;
    private int round = 15;

    public TextAreaSuggestionUI(JTextArea textarea) {
        this.textarea = textarea;
        border = new Border(10);
        border.setRound(round);
        textarea.setBorder(border);
        textarea.setOpaque(false);
        textarea.setBackground(Color.WHITE); // Set background color to white
        textarea.setSelectedTextColor(Color.WHITE);
        textarea.setSelectionColor(new Color(54, 189, 248));
        textarea.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent fe) {
                border.setFocusColor(new Color(128, 189, 255));
                textarea.repaint();
            }

            @Override
            public void focusLost(FocusEvent fe) {
                border.setFocusColor(new Color(206, 212, 218));
                textarea.repaint();
            }
        });
    }

    @Override
    protected void paintBackground(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(textarea.getBackground());
        g2.fillRoundRect(0, 0, textarea.getWidth(), textarea.getHeight(), round, round);
        g2.dispose();
    }

    @Override
    protected void paintSafely(Graphics g) {
        paintBackground(g);
        super.paintSafely(g);
    }

    public void setRound(int round) {
        this.round = round;
        border.setRound(round);
        textarea.repaint();
    }

    public int getRound() {
        return round;
    }

    private class Border extends EmptyBorder {

        private Color focusColor = new Color(128, 189, 255);
        private Color color = new Color(206, 212, 218);
        private int round;

        public Border(int border) {
            super(border, border, border, border);
        }

        public Border() {
            this(5);
        }

        public int getRound() {
            return round;
        }

        public void setRound(int round) {
            this.round = round;
        }

        public Color getFocusColor() {
            return focusColor;
        }

        public void setFocusColor(Color focusColor) {
            this.focusColor = focusColor;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(c.isFocusOwner() ? focusColor : color);
            g2.drawRoundRect(x, y, width - 1, height - 1, round, round);
            g2.dispose();
        }
    }
}
