package xin.lowang.token.link.client;

import xin.lowang.token.link.api.RpcApi;

/**
 * 链克应用客户端
 *
 * @author Wang.ch
 */
public class LinkTokenApplication {
  public static void main(String[] args) {
    System.out.println(RpcApi.getTransactions("0x0ad170c0edf12080e5f731dc59d2680f7c5f095b", 1, 20));
  }
}
