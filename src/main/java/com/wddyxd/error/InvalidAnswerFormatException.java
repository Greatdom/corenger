package com.wddyxd.error;


/**
 * &#064program: corenger
 * &#064description: 答案文件格式错误
 * &#064author: black-cat
 * &#064create: 2026-09-17 15:02
 **/

public class InvalidAnswerFormatException extends AppException {
    public InvalidAnswerFormatException(String message) { super(message); }
}