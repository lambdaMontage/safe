package com.springboot.shiro.config.exception;

import com.alibaba.fastjson.JSONObject;
import com.springboot.shiro.util.CommonUtil;
import com.springboot.shiro.util.constants.ErrorEnum;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 针对404做系统错误拦截
 * Created by shihao 2018/2/24 16:05
 */

@Controller
public class SiteErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";


    /**
     * 主要是登陆后的各种错误路径  404页面改为返回此json
     *
     * @return 501的错误json
     */
    @RequestMapping(value = ERROR_PATH)
    @ResponseBody
    public JSONObject handleError() {
        return CommonUtil.errorJson(ErrorEnum.E_501);
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
