package xin.lowang.token.link.server.api.vo;

import java.io.Serializable;

public class DeviceInfoVo implements Serializable {

    private String name;
    private String sn;
    private String ip;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
