package cn.datai.gift.web.contants;

/**
 * Redis key参数
 */
public interface RedisConstants {

    /** 微信accessToken*/
    String WEIXIN_ACCESS_TOKEN = "system:weixin_access_token:";

    /** 微信jsapiTicket*/
    String WEIXIN_JSAPI_TICKET = "system:weixin_jsapi_ticket:";

    /**
     * 系统参数配置参数
     */
    String SYSTEM_PARAMS = "SYSTEM_PARAMS";

    /**
     * 所有商品
     */
    String COMMODITY_ALL = "COMMODITYALL";

    /**
     * 所有商品
     */
    String COMMODITY_KEY = "COMMODITYKEY";

    /**
     *各自商品
     */
    String COMMODITY_SELF = "COMMODITYSELF";

    /**
     * 所有商品类型
     */
    String COMMODITY_ALL_TYPE = "COMMODITYALLTYPE";

    /**
     * 所有商品类型
     */
    String COMMODITY_ALL_TYPE_KEY = "COMMODITYALLTYPEKEY";


}
