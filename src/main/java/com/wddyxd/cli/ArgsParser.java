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

    public static Config parse(String[] args) {
        if (args == null || args.length == 0) {
            throw new UsageException("缺少命令行参数");
        }

        Config config = new Config();
        Integer n = null;
        Integer r = null;
        String e = null;
        String a = null;

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];

            switch (arg) {
                case "-n":
                    if (n != null) throw new UsageException("重复参数: -n");
                    if (i + 1 >= args.length) throw new UsageException("-n 缺少参数值");
                    n = parseInt(args[++i], "-n");
                    break;

                case "-r":
                    if (r != null) throw new UsageException("重复参数: -r");
                    if (i + 1 >= args.length) throw new UsageException("-r 缺少参数值");
                    r = parseInt(args[++i], "-r");
                    break;

                case "-e":
                    if (e != null) throw new UsageException("重复参数: -e");
                    if (i + 1 >= args.length) throw new UsageException("-e 缺少参数值");
                    e = args[++i];
                    break;

                case "-a":
                    if (a != null) throw new UsageException("重复参数: -a");
                    if (i + 1 >= args.length) throw new UsageException("-a 缺少参数值");
                    a = args[++i];
                    break;

                default:
                    throw new UsageException("未知参数: " + arg);
            }
        }

        boolean hasGenerateArg = n != null || r != null;
        boolean hasGradeArg = e != null || a != null;

        if (hasGenerateArg && hasGradeArg) {
            throw new UsageException("生成模式参数和判分模式参数不能混用");
        }

        if (hasGenerateArg) {
            if (n == null || r == null) {
                throw new UsageException("生成模式必须同时指定 -n 和 -r");
            }
            if (n <= 0) {
                throw new UsageException("-n 必须大于 0");
            }
            if (r < 1) {
                throw new UsageException("-r 必须大于等于 1");
            }

            config.setMode(Config.Mode.GENERATE);
            config.setN(n);
            config.setR(r);
        } else if (hasGradeArg) {
            if (e == null || a == null) {
                throw new UsageException("判分模式必须同时指定 -e 和 -a");
            }

            config.setMode(Config.Mode.GRADE);
            config.setExerciseFile(e);
            config.setAnswerFile(a);
        } else {
            throw new UsageException("未指定运行模式");
        }

        return config;
    }

    private static int parseInt(String value, String option) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            throw new UsageException(option + " 需要一个整数，实际为: " + value);
        }
    }

    public static void printHelp() {
        System.out.println("用法：");
        System.out.println("  生成：Myapp -n <数量> -r <范围>");
        System.out.println(" 判分：Myapp -e <题目文件> -a <答案文件>");
    }

//    public static Config parse(String[] args) {   // TODO 最小化测试版本
//        Config config = new Config();
//        Integer n = null;
//        Integer r = null;
//        String e = null;
//        String a = null;
//
//        for (int i = 0; i < args.length; i++) {
//            switch (args[i]) {
//                case "-n":
//                    n = Integer.parseInt(args[++i]);
//                    break;
//                case "-r":
//                    r = Integer.parseInt(args[++i]);
//                    break;
//                case "-e":
//                    e = args[++i];
//                    break;
//                case "-a":
//                    a = args[++i];
//                    break;
//                default:
//                    throw new UsageException("未知参数: " + args[i]);
//            }
//        }
//
//
//        boolean isGenerateMode = n != null && r != null && e == null && a == null;
//        boolean isGradeMode = e != null && a != null && n == null && r == null;
//
//        if (isGenerateMode) {
//            config.setMode(Config.Mode.GENERATE);
//            config.setN(n);
//            config.setR(r);
//        } else if (isGradeMode) {
//            config.setMode(Config.Mode.GRADE);
//            config.setExerciseFile(e);
//            config.setAnswerFile(a);
//        } else {
//            throw new UsageException(
//                    "参数组合不正确：生成模式必须同时指定 -n 和 -r；判分模式必须同时指定 -e 和 -a；两种模式不能混用"
//            );
//        }
//
//        return config;
//    }
//
//    public static void printHelp() {
//        System.out.println("用法：");
//        System.out.println("  生成：Myapp -n <数量> -r <范围>");
//        System.out.println("  判分：Myapp -e <题目文件> -a <答案文件>");
//    }
}