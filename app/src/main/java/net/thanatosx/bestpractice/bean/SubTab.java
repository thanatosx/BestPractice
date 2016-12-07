package net.thanatosx.bestpractice.bean;

import java.io.Serializable;

/**
 * Created by thanatosx on 16/10/26.
 */

public class SubTab implements Serializable {

    public static final String TAG_NEW = "new";
    public static final String TAG_HOT = "hot";

    public static final int BANNER_CATEGORY_NEWS = 1;
    public static final int BANNER_CATEGORY_EVENT = 2;

    private String token;
    private String name;
    private boolean fixed;
    private boolean needLogin;
    private String tag;
    private int type;
    private int subtype;
    private int order;
    private String href;
    private Banner banner;


    public class Banner implements Serializable {
        private int catalog;
        private String href;

        public int getCatalog() {
            return catalog;
        }

        public void setCatalog(int catalog) {
            this.catalog = catalog;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }
    }

    @Override
    public int hashCode() {
        return this.token == null ? 0 : this.token.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof SubTab){
            SubTab tab = (SubTab) obj;
            if (tab.getToken() == null) return false;
            if (this.token == null) return false;
            return tab.getToken().equals(this.token);
        }
        return false;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public boolean isNeedLogin() {
        return needLogin;
    }

    public void setNeedLogin(boolean needLogin) {
        this.needLogin = needLogin;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSubtype() {
        return subtype;
    }

    public void setSubtype(int subtype) {
        this.subtype = subtype;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Banner getBanner() {
        return banner;
    }

    public void setBanner(Banner banner) {
        this.banner = banner;
    }
}
