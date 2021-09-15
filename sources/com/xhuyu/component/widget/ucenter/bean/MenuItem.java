package com.xhuyu.component.widget.ucenter.bean;

public class MenuItem {
    public static final int TYPE_EXT = 1;
    public static final int TYPE_NATIVE = 0;
    private String imgName;
    private String imgUrl;
    private String name;
    private int type;
    private String url;

    public MenuItem(String name2, String imgName2, int type2) {
        this.name = name2;
        this.type = type2;
        this.imgName = imgName2;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name2) {
        this.name = name2;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type2) {
        this.type = type2;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url2) {
        this.url = url2;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl2) {
        this.imgUrl = imgUrl2;
    }

    public String getImgName() {
        return this.imgName;
    }

    public void setImgName(String imgName2) {
        this.imgName = imgName2;
    }
}
