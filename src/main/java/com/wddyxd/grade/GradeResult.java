package com.wddyxd.grade;


import java.util.ArrayList;
import java.util.Collections;
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
        this.correct = new ArrayList<>(correct);
        this.wrong = new ArrayList<>(wrong);

        Collections.sort(this.correct);
        Collections.sort(this.wrong);
    }

    public List<Integer> getCorrect() {
        return correct;
    }

    public List<Integer> getWrong() {
        return wrong;
    }

    /**
     * 转成 Grade.txt 的文本。
     */
    public String toText() {
        return "Correct: " + correct.size() + " (" + join(correct) + ")\n"
                + "Wrong: " + wrong.size() + " (" + join(wrong) + ")";
    }

    private static String join(List<Integer> list) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(list.get(i));
        }

        return sb.toString();
    }

//    private final List<Integer> correct;
//    private final List<Integer> wrong;
//
//    public GradeResult(List<Integer> correct, List<Integer> wrong) {
//        this.correct = correct;
//        this.wrong = wrong;
//    }
//
//    public List<Integer> getCorrect() { return correct; }
//    public List<Integer> getWrong() { return wrong; }
//
//    /** 转成 Grade.txt 的文本。 */
//    public String toText() { return ""; } // TODO
}