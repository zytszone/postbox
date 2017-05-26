package cn.datai.gift.web.model.createMenu;

/**
* 类名: CommonButton </br>
* 包名： com.souvc.weixin.menu
* 描述: 子菜单项 :没有子菜单的菜单项，有可能是二级菜单项，也有可能是不含二级菜单的一级菜单。 </br>
* 开发人员： souvc  </br>
* 创建时间：  2015-12-1 </br>
* 发布版本：V1.0  </br>
 */
public class CommonButton extends Button {
    
    private String type;
    private String key;

    private String url;

    public CommonButton(String name,String type,String key,String url){
        if("view".equals(type)){
            this.setName(name);
            this.setType(type);
            this.setUrl(url);
        }
        if("click".equals(type)){
            this.setName(name);
            this.setType(type);
            this.setKey(key);
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}