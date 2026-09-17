package com.wddyxd.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * &#064program: corenger
 * &#064description: 二元运算节点单元测试
 * &#064author: black-cat
 * &#064create: 2026-09-17 19:04
 **/

class BinaryExprTest {

    // 测试点：加法二元表达式 1/2 + 1/3 = 5/6
    @Test
    void shouldEvaluateAddition() {
        Expr expr = new BinaryExpr(
                "+",
                new NumberExpr(new Fraction(1, 2)),
                new NumberExpr(new Fraction(1, 3))
        );
        assertEquals(new Fraction(5, 6), expr.evaluate());
    }

    // 测试点：减法二元表达式 3/4 - 1/4 = 1/2
    @Test
    void shouldEvaluateSubtraction() {
        Expr expr = new BinaryExpr(
                "-",
                new NumberExpr(new Fraction(3, 4)),
                new NumberExpr(new Fraction(1, 4))
        );
        assertEquals(new Fraction(1, 2), expr.evaluate());
    }

    // 测试点：乘法二元表达式 2/3 * 3/4 = 1/2
    @Test
    void shouldEvaluateMultiplication() {
        Expr expr = new BinaryExpr(
                "*",
                new NumberExpr(new Fraction(2, 3)),
                new NumberExpr(new Fraction(3, 4))
        );
        assertEquals(new Fraction(1, 2), expr.evaluate());
    }

    // 测试点：除法二元表达式 1/2 ÷ 3/4 = 2/3
    @Test
    void shouldEvaluateDivision() {
        Expr expr = new BinaryExpr(
                "/",
                new NumberExpr(new Fraction(1, 2)),
                new NumberExpr(new Fraction(3, 4))
        );
        assertEquals(new Fraction(2, 3), expr.evaluate());
    }

    // 测试点：一个二元运算节点应只包含 1 个运算符
    @Test
    void shouldCountOperators() {
        Expr expr = new BinaryExpr(
                "+",
                new NumberExpr(new Fraction(1, 2)),
                new NumberExpr(new Fraction(1, 3))
        );
        assertEquals(1, expr.operatorCount());
    }

    // 测试点：简单二元表达式的显示字符串应使用空格分隔，如 "1/2 + 1/3"
    @Test
    void shouldDisplayWithSpaces() {
        Expr expr = new BinaryExpr(
                "+",
                new NumberExpr(new Fraction(1, 2)),
                new NumberExpr(new Fraction(1, 3))
        );
        assertEquals("1/2 + 1/3", expr.toDisplayString());
    }

    // 测试点：加法交换律去重，1/2 + 1/3 与 1/3 + 1/2 的 canonicalKey 应相同
    @Test
    void canonicalKeyShouldSupportCommutativeLawForAdd() {
        Expr a = new BinaryExpr(
                "+",
                new NumberExpr(new Fraction(1, 2)),
                new NumberExpr(new Fraction(1, 3))
        );
        Expr b = new BinaryExpr(
                "+",
                new NumberExpr(new Fraction(1, 3)),
                new NumberExpr(new Fraction(1, 2))
        );
        assertEquals(a.canonicalKey(), b.canonicalKey());
    }

    // 测试点：加法结合律去重，(1+2)+3 与 1+(2+3) 的 canonicalKey 应相同
    @Test
    void canonicalKeyShouldSupportAssociativeLawForAdd() {
        Expr a = new BinaryExpr(
                "+",
                new BinaryExpr(
                        "+",
                        new NumberExpr(new Fraction(1, 1)),
                        new NumberExpr(new Fraction(2, 1))
                ),
                new NumberExpr(new Fraction(3, 1))
        );
        Expr b = new BinaryExpr(
                "+",
                new NumberExpr(new Fraction(1, 1)),
                new BinaryExpr(
                        "+",
                        new NumberExpr(new Fraction(2, 1)),
                        new NumberExpr(new Fraction(3, 1))
                )
        );
        assertEquals(a.canonicalKey(), b.canonicalKey());
    }

    // 测试点：不同表达式应生成不同 canonicalKey，避免误去重
    @Test
    void canonicalKeyShouldDifferentiateDifferentExpressions() {
        Expr a = new BinaryExpr(
                "+",
                new NumberExpr(new Fraction(1, 2)),
                new NumberExpr(new Fraction(1, 3))
        );
        Expr b = new BinaryExpr(
                "+",
                new NumberExpr(new Fraction(1, 2)),
                new NumberExpr(new Fraction(1, 4))
        );
        assertNotEquals(a.canonicalKey(), b.canonicalKey());
    }
}