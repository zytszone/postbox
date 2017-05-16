package cn.datai.gift.web.service;

import cn.datai.gift.persist.po.UserInfo;

/**
 * Created by H.CAAHN on 2017/5/16.
 */
public interface UserInfoService {
    /**
     * 根据手机号查询UserInfo
     * @param mobilePhone
     * @return
     */
    UserInfo queryByMobilePhone(String mobilePhone);
}
