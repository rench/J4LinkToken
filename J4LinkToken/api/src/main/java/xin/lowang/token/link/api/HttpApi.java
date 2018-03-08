package xin.lowang.token.link.api;

import cn.hutool.crypto.digest.DigestUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

}
