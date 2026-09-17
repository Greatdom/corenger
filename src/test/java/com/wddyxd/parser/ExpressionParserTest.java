package com.wddyxd.parser;

import com.wddyxd.model.Expr;
import com.wddyxd.model.Fraction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * &#064program: corenger
 * &#064description: 表达式解析单元测试
 * &#064author: black-cat
 * &#064create: 2026-09-17 19:09
 **/

class ExpressionParserTest {

    // 测试点：应能解析自然数，如 "5"
    @Test
    void shouldParseInteger() {
        Expr expr = ExpressionParser.parse("5 = ");
        assertEquals(new Fraction(5, 1), expr.evaluate());
    }

    // 测试点：应能解析真分数，如 "3/5"
    @Test
    void shouldParseProperFraction() {
        Expr expr = ExpressionParser.parse("3/5 = ");
        assertEquals(new Fraction(3, 5), expr.evaluate());
    }

    // 测试点：应能解析带分数，如 "2'3/8"
    @Test
    void shouldParseMixedNumber() {
        Expr expr = ExpressionParser.parse("2'3/8 = ");
        assertEquals(new Fraction(19, 8), expr.evaluate());
    }

    // 测试点：应能解析分数加法，如 "1/6 + 1/8"
    @Test
    void shouldParseAdditionOfFractions() {
        Expr expr = ExpressionParser.parse("1/6 + 1/8 = ");
        assertEquals(new Fraction(7, 24), expr.evaluate());
    }

    // 测试点：应能处理乘法和加法优先级，如 "3 + 5 × 2" = 13
    @Test
    void shouldParseMultiplicationAndAdditionWithPrecedence() {
        Expr expr = ExpressionParser.parse("3 + 5 × 2 = ");
        assertEquals(new Fraction(13, 1), expr.evaluate());
    }

    // 测试点：应能解析小括号，如 "( 1 + 2 ) × 3" = 9
    @Test
    void shouldParseParentheses() {
        Expr expr = ExpressionParser.parse("( 1 + 2 ) × 3 = ");
        assertEquals(new Fraction(9, 1), expr.evaluate());
    }

    // 测试点：应能解析除法符号 "÷"，如 "6 ÷ 3" = 2
    @Test
    void shouldParseDivisionSymbol() {
        Expr expr = ExpressionParser.parse("6 ÷ 3 = ");
        assertEquals(new Fraction(2, 1), expr.evaluate());
    }
}