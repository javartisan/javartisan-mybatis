package com.javartisan.mybatis.plugin;

import org.apache.commons.lang3.reflect.TypeUtils;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.lang.reflect.Field;
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

            BoundSql boundSql = statementHandler.getBoundSql();
            String sql = boundSql.getSql();
            List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
            // StatementHandler里面持有ParameterHandler成员
            ParameterHandler paramterHandler = statementHandler.getParameterHandler();
            // 智者借力而行，参见源码实现：org.apache.ibatis.scripting.defaults.DefaultParameterHandler.setParameters()加以修改
            // 反射获取需要使用的成员
            TypeHandlerRegistry typeHandlerRegistry = getPrivateFieldValue(DefaultParameterHandler.class, paramterHandler, "typeHandlerRegistry");
            Configuration configuration = getPrivateFieldValue(DefaultParameterHandler.class, paramterHandler, "configuration");

            Object parameterObject = paramterHandler.getParameterObject();

            if (parameterMappings != null) {
                for (int i = 0; i < parameterMappings.size(); i++) {
                    ParameterMapping parameterMapping = parameterMappings.get(i);
                    if (parameterMapping.getMode() != ParameterMode.OUT) {
                        Object value;
                        String propertyName = parameterMapping.getProperty();
                        if (boundSql.hasAdditionalParameter(propertyName)) { // issue #448 ask first for additional params
                            value = boundSql.getAdditionalParameter(propertyName);
                        } else if (parameterObject == null) {
                            value = null;
                        } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                            value = parameterObject;
                        } else {
                            MetaObject metaObject = configuration.newMetaObject(parameterObject);
                            value = metaObject.getValue(propertyName);
                        }
                        sql = sql.replaceFirst("\\?", String.valueOf(value));
                    }
                }
            }

            System.out.println("========================================================================================");
            System.out.println("SQL = [ " + sql + "  ] , CostTime = [ " + (System.currentTimeMillis() - startTime) + "ms ].");
            System.out.println("========================================================================================");

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


    /**
     * @param clazz
     * @param object
     * @param fieldName
     * @return
     */
    public <T> T getPrivateFieldValue(Class clazz, Object object, String fieldName) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return (T) field.get(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
