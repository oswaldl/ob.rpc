package com.ob.rpc.client;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.Resource;

/**
 *
 * 这是一个给前端web工程用的工具类, 例如在spring.xml配置时需要
 <bean id="helloService" class="com.ob.rpc.client.LocalSpringBeanProxy" init-method="init">
 <property name="interfaceName" value="com.ob.service.api.HelloService" />
 </bean>
 *
 * @author YanJi(yanji.lz)
 * @since 16/8/16
 */
public class LocalSpringBeanProxy implements FactoryBean, InitializingBean {
    private static final Logger LOGGER = Logger.getLogger(LocalSpringBeanProxy.class);
    @Resource
    private RpcProxy rpcProxy;

    private String interfaceName;

    private Class cnfe;

    private Object bean;

    public Object getObject() throws Exception {
        return bean;
    }

    public Class<?> getObjectType() {
        return cnfe;
    }

    public boolean isSingleton() {
        return true;
    }

    /**
     * 请不要在这里打断点调试
     * @throws Exception
     */
    public void afterPropertiesSet() throws Exception {
        try {
            Class cnfe = Class.forName(interfaceName.trim());
            this.bean = rpcProxy.create(cnfe);
            this.cnfe = cnfe;
        } catch (ClassNotFoundException var4) {
            StringBuilder errorMsg = new StringBuilder();
            errorMsg.append("ConsumerBean中指定的接口类不存在[");
            errorMsg.append(interfaceName).append("].");
            LOGGER.warn(errorMsg);
        }
    }

    public void init() throws Exception {
        LOGGER.info("init...");
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public void setRpcProxy(RpcProxy rpcProxy) {
        this.rpcProxy = rpcProxy;
    }
}
