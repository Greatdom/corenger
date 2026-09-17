package com.wddyxd.model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * &#064program: corenger
 * &#064description: 数字叶子节点单元测试
 * &#064author: black-cat
 * &#064create: 2026-09-17 18:58
 **/


class NumberExprTest {

    // 测试点：数字表达式求值应返回自身包装的分数
    @Test
    void shouldEvaluateToItsValue() {
        NumberExpr expr = new NumberExpr(new Fraction(3, 5));
        assertEquals(new Fraction(3, 5), expr.evaluate());
    }

    // 测试点：数字表达式的显示字符串应等于分数的显示字符串
    @Test
    void shouldDisplayValue() {
        assertEquals("3/5", new NumberExpr(new Fraction(3, 5)).toDisplayString());
    }

    // 测试点：数字叶子节点不包含运算符，operatorCount 应为 0
    @Test
    void shouldHaveZeroOperators() {
        assertEquals(0, new NumberExpr(new Fraction(1, 2)).operatorCount());
    }

    // 测试点：数字表达式的查重签名应等于其显示字符串
    @Test
    void canonicalKeyShouldBeDisplayString() {
        assertEquals("3/5", new NumberExpr(new Fraction(3, 5)).canonicalKey());
    }
}