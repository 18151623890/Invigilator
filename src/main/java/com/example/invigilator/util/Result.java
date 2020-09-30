package com.example.invigilator.util;

/**
 * @author zwz
 * @date 2020/9/30 23:34
 * @description 接口返回统一规范
 */
public class Result {

    //状态码
    private String code;

    //提示信息
    private String msg;

    //数据
    private Object data;

    public Result() {
    }

    public Result(String code) {
        this.code = code;
    }

    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(String code, Object data) {
        this.code = code;
        this.data = data;
    }

    public static Result success(String msg){
        return new Result(StatusCode.REQUEST_SUCCESS,msg);
    }

    public static Result success(Object data){
        return new Result(StatusCode.REQUEST_SUCCESS,data);
    }

    public static Result failure(){
        return new Result(StatusCode.REQUEST_ERROR);
    }

    public static Result failure(String msg){
        return new Result(StatusCode.REQUEST_ERROR,msg);
    }

    public static Result failureCode(String code){
        return new Result(code);
    }


}
