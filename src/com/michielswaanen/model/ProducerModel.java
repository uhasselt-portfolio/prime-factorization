package com.michielswaanen.model;

import com.michielswaanen.util.ConsoleColor;

import java.util.*;

/**
 * @author Michiel Swaanen
 */
public class ProducerModel extends Observable implements Runnable {

    private Queue<Integer> queue;
    private int sizeLimit;

    /**
     * Constructor automatically starts the thread
     *
     * @pre sizeLimit   Must be 2 or higher
     * @param queue     Specify the queue where the {@link ConsumerModel} will remove numbers from
     * @param sizeLimit Specify the size limit for the @queue
     *                  When the limit is reached, the thread will sleep
     */
    public ProducerModel(Queue<Integer> queue, int sizeLimit) {
        this.queue = queue;
        this.sizeLimit = sizeLimit;
        new Thread(this).start();
    }

    /**
     * Creates a runnable, this function will be called when {@link Thread#start()} is called
     */
    @Override
    public void run() {
        while (true) {

            // Give the Consumer thread time to run
            if (this.queue.size() >= this.sizeLimit)
                sleep(100);

            // Generate random integer between 1.000 and 10.000
            int random = new Random().nextInt(99000) + 1000;

            // Add random to the queue
            this.queue.add(random);

            // Print to console
            System.out.println(ConsoleColor.ANSI_BLUE + "ProducerModel >> " + random);

            // Update view
            notifyObservers(random);
            setChanged();
        }
    }

    /**
     * Sleep the thread where it's currently running on
     *
     * @param time Specify the time in milliseconds
     */
    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ignore) {
        }
    }
}
