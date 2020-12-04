package org.nee.ny.video.recording.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author: alec
 * Description:
 * @date: 18:02 2020-11-12
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResponseResult<T> {

    private Integer code;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 数据
     */
    private T data;

    /**
     * 附加webhook 数据,
     * 一般不赋值不显示
     * */

    private Boolean enableHls;

    private Boolean enableMP4;

    private String realm;

    private Boolean encrypted;

    private String passwd;

    private Boolean close;



    static <T> ResponseResult<T> ok(T monoBody) {
        final ResponseResult<T> responseInfo = new ResponseResult<>();
        responseInfo.setCode(0);
        responseInfo.setData(monoBody);
        responseInfo.setMsg("success");
        return responseInfo;
    }

    static ResponseResult error (Integer code, String message) {
        final ResponseResult<?> responseInfo = new ResponseResult<>();
        responseInfo.setCode(code);
        responseInfo.setMsg(message);
        return responseInfo;
    }

    public static ResponseResult ok () {
        final ResponseResult responseInfo = new ResponseResult<>();
        responseInfo.setCode(0);
        responseInfo.setMsg("success");
        return responseInfo;
    }

    public static  ResponseResult ok (Boolean enableHls, Boolean enableMP4) {
        final ResponseResult responseInfo = new ResponseResult<>();
        responseInfo.setCode(0);
        responseInfo.setEnableHls(enableHls);
        responseInfo.setEnableMP4(enableMP4);
        responseInfo.setMsg("success");
        return responseInfo;
    }

    public static  ResponseResult faile (Boolean enableHls, Boolean enableMP4, Integer code,
                                         String message) {
        final ResponseResult responseInfo = new ResponseResult<>();
        responseInfo.setCode(code);
        responseInfo.setMsg(message);
        responseInfo.setEnableHls(enableHls);
        responseInfo.setEnableMP4(enableMP4);
        responseInfo.setMsg("success");
        return responseInfo;
    }

    public static  ResponseResult ok (String realm) {
        final ResponseResult responseInfo = new ResponseResult<>();
        responseInfo.setCode(0);
        responseInfo.setRealm(realm);
        return responseInfo;
    }

    public static  ResponseResult ok (String passwd, Boolean encrypted) {
        final ResponseResult responseInfo = new ResponseResult<>();
        responseInfo.setCode(0);
        responseInfo.setPasswd(passwd);
        responseInfo.setEncrypted(encrypted);
        return responseInfo;
    }

    public static  ResponseResult ok (Boolean isClose) {
        final ResponseResult responseInfo = new ResponseResult<>();
        responseInfo.setCode(0);
        responseInfo.setClose(isClose);
        return responseInfo;
    }
}
