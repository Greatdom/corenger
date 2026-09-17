package com.wddyxd.model;


/**
 * &#064program: corenger
 * &#064description: 分数,假分数,整数定义类
 * &#064author: black-cat
 * &#064create: 2026-09-17 14:53
 **/

public class Fraction {
    private final long numerator;
    private final long denominator;

    public Fraction(long numerator, long denominator) {
        if (denominator == 0) throw new IllegalArgumentException("分母不能为0");
        // 约分、符号规范化，见实现
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Fraction add(Fraction other) { return null; }   // TODO
    public Fraction sub(Fraction other) { return null; }   // TODO
    public Fraction mul(Fraction other) { return null; }   // TODO
    public Fraction div(Fraction other) { return null; }   // TODO

    public boolean isNegative() { return numerator < 0; }
    public boolean isZero() { return numerator == 0; }
    public boolean lessThan(Fraction other) { return false; } // TODO

    /** 输出：整数 "5"；真分数 "3/5"；带分数 "2'3/8"。 */
    public String toDisplayString() { return ""; } // TODO

    /** 解析 "5"、"3/5"、"2'3/8"。 */
    public static Fraction fromString(String s) { return null; } // TODO

    @Override
    public boolean equals(Object o) { return false; } // TODO
    @Override
    public int hashCode() { return 0; } // TODO
}