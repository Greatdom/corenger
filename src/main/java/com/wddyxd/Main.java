package com.wddyxd;


import com.wddyxd.cli.ArgsParser;
import com.wddyxd.config.Config;
import com.wddyxd.error.AppException;
import com.wddyxd.generator.QuestionGenerator;
import com.wddyxd.grade.GradeResult;
import com.wddyxd.grade.Grader;
import com.wddyxd.io.FileStore;
import com.wddyxd.model.Question;

import java.io.IOException;
import java.util.List;

/**
 * &#064program: corenger
 * &#064description: 程序入口,先解析参数，再根据模式分流，最后统一捕获异常
 * &#064author: black-cat
 * &#064create: 2026-09-17 14:51
 **/

public class Main {
    public static void main(String[] args) {

        try {
            Config config = ArgsParser.parse(args);

            if (config.getMode() == Config.Mode.GENERATE) {
                QuestionGenerator generator = new QuestionGenerator(config.getR());
                List<Question> questions = generator.generate(config.getN());

                FileStore.writeExercises("Exercises.txt", questions);
                FileStore.writeAnswers("Answers.txt", questions);

                System.out.println("生成完成：Exercises.txt、Answers.txt");
            } else {
                GradeResult result = new Grader()
                        .gradeFiles(config.getExerciseFile(), config.getAnswerFile());

                FileStore.writeGrade("Grade.txt", result);

                System.out.println("批改完成：Grade.txt");
            }
        } catch (AppException e) {
            System.err.println("错误：" + e.getMessage());
            ArgsParser.printHelp();
            System.exit(1);
        } catch (IOException e) {
            System.err.println("文件读写错误：" + e.getMessage());
            System.exit(1);
        }
    }
}

