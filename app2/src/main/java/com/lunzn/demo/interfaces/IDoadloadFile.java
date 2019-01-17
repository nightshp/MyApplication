package com.lunzn.demo.interfaces;

import com.lunzn.demo.bean.Article;

/**
 * Desc: TODO
 * <p>
 * Author: shirencong
 * PackageName: com.lunzn.demo.interfaces
 * ProjectName: MyApplication
 * Date: 2019/1/4 17:37
 */
public interface IDoadloadFile {

    public void startLoad(int position, Article mArticle);

    public void stopLoad(int position);

    public void delete(int position);

}
