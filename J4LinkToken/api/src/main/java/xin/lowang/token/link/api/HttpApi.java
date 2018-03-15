package xin.lowang.token.link.api;

import cn.hutool.crypto.digest.DigestUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xin.lowang.token.link.api.consts.Constants;
import xin.lowang.token.link.api.vo.WkyUser;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 链克HttpApi
 * 2018-3-8 13:49:20
 *
 * @author Wang.ch
 */
public class HttpApi {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpApi.class);

    /**
     * 密码混淆
     *
     * @param pwd 原始密码
     * @return 混淆后的密码
     */
    public String encodePassword(String pwd) {
        String md5Str = DigestUtil.md5Hex(pwd);
        char[] md5Char = md5Str.toCharArray();
        char swap = md5Char[2];
        md5Char[2] = md5Char[8];
        md5Char[8] = swap;
        swap = md5Char[17];
        md5Char[17] = md5Char[27];
        md5Char[27] = swap;
        return DigestUtil.md5Hex(new String(md5Char));
    }

    /**
     * 参数签名
     *
     * @param params
     * @param sessionid
     * @param isGet
     * @return
     */
    public static String singData(Map<String, String> params, String sessionid, boolean isGet) {
        TreeMap<String, String> map = new TreeMap<>(params);
        StringBuffer sb = new StringBuffer();
        map.forEach(
                (k, v) -> {
                    if (!StringUtils.isBlank(v)) {
                        sb.append("&").append(k).append("=").append(v);
                    }
                });
        if (isGet) {
            return DigestUtil.md5Hex(
                    sb.substring(1) + "&key=" + StringUtils.defaultIfBlank(sessionid, ""));
        } else {
            return DigestUtil.md5Hex(
                    sb.substring(1) + "&key=" + StringUtils.defaultIfBlank(sessionid, ""));
        }
    }

    /**
     * 构建form表单参数
     *
     * @param params
     * @return
     */
    public static String buildFormString(Map<String, String> params) {
        TreeMap<String, String> map = new TreeMap<>();
        map.putAll(params);
        StringBuffer sb = new StringBuffer();
        params.forEach(
                (k, v) -> {
                    sb.append("&").append(k).append("=").append(v);
                });
        return sb.substring(1);
    }

    /**
     * 用户登录
     *
     * @param user 用户信息
     */
    public static void login(WkyUser user) {
        String targetUrl = Constants.ACCOUNT_URL + "user/login?appversion=1.4.11"; //登录地址
        Map<String, String> formMap = new HashMap<>();
        String deviceid = RandomStringUtils.randomAlphanumeric(48);
        formMap.put("account_type", "4");
        formMap.put("deviceid", deviceid);
        formMap.put("imeiid", deviceid);
        formMap.put("ph_model", "iPhone 6");
        formMap.put("ph_ver", "iOS 10.3.3");
        formMap.put("phone", user.getUsername());
        formMap.put("pwd", user.getEncodedPassword());
        formMap.put("uuid", user.getUuid());
        formMap.put("sign", singData(formMap, user.getCookie("sessionid"), false));

    }

}
