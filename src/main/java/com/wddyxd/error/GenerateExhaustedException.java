package com.wddyxd.error;


/**
 * &#064program: corenger
 * &#064description: 范围太小，凑不出足够多不重复题
 * &#064author: black-cat
 * &#064create: 2026-09-17 15:02
 **/

public class GenerateExhaustedException extends AppException {
    public GenerateExhaustedException(String message) { super(message); }
}