package org.ecloga.paint;

import java.io.BufferedReader;

class Listener implements Runnable {

    private final Machine machine;
    private final BufferedReader reader;

    //odredjivanje Machine i BufferedReader objekta
    public Listener(Machine machine, BufferedReader reader) {
        this.machine = machine;
        this.reader = reader;
    }

    //izvrsavanje u pozadini
    @Override
    public void run() {
        //citanje linija BufferedReader objekta
        machine.read(reader);
    }
}
