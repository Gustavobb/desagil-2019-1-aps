package br.pro.hashi.ensino.desagil.aps.model;

public class HalfAdder extends Gate {
    private final NandGate nandOne;
    private final NandGate nandTwoTop;
    private final NandGate nandTwoBottom;
    private final NandGate nandThreeTop;
    private final NandGate nandThreeBottom;
    private boolean result = false;



    public HalfAdder() {
        super("Half-Adder", 2, 2);

        nandOne = new NandGate();
        nandTwoTop = new NandGate();
        nandTwoBottom = new NandGate();
        nandThreeTop = new NandGate();
        nandThreeBottom = new NandGate();

        nandTwoTop.connect(1, nandOne);

        nandTwoBottom.connect(0, nandOne);

        nandThreeTop.connect(0, nandTwoTop);
        nandThreeTop.connect(1, nandTwoBottom);

        nandThreeBottom.connect(0, nandOne);
        nandThreeBottom.connect(1, nandOne);

    }

    @Override
    public boolean read(int outputPin) {
        if (outputPin == 0) {
            result = nandThreeTop.read();
        }

        if (outputPin == 1) {
            result = nandThreeBottom.read();
        }

        return result;
    }

    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        switch (inputPin) {
            case 0:
                nandOne.connect(0, emitter);
                nandTwoTop.connect(0, emitter);
                break;
            case 1:
                nandOne.connect(1, emitter);
                nandTwoBottom.connect(1, emitter);
                break;
            default:
                throw new IndexOutOfBoundsException(inputPin);
        }
    }
}
