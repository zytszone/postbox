package cn.datai.gift.web.plugin.vo;

/**
 * 从微信服务器获取的用户令牌的相关数据
 */
public class WeixinUserToken {
    private String accessToken;

    private int expiresIn;

    private String refreshToken;

    private String openid;

    private String scope;

    private String unionid;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeixinUserToken that = (WeixinUserToken) o;

        if (expiresIn != that.expiresIn) return false;
        if (!accessToken.equals(that.accessToken)) return false;
        if (!refreshToken.equals(that.refreshToken)) return false;
        if (!openid.equals(that.openid)) return false;
        if (!scope.equals(that.scope)) return false;
        return !(unionid != null ? !unionid.equals(that.unionid) : that.unionid != null);

    }

    @Override
    public int hashCode() {
        int result = accessToken.hashCode();
        result = 31 * result + expiresIn;
        result = 31 * result + refreshToken.hashCode();
        result = 31 * result + openid.hashCode();
        result = 31 * result + scope.hashCode();
        result = 31 * result + (unionid != null ? unionid.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WeixinUserToken{" +
                "accessToken='" + accessToken + '\'' +
                ", expiresIn=" + expiresIn +
                ", refreshToken='" + refreshToken + '\'' +
                ", openid='" + openid + '\'' +
                ", scope='" + scope + '\'' +
                ", unionid='" + unionid + '\'' +
                '}';
    }
}
