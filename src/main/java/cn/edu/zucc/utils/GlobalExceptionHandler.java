package cn.edu.zucc.utils;


import cn.edu.zucc.response.Result;
import cn.edu.zucc.response.ResultCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 全局异常处理，没有指定异常，所有异常都会被捕获
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.error();
    }


}
