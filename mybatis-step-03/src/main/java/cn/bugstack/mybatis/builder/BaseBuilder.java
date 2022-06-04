package cn.bugstack.mybatis.builder;

import cn.bugstack.mybatis.session.Configuration;

/**
 * @author yao.shen
 */
public class BaseBuilder {
    protected Configuration configuration;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
    }
}
