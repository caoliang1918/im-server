package org.zhongweixian.live.exception;

/**
 * Created by caoliang on 2019-07-24
 */
public class NettyException extends RuntimeException {

    /**
     * 错误码
     **/
    private ErrorCode errorCode;

    public NettyException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;

    }

    public NettyException(ErrorCode errorCode, Throwable t) {
        this.errorCode = errorCode;
    }

    public NettyException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public NettyException(ErrorCode errorCode, String message, Throwable t) {
        super(message, t);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
