package cn.datai.gift.persist.vo;

/**
 * Created by Administrator on 2017/8/18.
 */
public class UserInfoVo {

    private String openId;
    private String nickname;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "UserInfoVo{" +
                "openId='" + openId + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
