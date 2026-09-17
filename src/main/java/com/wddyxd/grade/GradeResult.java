package com.wddyxd.grade;


import java.util.List;

/**
 * &#064program: corenger
 * &#064description: 判分结果：哪些对，哪些错
 * &#064author: black-cat
 * &#064create: 2026-09-17 15:07
 **/

public class GradeResult {
    private final List<Integer> correct;
    private final List<Integer> wrong;

    public GradeResult(List<Integer> correct, List<Integer> wrong) {
        this.correct = correct;
        this.wrong = wrong;
    }

    public List<Integer> getCorrect() { return correct; }
    public List<Integer> getWrong() { return wrong; }

    /** 转成 Grade.txt 的文本。 */
    public String toText() { return ""; } // TODO
}