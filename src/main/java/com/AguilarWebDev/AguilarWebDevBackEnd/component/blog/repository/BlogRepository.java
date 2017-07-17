package com.AguilarWebDev.AguilarWebDevBackEnd.component.blog.repository;

import com.AguilarWebDev.AguilarWebDevBackEnd.component.blog.model.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by peteraguilar on 6/27/17.
 */
public interface BlogRepository extends MongoRepository<Blog, String> {

}
