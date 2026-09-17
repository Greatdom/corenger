package com.wddyxd.io;


import com.wddyxd.grade.GradeResult;
import com.wddyxd.model.Expr;
import com.wddyxd.model.Fraction;
import com.wddyxd.model.Question;

import java.io.IOException;
import java.util.List;

/**
 * &#064program: corenger
 * &#064description: 文件仓库,所有 open/read/write 都找它，不要到处写文件操作
 * &#064author: black-cat
 * &#064create: 2026-09-17 15:06
 **/

public class FileStore {

    /** 写 Exercises.txt，每行 "表达式 = "。 */
    public static void writeExercises(String path, List<Question> questions) throws IOException { }

    /** 写 Answers.txt，每行一个答案。 */
    public static void writeAnswers(String path, List<Question> questions) throws IOException { }

    /** 读题目文件，返回 Expr 列表。 */
    public static List<Expr> readExercises(String path) throws IOException { return null; }

    /** 读答案文件，返回 Fraction 列表。 */
    public static List<Fraction> readAnswers(String path) throws IOException { return null; }

    /** 写 Grade.txt。 */
    public static void writeGrade(String path, GradeResult result) throws IOException { }
}