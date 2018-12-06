package com.javartisan.mybatis.plugin;

import org.apache.commons.lang3.reflect.TypeUtils;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;

import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * org.apache.ibatis.plugin.InterceptorChain 拦截器链，用于存储自定义拦截器之后进行拦截
 */
@Intercepts(@Signature(type = StatementHandler.class, method = "query",
        args = {Statement.class, ResultHandler.class}))
public class SqlExecutingCost implements Interceptor {

    private Properties properties;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Long startTime = System.currentTimeMillis();
        StatementHandler statementHandler = ((StatementHandler) invocation.getTarget());
        try {
            return invocation.proceed();
        } catch (Exception ex) {
            throw ex;
        } finally {

            // sql语句的抽象表示
            BoundSql boundSql = statementHandler.getBoundSql();
            Object boundSqlParameterObject = boundSql.getParameterObject();

            Class sqlParamsClazz = boundSqlParameterObject.getClass();

            // 参数
            ParameterHandler parameterHandler = statementHandler.getParameterHandler();
            Map<Object, Object> params = (Map<Object, Object>) parameterHandler.getParameterObject();

            // 参数属性名字
            List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();


            String sql = boundSql.getSql();

            for (int i = 0; i < parameterMappings.size(); i++) {
                // 基本类型或者基本类型的封装类型与集合类型获取参数方法不一样
                ParameterMapping pm = parameterMappings.get(i);

                String propertyName = pm.getProperty();
                Object paramValue = null;
                if (isPrimitiveOrPrimitiveWrapper(sqlParamsClazz)) {
                    paramValue = params.get(propertyName);
                } else {
                    paramValue = boundSql.getAdditionalParameter(propertyName);
                }

                sql = sql.replaceFirst("\\?", String.valueOf(paramValue));
            }
            System.out.println("SQL : [  " + sql + " ]  =>  Cost Time : [" + (System.currentTimeMillis() - startTime) + "]");
        }


    }

    /**
     * 对于plugin方法而言，其实Mybatis已经为我们提供了一个实现。Mybatis中有一个叫做Plugin的类，
     * 里面有一个静态方法wrap(Object target,Interceptor interceptor)，
     * 通过该方法可以决定要返回的对象是目标对象还是对应的代理。
     *
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {

        return Plugin.wrap(target, this);
    }

    /**
     * 用于配置插件时候配置的属性，参见：mybatis-config.xml
     *
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        System.out.println("properties = [ " + properties + "]");
        this.properties = properties;
    }


    public static boolean isPrimitiveOrPrimitiveWrapper(Class<?> parameterObjectClass) {
        return parameterObjectClass.isPrimitive() ||
                (parameterObjectClass.isAssignableFrom(Byte.class) || parameterObjectClass.isAssignableFrom(Short.class) ||
                        parameterObjectClass.isAssignableFrom(Integer.class) || parameterObjectClass.isAssignableFrom(Long.class) ||
                        parameterObjectClass.isAssignableFrom(Double.class) || parameterObjectClass.isAssignableFrom(Float.class) ||
                        parameterObjectClass.isAssignableFrom(Character.class) || parameterObjectClass.isAssignableFrom(Boolean.class));
    }

}
