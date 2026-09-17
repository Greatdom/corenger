package com.wddyxd.model;


/**
 * &#064program: corenger
 * &#064description: 实现 Expr,二元运算节点，左右各一个子表达式
 * &#064author: black-cat
 * &#064create: 2026-09-17 14:57
 **/

public class BinaryExpr implements Expr {
    private final String op;     // "+", "-", "*", "/"
    private final Expr left;
    private final Expr right;

    public BinaryExpr(String op, Expr left, Expr right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    @Override
    public Fraction evaluate() {
        Fraction l = left.evaluate();
        Fraction r = right.evaluate();
        switch (op) {
            case "+": return l.add(r);
            case "-": return l.sub(r);
            case "*": return l.mul(r);
            case "/": return l.div(r);
            default: throw new IllegalStateException("未知运算符: " + op);
        }
    }

    @Override
    public String toDisplayString() { return ""; } // TODO

    @Override
    public int operatorCount() {
        return 1 + left.operatorCount() + right.operatorCount();
    }

    @Override
    public String canonicalKey() { return ""; } // TODO
}