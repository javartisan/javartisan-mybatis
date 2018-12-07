package com.javartisan.service;


import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.parser.ParserException;
import com.alibaba.druid.util.JdbcUtils;
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
    public void formatMySqlErrorSQL() {
        String sql = "SELECT * FROM USERINFO";
        try {
            //toSQLExpr 底层是不catch异常的，会将异常返回给业务层，这样便于业务层处理错误信息
            SQLExpr sqlExpr = SQLUtils.toSQLExpr(sql, JdbcUtils.MYSQL);

            System.out.println(SQLUtils.formatMySql(sql));
        } catch (ParserException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * sql结尾的';'不属于SQL的一部分，因此在预发检查时候去掉结尾的分号，格式化时候可以添加分号
     */
    @Test
    public void formatMySqlSQL() {
        String sql = "SELECT * FROM USERINFO";
        try {
            //toSQLExpr 底层是不catch异常的，会将异常返回给业务层，这样便于业务层处理错误信息
            SQLExpr sqlExpr = SQLUtils.toSQLExpr(sql, JdbcUtils.MYSQL);

            System.out.println(SQLUtils.formatMySql(sql));
        } catch (ParserException e) {
            System.out.println(e.getMessage());
        }

    }

}
