package com.code;

import java.util.Stack;

/**
 * 算式计算类
 * <p>
 * 算式规范：所有运算符（包括括号）前后必须加空格符
 * @author NorwegianForest
 * @version 1.0
 * @see java.util.Stack
 */
public class FormulaCal {
    private String formula;
    private String[] formulaSplit;
    private String result;
    private Stack number;
    private Stack operator;
    private Stack formulaStack;

    /**
     * 构造方法
     * <p>以空格符为标记，将算式分解</p>
     * @param formula 算式字符串——所有运算符（包括括号）前后必须加空格
     */
    FormulaCal(String formula){
        this.formula = formula;
        this.formulaSplit = this.formula.split(" ");
        this.result = "null";
        this.number = new Stack();
        this.operator = new Stack();
        this.formulaStack = new Stack();
        calculateFormula();
    }

    /**
     * 计算算式方法
     * <p>通过算式堆栈、运算符堆栈和操作数堆栈实现加减乘除及括号的运算</p>
     */
    private void calculateFormula(){
        pushFormula();
        while (true){
            if (!formulaStack.empty()){
                String unknow = formulaStack.peek().toString();
                if (unknow.equals("+") || unknow.equals("-") || unknow.equals("*") || unknow.equals("/")){
                    if (operator.empty()){
                        operator.push(formulaStack.pop());
                    } else if ((unknow.equals("*") || unknow.equals("/")) && (operator.peek().toString().equals("+") || operator.peek().toString().equals("-"))){
                        operator.push(formulaStack.pop());
                    } else if (operator.peek().toString().equals("(")){
                        operator.push(formulaStack.pop());
                    } else {
                        number.push(singleCalculate());
                        if (number.peek().toString().equals("Infinity")){
                            break;
                        }
                    }
                } else if (unknow.equals("(")){
                    operator.push(formulaStack.pop());
                } else if (unknow.equals(")")){
                    if (operator.peek().toString().equals("(")){
                        operator.pop();
                        formulaStack.pop();
                    } else {
                        number.push(singleCalculate());
                        if (number.peek().toString().equals("Infinity")){
                            break;
                        }
                    }
                } else {
                    number.push(formulaStack.pop());
                }
            } else {
                number.push(singleCalculate());
                if (operator.empty())
                    break;
            }
        }
        result = number.pop().toString();
    }

    /**
     * 取出number堆栈的栈顶两层和operator堆栈的栈顶一层，进行单次运算
     * @return 运算结果字符串
     */
    private String singleCalculate(){
        String result = null;
        double num1 = Double.parseDouble(number.pop().toString());
        double num2 = Double.parseDouble(number.pop().toString());
        switch (operator.pop().toString()){
            case "+":
                return Double.toString(num2 + num1);
            case "-":
                return Double.toString(num2 - num1);
            case "*":
                return Double.toString(num2 * num1);
            case "/":
                return Double.toString(num2 / num1);
                default:return "null";
        }
    }

    private void pushFormula(){
        popAll();
        int i = formulaSplit.length - 1;
        while (i >= 0){
            formulaStack.push(formulaSplit[i--]);
        }
    }

    private void popAll(){
        while (!formulaStack.empty()){
            formulaStack.pop();
        }
    }

    public String getResult(){
        return this.result;
    }

    public void setFormula(String formula){
        this.formula = formula;
        calculateFormula();
    }

    public String getFormula(){
        return this.formula;
    }

    /**
     * 打印相关参数（测试）
     */
    public void print(){
        System.out.println("formula:" + this.formula);
        System.out.println("formulaSplit:");
        int i = 0;
        while (this.formulaSplit.length > i){
            System.out.println(this.formulaSplit[i++] + "|");
        }
        System.out.println("formulaStack:");
        while (!this.formulaStack.empty()){
            System.out.println(this.formulaStack.pop().toString() + "|");
        }
        System.out.println("result:" + this.result);
    }
}
