package com.AguilarWebDev.AguilarWebDevBackEnd.component.blog.model;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Blog {
    @Id
    public String id;
    private String title;
    private String description;
    private String type;
    private List<String> tags;
    private List<String> imageName; //change to image id

    public Blog() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getImageName() {
        return imageName;
    }

    public void setImageName(List<String> imageName) {
        this.imageName = imageName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Blog blog = (Blog) o;

        if (id != null ? !id.equals(blog.id) : blog.id != null) return false;
        if (title != null ? !title.equals(blog.title) : blog.title != null) return false;
        if (description != null ? !description.equals(blog.description) : blog.description != null) return false;
        if (type != null ? !type.equals(blog.type) : blog.type != null) return false;
        if (tags != null ? !tags.equals(blog.tags) : blog.tags != null) return false;
        return imageName != null ? imageName.equals(blog.imageName) : blog.imageName == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (imageName != null ? imageName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", MetaData='" + type + '\'' +
                ", tags=" + tags +
                ", imageName=" + imageName +
                '}';
    }
}
