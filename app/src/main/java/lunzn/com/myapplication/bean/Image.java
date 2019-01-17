package lunzn.com.myapplication.bean;

/**
 * Desc: TODO
 * <p>listview显示实体类
 * Author: meijie
 * PackageName: lunzn.com.myapplication.bean
 * ProjectName: MyApplication
 * Date: 2018/12/23 14:24
 */
public class Image {
    /**
     * 图片url
     */
    private String imgUrl;

    /**
     * 文本显示
     */
    private String txt;

    public Image(String imgUrl, String txt) {
        this.imgUrl = imgUrl;
        this.txt = txt;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
}
