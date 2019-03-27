package br.pro.hashi.ensino.desagil.aps.model;

public class XorGate extends Gate {

    private final NandGate nandOne;
    private final NandGate nandTwo;
    private final NandGate nandThree;
    private final NandGate nandFour;


    public XorGate() {
        super(2);
        nandOne = new NandGate();
        nandTwo = new NandGate();
        nandThree = new NandGate();
        nandFour = new NandGate();

        nandThree.connect(0, nandOne);

        nandTwo.connect(1, nandOne);

        nandFour.connect(0, nandTwo);
        nandFour.connect(1, nandThree);


    }

    @Override
    public boolean read() {
        return nandFour.read();
    }

    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        if (inputPin < 0 || inputPin > 1) {
            throw new IndexOutOfBoundsException(inputPin);
        }

        nandOne.connect(inputPin, emitter);

        if (inputPin == 1) {
            nandThree.connect(inputPin, emitter);
        }

        if (inputPin == 0) {
            nandTwo.connect(inputPin, emitter);
        }


    }
}
