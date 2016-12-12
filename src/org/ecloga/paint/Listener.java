package org.ecloga.paint;

import java.io.BufferedReader;

public class Listener implements Runnable {

    protected Machine machine;
    protected BufferedReader reader;

    public Listener(Machine machine, BufferedReader reader) {
        this.machine = machine;
        this.reader = reader;
    }

    @Override
    public void run() {
        machine.read(reader);
    }
}
