package edu.postech.csed332.homework3;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.Objects;

public class CellUI extends JTextField implements Observer {

    CellUI(Cell cell, boolean diagonal) {
        cell.addObserver(this);
        initCellUI(cell, diagonal);

        if (cell.getNumber().isEmpty()) {
            //TODO: whenever the content is changed, cell.setNumber() or cell.unsetNumber()
            // is accordingly invoked. You may use an action listener, a key listener, a
            // document listener, etc.
            this.getDocument().addDocumentListener(
                    new DocumentListener() {
                        @Override
                        public void insertUpdate(DocumentEvent e) {
                            String s = getText();
                            try {
                                int number = Integer.parseInt(s);
                                if (number > 9 || !cell.containsPossibility(number)) {
                                    throw new NumberFormatException();
                                }
                                cell.setNumber(number);
                            }
                            catch (NumberFormatException ignored) {
                                cell.unsetNumber();
                                SwingUtilities.invokeLater(() -> setText(""));
                            }
                        }

                        @Override
                        public void removeUpdate(DocumentEvent e) {
                            insertUpdate(e);
                        }

                        @Override
                        public void changedUpdate(DocumentEvent e) {
                        }
                    }
            );
        }
    }

    /**
     * If the underlying cell is activated, mark this cell UI as active. If the underlying cell
     * is deactivated, mark this cell UI as inactive.
     *
     * @param caller the subject
     * @param arg    an argument passed to caller
     */
    @Override
    public void update(@NotNull Subject caller, @NotNull Event arg) {
        //TODO: implement this
        if (arg instanceof ActivationEvent) {
            this.setActivated(((ActivationEvent) arg).activated());
        }
    }

    /**
     * Mark this cell UI as activated or deactivated
     *
     * @param activated true if this cell UI is activated, and false otherwise
     */
    private void setActivated(boolean activated) {
        setBorder(BorderFactory.createLineBorder(activated ? Color.BLACK : Color.RED));
        setEditable(true);
    }


    /**
     * Initialize this cell UI
     *
     * @param cell a cell model
     */
    private void initCellUI(Cell cell, boolean diagonal) {
        setFont(new Font("Times", Font.BOLD, 30));
        setHorizontalAlignment(JTextField.CENTER);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        if (diagonal)
            setBackground(Color.LIGHT_GRAY);

        if (cell.getNumber().isPresent()) {
            setForeground(Color.BLUE);
            setText(cell.getNumber().get().toString());
            setEditable(false);
        }
    }
}
