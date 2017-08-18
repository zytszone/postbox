package cn.datai.gift.web.vo;

import cn.datai.gift.persist.po.TBoxInfo;

/**
 * Created by Administrator on 2017/8/18.
 */
public class TBoxInfoVo extends TBoxInfo {
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
