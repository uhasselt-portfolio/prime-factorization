package com.michielswaanen;

import com.michielswaanen.model.ConsumerModel;
import com.michielswaanen.model.ProducerModel;
import com.michielswaanen.view.ConsumerView;
import com.michielswaanen.view.ProducerView;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * @author Michiel Swaanen
 */
public class Application {

    public static void main(String[] args) {

        // Initialize application
        Application application = new Application();
        application.start();

    }

    /**
     * Start the application
     */
    public void start() {
        // Initialize queue and size limit
        Queue<Integer> queue = new LinkedList<>();
        int sizeLimit = 10;

        // Views
        ProducerView producerView = new ProducerView("Producer");
        ConsumerView consumerView = new ConsumerView("Consumer");

        // Models
        ProducerModel producerModel = new ProducerModel(queue, sizeLimit);
        ConsumerModel consumerModel = new ConsumerModel(queue);

        // Connect view and model
        producerModel.addObserver(producerView);
        consumerModel.addObserver(consumerView);

        // Display GUI
        display(producerView.getGUI(), consumerView.getGUI());
    }

    /**
     * Create and display the GUI
     *
     * @param components Specify the {@link JComponent}s that must be added to the frame
     */
    private void display(JComponent... components) {
        JFrame frame = new JFrame();
        frame.setLayout(new GridLayout(1,2));
        frame.setPreferredSize(new Dimension(700, 700));

        for (JComponent component : components)
            frame.add(component);

        frame.pack();
        frame.setVisible(true);
    }
}
