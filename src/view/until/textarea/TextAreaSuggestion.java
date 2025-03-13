package view.until.textarea;

import javax.swing.JTextArea;

public class TextAreaSuggestion extends JTextArea {

    private TextAreaSuggestionUI textUI;

    public TextAreaSuggestion() {
        textUI = new TextAreaSuggestionUI(this);
        setUI(textUI);
    }

    public void setRound(int round) {
        textUI.setRound(round);
    }

    public int getRound() {
        return textUI.getRound();
    }
}
