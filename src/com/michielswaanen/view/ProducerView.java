package com.michielswaanen.view;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * @author Michiel Swaanen
 */
public class ProducerView implements Observer {

    private JTextArea textArea;
    private JScrollPane textPane;
    private JTextField title;

    public ProducerView(String title) {
        this.textArea = new JTextArea();

        this.textPane = new JScrollPane(this.textArea);
        this.textPane.setFont(new Font("Arial", Font.PLAIN, 16));
        this.textPane.setAutoscrolls(true);

        this.title = new JTextField();
        this.title.setFont(new Font("Arial", Font.BOLD, 30));
        this.title.setText(title);
    }

    /**
     * Retrieves the sub-GUI that must be added as a {@link JComponent} to the {@link JFrame}
     *
     * @return JComponent that contains the GUI for this view
     */
    public JComponent getGUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,1));
        panel.add(this.title);
        panel.add(this.textPane);
        return panel;
    }

    /**
     * Receives the update signal from the {@link Observer} (model)
     *
     * @param o   Contains an instance of the model
     * @param arg Contains the external arguments specified in the {@link com.michielswaanen.model.ProducerModel}
     */
    @Override
    public void update(Observable o, Object arg) {
        this.textArea.append(arg + "\n");

        // Automatically move your scrollbar down
        this.textArea.setCaretPosition(this.textArea.getDocument().getLength());
    }
}
