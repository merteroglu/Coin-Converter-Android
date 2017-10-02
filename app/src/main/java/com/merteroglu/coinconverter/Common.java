package com.merteroglu.coinconverter;

import com.merteroglu.coinconverter.Remote.CoinService;
import com.merteroglu.coinconverter.Remote.RetrofitClient;

/**
 * Created by Mert on 02.10.2017.
 */

public class Common {

    private static final String API_URL = "https://min-api.cryptocompare.com";
    private static final String BASE_URL = "https://cryptocompare.com";

    public static final String ETH_IMAGE = new StringBuilder(BASE_URL).append("/media/20646/eth.png").toString();
    public static final String BTC_IMAGE = new StringBuilder(BASE_URL).append("/media/19633/btc.png").toString();
    public static final String ETC_IMAGE = new StringBuilder(BASE_URL).append("/media/20275/etc2.png").toString();
    public static final String LTC_IMAGE = new StringBuilder(BASE_URL).append("/media/19782/litecoin-logo.png").toString();
    public static final String XRP_IMAGE = new StringBuilder(BASE_URL).append("/media/19972/ripple.png").toString();
    public static final String XMR_IMAGE = new StringBuilder(BASE_URL).append("/media/19969/xmr.png").toString();
    public static final String DASH_IMAGE = new StringBuilder(BASE_URL).append("/media/20026/dash.png").toString();
    public static final String MAID_IMAGE = new StringBuilder(BASE_URL).append("/media/352247/maid.png").toString();
    public static final String AUR_IMAGE = new StringBuilder(BASE_URL).append("/media/19608/aur.png").toString();
    public static final String XEM_IMAGE = new StringBuilder(BASE_URL).append("/media/20490/xem.png").toString();

    public static CoinService getCoinService(){
        return RetrofitClient.getClient(API_URL).create(CoinService.class);
    }


}
