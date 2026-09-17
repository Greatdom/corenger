package com.wddyxd.cli;

import com.wddyxd.config.Config;
import com.wddyxd.error.UsageException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * &#064program: corenger
 * &#064description: 命令行参数解析单元测试
 * &#064author: black-cat
 * &#064create: 2026-09-17 19:07
 **/

class ArgsParserTest {

    // 测试点：生成模式应能正确解析 -n 和 -r
    @Test
    void shouldParseGenerateMode() {
        Config c = ArgsParser.parse(new String[]{"-n", "10", "-r", "10"});

        assertEquals(Config.Mode.GENERATE, c.getMode());
        assertEquals(Integer.valueOf(10), c.getN());
        assertEquals(Integer.valueOf(10), c.getR());
        assertNull(c.getExerciseFile());
        assertNull(c.getAnswerFile());
    }

    // 测试点：判分模式应能正确解析 -e 和 -a
    @Test
    void shouldParseGradeMode() {
        Config c = ArgsParser.parse(new String[]{"-e", "Exercises.txt", "-a", "Answers.txt"});

        assertEquals(Config.Mode.GRADE, c.getMode());
        assertEquals("Exercises.txt", c.getExerciseFile());
        assertEquals("Answers.txt", c.getAnswerFile());
        assertNull(c.getN());
        assertNull(c.getR());
    }

    // 测试点：生成参数和判分参数不能混用
    @Test
    void shouldRejectMixedMode() {
        assertThrows(UsageException.class, () ->
                ArgsParser.parse(new String[]{"-n", "10", "-r", "10", "-e", "Exercises.txt"})
        );
    }

    // 测试点：未知参数应抛出 UsageException
    @Test
    void shouldRejectUnknownArgument() {
        assertThrows(UsageException.class, () ->
                ArgsParser.parse(new String[]{"-x", "1"})
        );
    }

    // 测试点：生成模式缺少 -n 或 -r 应抛出 UsageException
    @Test
    void shouldRejectMissingNOrR() {
        assertThrows(UsageException.class, () ->
                ArgsParser.parse(new String[]{"-n", "10"})
        );
        assertThrows(UsageException.class, () ->
                ArgsParser.parse(new String[]{"-r", "10"})
        );
    }
}