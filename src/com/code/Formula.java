package com.code;

import java.util.Stack;

/**
 * 算式类，用于计算器输入算式时的缓存
 * <p>基类是class:MyStack
 * @author NorwegianForest
 * @version 1.0
 */
public class Formula extends MyStack{
    private Stack history;
    private Stack reverse;
    private int length;

    public Formula() {
        super();
        history = new Stack();
        reverse = new Stack();
        length = 0;
    }

    public Formula(String str) {
        super(str);
        history = new Stack();
        reverse = new Stack();
        length = 0;
    }

    /**
     * 保存历史算式，最多10条
     * @param formula 算式字符串
     */
    public void setHistory(String formula) {
        history.push(formula);
        if (length < 10) {
            length ++;
        }
    }

    /**
     * 获取历史算式堆栈顶层字符串
     * @return 算式字符串
     */
    public String getHistoryTop() {
        reverse.push(history.pop());
        return reverse.peek().toString();
    }

    /**
     * 将算式重新压回history堆栈，历史算式获取完成后，必须调用此方法
     */
    public void back() {
        while (!reverse.empty()) {
            history.push(reverse.pop());
        }
    }

    public int getLength() {
        return length;
    }

    @Override
    public void init() {
        while (!stack.empty()) {
            stack.pop();
        }
    }

    /**
     * 重写基类class:MyStack的getJoin方法
     * <p>从栈底到顶每层字符串以空格符连接为一个字符串</p>
     * <p>以便于class:FormulaCal的算式计算</p>
     * @return 连接字符串
     */
    @Override
    public String getJoin() {
        String join = null;
        Stack reverse = new Stack();
        while (!stack.empty()){
            reverse.push(stack.pop());
        }
        while (!reverse.empty()){
            stack.push(reverse.peek());
            if (join == null) {
                join = reverse.pop().toString();
            } else {
                join = join + " " + reverse.pop().toString();
            }
        }
        return join;
    }
}
