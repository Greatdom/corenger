package com.wddyxd.model;


/**
 * &#064program: corenger
 * &#064description: 表达式接口,不管是数字还是运算，都叫表达式，都能计算和打印
 * &#064author: black-cat
 * &#064create: 2026-09-17 14:55
 **/

public interface Expr {
    Fraction evaluate();          // 计算值
    String toDisplayString();     // 转成题目字符串，不含等号
    int operatorCount();          // 运算符个数
    String canonicalKey();        // 查重签名
}