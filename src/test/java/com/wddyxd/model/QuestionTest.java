package com.wddyxd.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * &#064program: corenger
 * &#064description: 完整题目单元测试
 * &#064author: black-cat
 * &#064create: 2026-09-17 19:06
 **/

class QuestionTest {

    // 测试点：题目文本应以 " = " 结尾，且等号前后有空格
    @Test
    void exerciseTextShouldEndWithEqualsAndSpace() {
        Question q = new Question(
                new NumberExpr(new Fraction(1, 6)),
                new Fraction(7, 24),
                "1/6"
        );
        assertEquals("1/6 = ", q.exerciseText());
    }

    // 测试点：答案文本应等于标准答案的显示字符串
    @Test
    void answerTextShouldBeDisplayString() {
        Question q = new Question(
                new NumberExpr(new Fraction(1, 6)),
                new Fraction(7, 24),
                "1/6"
        );
        assertEquals("7/24", q.answerText());
    }
}