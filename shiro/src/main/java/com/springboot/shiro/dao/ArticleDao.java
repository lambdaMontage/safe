package com.springboot.shiro.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * 文章dao层
 * Created by shihao 2018/2/24 18:35
 */
public interface ArticleDao {

    /**
     * 新增文章
     *
     * @param jsonObject
     * @return
     */
    int addArticleDao(JSONObject jsonObject);

    /**
     * 统计文章数量
     *
     * @param jsonObject
     * @return
     */
    int countArticle(JSONObject jsonObject);

    /**
     * 文章列表
     *
     * @param jsonObject
     * @return
     */
    List<JSONObject> listArticle(JSONObject jsonObject);

    /**
     * 更新文章
     *
     * @param jsonObject
     * @return
     */
    int updateArticle(JSONObject jsonObject);
}
