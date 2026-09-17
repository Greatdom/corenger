package com.wddyxd.model;


/**
 * &#064program: corenger
 * &#064description: 实现 Expr,数字节点，表达式树的叶子
 * &#064author: black-cat
 * &#064create: 2026-09-17 14:56
 **/

public class NumberExpr implements Expr {
    private final Fraction value;

    public NumberExpr(Fraction value) {
        this.value = value;
    }

    @Override
    public Fraction evaluate() { return value; }

    @Override
    public String toDisplayString() { return value.toDisplayString(); }

    @Override
    public int operatorCount() { return 0; }

    @Override
    public String canonicalKey() { return value.toDisplayString(); }
}