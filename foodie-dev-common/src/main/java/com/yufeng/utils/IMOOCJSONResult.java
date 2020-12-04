package com.yufeng.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

/**
 * 描述:
 *      自定义响应数据结构
 *          本类可提供给 H5/IOS/安卓/公众号/小程序 使用
 *          前端接受此类数据(json object)后, 可自行根据业务去实现相关功能
 *
 *          200: 表示成功
 *          500: 表示错误, 错误信息在msg字段中
 *          501: bean验证错误, 不管多少个错误都以map形式返回
 *          502: 拦截器拦截到用户token出错
 *          555: 异常抛出新
 *          556: 用户qq校验异常
 *
 * @author yufeng
 * @create 2020-10-20
 * @version V1.0
 */
@Data
public class IMOOCJSONResult {

    // 定义jackson 对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    /**
     * todo 它这里使用的是Object类型，跟我在pay项目中使用的是泛型有区别!
     */
    private Object data;

    @JsonIgnore
    private String ok;

    public static IMOOCJSONResult build(Integer status, String msg, Object data) {
        return new IMOOCJSONResult(status, msg, data);
    }

    public static IMOOCJSONResult build(Integer status, String msg, Object data, String ok) {
        return new IMOOCJSONResult(status, msg, data, ok);
    }

    public static IMOOCJSONResult ok(Object data) {
        return new IMOOCJSONResult(data);
    }

    public static IMOOCJSONResult ok() {
        return new IMOOCJSONResult(null);
    }

    public static IMOOCJSONResult errorMsg(String msg) {
        return new IMOOCJSONResult(500, msg, null);
    }

    public static IMOOCJSONResult errorMap(Object data) {
        return new IMOOCJSONResult(501, "error", data);
    }

    public static IMOOCJSONResult errorTokenMsg(String msg) {
        return new IMOOCJSONResult(502, msg, null);
    }

    public static IMOOCJSONResult errorException(String msg) {
        return new IMOOCJSONResult(555, msg, null);
    }

    public static IMOOCJSONResult errorUserQQ(String msg) {
        return new IMOOCJSONResult(556, msg, null);
    }

    public IMOOCJSONResult() {}

    public IMOOCJSONResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public IMOOCJSONResult(Integer status, String msg, Object data, String ok) {
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.ok = ok;
    }

    public IMOOCJSONResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public Boolean isOK() {
        return this.status == 200;
    }
}
