package com.springboot.shiro.config.exception;

import com.alibaba.fastjson.JSONObject;
import com.springboot.shiro.util.CommonUtil;
import com.springboot.shiro.util.constants.ErrorEnum;

/**
 * 自定义错误类返回json给前端
 * Created by shihao 2018/2/24 15:24
 */

public class CommonJsonException extends RuntimeException {
    private JSONObject resultJson;

    /**
     * 调用时可以在任何代码处直接throws这个Exception,
     * 都会统一被拦截,并封装好json返回给前台
     *
     * @param errorEnum 以错误的ErrorEnum做参数
     */
    public CommonJsonException(ErrorEnum errorEnum) {
        this.resultJson = CommonUtil.errorJson(errorEnum);
    }

    public CommonJsonException(JSONObject resultJson) {
        this.resultJson = resultJson;
    }

    public JSONObject getResultJson() {
        return resultJson;
    }
}
