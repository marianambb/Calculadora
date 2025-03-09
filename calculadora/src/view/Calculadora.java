package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Calculadora extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Color corBotoes;
    private Color corFundo;
    private Color corLinha;
    private Color corTexto;
    private int x, y;
    private JTextField textFieldDisplay;
    private boolean blackMode = true;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Calculadora frame = new Calculadora();
                    frame.setLocationRelativeTo(frame); // Centraliza a janela
                    frame.setUndecorated(true);
                    frame.setVisible(true); // Visibilidade
                    frame.setOpacity((float) 0.90);
                    frame.setTitle("Calculadora");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Calculadora() {
        // Definindo cores da interface
        corBotoes = new Color(60, 62, 65);
        corFundo = new Color(43, 43, 39);
        corLinha = new Color(38, 38, 35);
        corTexto = Color.white;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 300, 400);
        contentPane = new JPanel();
        contentPane.setBackground(corFundo);
        contentPane.setBorder(null); // null -> Remoção de qualquer borda

        setContentPane(contentPane);
        contentPane.setLayout(null);

        movimentos();
        JLabel btnTema = new JLabel();
        btnTema.setIcon(new ImageIcon(Calculadora.class.getResource("/image/bright.png")));
        btnTema.setBounds(4, 4, 30, 20);
        btnTema.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            }
        });
        contentPane.add(btnTema);

        // Botão de Fechar
        JButton btnFechar = new JButton("x");
        btnFechar.setBounds(270, 0, 40, 19);
        btnFechar.setFont(new Font("Verdana", Font.PLAIN, 18));
        btnFechar.setForeground(new Color(125, 13, 13));
        btnFechar.setFocusPainted(false);
        btnFechar.setBackground(new Color(125, 13, 13));
        btnFechar.setBorder(new LineBorder(corFundo, 2));

        btnFechar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Fecha o programa
            }
        });
        contentPane.add(btnFechar);

        // Display
        textFieldDisplay = new JTextField();
        textFieldDisplay.setBorder(null);
        textFieldDisplay.setEditable(false);
        textFieldDisplay.setFont(new Font("Verdana", Font.PLAIN, 36));
        textFieldDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
        textFieldDisplay.setText("0");
        textFieldDisplay.setForeground(Color.white);
        textFieldDisplay.setBounds(0, 0, 300, 70);
        textFieldDisplay.setBackground(new Color(43, 43, 39));
        contentPane.add(textFieldDisplay);
        textFieldDisplay.setColumns(10);
        textFieldDisplay.setCaretColor(new Color(0, 0, 0, 0));

        // Botões Principais
        JButton btnApagar = criarBotao("C", 3, 70, 75, 62);
        btnApagar.addActionListener(this);
        contentPane.add(btnApagar);

        JButton btnSqrt = criarBotao("√", 76, 70, 75, 62);
        btnSqrt.addActionListener(this);
        contentPane.add(btnSqrt);

        JButton btnPorcentagem = criarBotao("%", 149, 70, 75, 62);
        btnPorcentagem.addActionListener(this);
        contentPane.add(btnPorcentagem);

        JButton btnDivisao = criarBotao("÷", 223, 70, 75, 62);
        btnDivisao.addActionListener(this);
        contentPane.add(btnDivisao);

        JButton btnMultiplicacao = criarBotao("x", 223, 130, 75, 62);
        btnMultiplicacao.addActionListener(this);
        contentPane.add(btnMultiplicacao);

        JButton btnSubtracao = criarBotao("-", 223, 190, 75, 62);
        btnSubtracao.addActionListener(this);
        contentPane.add(btnSubtracao);

        JButton btnAdicao = criarBotao("+", 223, 250, 75, 62);
        btnAdicao.addActionListener(this);
        contentPane.add(btnAdicao);

        JButton btnIgual = criarBotao("=", 223, 310, 75, 62);
        btnIgual.addActionListener(this);
        contentPane.add(btnIgual);

        // Botões Numéricos
        JButton btnZero = criarBotao("0", 3, 310, 150, 62);
        btnZero.addActionListener(this);
        contentPane.add(btnZero);

        JButton btnVirgula = criarBotao(",", 149, 310, 75, 62);
        btnVirgula.addActionListener(this);
        contentPane.add(btnVirgula);

        JButton btn7 = criarBotao("7", 3, 130, 75, 62);
        btn7.addActionListener(this);
        contentPane.add(btn7);

        JButton btn8 = criarBotao("8", 76, 130, 75, 62);
        btn8.addActionListener(this);
        contentPane.add(btn8);

        JButton btn9 = criarBotao("9", 149, 130, 75, 62);
        btn9.addActionListener(this);
        contentPane.add(btn9);

        JButton btn4 = criarBotao("4", 3, 190, 75, 62);
        btn4.addActionListener(this);
        contentPane.add(btn4);

        JButton btn5 = criarBotao("5", 76, 190, 75, 62);
        btn5.addActionListener(this);
        contentPane.add(btn5);

        JButton btn6 = criarBotao("6", 149, 190, 75, 62);
        btn6.addActionListener(this);
        contentPane.add(btn6);

        JButton btn1 = criarBotao("1", 3, 250, 75, 62);
        btn1.addActionListener(this);
        contentPane.add(btn1);

        JButton btn2 = criarBotao("2", 76, 250, 75, 62);
        btn2.addActionListener(this);
        contentPane.add(btn2);

        JButton btn3 = criarBotao("3", 149, 250, 75, 62);
        btn3.addActionListener(this);
        contentPane.add(btn3);

        // Mudança de tema da Calculadora
        for (Component component : contentPane.getComponents()) {
            if (component instanceof JButton) {
                JButton btn = (JButton) component;
                alternarCorBotao(btn);
            }
            if (component instanceof JPanel) {
                for (Component botoesPanel : ((JPanel) component).getComponents()) {
                    if (botoesPanel instanceof JButton) {
                        JButton btn = (JButton) botoesPanel;
                        alternarCorBotao(btn);
                    }
                }
            }
        }

        btnTema.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                blackMode = !blackMode;
                if (blackMode) {
                    btnTema.setIcon(new ImageIcon(Calculadora.class.getResource("/image/bright.png")));
                    corFundo = new Color(43, 43, 39);
                    corBotoes = new Color(60, 62, 65);
                    corTexto = Color.white;
                    corLinha = new Color(38, 38, 35);
                    btnFechar.setForeground(new Color(125, 13, 13));

                    contentPane.setBackground(Color.white);
                    textFieldDisplay.setBackground(corFundo);
                    textFieldDisplay.setForeground(Color.white);

                    for (Component component : contentPane.getComponents()) {
                        if (component instanceof JButton) {
                            JButton btn = (JButton) component;

                            btn.setForeground(corTexto);
                            btn.setBackground(corBotoes);
                            btn.setBorder(new LineBorder(corLinha, 2));
                            btnFechar.setForeground(new Color(125, 13, 13));
                            btnFechar.setBorder(new LineBorder(corFundo, 2));

                            contentPane.setBackground(corFundo);
                        }
                        if (component instanceof JPanel) {
                            for (Component botoesPanel : ((JPanel) component).getComponents()) {
                                if (botoesPanel instanceof JButton) {
                                    JButton btn = (JButton) botoesPanel;
                                    btn.setForeground(corTexto);
                                    btn.setBackground(corBotoes);
                                    btn.setBorder(new LineBorder(corLinha, 2));
                                    contentPane.setBackground(corFundo);
                                }
                            }
                        }
                    }

                } else {
                    btnTema.setIcon(new ImageIcon(Calculadora.class.getResource("/image/dark.png")));
                    corFundo = new Color(195, 229, 205);
                    corBotoes = new Color(195, 229, 205);
                    corTexto = new Color(77, 77, 77);
                    corLinha = new Color(168, 201, 178);
                    contentPane.setBackground(Color.white);
                    textFieldDisplay.setBackground(new Color(195, 229, 205));
                    textFieldDisplay.setForeground(corTexto);

                    for (Component component : contentPane.getComponents()) {
                        if (component instanceof JButton) {
                            JButton btn = (JButton) component;

                            btn.setForeground(corTexto);
                            btn.setBackground(corBotoes);
                            btn.setBorder(new LineBorder(corLinha, 2));
                            btnFechar.setForeground(new Color(125, 13, 13));
                            btnFechar.setBorder(new LineBorder(corFundo, 2));
                            contentPane.setBackground(corFundo);
                        }
                        if (component instanceof JPanel) {
                            for (Component botoesPanel : ((JPanel) component).getComponents()) {
                                if (botoesPanel instanceof JButton) {
                                    JButton btn = (JButton) botoesPanel;
                                    btn.setForeground(corTexto);
                                    btn.setBackground(corBotoes);
                                    btn.setBorder(new LineBorder(corLinha, 2));
                                    contentPane.setBackground(corFundo);
                                }
                            }
                        }
                    }

                }
            }
        });
        repaint();
    }

    private void alternarCorBotao(JButton btn) {
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(corFundo);
            }

            public void mouseExited(MouseEvent e) {
                btn.setBackground(corBotoes);
            }

        });
    }

    private JButton criarBotao(String textoBotao, int x, int y, int width, int height) {
        JButton jButton = new JButton(textoBotao);
        jButton.setHorizontalAlignment(SwingConstants.CENTER);
        jButton.setFont(new Font("Verdana", Font.PLAIN, 30));
        jButton.setForeground(corTexto);
        jButton.setFocusPainted(false);
        jButton.setBackground(corBotoes);
        jButton.setBorder(new LineBorder(corLinha, 2));
        jButton.setBounds(x, y, width, height);

        return jButton;
    }

    private void movimentos() {
        contentPane.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                setLocation(e.getXOnScreen() - x, e.getYOnScreen() - y);
            }
        });
        contentPane.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if ("=".equals(comando)) {
            String expressao = textFieldDisplay.getText();
            double resultado = calcularExpressao(expressao);
            textFieldDisplay.setText(Double.toString(resultado));
        } else if ("C".equalsIgnoreCase(comando)) {
            textFieldDisplay.setText("0");
        } else if ("√".equals(comando)) {
            String expressao = textFieldDisplay.getText();
            double valor = Double.parseDouble(expressao);
            double resultado = Math.sqrt(valor);
            textFieldDisplay.setText(Double.toString(resultado));
        } else if ("%".equals(comando)) {
            String expressao = textFieldDisplay.getText();
            if (!expressao.isEmpty() && !expressao.matches(".*[+\\-x÷].*")) {
                double valor = Double.parseDouble(expressao);
                double resultado = valor / 100;
                textFieldDisplay.setText(Double.toString(resultado));
            } else {
                textFieldDisplay.setText(expressao + comando);
            }
        } else {
            String textoDisplay = textFieldDisplay.getText();
            if (textoDisplay.equals("0")) {
                textoDisplay = "";
            }
            textFieldDisplay.setText(textoDisplay + comando);
        }
    }

    private double calcularExpressao(String expressao) {
        try {
            expressao = expressao.replaceAll("\\s+", "");
            if (expressao.isEmpty()) {
                return 0;
            }

            // Processa porcentagens antes de calcular a expressão
            expressao = processarPorcentagens(expressao);

            Stack<Double> numeros = new Stack<>();
            Stack<Character> operadores = new Stack<>();
            int i = 0;

            while (i < expressao.length()) {
                char c = expressao.charAt(i);

                if (Character.isDigit(c) || c == '.') {
                    StringBuilder sb = new StringBuilder();
                    while (i < expressao.length() && (Character.isDigit(expressao.charAt(i)) || expressao.charAt(i) == '.')) {
                        sb.append(expressao.charAt(i++));
                    }
                    numeros.push(Double.parseDouble(sb.toString()));
                    i--;
                } else if (c == '(') {
                    operadores.push(c);
                } else if (c == ')') {
                    while (operadores.peek() != '(') {
                        numeros.push(aplicarOperacao(operadores.pop(), numeros.pop(), numeros.pop()));
                    }
                    operadores.pop();
                } else if (c == '+' || c == '-' || c == 'x' || c == '÷') {
                    while (!operadores.isEmpty() && precedencia(operadores.peek()) >= precedencia(c)) {
                        numeros.push(aplicarOperacao(operadores.pop(), numeros.pop(), numeros.pop()));
                    }
                    operadores.push(c);
                }
                i++;
            }

            while (!operadores.isEmpty()) {
                numeros.push(aplicarOperacao(operadores.pop(), numeros.pop(), numeros.pop()));
            }

            return numeros.pop();
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    private String processarPorcentagens(String expressao) {
        // Regex para encontrar padrões como "100 + 10%" ou "100 - 10%"
        Pattern pattern = Pattern.compile("(\\d+\\.?\\d*)([+\\-])(\\d+\\.?\\d*)%");
        Matcher matcher = pattern.matcher(expressao);

        while (matcher.find()) {
            double valorBase = Double.parseDouble(matcher.group(1));
            char operador = matcher.group(2).charAt(0);
            double porcentagem = Double.parseDouble(matcher.group(3)) / 100;

            double resultado = 0;
            switch (operador) {
                case '+':
                    resultado = valorBase + (valorBase * porcentagem);
                    break;
                case '-':
                    resultado = valorBase - (valorBase * porcentagem);
                    break;
            }

            // Substitui a expressão original pelo resultado calculado
            expressao = expressao.replace(matcher.group(), Double.toString(resultado));
        }

        return expressao;
    }

    private int precedencia(char operador) {
        switch (operador) {
            case '+':
            case '-':
                return 1;
            case 'x':
            case '÷':
                return 2;
        }
        return -1;
    }

    private double aplicarOperacao(char operador, double b, double a) {
        switch (operador) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case 'x':
                return a * b;
            case '÷':
                if (b == 0) {
                    throw new ArithmeticException("Divisão por zero");
                }
                return a / b;
        }
        return 0;
    }
}