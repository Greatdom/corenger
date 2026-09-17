package com.wddyxd.generator;


import com.wddyxd.model.Expr;
import com.wddyxd.model.Fraction;
import com.wddyxd.model.Question;

import java.util.List;

/**
 * &#064program: corenger
 * &#064description: 题目生成器,告诉它范围 r 和数量 n，它给你 n 道合法且不重复的题
 * &#064author: black-cat
 * &#064create: 2026-09-17 15:03
 **/

public class QuestionGenerator {
    private final int range;

    public QuestionGenerator(int range) {
        if (range < 1) throw new IllegalArgumentException("范围必须 >= 1");
        this.range = range;
    }

    /** 生成 n 道题，保证不重复。 */
    public List<Question> generate(int n) { return null; } // TODO

    /** 生成一道合法题。 */
    public Question generateOne() { return null; } // TODO

    /** 随机生成一个操作数，范围 [0, r)。 */
    private Fraction randomOperand() { return null; } // TODO

    /** 随机返回 "+", "-", "*", "/"。 */
    private String randomOperator() { return null; } // TODO

    /** 随机生成表达式树，最多 maxOperators 个运算符。 */
    private Expr buildExpression(int maxOperators) { return null; } // TODO

    /** 校验：运算符 <= 3，无负数中间结果，除法结果是真分数。 */
    private boolean isValid(Expr expr) { return false; } // TODO
}