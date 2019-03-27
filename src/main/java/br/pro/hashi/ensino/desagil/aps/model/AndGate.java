package br.pro.hashi.ensino.desagil.aps.model;

public class AndGate extends Gate {

    private final NandGate nandOne;
    private final NandGate nandTwo;

    public AndGate() {
        super(2);
        nandOne = new NandGate();
        nandTwo = new NandGate();
    }

    @Override
    public boolean read() {
        return nandTwo.read();
    }

    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        if (inputPin < 0 || inputPin > 1) {
            throw new IndexOutOfBoundsException(inputPin);
        }
        nandOne.connect(inputPin, emitter);
        nandTwo.connect(inputPin, nandOne);
    }

}
