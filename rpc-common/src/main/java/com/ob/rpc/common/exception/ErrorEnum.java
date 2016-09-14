package com.ob.rpc.common.exception;

/**
 * @author YanJi(yanji.lz@cainiao.com)
 * @since 16/9/13
 */
public enum ErrorEnum {
    UNKNOW_ERROR("UNKNOW_ERROR"),
    REGIST_ZK_ERROR("REGIST_ZK_ERROR"),
    SERVICE_INVOKE("SERVICE_INVOKE_ERROR");



    private String name;

    private ErrorEnum(String name){
        this.name=name;
    }

    public String toString(){
        return name;
    }
}
