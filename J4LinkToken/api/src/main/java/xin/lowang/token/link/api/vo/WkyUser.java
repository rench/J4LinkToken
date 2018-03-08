package xin.lowang.token.link.api.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 玩客云用户信息
 *
 * @author Wang.ch
 * @date 2018-3-8 13:53:04
 */
public class WkyUser {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 加密后的密码
     */
    private String encodedPassword;
    /**
     * uuid
     */
    private String uuid;
    /**
     * 设备sn
     */
    private String sn;
    /**
     * 设备peerid
     */
    private String peerId;
    /**
     * 状态
     */
    private String status;
    /**
     * 名称
     */
    private String name;
    /**
     * ip地址
     */
    private String ip;
    /**
     * 昨日收入
     */
    private String yesterday;
    /**
     * 余额
     */
    private String balance;
    /**
     * 提币地址
     */
    private String addr;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getPeerId() {
        return peerId;
    }

    public void setPeerId(String peerId) {
        this.peerId = peerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getYesterday() {
        return yesterday;
    }

    public void setYesterday(String yesterday) {
        this.yesterday = yesterday;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("username", username)
                .append("password", password)
                .append("encodedPassword", encodedPassword)
                .append("uuid", uuid)
                .append("sn", sn)
                .append("peerId", peerId)
                .append("status", status)
                .append("name", name)
                .append("ip", ip)
                .append("yesterday", yesterday)
                .append("balance", balance)
                .append("addr", addr)
                .toString();
    }
}
