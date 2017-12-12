package com.code;

/**
 * 数字类，用于计算器输入数字时的缓存
 * <p>基类是class:MyStack
 * @author NorwegianForest
 * @version 1.0
 */
public class Number extends MyStack {
    private boolean isNegative = false;

    Number(String str){
        super(str);
    }

    /**
     * 重写class:MyStack的push方法
     * <p>加入了数字的书写规范</p>
     * <p>数字的正负号书写和小数点书写</p>
     * @param str 写入字符（数字或小数点或正负号）
     */
    @Override
    public void push(String str) {
        if (str.equals(".")) {
            if (stack.search(".") == -1) {
                stack.push(str);
            }
        } else if (str.equals("±")) {
            if (isNegative) {
                this.leftPop();
                isNegative = false;
            } else {
                this.leftPush("-");
                isNegative = true;
            }
        } else {
            if (this.isSingle() && stack.peek().toString().equals("0")) {
                stack.pop();
            }
            stack.push(str);
        }
    }

    /**
     * 退格实现方法
     */
    public void backSpace() {
        if (this.isSingle() && stack.peek().toString().equals("0")) {

        } else if (this.isSingle()) {
            stack.pop();
            stack.push("0");
        } else {
            stack.pop();
        }
    }
}
