package com.wddyxd.cli;


import com.wddyxd.config.Config;
import com.wddyxd.error.UsageException;

/**
 * &#064program: corenger
 * &#064description: 解析命令行参数，产出 Config,-n 数量、-r 范围、-e 题目文件、-a 答案文件
 * &#064author: black-cat
 * &#064create: 2026-09-17 15:09
 **/

//public class ArgsParser {
//
//    public static Config parse(String[] args) {
//        return null; // TODO
//    }
//
//    public static void printHelp() {
//        System.out.println("用法：");
//        System.out.println("  生成：Myapp -n <数量> -r <范围>");
//        System.out.println("  判分：Myapp -e <题目文件> -a <答案文件>");
//    }
//}
public class ArgsParser {

    public static Config parse(String[] args) {   // TODO 最小化测试版本
        Config config = new Config();
        Integer n = null;
        Integer r = null;
        String e = null;
        String a = null;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-n":
                    n = Integer.parseInt(args[++i]);
                    break;
                case "-r":
                    r = Integer.parseInt(args[++i]);
                    break;
                case "-e":
                    e = args[++i];
                    break;
                case "-a":
                    a = args[++i];
                    break;
                default:
                    throw new UsageException("未知参数: " + args[i]);
            }
        }


        boolean isGenerateMode = n != null && r != null && e == null && a == null;
        boolean isGradeMode = e != null && a != null && n == null && r == null;

        if (isGenerateMode) {
            config.setMode(Config.Mode.GENERATE);
            config.setN(n);
            config.setR(r);
        } else if (isGradeMode) {
            config.setMode(Config.Mode.GRADE);
            config.setExerciseFile(e);
            config.setAnswerFile(a);
        } else {
            throw new UsageException(
                    "参数组合不正确：生成模式必须同时指定 -n 和 -r；判分模式必须同时指定 -e 和 -a；两种模式不能混用"
            );
        }

        return config;
    }

    public static void printHelp() {
        System.out.println("用法：");
        System.out.println("  生成：Myapp -n <数量> -r <范围>");
        System.out.println("  判分：Myapp -e <题目文件> -a <答案文件>");
    }
}