package com.AguilarWebDev.AguilarWebDevBackEnd.component.blog.MetaData;

public class MetaData {
    private String type;
    private String imageTitle;
    private String siteUrl;

    public MetaData() {
    }

    public MetaData(String type, String imageTitle, String siteUrl) {
        this.type = type;
        this.imageTitle = imageTitle;
        this.siteUrl = siteUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MetaData metaData = (MetaData) o;

        if (type != null ? !type.equals(metaData.type) : metaData.type != null) return false;
        if (imageTitle != null ? !imageTitle.equals(metaData.imageTitle) : metaData.imageTitle != null) return false;
        return siteUrl != null ? siteUrl.equals(metaData.siteUrl) : metaData.siteUrl == null;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (imageTitle != null ? imageTitle.hashCode() : 0);
        result = 31 * result + (siteUrl != null ? siteUrl.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MetaData{" +
                "type='" + type + '\'' +
                ", imageTitle='" + imageTitle + '\'' +
                ", siteUrl='" + siteUrl + '\'' +
                '}';
    }
}
