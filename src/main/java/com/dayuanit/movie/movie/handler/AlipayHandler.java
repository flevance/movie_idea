package com.dayuanit.movie.movie.handler;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.dayuanit.movie.movie.entity.Order;
import com.dayuanit.movie.movie.exception.BizException;
import com.dayuanit.movie.movie.service.FilmService;
import com.dayuanit.movie.movie.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AlipayHandler {

    @Autowired
    private FilmService filmService;
    @Autowired
    private OrderService orderService;
    @Value("${alipay.serverUrl}")
    private String serverUrl;
    @Value("${alipay.appId}")
    private String appId;
    @Value("${alipay.privateKey}")
    private String privateKey;
    @Value("${alipay.alipayPublicKey}")
    private String alipayPublicKey;
    @Value("${alipay.returnUrl}")
    private String returnUrl;
    @Value("${alipay.notifyUrl}")
    private String notifyUrl;
    @Value("${alipay.publicKey}")
    private String publicKey;

    private static final String TRADE_SUCCESS = "TRADE_SUCCESS";

    private static final String TRADE_FINISHED = "TRADE_FINISHED";

    public String alipayProcess(Order order){
        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl,appId,privateKey, "json","UTF-8",alipayPublicKey,"RSA2");
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(returnUrl + order.getId());
        alipayRequest.setNotifyUrl(notifyUrl);

        String body = "大猿影院购票系统";
        alipayRequest.setBizContent("{\"out_trade_no\":\""+order.getId()+"\"," +
                "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "\"total_amount\": \"" + order.getAmount() + "\"," +
                "\"subject\":\"" + filmService.showFilm(order.getFilmId()).getFilmName() + "\"," +
                "\"body\":\"" + body + "\"" +
                "}");
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return form;
    }

    public void alipayNotify(Map<String, String[]> parameterMap){

        Map<String,String> map = new HashMap<>(parameterMap.size());
        for (Map.Entry<String,String[]> entry : parameterMap.entrySet()){
            map.put(entry.getKey(),entry.getValue()[0]);
        }
        try {
            boolean signVerified = AlipaySignature.rsaCheckV1(map, publicKey, "UTF-8", "RSA2");
            //进行验签操作,如果为false,则说明验签不通过
            if (!signVerified){
                throw new BizException("验签不通过");
            }
            String trade_status = map.get("trade_status");
            if ( !TRADE_SUCCESS.equals(trade_status) && !TRADE_FINISHED.equals(trade_status)){
                throw new BizException("交易未完成");
            }

            String outTradeNo = map.get("out_trade_no");
            String totalAmount = map.get("total_amount");
            if (!appId.equals(map.get("app_id"))){
                throw new BizException("商户ID不一致");
            }

            boolean b = orderService.processAliPayNotify(outTradeNo, totalAmount);
            if (!b){
                throw new BizException("交易状态更改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void alipayQuery(Order order){
        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl,appId,privateKey,"json","UTF-8",alipayPublicKey,"RSA2");
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent("{\"out_trade_no\":\""+ order.getId() + "\"}");
        try {
            AlipayTradeQueryResponse response = alipayClient.execute(request);
            if(!response.isSuccess()){
                System.out.println("调用失败");
            }
            String tradeStatus = response.getTradeStatus();
            String totalAmount = response.getTotalAmount();
            if ( !TRADE_SUCCESS.equals(tradeStatus) && !TRADE_FINISHED.equals(tradeStatus)){
                throw new BizException("交易未完成");
            }
            boolean b = orderService.processQueryOrder(order, totalAmount);
            if (!b){
                throw new BizException("修改失败");
            }

        }catch (Exception e){
            e.printStackTrace();
        }




    }


}
