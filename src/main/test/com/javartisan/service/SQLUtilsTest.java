package com.javartisan.service;


import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.parser.ParserException;
import org.junit.Test;

/**
 * 很多时候我们需要将文本的sql转换为可阅读的格式化好的sql。
 * <p>
 * 这里推荐使用阿里的druid。里面有个工具类可以完成这个功能。
 * <p>
 * com.alibaba.druid.sql.SQLUtils
 * <p>
 * 支持预发检查
 */
public class SQLUtilsTest {


    //SQL错误时候底层实现Catch住了异常，如何将异常抛到业务层？
    // com.alibaba.druid.sql.parser.ParserException: ERROR. token : SEMI, pos : 25
    @Test
    public static void formatMySql() {
        String sql = "SELECT * FROM USERINFO, ;";
        try {
            System.out.println(SQLUtils.formatMySql(sql));
        } catch (ParserException e) {
            System.out.println(e.getMessage());
        }

    }


    public static void main(String[] args) {
        String sql = "SELECT * FROM USERINFO, ;";
        try {
            sql = SQLUtils.formatMySql(sql);
            System.out.println(Thread.getAllStackTraces());
            System.out.println(sql);
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
    }


}
