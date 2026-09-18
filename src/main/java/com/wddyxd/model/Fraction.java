package com.wddyxd.model;

import java.math.BigInteger;

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
        // 统一规定分母为正，便于比较、显示和判断两个分数是否相等。
        if (denominator < 0) {
            numerator = Math.negateExact(numerator);
            denominator = Math.negateExact(denominator);
        }
        // 零分数统一保存为 0/1，避免出现 0/2、0/5 等不同表示。
        if (numerator == 0) {
            this.numerator = 0;
            this.denominator = 1;
            return;
        }
        // 构造时立即约分，使每个 Fraction 始终处于最简形式。
        long gcd = gcd(Math.abs(numerator), denominator);
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
    }

    /** 返回当前分数与另一个分数相加后的最简结果。 */
    public Fraction add(Fraction other) {
        requireOther(other);
        return new Fraction(
                Math.addExact(Math.multiplyExact(numerator, other.denominator),
                        Math.multiplyExact(other.numerator, denominator)),
                Math.multiplyExact(denominator, other.denominator));
    }

    /** 返回当前分数减去另一个分数后的最简结果。 */
    public Fraction sub(Fraction other) {
        requireOther(other);
        return new Fraction(
                Math.subtractExact(Math.multiplyExact(numerator, other.denominator),
                        Math.multiplyExact(other.numerator, denominator)),
                Math.multiplyExact(denominator, other.denominator));
    }

    /** 返回当前分数与另一个分数相乘后的最简结果。 */
    public Fraction mul(Fraction other) {
        requireOther(other);
        return new Fraction(
                Math.multiplyExact(numerator, other.numerator),
                Math.multiplyExact(denominator, other.denominator));
    }

    /** 返回当前分数除以另一个分数后的最简结果。 */
    public Fraction div(Fraction other) {
        requireOther(other);
        if (other.isZero()) throw new ArithmeticException("不能除以0");
        return new Fraction(
                Math.multiplyExact(numerator, other.denominator),
                Math.multiplyExact(denominator, other.numerator));
    }

    /** 判断当前分数是否为负数。 */
    public boolean isNegative() { return numerator < 0; }

    /** 判断当前分数是否为零。 */
    public boolean isZero() { return numerator == 0; }

    /** 使用交叉相乘比较大小，BigInteger 用于避免中间乘法溢出。 */
    public boolean lessThan(Fraction other) {
        requireOther(other);
        return BigInteger.valueOf(numerator)
                .multiply(BigInteger.valueOf(other.denominator))
                .compareTo(BigInteger.valueOf(other.numerator)
                        .multiply(BigInteger.valueOf(denominator))) < 0;
    }

    /** 输出：整数 "5"；真分数 "3/5"；带分数 "2'3/8"。 */
    public String toDisplayString() {
        if (denominator == 1) return Long.toString(numerator);
        // 负数统一输出为假分数，避免出现不符合约定的负带分数。
        if (numerator < 0) return numerator + "/" + denominator;
        if (numerator < denominator) return numerator + "/" + denominator;
        return (numerator / denominator) + "'" + (numerator % denominator) + "/" + denominator;
    }

    /** 解析 "5"、"3/5"、"2'3/8"。 */
    public static Fraction fromString(String s) {
        if (s == null || s.trim().isEmpty()) {
            throw new IllegalArgumentException("分数不能为空");
        }
        String value = s.trim();
        try {
            // 带分数格式为“整数'分子/分母”，例如 2'3/8。
            if (value.indexOf('\'') >= 0) {
                String[] mixed = value.split("'", -1);
                if (mixed.length != 2) throw new IllegalArgumentException("带分数格式错误");
                String[] proper = mixed[1].split("/", -1);
                if (proper.length != 2) throw new IllegalArgumentException("带分数格式错误");
                long whole = Long.parseLong(mixed[0]);
                long numerator = Long.parseLong(proper[0]);
                long denominator = Long.parseLong(proper[1]);
                if (whole < 0 || numerator < 0 || numerator >= denominator) {
                    throw new IllegalArgumentException("带分数格式错误");
                }
                return new Fraction(Math.addExact(Math.multiplyExact(whole, denominator), numerator),
                        denominator);
            }
            // 普通分数格式为“分子/分母”，整数则按分母 1 处理。
            if (value.indexOf('/') >= 0) {
                String[] parts = value.split("/", -1);
                if (parts.length != 2) throw new IllegalArgumentException("分数格式错误");
                return new Fraction(Long.parseLong(parts[0].trim()), Long.parseLong(parts[1].trim()));
            }
            return new Fraction(Long.parseLong(value), 1);
        } catch (NumberFormatException | ArithmeticException e) {
            throw new IllegalArgumentException("分数格式错误: " + s, e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fraction)) return false;
        Fraction other = (Fraction) o;
        return numerator == other.numerator && denominator == other.denominator;
    }

    @Override
    public int hashCode() {
        return 31 * Long.hashCode(numerator) + Long.hashCode(denominator);
    }

    private static long gcd(long a, long b) {
        // 欧几里得算法求最大公约数，用于构造时约分。
        while (b != 0) {
            long remainder = a % b;
            a = b;
            b = remainder;
        }
        return a;
    }

    private static void requireOther(Fraction other) {
        // 尽早暴露非法调用，避免在运算过程中出现不明确的空指针错误。
        if (other == null) throw new NullPointerException("分数不能为null");
    }
}