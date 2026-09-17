package com.wddyxd.generator;

import com.wddyxd.error.GenerateExhaustedException;
import com.wddyxd.model.Question;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * &#064program: corenger
 * &#064description: 题目生成器单元测试
 * &#064author: black-cat
 * &#064create: 2026-09-17 19:11
 **/

class QuestionGeneratorTest {

    // 测试点：请求生成多少道题，就应返回多少道题
    @Test
    void shouldGenerateRequestedNumber() {
        List<Question> questions = new QuestionGenerator(10).generate(20);
        assertEquals(20, questions.size());
    }

    // 测试点：单次运行生成的题目必须完全唯一，不能有重复签名
    @Test
    void shouldGenerateUniqueQuestions() {
        List<Question> questions = new QuestionGenerator(10).generate(50);

        Set<String> signatures = questions.stream()
                .map(Question::getSignature)
                .collect(Collectors.toSet());

        assertEquals(questions.size(), signatures.size());
    }

    // 测试点：每道题的运算符数量不能超过 3 个
    @Test
    void shouldLimitOperatorsToThree() {
        List<Question> questions = new QuestionGenerator(10).generate(50);

        assertTrue(questions.stream()
                .allMatch(q -> q.getExpr().operatorCount() <= 3));
    }

    // 测试点：生成题目的最终答案不能为负数
    @Test
    void shouldNotProduceNegativeAnswer() {
        List<Question> questions = new QuestionGenerator(10).generate(50);

        assertTrue(questions.stream()
                .noneMatch(q -> q.getAnswer().isNegative()));
    }

    // 测试点：当范围太小、无法生成足够多不重复题目时，应抛出 GenerateExhaustedException
    @Test
    void shouldThrowWhenRangeTooSmall() {
        assertThrows(GenerateExhaustedException.class, () ->
                new QuestionGenerator(2).generate(10000)
        );
    }
}