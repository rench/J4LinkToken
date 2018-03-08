package xin.lowang.token.link.api.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * 玩客云设备信息
 *
 * @author Wang.ch
 * @date 2018-3-8 13:54:13
 */
public class WkyDevice {
    /**
     * 手机号
     */
    private String username;
    /**
     * 余额
     */
    private String balance;
    /**
     * 提币地址
     */
    private String addr;
    /**
     * 昨日收益
     */
    private String yes;
    /**
     * 序列号
     */
    private String sn;
    /**
     * 设备名称
     */
    private String name;
    /**
     * mac地址
     */
    private String mac;
    /**
     * 状态
     */
    private String status;
    /**
     * 外网ip
     */
    private String wanip;
    /**
     * ip归属地
     */
    private String ipinfo;
    /**
     * 局域网ip
     */
    private String lanip;
    /**
     * peerid
     */
    private String peerid;
    /**
     * 固件版本号
     */
    private String systemversion;
    /**
     * 错误信息
     */
    private String errmsg;
    /**
     * 原始json数据
     */
    private Map<String, Object> ext = new HashMap<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getYes() {
        return yes;
    }

    public void setYes(String yes) {
        this.yes = yes;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWanip() {
        return wanip;
    }

    public void setWanip(String wanip) {
        this.wanip = wanip;
    }

    public String getIpinfo() {
        return ipinfo;
    }

    public void setIpinfo(String ipinfo) {
        this.ipinfo = ipinfo;
    }

    public String getLanip() {
        return lanip;
    }

    public void setLanip(String lanip) {
        this.lanip = lanip;
    }

    public String getPeerid() {
        return peerid;
    }

    public void setPeerid(String peerid) {
        this.peerid = peerid;
    }

    public String getSystemversion() {
        return systemversion;
    }

    public void setSystemversion(String systemversion) {
        this.systemversion = systemversion;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Map<String, Object> getExt() {
        return ext;
    }

    public void setExt(Map<String, Object> ext) {
        this.ext = ext;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("username", username)
                .append("balance", balance)
                .append("addr", addr)
                .append("yes", yes)
                .append("sn", sn)
                .append("name", name)
                .append("mac", mac)
                .append("status", status)
                .append("wanip", wanip)
                .append("ipinfo", ipinfo)
                .append("lanip", lanip)
                .append("peerid", peerid)
                .append("systemversion", systemversion)
                .append("errmsg", errmsg)
                .append("ext", ext)
                .toString();
    }
}
