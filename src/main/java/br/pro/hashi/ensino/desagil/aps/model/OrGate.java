package br.pro.hashi.ensino.desagil.aps.model;

public class OrGate extends Gate {

    private final NandGate nandOne;
    private final NandGate nandTwo;
    private final NandGate nandThree;

    public OrGate() {
        super(2);
        nandOne = new NandGate();
        nandTwo = new NandGate();
        nandThree = new NandGate();

        nandThree.connect(0, nandOne);
        nandThree.connect(1, nandTwo);

    }

    @Override
    public boolean read() {
        return nandThree.read();
    }

    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        if (inputPin < 0 || inputPin > 1) {
            throw new IndexOutOfBoundsException(inputPin);
        }

        if (inputPin == 0) {
            nandOne.connect(0, emitter);
            nandOne.connect(1, emitter);
        }

        if (inputPin == 1) {
            nandTwo.connect(0, emitter);
            nandTwo.connect(1, emitter);
        }

    }

}
