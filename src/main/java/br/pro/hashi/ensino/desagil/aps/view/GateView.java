package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GateView extends JPanel implements ActionListener {

    private final Gate gate;
    private final JCheckBox[] inputGate;
    private final JCheckBox outputGate;
    private final Switch[] switches;

    public GateView(Gate gate) {
        this.gate = gate;

        inputGate = new JCheckBox[gate.getInputSize()];
        outputGate = new JCheckBox();
        switches = new Switch[gate.getInputSize()];
        JLabel inputGateLabel;

        if (inputGate.length == 1) {
            inputGateLabel = new JLabel("Entrada:");
        } else {
            inputGateLabel = new JLabel("Entradas:");
        }

        JLabel outputGateLabel = new JLabel("Saida:");

        add(inputGateLabel);
        for (int i = 0; i < inputGate.length; i++) {
            inputGate[i] = new JCheckBox();
            add(inputGate[i]);
            inputGate[i].addActionListener(this);
            switches[i] = new Switch();
            gate.connect(i, switches[i]);
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(outputGateLabel);
        add(outputGate);

        outputGate.setEnabled(false);

        update();

    }

    private void update() {

        for (int i = 0; i < inputGate.length; i++) {

            if (inputGate[i].isSelected()) {
                switches[i].turnOn();
            } else {
                switches[i].turnOff();
            }
        }

        outputGate.setSelected(gate.read());

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        update();
    }
}

