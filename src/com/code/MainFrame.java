package com.code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static javax.swing.SwingConstants.RIGHT;

/**
 * 计算器GUI窗口类
 * <p>实现按钮输入运算符和数字，得出计算结果，并记录历史算式</p>
 * @author NorwegianForest
 * @version 1.0
 */
public class MainFrame extends JFrame implements ActionListener, KeyListener {
    private JPanel leftPanel, rightPanel;
    private JPanel dispPanel, keyPanel;
    private JPanel historyPanel;
    private JLabel formulaLabel;
    private JLabel numberLabel;
    private JLabel[] historyLabel;
    private JButton[] keyButton;
    private String[] buttonStr = {"(", ")", "√", "sqrt()", "CE", "C", "DEL", "/", "7", "8", "9", "*", "4", "5", "6",
    "-", "1", "2", "3", "+", "±", "0", ".", "="};
    private Number number;
    private Formula formula;

    MainFrame(){
        number = new Number("0");
        formula = new Formula();

        formulaLabel = new JLabel(formula.getJoin(), RIGHT);
        formulaLabel.setFont(new Font("Malgun Gothic", 0, 35));
        numberLabel = new JLabel(number.getJoin(), RIGHT);
        numberLabel.setFont(new Font("Malgun Gothic", 1, 55));
        dispPanel = new JPanel();
        dispPanel.setLayout(new GridLayout(2, 1));
        dispPanel.add(formulaLabel);
        dispPanel.add(numberLabel);

        keyPanel = new JPanel();
        keyPanel.setLayout(new GridLayout(6, 4));
        keyButton = new JButton[24];
        int i = 0;
        while (i < buttonStr.length){
            keyButton[i] = new JButton(buttonStr[i]);
            keyButton[i].setFont(new Font("Maalgun Gothic", 1, 25));
            keyButton[i].addActionListener(this);
            keyButton[i].addKeyListener(this);
            keyPanel.add(keyButton[i++]);
        }

        leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add("North", dispPanel);
        leftPanel.add("Center", keyPanel);

        historyPanel = new JPanel();
        historyPanel.setLayout(new GridLayout(11, 1));
        historyLabel = new JLabel[11];
        historyLabel[0] = new JLabel("  History          ");
        historyLabel[0].setFont(new Font("Malgun Gothic", 1, 35));
        historyPanel.add(historyLabel[0]);
        for (int j = 1; j <= 10; j++){
            historyLabel[j] = new JLabel("", RIGHT);
            historyLabel[j].setFont(new Font("Malgun Gothic", 0, 20));
            historyPanel.add(historyLabel[j]);
        }

        rightPanel = new JPanel();
        rightPanel.add(historyPanel);

        int width = 730;
        int height = 600;
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
        this.setBounds(x, y, width, height);

        this.setLayout(new BorderLayout());
        this.add("Center", leftPanel);
        this.add("East", rightPanel);
        this.setTitle("Calculator");
        this.setVisible(true);
    }

    private void pushAndSetNumber(String num) {
        number.push(num);
        numberLabel.setText(number.getJoin());
    }

    private void pushAndSetOperator(String operator) {
        if (!"(".equals(operator)) {
            formula.push(number.getJoin());
        }
        formula.push(operator);
        formulaLabel.setText(formula.getJoin());
        number.init();
        numberLabel.setText(number.getJoin());
    }

    private void enterAndEqual() {
        if (!formula.peek().equals(")")) {
            formula.push(number.getJoin());
        }
        FormulaCal c = new FormulaCal(formula.getJoin());
        number.setResult(c.getResult());
        String historyStr = formula.getJoin() + " = " + number.getJoin();
        numberLabel.setText(number.getJoin());
        formula.init();
        formulaLabel.setText(formula.getJoin());

        formula.setHistory(historyStr);
        for (int i = 1; i <= formula.getLength(); i ++) {
            historyLabel[i].setText(formula.getHistoryTop());
        }
        formula.back();
    }

