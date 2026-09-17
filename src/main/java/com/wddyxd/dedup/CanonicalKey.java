package com.wddyxd.dedup;


import com.wddyxd.model.Expr;

/**
 * &#064program: corenger
 * &#064description: 查重签名工具,生成表达式的规范签名
 * &#064author: black-cat
 * &#064create: 2026-09-17 15:04
 **/

public class CanonicalKey {

    /** 生成表达式的规范签名。 */
    public static String of(Expr expr) {
        return expr.canonicalKey();
    }
}