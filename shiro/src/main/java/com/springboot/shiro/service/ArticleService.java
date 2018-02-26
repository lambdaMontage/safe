package com.springboot.shiro.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 文章Service层
 * <p>
 * Created by shihao 2018/2/24 18:11
 */
public interface ArticleService {

    /**
     * 增加文章
     *
     * @param jsonObject
     * @return
     */
    JSONObject addArticle(JSONObject jsonObject);

    /**
     * 查看所有文章
     *
     * @param jsonObject
     * @return
     */
    JSONObject listArticle(JSONObject jsonObject);

    /**
     * 修改文章
     *
     * @param jsonObject
     * @return
     */
    JSONObject updateArticle(JSONObject jsonObject);
}
