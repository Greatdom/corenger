package com.wddyxd;


import com.wddyxd.cli.ArgsParser;
import com.wddyxd.config.Config;
import com.wddyxd.error.AppException;

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
            //测试代码
            System.out.println("参数识别成功：");
            System.out.println("mode = " + config.getMode());
            System.out.println("n = " + config.getN());
            System.out.println("r = " + config.getR());
            System.out.println("exerciseFile = " + config.getExerciseFile());
            System.out.println("answerFile = " + config.getAnswerFile());

            if (config.getMode() == Config.Mode.GENERATE) {
                // TODO: 调用生成器 + FileStore
            } else {
                // TODO: 调用 Grader + FileStore
            }
        } catch (AppException e) {
            System.err.println("错误：" + e.getMessage());
            ArgsParser.printHelp();
            System.exit(1);
        }
    }
}
