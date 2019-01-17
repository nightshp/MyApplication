package lunzn.com.transmit.bean;

import java.util.List;

/**
 * Desc: TODO
 * <p>
 * Author: shirencong
 * PackageName: com.lunzn.demo.bean
 * ProjectName: MyApplication
 * Date: 2019/1/15 17:12
 */
public class FileCheckBean {

    private int Z = 0;

    private List<Info> data = null;


    public int getZ() {
        return Z;
    }

    public void setZ(int z) {
        Z = z;
    }

    public List<Info> getData() {
        return data;
    }

    public void setData(List<Info> data) {
        this.data = data;
    }

    class Info {

        String code = null;

        String name = null;

        String type = null;

        String url = null;

        String version = null;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "code='" + code + '\'' +
                    ", name='" + name + '\'' +
                    ", type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    ", version='" + version + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "FileCheckBean{" +
                "Z=" + Z +
                ", data=" + data +
                '}';
    }
}
