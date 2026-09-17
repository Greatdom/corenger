package com.wddyxd.io;

import com.wddyxd.grade.GradeResult;
import com.wddyxd.model.Expr;
import com.wddyxd.model.Fraction;
import com.wddyxd.model.NumberExpr;
import com.wddyxd.model.Question;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * &#064program: corenger
 * &#064description: 文件读写单元测试
 * &#064author: black-cat
 * &#064create: 2026-09-17 19:15
 **/

class FileStoreTest {

    @TempDir
    Path tempDir;

    // 测试点：写 Exercises.txt 时应带题号，并以 " = " 结尾；读回时应能解析表达式
    @Test
    void shouldWriteAndReadExercises() throws Exception {
        Question q = new Question(
                new NumberExpr(new Fraction(1, 6)),
                new Fraction(7, 24),
                "1/6"
        );

        Path exercisePath = tempDir.resolve("Exercises.txt");

        FileStore.writeExercises(exercisePath.toString(), List.of(q));

        List<String> lines = Files.readAllLines(exercisePath);
        assertEquals(1, lines.size());
        assertTrue(lines.get(0).startsWith("1. "));
        assertTrue(lines.get(0).endsWith(" = "));

        List<Expr> read = FileStore.readExercises(exercisePath.toString());
        assertEquals(1, read.size());
        assertEquals(new Fraction(1, 6), read.get(0).evaluate());
    }

    // 测试点：写 Answers.txt 时每行一个答案；读回时应得到对应 Fraction 列表
    @Test
    void shouldWriteAndReadAnswers() throws Exception {
        Question q = new Question(
                new NumberExpr(new Fraction(1, 6)),
                new Fraction(7, 24),
                "1/6"
        );

        Path answerPath = tempDir.resolve("Answers.txt");

        FileStore.writeAnswers(answerPath.toString(), List.of(q));

        List<String> lines = Files.readAllLines(answerPath);
        assertEquals(List.of("7/24"), lines);

        List<Fraction> read = FileStore.readAnswers(answerPath.toString());
        assertEquals(List.of(new Fraction(7, 24)), read);
    }

    // 测试点：写 Grade.txt 时应输出标准 Correct / Wrong 两行格式
    @Test
    void shouldWriteGradeFile() throws Exception {
        GradeResult result = new GradeResult(
                List.of(1, 3),
                List.of(2)
        );

        Path gradePath = tempDir.resolve("Grade.txt");

        FileStore.writeGrade(gradePath.toString(), result);

        String text = Files.readString(gradePath).trim();

        String expected = """
                Correct: 2 (1, 3)
                Wrong: 1 (2)""";

        assertEquals(expected, text);
    }
}