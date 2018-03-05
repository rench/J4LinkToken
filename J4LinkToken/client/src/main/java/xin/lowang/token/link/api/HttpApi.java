package xin.lowang.token.link.api;

import com.alibaba.fastjson.JSONArray;

import cn.hutool.http.HttpUtil;
import xin.lowang.token.link.consts.Constants;

/**
 * 链克HttpApi
 *
 * @author Wang.ch
 */
public class HttpApi {
  /**
   * 获取转账记录
   *
   * @param address
   * @param page
   * @param size
   * @return
   */
  public static String getTransactions(String address, Integer page, Integer size) {
    String url = Constants.RPC_URL + "getTransactionRecords";
    String[] data = {address, "0", "0", String.valueOf(page), String.valueOf(size)};
    return HttpUtil.post(url, JSONArray.toJSONString(data));
  }
}
