package com.code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.SwingConstants.RIGHT;

/**
 * 计算器GUI窗口类
 * <p>实现按钮输入运算符和数字，得出计算结果，并记录历史算式</p>
 * @author NorwegianForest
 * @version 1.0
 */
public class MainFrame extends JFrame implements ActionListener{
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

    @Override
    public void actionPerformed(ActionEvent e){
       if (e.getSource() == keyButton[8]) {
            number.push("7");
            numberLabel.setText(number.getJoin());
       } else if (e.getSource() == keyButton[9]) {
           number.push("8");
           numberLabel.setText(number.getJoin());
       } else if (e.getSource() == keyButton[10]) {
           number.push("9");
           numberLabel.setText(number.getJoin());
       } else if (e.getSource() == keyButton[12]) {
           number.push("4");
           numberLabel.setText(number.getJoin());
       } else if (e.getSource() == keyButton[13]) {
           number.push("5");
           numberLabel.setText(number.getJoin());
       } else if (e.getSource() == keyButton[14]) {
           number.push("6");
           numberLabel.setText(number.getJoin());
       } else if (e.getSource() == keyButton[16]) {
           number.push("1");
           numberLabel.setText(number.getJoin());
       } else if (e.getSource() == keyButton[17]) {
           number.push("2");
           numberLabel.setText(number.getJoin());
       } else if (e.getSource() == keyButton[18]) {
           number.push("3");
           numberLabel.setText(number.getJoin());
       } else if (e.getSource() == keyButton[20]) {
           number.push("±");
           numberLabel.setText(number.getJoin());
       } else if (e.getSource() == keyButton[21]) {
           number.push("0");
           numberLabel.setText(number.getJoin());
       } else if (e.getSource() == keyButton[22]) {
           number.push(".");
           numberLabel.setText(number.getJoin());
       } else if (e.getSource() == keyButton[6]) {
           number.backSpace();
           numberLabel.setText(number.getJoin());
       } else if (e.getSource() == keyButton[19]) {
           formula.push(number.getJoin());
           formula.push("+");
           formulaLabel.setText(formula.getJoin());
           number.init();
           numberLabel.setText(number.getJoin());
       } else if (e.getSource() == keyButton[15]) {
           formula.push(number.getJoin());
           formula.push("-");
           formulaLabel.setText(formula.getJoin());
           number.init();
           numberLabel.setText(number.getJoin());
       } else if (e.getSource() == keyButton[11]) {
           formula.push(number.getJoin());
           formula.push("*");
           formulaLabel.setText(formula.getJoin());
           number.init();
           numberLabel.setText(number.getJoin());
       } else if (e.getSource() == keyButton[7]) {
           formula.push(number.getJoin());
           formula.push("/");
           formulaLabel.setText(formula.getJoin());
           number.init();
           numberLabel.setText(number.getJoin());
       } else if (e.getSource() == keyButton[0]) {
           formula.push("(");
           formulaLabel.setText(formula.getJoin());
           number.init();
           numberLabel.setText(number.getJoin());
       } else if (e.getSource() == keyButton[1]) {
           formula.push(number.getJoin());
           formula.push(")");
           formulaLabel.setText(formula.getJoin());
           number.init();
           numberLabel.setText(number.getJoin());
       } else if (e.getSource() == keyButton[23]) {
           if (!formula.peek().equals(")")) {
               formula.push(number.getJoin());
           }
           FormulaCal c = new FormulaCal(formula.getJoin());
           number.setSingle(c.getResult());
           String historyStr = formula.getJoin() + " = " + number.getJoin();
           numberLabel.setText(number.getJoin());
           formula.init();
           formulaLabel.setText(formula.getJoin());

           formula.setHistory(historyStr);
           for (int i = 1; i <= formula.getLength(); i ++) {
               historyLabel[i].setText(formula.getHistoryTop());
           }
           formula.back();
       } else if (e.getSource() == keyButton[5]) {
           number.init();
           formula.init();
           numberLabel.setText(number.getJoin());
           formulaLabel.setText(formula.getJoin());
       }
    }
}
