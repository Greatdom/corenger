package com.wddyxd.io;


import com.wddyxd.error.InvalidAnswerFormatException;
import com.wddyxd.grade.GradeResult;
import com.wddyxd.model.Expr;
import com.wddyxd.model.Fraction;
import com.wddyxd.model.Question;
import com.wddyxd.parser.ExpressionParser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * &#064program: corenger
 * &#064description: 文件仓库,所有 open/read/write 都找它，不要到处写文件操作
 * &#064author: black-cat
 * &#064create: 2026-09-17 15:06
 **/

public class FileStore {

    /**
     * 写 Exercises.txt，每行 "1. 表达式 = "。
     */
    public static void writeExercises(String path, List<Question> questions) throws IOException {
        List<String> lines = new ArrayList<>();

        for (int i = 0; i < questions.size(); i++) {
            lines.add((i + 1) + ". " + questions.get(i).exerciseText());
        }

        Files.write(Path.of(path), lines, StandardCharsets.UTF_8);
    }

    /**
     * 写 Answers.txt，每行一个答案。
     */
    public static void writeAnswers(String path, List<Question> questions) throws IOException {
        List<String> lines = new ArrayList<>();

        for (Question question : questions) {
            lines.add(question.answerText());
        }

        Files.write(Path.of(path), lines, StandardCharsets.UTF_8);
    }

    /**
     * 读题目文件，返回 Expr 列表。
     */
    public static List<Expr> readExercises(String path) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(path), StandardCharsets.UTF_8);
        List<Expr> result = new ArrayList<>();

        for (String line : lines) {
            if (line == null || line.trim().isEmpty()) {
                continue;
            }

            // 去掉 "1. " 这样的题号前缀
            String exprText = line.replaceFirst("^\\s*\\d+\\.\\s*", "");
            result.add(ExpressionParser.parse(exprText));
        }

        return result;
    }

    /**
     * 读答案文件，返回 Fraction 列表。
     */
    public static List<Fraction> readAnswers(String path) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(path), StandardCharsets.UTF_8);
        List<Fraction> result = new ArrayList<>();

        for (String line : lines) {
            if (line == null || line.trim().isEmpty()) {
                continue;
            }

            try {
                result.add(Fraction.fromString(line.trim()));
            } catch (IllegalArgumentException ex) {
                throw new InvalidAnswerFormatException("答案格式错误: " + line);
            }
        }

        return result;
    }

    /**
     * 写 Grade.txt。
     */
    public static void writeGrade(String path, GradeResult result) throws IOException {
        Files.writeString(
                Path.of(path),
                result.toText() + System.lineSeparator(),
                StandardCharsets.UTF_8
        );
    }

//    /** 写 Exercises.txt，每行 "表达式 = "。 */
//    public static void writeExercises(String path, List<Question> questions) throws IOException { }
//
//    /** 写 Answers.txt，每行一个答案。 */
//    public static void writeAnswers(String path, List<Question> questions) throws IOException { }
//
//    /** 读题目文件，返回 Expr 列表。 */
//    public static List<Expr> readExercises(String path) throws IOException { return null; }
//
//    /** 读答案文件，返回 Fraction 列表。 */
//    public static List<Fraction> readAnswers(String path) throws IOException { return null; }
//
//    /** 写 Grade.txt。 */
//    public static void writeGrade(String path, GradeResult result) throws IOException { }
}