package com.example.mysurfaceview.orderapp.mode;

/**
 * 菜单模型
 */
public class MenuMOode {

    int id;
    String category;
    String menuname;
    int price;
    int units;
    String pic;
    String version;
    String remark;
    boolean isCherked;

    public boolean isCherked() {
        return isCherked;
    }

    public void setIsCherked(boolean isCherked) {
        this.isCherked = isCherked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
