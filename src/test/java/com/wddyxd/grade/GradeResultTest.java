package com.wddyxd.grade;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * &#064program: corenger
 * &#064description: 判分结果单元测试
 * &#064author: black-cat
 * &#064create: 2026-09-17 19:14
 **/

class GradeResultTest {

    // 测试点：GradeResult.toText() 应输出 Correct / Wrong 两行标准格式
    @Test
    void shouldFormatGradeText() {
        GradeResult result = new GradeResult(
                List.of(1, 3, 5),
                List.of(2, 4)
        );

        String expected = """
                Correct: 3 (1, 3, 5)
                Wrong: 2 (2, 4)""";

        assertEquals(expected, result.toText().trim());
    }

    // 测试点：正确和错误列表为空时，也应输出合法格式，如 Correct: 0 ()
    @Test
    void shouldFormatEmptyLists() {
        GradeResult result = new GradeResult(
                List.of(),
                List.of()
        );

        String expected = """
                Correct: 0 ()
                Wrong: 0 ()""";

        assertEquals(expected, result.toText().trim());
    }
}