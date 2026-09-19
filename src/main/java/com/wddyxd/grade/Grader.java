package com.wddyxd.grade;


import com.wddyxd.error.InvalidAnswerFormatException;
import com.wddyxd.io.FileStore;
import com.wddyxd.model.Expr;
import com.wddyxd.model.Fraction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * &#064program: corenger
 * &#064description: 评分器,不信任 Answers.txt，自己重新算一遍标准答案再比较
 * &#064author: black-cat
 * &#064create: 2026-09-17 15:08
 **/

public class Grader {

    /**
     * 对题目和用户答案判分。
     * 不信任 Answers.txt，重新对表达式求值。
     */
    public GradeResult grade(List<Expr> exercises, List<Fraction> userAnswers) {
        if (exercises == null || userAnswers == null) {
            throw new IllegalArgumentException("题目和答案不能为 null");
        }

        if (exercises.size() != userAnswers.size()) {
            throw new InvalidAnswerFormatException("题目数量与答案数量不一致");
        }

        List<Integer> correct = new ArrayList<>();
        List<Integer> wrong = new ArrayList<>();

        for (int i = 0; i < exercises.size(); i++) {
            Fraction standard = exercises.get(i).evaluate();
            Fraction user = userAnswers.get(i);

            if (standard.equals(user)) {
                correct.add(i + 1);
            } else {
                wrong.add(i + 1);
            }
        }

        return new GradeResult(correct, wrong);
    }

    /**
     * 从文件读题和答案，然后判分。
     */
    public GradeResult gradeFiles(String exerciseFile, String answerFile) throws IOException {
        List<Expr> exercises = FileStore.readExercises(exerciseFile);
        List<Fraction> answers = FileStore.readAnswers(answerFile);
        return grade(exercises, answers);
    }

}