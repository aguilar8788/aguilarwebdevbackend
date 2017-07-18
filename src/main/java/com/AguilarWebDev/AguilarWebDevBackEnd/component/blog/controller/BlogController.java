package com.AguilarWebDev.AguilarWebDevBackEnd.component.blog.controller;

import com.AguilarWebDev.AguilarWebDevBackEnd.component.blog.model.Blog;
import com.AguilarWebDev.AguilarWebDevBackEnd.component.blog.repository.BlogRepository;
import com.AguilarWebDev.AguilarWebDevBackEnd.component.blog.MetaData.MetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogRepository blogRepo;

//    @CrossOrigin("http://localhost:3000")
    @CrossOrigin("https://aguilarwebdevelopment-63428.firebaseapp.com/")
    @RequestMapping(method= RequestMethod.GET)
    public List<Blog> getAll() {
        return blogRepo.findAll();
    }

//    @CrossOrigin("http://localhost:3000")
    @CrossOrigin("https://aguilarwebdevelopment-63428.firebaseapp.com/")
    @RequestMapping(method= RequestMethod.POST)
    public String create(@ModelAttribute Blog blog, MetaData metaData, RedirectAttributes redirectAttributes) {
        String imageNames = blog.getImageName().toString().replaceAll("\\[", "").replaceAll("\\]","");
        String [] items = imageNames.split(", ");
        List<String> container = Arrays.asList(items);
        blog.setImageName(container);
        blog.setType(metaData.getType());
        blogRepo.save(blog);
        redirectAttributes.addFlashAttribute("blogUploadSuccess", "blog post submitted");
        return "post successful";
    }

    @RequestMapping(method= RequestMethod.DELETE, value="{id}")
    public List<Blog> delete(@PathVariable String id) {
        blogRepo.delete(id);
        return blogRepo.findAll();
    }

    @RequestMapping(method= RequestMethod.PUT, value="{id}")
    public Blog update(@PathVariable String id, @RequestBody Blog blog) {
        Blog update = blogRepo.findOne(id);

        update.setTitle(blog.getTitle());
        update.setDescription(blog.getDescription());
        update.setTags(blog.getTags());
        update.setImageName(blog.getImageName());
        return blogRepo.save(update);
    }
}
