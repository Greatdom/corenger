package com.wddyxd.error;


/**
 * &#064program: corenger
 * &#064description: 命令行参数错误，例如缺少 -r
 * &#064author: black-cat
 * &#064create: 2026-09-17 15:00
 **/

public class UsageException extends AppException {
    public UsageException(String message) { super(message); }
}