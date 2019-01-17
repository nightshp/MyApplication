package com.lunzn.demo.bean;

/**
 * Desc: TODO
 * <p>
 * Author: Administrator
 * PackageName: com.lunzn.demo.bean
 * ProjectName: MyApplication
 * Date: 2018/12/20 16:41
 */
public class Article {

    private String title = null;

    private String url = null;

    private int progress = 0;

    private int id = 0;

    // 0 - 任务空闲， 1 - 下载中， 2 - 下载成功，  3 -- 下载失败,  4 -- 暂停下载
    private int state = 0;

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", progress=" + progress +
                ", id=" + id +
                ", state=" + state +
                '}';
    }
}
