package com.ob.rpc.common.exception;

/**
 * @author YanJi(yanji.lz@cainiao.com)
 * @since 16/9/13
 */
public class SystemException extends RuntimeException{
    private String errorCode;
    private String errorMsg;

    public SystemException(String errorCode, String errorMsg){
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }



    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
