package com.pookraidee.panupongthongsri.pookraidee;

public class plantsmodel {
    private String id;
    private String ph;
    private String moi;
    private String sun;
    private String price;
    private String name;
    private String img;
    private String rich;
    private String name_ph;


    public plantsmodel(String id, String ph, String moi, String sun, String price, String name, String img, String rich, String name_ph) {
        this.id = id;
        this.ph = ph;
        this.moi = moi;
        this.sun = sun;
        this.price = price;
        this.name = name;
        this.img = img;
        this.rich = rich;
        this.name_ph = name_ph;
    }

    public String getSun() {
        return sun;
    }

    public void setSun(String sun) {
        this.sun = sun;
    }

    public String getRich() {
        return rich;
    }

    public void setRich(String rich) {
        this.rich = rich;
    }

    public String getName_ph() {
        return name_ph;
    }

    public void setName_ph(String name_ph) {
        this.name_ph = name_ph;
    }




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getMoi() {
        return moi;
    }

    public void setMoi(String moi) {
        this.moi = moi;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
