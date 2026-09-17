package com.wddyxd.grade;


import com.wddyxd.model.Expr;
import com.wddyxd.model.Fraction;

import java.io.IOException;
import java.util.List;

/**
 * &#064program: corenger
 * &#064description: 评分器,不信任 Answers.txt，自己重新算一遍标准答案再比较
 * &#064author: black-cat
 * &#064create: 2026-09-17 15:08
 **/

public class Grader {

    /** 对题目和用户答案判分。 */
    public GradeResult grade(List<Expr> exercises, List<Fraction> userAnswers) {
        return null; // TODO
    }

    /** 从文件读题和答案，然后判分。 */
    public GradeResult gradeFiles(String exerciseFile, String answerFile) throws IOException {
        return null; // TODO
    }
}