package scau.zzf.dictionary;

/**
 * Created by zzf on 2017/1/15.
 */
public enum Code {
    OPERATION_SUCCESS(1, "操作成功"),
    ADD_DATA_ERROR(2, "新增失败"),
    QUERY_DATA_ERROR(3, "查询失败"),
    ILLEGAL_DATA_ERROR(4,"非法参数"),
    USER_NO_EXIST(5,"用户不存在"),
    INCORRECT(6,"用户名或者密码错误"),
    UPDATE_DATA_ERROR(7,"更新失败"),
    USER_EXIST(8,"该用户已被注册"),
    USER_SUCCESS(9,"该用户可以注册"),
    PHONECODE_ERROR(10,"手机验证码错误"),
    UN_LOGIN(10,"用户未登陆"),
    REJECT(11, "该资源不存在或者拒绝访问"),
    ;
    private int statusCode;
    private String statusMsg;
    Code(int statusCode, String statusMsg) {
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
    }
    public int getStatusCode() {
        return statusCode;
    }
    public String getStatusMsg() {
        return statusMsg;
    }
}
