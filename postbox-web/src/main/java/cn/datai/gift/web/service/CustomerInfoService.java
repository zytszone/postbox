package cn.datai.gift.web.service;

import cn.datai.gift.persist.po.TCustomerInfo;
import cn.datai.gift.persist.po.UserWxInfo;
import cn.datai.gift.persist.vo.UserInfoVo;
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
     * 通过参数查询微信信息
     * @param map
     * @return
     */
    UserInfoVo queryUserWxInfoByCons(Map<String,Object> map);

}
