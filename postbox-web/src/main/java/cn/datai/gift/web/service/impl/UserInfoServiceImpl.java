package cn.datai.gift.web.service.impl;

import cn.datai.gift.web.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by H.CAAHN on 2017/5/16.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapperExt userInfoMapperExt;

    @Override
    public UserInfo queryByMobilePhone(String mobilePhone) {
        UserInfoExample example = new UserInfoExample();
        example.createCriteria().andMobilePhoneEqualTo(mobilePhone);
        List<UserInfo> userList = this.userInfoMapperExt.selectByExample(example);
        if (userList != null && !userList.isEmpty()) {
            return userList.get(0);
        }
        return null;
    }
}
