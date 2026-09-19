package com.wddyxd.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    /** 按运算优先级输出表达式，避免改变原表达式树的计算顺序。 */
    @Override
    public String toDisplayString() {
        return displayChild(left, false) + " " + displayOperator() + " "
                + displayChild(right, true);
    }

    @Override
    public int operatorCount() {
        return 1 + left.operatorCount() + right.operatorCount();
    }

    /**
     * 为加法和乘法收集全部同类项并排序，从而消除交换律和结合律造成的差异。
     * 减法和除法保留左右顺序，避免把不等价的表达式误判为重复。
     */
    @Override
    public String canonicalKey() {
        if ("+".equals(op) || "*".equals(op)) {
            List<String> terms = new ArrayList<>();
            collectAssociativeTerms(this, op, terms);
            Collections.sort(terms);
            return "(" + op + " " + String.join(" ", terms) + ")";
        }
        return "(" + op + " " + left.canonicalKey() + " " + right.canonicalKey() + ")";
    }

    private String displayOperator() {
        if ("*".equals(op)) return "×";
        if ("/".equals(op)) return "÷";
        return op;
    }

    private String displayChild(Expr child, boolean rightHandSide) {
        if (!(child instanceof BinaryExpr)) {
            return child.toDisplayString();
        }

        BinaryExpr binary = (BinaryExpr) child;
        // 低优先级子表达式必须加括号；右侧同级减法/除法也必须保留括号。
        int parentPrecedence = precedence(op);
        int childPrecedence = precedence(binary.op);
        boolean needsParentheses = childPrecedence < parentPrecedence;

        if (childPrecedence == parentPrecedence && rightHandSide) {
            needsParentheses = !op.equals(binary.op)
                    || "-".equals(op)
                    || "/".equals(op);
        }

        String value = binary.toDisplayString();
        return needsParentheses ? "(" + value + ")" : value;
    }

    private static int precedence(String operator) {
        return ("+".equals(operator) || "-".equals(operator)) ? 1 : 2;
    }

    private static void collectAssociativeTerms(Expr expr, String operator, List<String> terms) {
        if (expr instanceof BinaryExpr) {
            BinaryExpr binary = (BinaryExpr) expr;
            if (operator.equals(binary.op)) {
                collectAssociativeTerms(binary.left, operator, terms);
                collectAssociativeTerms(binary.right, operator, terms);
                return;
            }
        }
        terms.add(expr.canonicalKey());
    }
}