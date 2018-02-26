package com.springboot.shiro.config.exception;

import com.alibaba.fastjson.JSONObject;
import com.springboot.shiro.util.CommonUtil;
import com.springboot.shiro.util.constants.ErrorEnum;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * 统一异常处理器
 * <p>
 * Created by shihao 2018/2/24 15:38
 */

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger("GlobalExceptionHandler");

    @ExceptionHandler(value = Exception.class)
    public JSONObject defaultErrorHandler(HttpServletRequest resq, Exception exception) throws Exception {
        String errorPosition = "";
        //如果错误堆栈信息存在
        if (exception.getStackTrace().length > 0) {
            StackTraceElement stackTraceElement = exception.getStackTrace()[0];
            String fileName = stackTraceElement.getFileName() == null ? "未找到错误文件" : stackTraceElement.getFileName();
            int lineNumber = stackTraceElement.getLineNumber();
            errorPosition = fileName + ":" + lineNumber;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("returnCode", ErrorEnum.E_400.getErrorCode());
        jsonObject.put("returnMsg", ErrorEnum.E_400.getErrorMsg());
        JSONObject errorObject = new JSONObject();
        errorObject.put("errorLocation", exception.toString() + "错误位置:" + errorPosition);
        jsonObject.put("returnData", errorObject);
        logger.error("异常", exception);
        return jsonObject;
    }

    /**
     * GET/POST 错误拦截器
     *
     * @return
     * @throws Exception
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public JSONObject httpRequestMethodHandel() throws Exception {
        return CommonUtil.errorJson(ErrorEnum.E_500);
    }


    /**
     * 自定义错误拦截器 拦截错误之后 返回对应json给前端
     *
     * @param commonJsonException
     * @return
     */
    @ExceptionHandler(CommonJsonException.class)
    public JSONObject commonJsonExceptionHandler(CommonJsonException commonJsonException) throws Exception {
        return commonJsonException.getResultJson();
    }


    /**
     * 未登录报错拦截 在请求需要权限的接口时，而连登录都还未登录的时候，会报此错。
     *
     * @return
     * @throws Exception
     */
    @ExceptionHandler(UnauthenticatedException.class)
    public JSONObject unauthenticatedException() throws Exception {
        return CommonUtil.errorJson(ErrorEnum.E_20011);
    }


    /**
     * 权限不足报错拦截
     *
     * @return
     * @throws Exception
     */
    @ExceptionHandler(UnauthorizedException.class)
    public JSONObject unauthorizedExceptionHandler() throws Exception {
        return CommonUtil.errorJson(ErrorEnum.E_502);
    }

}
