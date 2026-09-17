package com.wddyxd.model;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * &#064program: corenger
 * &#064description: 分数类单元测试
 * &#064author: black-cat
 * &#064create: 2026-09-17 18:56
 **/


class FractionTest {

    // 测试点：2/4 应被约分为最简分数 1/2
    @Test
    void shouldReduceToLowestTerms() {
        Fraction f = new Fraction(2, 4);
        assertEquals("1/2", f.toDisplayString());
    }

    // 测试点：分母为负时，符号应规范化到分子上，1/-2 -> -1/2
    @Test
    void shouldNormalizeSign() {
        Fraction f = new Fraction(1, -2);
        assertTrue(f.isNegative());
        assertEquals("-1/2", f.toDisplayString());
    }

    // 测试点：分数加法 1/6 + 1/8 = 7/24
    @Test
    void shouldAddFractions() {
        Fraction a = new Fraction(1, 6);
        Fraction b = new Fraction(1, 8);
        assertEquals(new Fraction(7, 24), a.add(b));
    }

    // 测试点：分数减法 3/4 - 1/4 = 1/2
    @Test
    void shouldSubtractFractions() {
        Fraction a = new Fraction(3, 4);
        Fraction b = new Fraction(1, 4);
        assertEquals(new Fraction(1, 2), a.sub(b));
    }

    // 测试点：分数乘法 2/3 * 3/4 = 1/2
    @Test
    void shouldMultiplyFractions() {
        Fraction a = new Fraction(2, 3);
        Fraction b = new Fraction(3, 4);
        assertEquals(new Fraction(1, 2), a.mul(b));
    }

    // 测试点：分数除法 1/2 ÷ 3/4 = 2/3
    @Test
    void shouldDivideFractions() {
        Fraction a = new Fraction(1, 2);
        Fraction b = new Fraction(3, 4);
        assertEquals(new Fraction(2, 3), a.div(b));
    }

    // 测试点：分数大小比较，1/2 < 2/3
    @Test
    void shouldCompareFractions() {
        assertTrue(new Fraction(1, 2).lessThan(new Fraction(2, 3)));
        assertFalse(new Fraction(2, 3).lessThan(new Fraction(1, 2)));
    }

    // 测试点：输出格式应区分整数、真分数、带分数
    @Test
    void shouldDisplayIntegerProperFractionAndMixedNumber() {
        assertEquals("5", new Fraction(5, 1).toDisplayString());
        assertEquals("3/5", new Fraction(3, 5).toDisplayString());
        assertEquals("2'3/8", new Fraction(19, 8).toDisplayString());
    }

    // 测试点：解析整数、真分数、带分数
    @Test
    void shouldParseIntegerProperFractionAndMixedNumber() {
        assertEquals(new Fraction(5, 1), Fraction.fromString("5"));
        assertEquals(new Fraction(3, 5), Fraction.fromString("3/5"));
        assertEquals(new Fraction(19, 8), Fraction.fromString("2'3/8"));
    }

    // 测试点：equals 和 hashCode 应基于分数值，而不是对象地址
    @Test
    void equalsAndHashCodeShouldUseValue() {
        assertEquals(new Fraction(1, 2), new Fraction(2, 4));
        assertEquals(new Fraction(1, 2).hashCode(), new Fraction(2, 4).hashCode());
    }
}