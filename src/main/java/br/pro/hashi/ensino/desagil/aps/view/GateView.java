package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Light;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class GateView extends FixedPanel implements ItemListener, MouseListener {

    private final Switch[] switches;
    private final Gate gate;

    private final JCheckBox[] inputBoxes;
    private final Image image;
    private final Light light;

    public GateView(Gate gate) {

        super(230, 95);

        this.gate = gate;

        int inputSize = gate.getInputSize();

        light = new Light();

        switches = new Switch[inputSize];
        inputBoxes = new JCheckBox[inputSize];

        for (int i = 0; i < inputSize; i++) {
            switches[i] = new Switch();
            inputBoxes[i] = new JCheckBox();

            gate.connect(i, switches[i]);
        }

        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        int i = 1;

        for (JCheckBox inputBox : inputBoxes) {
            if (inputSize == 1) {
                add(inputBox, 0, 40, 17, 17);
            } else if (inputSize == 2) {
                add(inputBox, 0, 20 * i, 17, 17);
                i += 2;
            }
        }

        for (JCheckBox inputBox : inputBoxes) {
            inputBox.addItemListener(this);
        }

        addMouseListener(this);


        String name = gate.toString() + ".png";
        URL url = getClass().getClassLoader().getResource(name);
        image = getToolkit().getImage(url);

        light.connect(0, gate);
        light.setR(255);

        update();
    }

    private void update() {
        for (int i = 0; i < gate.getInputSize(); i++) {
            if (inputBoxes[i].isSelected()) {
                switches[i].turnOn();
            } else {
                switches[i].turnOff();
            }
        }

        repaint();

    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        update();
    }

    @Override
    public void mouseClicked(MouseEvent event) {

        // Descobre em qual posição o clique ocorreu.
        int x = event.getX();
        int y = event.getY();
        Color color;

        // Se o clique foi dentro do circulo colorido...
        if (Math.pow(x - (206 + 7.5), 2) + Math.pow(y - (40 + 7.5), 2) < Math.pow(7.5, 2)) {

            // ...então abrimos a janela seletora de cor...
            color = JColorChooser.showDialog(this, null, new Color(light.getR(), light.getG(), light.getB()));

            if (color != null) {
                light.setR(color.getRed());
                light.setB(color.getBlue());
                light.setG(color.getGreen());
            }

            // ...e chamamos repaint para atualizar a tela.
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent event) {
        // Não precisamos de uma reação específica à ação de pressionar
        // um botão do mouse, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        // Não precisamos de uma reação específica à ação de soltar
        // um botão do mouse, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        // Não precisamos de uma reação específica à ação do mouse
        // entrar no painel, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void mouseExited(MouseEvent event) {
        // Não precisamos de uma reação específica à ação do mouse
        // sair do painel, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void paintComponent(Graphics g) {

        // Não podemos esquecer desta linha, pois não somos os
        // únicos responsáveis por desenhar o painel, como era
        // o caso nos Desafios. Agora é preciso desenhar também
        // componentes internas, e isso é feito pela superclasse.
        super.paintComponent(g);

        // Desenha a imagem, passando sua posição e seu tamanho.
        g.drawImage(image, 0, 10, 221, 77, this);

        // Desenha um quadrado cheio.
        g.setColor(new Color(light.getR(), light.getG(), light.getB()));
        g.fillOval(206, 40, 15, 15);

        // Linha necessária para evitar atrasos
        // de renderização em sistemas Linux.
        getToolkit().sync();
    }
}
