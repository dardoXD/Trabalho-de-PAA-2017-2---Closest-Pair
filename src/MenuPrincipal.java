import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class MenuPrincipal extends JPanel implements ActionListener {
    private JButton forcaBruta;
    private JButton closestPair;
    private JButton sair;

    public MenuPrincipal() {
        forcaBruta = new JButton("Força Bruta");
        this.add(forcaBruta);
        forcaBruta.addActionListener(this);
        closestPair = new JButton("Closest Pair");
        add(closestPair);
        closestPair.addActionListener(this);
        sair = new JButton("Sair");
        add(sair);
        sair.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        Color color = getBackground();
        if (source == forcaBruta) {
            color = Color.yellow;
        } else if (source == closestPair) {
            color = Color.blue;
        } else if (source == sair) {
            color = Color.red;
        }
        setBackground(color);
        repaint();
    }
}
