package primerParcial;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class Calculadora_13 extends JFrame implements ActionListener, KeyListener {

    private JButton btnIgual;
    private JButton btnC;
    private JButton btn5;
    private JButton btn6;
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btn0;
    private JButton btnPunto;
    private JButton btnDivision;
    private JButton btnMultiplicacion;
    private JButton btnRestar;
    private JButton btn7;
    private JButton btn8;
    private JButton btn9;
    private JButton btnSumar;
    private JButton btn4;
    private JPanel panelBotones;
    private JPanel panelPantalla;
    private JTextField textPantalla;
    private Font fuenteBotones;
    private Font fuentePantalla;
    private String operador;
    private double numero1, numero2, resultado;

    public Calculadora_13() {
        initComponents();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setResizable(false);
        setSize(400, 500);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        panelPantalla = new JPanel();
        textPantalla = new JTextField();
        panelBotones = new JPanel();
        btnC = new JButton();
        btnDivision = new JButton();
        btnMultiplicacion = new JButton();
        btnRestar = new JButton();
        btn7 = new JButton();
        btn8 = new JButton();
        btn9 = new JButton();
        btnSumar = new JButton();
        btn4 = new JButton();
        btn5 = new JButton();
        btn6 = new JButton();
        btn1 = new JButton();
        btn2 = new JButton();
        btn3 = new JButton();
        btnIgual = new JButton();
        btn0 = new JButton();
        btnPunto = new JButton();
        fuenteBotones = new Font("Arial", Font.PLAIN, 20);
        fuentePantalla = new Font("Arial", Font.BOLD, 60);

        panelPantalla.setLayout(new BorderLayout());
        textPantalla.setText("");
        textPantalla.setHorizontalAlignment(SwingConstants.RIGHT);
        textPantalla.setFont(fuentePantalla);
        textPantalla.setPreferredSize(new Dimension(500, 100));
        textPantalla.setEditable(false);
        textPantalla.addKeyListener(this);
        panelPantalla.add(textPantalla, BorderLayout.CENTER);

        panelBotones.setLayout(new GridBagLayout());

        btnC.setText("C");
        btnC.setFont(fuenteBotones);
        btnC.addActionListener(this);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        panelBotones.add(btnC, gridBagConstraints);

        btnDivision.setText("/");
        btnDivision.setFont(fuenteBotones);
        btnDivision.addActionListener(this);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        panelBotones.add(btnDivision, gridBagConstraints);

        btnMultiplicacion.setText("*");
        btnMultiplicacion.setFont(fuenteBotones);
        btnMultiplicacion.addActionListener(this);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        panelBotones.add(btnMultiplicacion, gridBagConstraints);

        btnRestar.setText("-");
        btnRestar.setFont(fuenteBotones);
        btnRestar.addActionListener(this);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        panelBotones.add(btnRestar, gridBagConstraints);

        btn7.setText("7");
        btn7.setFont(fuenteBotones);
        btn7.addActionListener(this);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        panelBotones.add(btn7, gridBagConstraints);

        btn8.setText("8");
        btn8.setFont(fuenteBotones);
        btn8.addActionListener(this);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        panelBotones.add(btn8, gridBagConstraints);

        btn9.setText("9");
        btn9.setFont(fuenteBotones);
        btn9.addActionListener(this);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        panelBotones.add(btn9, gridBagConstraints);

        btnSumar.setText("+");
        btnSumar.setFont(fuenteBotones);
        btnSumar.addActionListener(this);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        panelBotones.add(btnSumar, gridBagConstraints);

        btn4.setText("4");
        btn4.setFont(fuenteBotones);
        btn4.addActionListener(this);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        panelBotones.add(btn4, gridBagConstraints);

        btn5.setText("5");
        btn5.setFont(fuenteBotones);
        btn5.addActionListener(this);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        panelBotones.add(btn5, gridBagConstraints);

        btn6.setText("6");
        btn6.setFont(fuenteBotones);
        btn6.addActionListener(this);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        panelBotones.add(btn6, gridBagConstraints);

        btn1.setText("1");
        btn1.setFont(fuenteBotones);
        btn1.addActionListener(this);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        panelBotones.add(btn1, gridBagConstraints);

        btn2.setText("2");
        btn2.setFont(fuenteBotones);
        btn2.addActionListener(this);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        panelBotones.add(btn2, gridBagConstraints);

        btn3.setText("3");
        btn3.setFont(fuenteBotones);
        btn3.addActionListener(this);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        panelBotones.add(btn3, gridBagConstraints);

        btnIgual.setText("=");
        btnIgual.setFont(fuenteBotones);
        btnIgual.addActionListener(this);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        panelBotones.add(btnIgual, gridBagConstraints);

        btn0.setText("0");
        btn0.setFont(fuenteBotones);
        btn0.addActionListener(this);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        panelBotones.add(btn0, gridBagConstraints);

        btnPunto.setText(".");
        btnPunto.setFont(fuenteBotones);
        btnPunto.addActionListener(this);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        panelBotones.add(btnPunto, gridBagConstraints);

        getContentPane().add(panelBotones, java.awt.BorderLayout.CENTER);
        getContentPane().add(panelPantalla, BorderLayout.PAGE_START);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnC) {
            textPantalla.setText("");
        } else if (e.getSource() == btnDivision) {
            operador = "/";
            numero1 = Double.parseDouble(textPantalla.getText());
            textPantalla.setText("");
        } else if (e.getSource() == btnMultiplicacion) {
            operador = "*";
            numero1 = Double.parseDouble(textPantalla.getText());
            textPantalla.setText("");
        } else if (e.getSource() == btnRestar) {
            operador = "-";
            numero1 = Double.parseDouble(textPantalla.getText());
            textPantalla.setText("");
        } else if (e.getSource() == btnSumar) {
            operador = "+";
            numero1 = Double.parseDouble(textPantalla.getText());
            textPantalla.setText("");
        } else if (e.getSource() == btnIgual) {
            numero2 = Double.parseDouble(textPantalla.getText());
            switch (operador) {
                case "+":
                    resultado = numero1 + numero2;
                    break;
                case "-":
                    resultado = numero1 - numero2;
                    break;
                case "*":
                    resultado = numero1 * numero2;
                    break;
                case "/":
                    if (numero2 != 0) {
                        resultado = numero1 / numero2;
                    } else {
                        textPantalla.setText("Error: División por cero");
                        return;
                    }
                    break;
            }
            textPantalla.setText(String.valueOf(resultado));
        } else {
            JButton btn = (JButton) e.getSource();
            String valorBoton = btn.getText();
            String valorPantalla = textPantalla.getText();
            textPantalla.setText(valorPantalla + valorBoton);
        }
    }

    public void keyPressed(KeyEvent e) {
        char keyChar = e.getKeyChar();
        if (Character.isDigit(keyChar)) {
            textPantalla.setText(textPantalla.getText() + keyChar);
        } else {
            switch (keyChar) {
                case '+':
                    operador = "+";
                    numero1 = Double.parseDouble(textPantalla.getText());
                    textPantalla.setText("");
                    break;
                case '-':
                    operador = "-";
                    numero1 = Double.parseDouble(textPantalla.getText());
                    textPantalla.setText("");
                    break;
                case '*':
                    operador = "*";
                    numero1 = Double.parseDouble(textPantalla.getText());
                    textPantalla.setText("");
                    break;
                case '/':
                    operador = "/";
                    numero1 = Double.parseDouble(textPantalla.getText());
                    textPantalla.setText("");
                    break;
                case '.':
                    textPantalla.setText(textPantalla.getText() + ".");
                    break;
                case '=':
                    if (operador != null) {
                        numero2 = Double.parseDouble(textPantalla.getText());
                        switch (operador) {
                            case "+":
                                resultado = numero1 + numero2;
                                break;
                            case "-":
                                resultado = numero1 - numero2;
                                break;
                            case "*":
                                resultado = numero1 * numero2;
                                break;
                            case "/":
                                if (numero2 != 0) {
                                    resultado = numero1 / numero2;
                                } else {
                                    textPantalla.setText("Error: División por cero");
                                    return;
                                }
                                break;
                        }
                        textPantalla.setText(String.valueOf(resultado));
                    }
                    break;
                case 'C':
                case 'c':
                    textPantalla.setText("");
                    break;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public static void main(String args[]) {
        EventQueue.invokeLater(() -> {
            new Calculadora_13().setVisible(true);
        });
    }
}
