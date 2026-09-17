package com.wddyxd.grade;

import com.wddyxd.model.Expr;
import com.wddyxd.model.Fraction;
import com.wddyxd.parser.ExpressionParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * &#064program: corenger
 * &#064description: 判分器单元测试
 * &#064author: black-cat
 * &#064create: 2026-09-17 19:12
 **/

class GraderTest {

    // 测试点：全部答对时，正确题号应为 1、2，错误题号为空
    @Test
    void shouldGradeAllCorrect() {
        List<Expr> exercises = List.of(
                ExpressionParser.parse("1/6 + 1/8 = "),
                ExpressionParser.parse("3 + 5 × 2 = ")
        );

        List<Fraction> userAnswers = List.of(
                Fraction.fromString("7/24"),
                Fraction.fromString("13")
        );

        GradeResult result = new Grader().grade(exercises, userAnswers);

        assertEquals(List.of(1, 2), result.getCorrect());
        assertEquals(List.of(), result.getWrong());
    }

    // 测试点：部分答对时，应正确区分正确题号和错误题号
    @Test
    void shouldGradePartiallyCorrect() {
        List<Expr> exercises = List.of(
                ExpressionParser.parse("1/6 + 1/8 = "),
                ExpressionParser.parse("3 + 5 × 2 = ")
        );

        List<Fraction> userAnswers = List.of(
                Fraction.fromString("7/24"),
                Fraction.fromString("12")
        );

        GradeResult result = new Grader().grade(exercises, userAnswers);

        assertEquals(List.of(1), result.getCorrect());
        assertEquals(List.of(2), result.getWrong());
    }
}