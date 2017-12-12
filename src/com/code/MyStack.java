package com.code;

import java.util.Stack;

/**
 * 特殊堆栈类（并没有继承Stack类）
 * <p>扩展了堆栈的功能，可以直接操作堆栈底部<br>
 *     例如leftPop()和leftPush(String str)方法</p>
 * <p>作为class:Number和class:Formula的基类</p>
 * @author NorwegianForest
 * @version 1.0
 */
public class MyStack {
    public Stack stack;

    MyStack(){
        stack = new Stack();
    }

    MyStack(String str){
        stack = new Stack();
        stack.push(str);
    }

    public java.util.Stack getStack() {
        return stack;
    }

    public String peek() {
        return stack.peek().toString();
    }

    /**
     * 初始化方法，清空堆栈并将字符串"0"压入堆栈
     */
    public void init() {
        while (!stack.empty()) {
            stack.pop();
        }
        stack.push("0");
    }

    /**
     * 初始幅值方法，清空堆栈，并压入该参数
     * @param str 压入堆栈的字符串
     */
    public void setSingle(String str) {
        while (!stack.empty()) {
            stack.pop();
        }
        stack.push(str);
    }

    public String pop() {
        return stack.pop().toString();
    }

    /**
     * 栈底层出栈
     * @return 栈底字符串
     */
    public String leftPop() {
        String bottom = null;
        Stack reverse = new Stack();
        while (!stack.empty()){
            reverse.push(stack.pop());
        }
        bottom = reverse.pop().toString();
        while (!reverse.empty()) {
            stack.push(reverse.pop());
        }
        return bottom;
    }

    public void push(String str) {
        stack.push(str);
    }

    /**
     * 将参数压入栈底
     * @param str 压入栈底的字符串
     */
    public void leftPush(String str) {
        Stack reverse = new Stack();
        while (!stack.empty()){
            reverse.push(stack.pop());
        }
        reverse.push(str);
        while (!reverse.empty()) {
            stack.push(reverse.pop());
        }
    }

    /**
     * 判断堆栈是否只有一层
     * @return true:堆栈只有一层
     */
    public boolean isSingle() {
        String top = stack.pop().toString();
        if (stack.empty()) {
            stack.push(top);
            return true;
        } else {
            stack.push(top);
            return false;
        }
    }

    /**
     * 获得堆栈从低到顶每层字符串的连接值
     * @return 连接后的字符串
     */
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
                join = join + reverse.pop().toString();
            }
        }

        return join;
    }
}
