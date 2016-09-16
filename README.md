
# 目录说明
rpc-boot  springboot启动模式   
rpc-client 给消费者提供    
rpc-common    
rpc-registry 注册接口    
rpc-registry-zookeeper 注册实现    
rpc-server 服务通道    
rpc-web web启动模式    

# 部署说明
## install zkServer    
完毕后记得zkServer.sh start 
## update /etc/hosts
127.0.0.1 genes.ob.com
## mvn clean install      
## 启动模式一：spring boot 
rpc-boot    
run Bootstrap
## 启动模式二：web war
用rpc-web启动     

   
     
