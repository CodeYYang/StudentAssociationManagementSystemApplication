package cn.edu.zucc.response;

/**
 * 规定:
 * #200表示成功
 * #1001～1999 区间表示参数错误
 * #2001～2999 区间表示用户错误
 * #3001～3999 区间表示接口异常
 * #后面对什么的操作自己在这里注明就行了
 */
public enum ResultCode implements IResultCode{
    /* 成功 */
    SUCCESS(200, "成功"),

    /* 默认失败 */
    ERROR(201, "失败"),

    /**
     * 用户部分1001-1099
     */
    LOGIN_ERROR(1001,"账户或密码错误");

    private Integer code;

    private String message;

    ResultCode(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
