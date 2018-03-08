package xin.lowang.token.link.api;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import org.web3j.utils.Convert.Unit;
import org.web3j.utils.Numeric;
import xin.lowang.token.link.api.consts.Constants;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.Proxy;

/**
 * 链克RpcApi
 *
 * @author Wang.ch
 */
public class RpcApi {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RpcApi.class);

    /**
     * 获取余额(单位:链克)
     *
     * @param address rpc地址
     * @param address 地址
     * @return
     */
    public static BigDecimal getBalance(String address, Proxy proxy) {
        Web3j web3j = buildWeb3j(proxy);
        try {
            EthGetBalance balance =
                    web3j.ethGetBalance(address, DefaultBlockParameterName.PENDING).send();
            return Convert.fromWei(balance.getBalance().toString(), Convert.Unit.ETHER);
        } catch (IOException e) {
            LOGGER.error("获取余额异常", e);
        }
        return null;
    }

    /**
     * 链克转账
     *
     * @param filePath  密钥文件
     * @param password  密码
     * @param toAddress 对方地址
     * @param toNum     转账数量
     * @param proxy     代理设置
     * @return
     * @throws Exception
     */
    public static String sendRawTransaction(
            String filePath, String password, String toAddress, BigDecimal toNum, Proxy proxy)
            throws Exception {
        Credentials cred = WalletUtils.loadCredentials(password, new File(filePath));
        BigInteger nonce = getTransactionCount(cred.getAddress(), proxy); //获取上次交易总数
        BigInteger gasLimit = BigInteger.valueOf(Long.parseLong("186a0", 16)); //单价上限
        BigInteger gasPrice = BigInteger.valueOf(Long.parseLong("174876e800", 16)); //单价
        BigDecimal num = Convert.toWei(toNum.toPlainString(), Unit.ETHER); //转换为wei
        RawTransaction tx =
                RawTransaction.createEtherTransaction(
                        nonce, gasPrice, gasLimit, toAddress, num.toBigInteger()); //创建交易
        byte[] data = TransactionEncoder.signMessage(tx, cred); //签名数据
        String encoded = Numeric.toHexString(data);
        Web3j web3j = buildWeb3j(proxy);
        EthSendTransaction send = web3j.ethSendRawTransaction(encoded).send();
        if (send.getError() == null) {
            String hash = send.getTransactionHash();
            return hash;
        } else {
            throw new RuntimeException(send.getError().getMessage());
        }
    }

    /**
     * 获取上次交易次数
     *
     * @param address
     * @param proxy
     * @return
     * @throws Exception
     */
    public static BigInteger getTransactionCount(String address, Proxy proxy) throws Exception {
        Web3j web3j = buildWeb3j(proxy);
        EthGetTransactionCount ethGetTransactionCount =
                web3j.ethGetTransactionCount(address, DefaultBlockParameterName.LATEST).send();
        BigInteger nonce = ethGetTransactionCount.getTransactionCount(); //获取上次交易总数
        return nonce;
    }

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

    /**
     * 新建web3j客户端
     *
     * @param proxy
     * @return web3j
     */
    private static Web3j buildWeb3j(Proxy proxy) {
        OkHttpClient okHttpClient;
        if (proxy != null) {
            okHttpClient = new OkHttpClient.Builder().proxy(proxy).build();
        } else {
            okHttpClient = new OkHttpClient.Builder().build();
        }
        HttpService service = new HttpService(Constants.RPC_URL, okHttpClient, true);
        service.addHeader("Nc", "IN");
        Web3j web3j = Web3j.build(service);
        return web3j;
    }

}
