package com.AguilarWebDev.AguilarWebDevBackEnd.component.work;

import com.AguilarWebDev.AguilarWebDevBackEnd.component.blog.MetaData.MetaData;

public class Work {
    private MetaData metaData;
    private String fileName;

    public Work(MetaData metaData, String fileName) {
        this.metaData = metaData;
        this.fileName = fileName;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Work work = (Work) o;

        if (metaData != null ? !metaData.equals(work.metaData) : work.metaData != null) return false;
        return fileName != null ? fileName.equals(work.fileName) : work.fileName == null;
    }

    @Override
    public int hashCode() {
        int result = metaData != null ? metaData.hashCode() : 0;
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Work{" +
                "metaData=" + metaData +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
