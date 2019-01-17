package lunzn.com.transmit.bean;

import org.litepal.crud.DataSupport;

/**
 * Desc: Book实体
 * <p>
 * Author: suhongpeng
 * PackageName: lunzn.com.transmit.bean
 * ProjectName: MyApplication4
 * Date: 2019/1/16 16:18
 */
public class Book extends DataSupport {
    // ID
    private int bId;
    // 书名
    private String bName;
    // 作者
    private String author;

    public int getbId() {
        return bId;
    }

    public void setbId(int bId) {
        this.bId = bId;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bId=" + bId +
                ", bName='" + bName + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
