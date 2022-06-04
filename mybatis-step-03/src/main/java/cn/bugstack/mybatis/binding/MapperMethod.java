package cn.bugstack.mybatis.binding;

import cn.bugstack.mybatis.mapping.MappedStatement;
import cn.bugstack.mybatis.mapping.SqlCommandType;
import cn.bugstack.mybatis.session.Configuration;
import cn.bugstack.mybatis.session.SqlSession;

import java.lang.reflect.Method;

/**
 * @author yao.shen
 */
public class MapperMethod {
    private final SqlCommand command;

    public MapperMethod(Class<?> mapperInterface,Method method,Configuration configuration){
        this.command = new SqlCommand(configuration, mapperInterface, method);
    }

    public Object execute(SqlSession sqlSession, Object[] args){
        Object result = null;
        switch (command.getType()){
            case INSERT:
                break;
            case DELETE:
                break;
            case UPDATE:
                break;
            case SELECT:
                result = sqlSession.selectOne(command.getName(), args);
                break;
            default:
                throw new RuntimeException("Unknown execution method for: " + command.getName());
        }
        return result;
    }



    public static class SqlCommand{
        //类+方法
        private final String name;
        //操作方法
        private final SqlCommandType type;

        public SqlCommand(Configuration configuration, Class<?> mapperInterface, Method method) {
            String statementName = mapperInterface.getName() + "." + method.getName();
            MappedStatement ms = configuration.getMappedStatement(statementName);
            name = ms.getId();
            type = ms.getSqlCommandType();
        }

        public String getName(){
            return name;
        }

        public SqlCommandType getType(){
            return type;
        }
    }
}
