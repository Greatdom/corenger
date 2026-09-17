package com.wddyxd.model;


/**
 * &#064program: corenger
 * &#064description: 一道完整题目：表达式 + 答案 + 查重签名
 * &#064author: black-cat
 * &#064create: 2026-09-17 14:58
 **/

public class Question {
    private final Expr expr;
    private final Fraction answer;
    private final String signature;

    public Question(Expr expr, Fraction answer, String signature) {
        this.expr = expr;
        this.answer = answer;
        this.signature = signature;
    }

    public Expr getExpr() { return expr; }
    public Fraction getAnswer() { return answer; }
    public String getSignature() { return signature; }

    /** 写入 Exercises.txt 的一行："1/6 + 1/8 = "。 */
    public String exerciseText() {
        return expr.toDisplayString() + " = ";
    }

    /** 写入 Answers.txt 的一行："7/24"。 */
    public String answerText() {
        return answer.toDisplayString();
    }
}