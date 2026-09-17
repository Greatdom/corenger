package com.wddyxd.error;


/**
 * &#064program: corenger
 * &#064description: 所有自定义异常的父类
 * &#064author: black-cat
 * &#064create: 2026-09-17 15:00
 **/

public class AppException extends RuntimeException {
    public AppException(String message) { super(message); }
}