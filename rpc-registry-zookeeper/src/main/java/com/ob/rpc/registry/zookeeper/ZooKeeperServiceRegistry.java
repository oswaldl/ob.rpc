package com.ob.rpc.registry.zookeeper;

import com.ob.rpc.common.exception.ErrorEnum;
import com.ob.rpc.common.exception.SystemException;
import com.ob.rpc.registry.ServiceRegistry;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/**
 * 基于 ZooKeeper 的服务注册接口实现
 *
 * @author o&b
 *
 */
@Component
public class ZooKeeperServiceRegistry implements ServiceRegistry {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZooKeeperServiceRegistry.class);

    private final ZkClient zkClient;


    public ZooKeeperServiceRegistry(@Value("${rpc.registry.address}") String zkAddress) {
        try {
            // 创建 ZooKeeper 客户端
            zkClient = new ZkClient(zkAddress, Constant.ZK_SESSION_TIMEOUT, Constant.ZK_CONNECTION_TIMEOUT);
            LOGGER.info("connect zookeeper");
        }catch(Exception e){
            LOGGER.error("can not connect zk:"+zkAddress);
            throw new SystemException(ErrorEnum.REGIST_ZK_ERROR.toString(),"zkAddress:"+zkAddress);
        }


    }

    @Override
    public void register(String serviceName, String serviceAddress) {
        // 创建 registry 节点（持久）
        String registryPath = Constant.ZK_REGISTRY_PATH;
        if (!zkClient.exists(registryPath)) {
            zkClient.createPersistent(registryPath);
            LOGGER.debug("create registry node: {}", registryPath);
        }
        // 创建 service 节点（持久）
        String servicePath = registryPath + "/" + serviceName;
        if (!zkClient.exists(servicePath)) {
            zkClient.createPersistent(servicePath);
            LOGGER.debug("create service node: {}", servicePath);
        }
        // 创建 address 节点（临时）
        String addressPath = servicePath + "/address-";
        String addressNode = zkClient.createEphemeralSequential(addressPath, serviceAddress);
        LOGGER.debug("create address node: {}", addressNode);
    }


}