    @Override
    public void actionPerformed(ActionEvent e){
       if (e.getSource() == keyButton[8]) {
           pushAndSetNumber("7");
       } else if (e.getSource() == keyButton[9]) {
           pushAndSetNumber("8");
       } else if (e.getSource() == keyButton[10]) {
           pushAndSetNumber("9");
       } else if (e.getSource() == keyButton[12]) {
           pushAndSetNumber("4");
       } else if (e.getSource() == keyButton[13]) {
           pushAndSetNumber("5");
       } else if (e.getSource() == keyButton[14]) {
           pushAndSetNumber("6");
       } else if (e.getSource() == keyButton[16]) {
           pushAndSetNumber("1");
       } else if (e.getSource() == keyButton[17]) {
           pushAndSetNumber("2");
       } else if (e.getSource() == keyButton[18]) {
           pushAndSetNumber("3");
       } else if (e.getSource() == keyButton[20]) {
           pushAndSetNumber("±");
       } else if (e.getSource() == keyButton[21]) {
           pushAndSetNumber("0");
       } else if (e.getSource() == keyButton[22]) {
           pushAndSetNumber(".");
       } else if (e.getSource() == keyButton[6]) {
           number.backSpace();
           numberLabel.setText(number.getJoin());
       } else if (e.getSource() == keyButton[19]) {
           pushAndSetOperator("+");
       } else if (e.getSource() == keyButton[15]) {
           pushAndSetOperator("-");
       } else if (e.getSource() == keyButton[11]) {
           pushAndSetOperator("*");
       } else if (e.getSource() == keyButton[7]) {
           pushAndSetOperator("/");
       } else if (e.getSource() == keyButton[0]) {
           pushAndSetOperator("(");
       } else if (e.getSource() == keyButton[1]) {
           pushAndSetOperator(")");
       } else if (e.getSource() == keyButton[23]) {
           enterAndEqual();
       } else if (e.getSource() == keyButton[5]) {
           number.init();
           formula.init();
           numberLabel.setText(number.getJoin());
           formulaLabel.setText(formula.getJoin());
       }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_NUMPAD0 || e.getKeyCode() == KeyEvent.VK_0) {
            pushAndSetNumber("0");
        } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD1 || e.getKeyCode() == KeyEvent.VK_1) {
            pushAndSetNumber("1");
        } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD2 || e.getKeyCode() == KeyEvent.VK_2) {
            pushAndSetNumber("2");
        } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD3 || e.getKeyCode() == KeyEvent.VK_3) {
            pushAndSetNumber("3");
        } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD4 || e.getKeyCode() == KeyEvent.VK_4) {
            pushAndSetNumber("4");
        } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD5 || e.getKeyCode() == KeyEvent.VK_5) {
            pushAndSetNumber("5");
        } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD6 || e.getKeyCode() == KeyEvent.VK_6) {
            pushAndSetNumber("6");
        } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD7 || e.getKeyCode() == KeyEvent.VK_7) {
            pushAndSetNumber("7");
        } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD8 || e.getKeyCode() == KeyEvent.VK_8) {
            pushAndSetNumber("8");
        } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD9 || e.getKeyCode() == KeyEvent.VK_9) {
            pushAndSetNumber("9");
        } else if (e.getKeyCode() == KeyEvent.VK_ADD) {
            pushAndSetOperator("+");
        } else if (e.getKeyCode() == KeyEvent.VK_MINUS || e.getKeyCode() == KeyEvent.VK_SUBTRACT) {
            pushAndSetOperator("-");
        } else if (e.getKeyCode() == KeyEvent.VK_MULTIPLY) {
            pushAndSetOperator("*");
        } else if (e.getKeyCode() == KeyEvent.VK_SLASH || e.getKeyCode() == KeyEvent.VK_DIVIDE) {
            pushAndSetOperator("/");
        } else if (e.getKeyCode() == KeyEvent.VK_PERIOD || e.getKeyCode() == KeyEvent.VK_DECIMAL) {
            pushAndSetNumber(".");
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            enterAndEqual();
        } else {
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
