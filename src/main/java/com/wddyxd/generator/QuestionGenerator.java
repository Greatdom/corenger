package com.wddyxd.generator;


import com.wddyxd.dedup.CanonicalKey;
import com.wddyxd.error.GenerateExhaustedException;
import com.wddyxd.model.*;

import java.util.*;

/**
 * &#064program: corenger
 * &#064description: 题目生成器,告诉它范围 r 和数量 n，它给你 n 道合法且不重复的题
 * &#064author: black-cat
 * &#064create: 2026-09-17 15:03
 **/

public class QuestionGenerator {

    private static final int MAX_OPERATORS = 3;

    private final int range;
    private final Random random = new Random();

    public QuestionGenerator(int range) {
        if (range < 1) {
            throw new IllegalArgumentException("范围必须 >= 1");
        }
        this.range = range;
    }

    /**
     * 生成 n 道不重复题目。
     */
    public List<Question> generate(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("生成数量不能为负");
        }

        List<Question> result = new ArrayList<>();
        Set<String> seen = new HashSet<>();

        int attempts = 0;
        int maxAttempts = Math.max(1000, n * 3);

        while (result.size() < n) {
            if (attempts++ >= maxAttempts) {
                throw new GenerateExhaustedException("范围太小，无法生成足够多不重复题目");
            }

            Question question = generateOne();
            if (seen.add(question.getSignature())) {
                result.add(question);
            }
        }

        return result;
    }

    /**
     * 生成一道合法题目。
     */
    public Question generateOne() {
        for (int i = 0; i < 1000; i++) {
            Expr expr = buildExpression(MAX_OPERATORS);

            if (isValid(expr)) {
                Fraction answer = expr.evaluate();
                String signature = CanonicalKey.of(expr);
                return new Question(expr, answer, signature);
            }
        }

        throw new GenerateExhaustedException("无法生成合法题目");
    }

    /**
     * 随机生成操作数，范围 [0, range)。
     * 范围很小时只生成整数，避免题目空间爆炸。
     */
    private Fraction randomOperand() {
        if (range <= 1) {
            return new Fraction(0, 1);
        }

        int type = random.nextInt(3);

        // 整数
        if (type == 0) {
            return new Fraction(random.nextInt(range), 1);
        }

        // 范围太小，不生成真分数/带分数
        if (range <= 2) {
            return new Fraction(random.nextInt(range), 1);
        }

        int denominator = 2 + random.nextInt(range - 2); // 2 ~ range-1
        int numerator = 1 + random.nextInt(denominator - 1); // 1 ~ denominator-1

        // 真分数
        if (type == 1) {
            return new Fraction(numerator, denominator);
        }

        // 带分数，保证值 < range
        int whole = random.nextInt(range - 1); // 0 ~ range-2
        Fraction mixed = new Fraction((long) whole * denominator + numerator, denominator);
        Fraction limit = new Fraction(range, 1);

        if (mixed.lessThan(limit)) {
            return mixed;
        }

        return new Fraction(random.nextInt(range), 1);
    }

    /**
     * 随机返回 "+", "-", "*", "/"。
     */
    private String randomOperator() {
        String[] ops = {"+", "-", "*", "/"};
        return ops[random.nextInt(ops.length)];
    }

    /**
     * 递归构建表达式，最多 maxOperators 个运算符。
     */
    private Expr buildExpression(int maxOperators) {
        if (maxOperators <= 0 || random.nextDouble() < 0.25) {
            return new NumberExpr(randomOperand());
        }

        for (int attempt = 0; attempt < 50; attempt++) {
            int leftMax = random.nextInt(maxOperators);
            int rightMax = maxOperators - 1 - leftMax;

            Expr left = buildExpression(leftMax);
            Expr right = buildExpression(rightMax);
            String op = randomOperator();

            Fraction leftValue = safeEvaluate(left);
            Fraction rightValue = safeEvaluate(right);

            if (leftValue == null || rightValue == null) {
                continue;
            }

            // 减法不能出现负数
            if ("-".equals(op) && leftValue.lessThan(rightValue)) {
                continue;
            }

            // 除法不能除以 0
            if ("/".equals(op) && rightValue.isZero()) {
                continue;
            }

            Expr expr = new BinaryExpr(op, left, right);
            if (isValid(expr)) {
                return expr;
            }
        }

        return new NumberExpr(randomOperand());
    }

    /**
     * 校验：运算符 <= 3，最终结果非负，计算过程不抛异常。
     */
    private boolean isValid(Expr expr) {
        if (expr == null) {
            return false;
        }

        if (expr.operatorCount() > MAX_OPERATORS) {
            return false;
        }

        Fraction value = safeEvaluate(expr);
        return value != null && !value.isNegative();
    }

    private Fraction safeEvaluate(Expr expr) {
        try {
            return expr.evaluate();
        } catch (RuntimeException ex) {
            return null;
        }
    }

}