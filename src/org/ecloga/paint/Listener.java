package org.ecloga.paint;

import java.io.BufferedReader;

class Listener implements Runnable {

    private final Machine machine;
    private final BufferedReader reader;

    public Listener(Machine machine, BufferedReader reader) {
        this.machine = machine;
        this.reader = reader;
    }

    @Override
    public void run() {
        machine.read(reader);
    }
}