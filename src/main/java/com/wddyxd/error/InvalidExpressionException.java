package com.wddyxd.error;


/**
 * &#064program: corenger
 * &#064description: 题目表达式格式错误
 * &#064author: black-cat
 * &#064create: 2026-09-17 15:01
 **/

public class InvalidExpressionException extends AppException {
    public InvalidExpressionException(String message) { super(message); }
}