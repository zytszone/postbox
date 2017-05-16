package cn.datai.gift.web.service.impl;

import cn.datai.gift.persist.po.UserInfo;
import cn.datai.gift.web.service.BoxInfoService;
import cn.datai.gift.web.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by H.CAAHN on 2017/5/16.
 */
@Service
public class BoxInfoServiceImpl implements BoxInfoService {
    @Autowired
    private UserInfoService userInfoService;

    public String updateForDecode(Long boxId, String mkey, UserInfo userInfo) {
        return null;
    }
}
