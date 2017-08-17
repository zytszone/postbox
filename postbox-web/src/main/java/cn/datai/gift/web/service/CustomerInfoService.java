package cn.datai.gift.web.service;

import cn.datai.gift.persist.po.TCustomerInfo;
import cn.datai.gift.persist.po.UserWxInfo;
import cn.datai.gift.utils.RespResult;
import cn.datai.gift.web.plugin.vo.UserLoginInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by H.CAAHN on 2017/6/19.
 */
public interface CustomerInfoService {
    TCustomerInfo queryById(Long customerId);

    /**
     * 绑定手机号
     * @param phone
     * @param customerName
     * @param isSpecial
     * @param userWxInfo
     * @param userLoginInfo
     * @param redirecturl
     * @param code
     * @param serverIds
     * @param request
     * @return
     */
    RespResult bind(String phone,
                    String customerName,
                    String isSpecial,
                    UserWxInfo userWxInfo,
                    UserLoginInfo userLoginInfo,
                    String redirecturl,
                    String code,
                    String serverIds,
                    HttpServletRequest request);

    /**
     * 通过Id查询用户信息
     * @param customerInfoId
     * @return
     */
    TCustomerInfo queryTCustomerInfoById(Long customerInfoId);

    /**
     * 通过参数查询用户openId
     * @param map
     * @return
     */
    String queryUserOpenId(Map<String,Object> map);

    /**
     * 根据手机号查询用户信息
     * @param mobile
     * @return
     */
    TCustomerInfo queryTCustomerInfoByMobile(String mobile);
}
