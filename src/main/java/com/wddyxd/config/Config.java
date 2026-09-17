package com.wddyxd.config;


/**
 * &#064program: corenger
 * &#064description: 程序配置对象,命令行参数解析后装进这个盒子，后面模块只看 Config
 * &#064author: black-cat
 * &#064create: 2026-09-17 14:59
 **/

public class Config {
    public enum Mode { GENERATE, GRADE }

    private Mode mode;
    private Integer n;            // 生成数量
    private Integer r;            // 数值范围，必须给定
    private String exerciseFile;  // 判分模式题目文件
    private String answerFile;    // 判分模式答案文件

    // 全部字段的 getter / setter
    public Mode getMode() { return mode; }
    public void setMode(Mode mode) { this.mode = mode; }
    public Integer getN() { return n; }
    public void setN(Integer n) { this.n = n; }
    public Integer getR() { return r; }
    public void setR(Integer r) { this.r = r; }
    public String getExerciseFile() { return exerciseFile; }
    public void setExerciseFile(String f) { this.exerciseFile = f; }
    public String getAnswerFile() { return answerFile; }
    public void setAnswerFile(String f) { this.answerFile = f; }
}