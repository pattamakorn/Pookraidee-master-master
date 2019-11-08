package com.pookraidee.panupongthongsri.pookraidee;

public class Shistory {

    private String starttree,endtree,ntree,wtree,rtree;

    public Shistory() {
    }

    public Shistory(String starttree, String endtree, String ntree, String wtree, String rtree) {
        this.starttree = starttree;
        this.endtree = endtree;
        this.ntree = ntree;
        this.wtree = wtree;
        this.rtree = rtree;
    }

    public String getStarttree() {
        return starttree;
    }

    public void setStarttree(String starttree) {
        this.starttree = starttree;
    }

    public String getEndtree() {
        return endtree;
    }

    public void setEndtree(String endtree) {
        this.endtree = endtree;
    }

    public String getNtree() {
        return ntree;
    }

    public void setNtree(String ntree) {
        this.ntree = ntree;
    }

    public String getWtree() {
        return wtree;
    }

    public void setWtree(String wtree) {
        this.wtree = wtree;
    }

    public String getRtree() {
        return rtree;
    }

    public void setRtree(String rtree) {
        this.rtree = rtree;
    }
}
