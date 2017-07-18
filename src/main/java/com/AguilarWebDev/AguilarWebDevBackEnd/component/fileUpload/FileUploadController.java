package com.AguilarWebDev.AguilarWebDevBackEnd.component.fileUpload;

import com.AguilarWebDev.AguilarWebDevBackEnd.component.blog.MetaData.MetaData;
import com.AguilarWebDev.AguilarWebDevBackEnd.component.work.Work;
import com.AguilarWebDev.AguilarWebDevBackEnd.storage.StorageFileNotFoundException;
import com.AguilarWebDev.AguilarWebDevBackEnd.storage.StorageService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class FileUploadController {

    private final StorageService storageService;
    private final GridFsTemplate gridFsTemplate;

    @Autowired
    public FileUploadController(StorageService storageService, GridFsTemplate gridFsTemplate) {
        this.storageService = storageService;
        this.gridFsTemplate = gridFsTemplate;
    }

    @GetMapping("/")
    public String showAdminConsole(Model model) throws IOException {
        model.addAttribute("file", storageService
                .loadAll()
                .map(path ->
                        MvcUriComponentsBuilder
                                .fromMethodName(FileUploadController.class, "serveFile" , path.getFileName().toString())
                                .build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }
//    @CrossOrigin("http://localhost:3000")
    @CrossOrigin("https://aguilarwebdevelopment-63428.firebaseapp.com/")
    @GetMapping("/portfolio")
    public ResponseEntity<List<Work>> ListUploadedFiles() throws IOException {

        List<GridFSDBFile> gridFsdbFiles =
                gridFsTemplate.find(new Query(Criteria.where("metadata.type").is("portfolio")));
        List<Work> fileNames = new ArrayList<>();
        if(gridFsdbFiles.size() > 0) {
            for (GridFSDBFile file : gridFsdbFiles) {
                MetaData metaData = new MetaData(file.getMetaData().get("type").toString(), file.getMetaData().get("title").toString(), file.getMetaData().get("siteUrl").toString());
                Work work = new Work(metaData, file.getFilename());
                fileNames.add(work);
            }
        }
        return ResponseEntity.ok()
                .body(fileNames);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity serveFile(@PathVariable String filename) throws IOException {
       Error errorCatch = new Error();
        GridFSDBFile file = gridFsTemplate.findOne(new Query(Criteria.where("filename").is(filename)));
      try {
          if (file != null) {
              final HttpHeaders headers = new HttpHeaders();
              headers.setContentType(MediaType.IMAGE_PNG);
              return ResponseEntity.ok()
                      .contentLength(file.getLength())
                      .contentType(MediaType.parseMediaType(file.getContentType()))
                      .body(new InputStreamResource(file.getInputStream()));
          }
      } catch(Error error) {
          errorCatch = error;
      }
       return ResponseEntity.badRequest().body(errorCatch);
    }

    @PostMapping("/files")
    public String  createOrUpdate(@RequestParam("file") MultipartFile[] files, RedirectAttributes redirectAttributes, MetaData postMetaData) {
        DBObject metaDataToBeStored = new BasicDBObject();
        metaDataToBeStored.put("type", postMetaData.getType());
        metaDataToBeStored.put("title", postMetaData.getImageTitle());
        metaDataToBeStored.put("siteUrl", postMetaData.getSiteUrl());

        try {
            List<String> fileNames = new ArrayList<>();
            for(MultipartFile file : files) {
                Optional<GridFSDBFile> existing = maybeLoadFile(file.getOriginalFilename());
                if (existing.isPresent()) {
                    gridFsTemplate.delete(getFilenameQuery(file.getOriginalFilename()));
                }
                gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType(), metaDataToBeStored).save();
                fileNames.add(file.getOriginalFilename());
            }
            redirectAttributes.addFlashAttribute("message", fileNames);
            redirectAttributes.addFlashAttribute("type", postMetaData.getType());
            return "redirect:/";
        } catch (IOException e) {
            for(MultipartFile file : files) {
                redirectAttributes.addFlashAttribute("message", "File did not upload successfully: " + file.getOriginalFilename() + "!");
            }
            return "redirect:/";
        }
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

    private Optional<GridFSDBFile> maybeLoadFile(String name) {
        GridFSDBFile file = gridFsTemplate.findOne(getFilenameQuery(name));
        return Optional.ofNullable(file);
    }

    private static Query getFilenameQuery(String name) {
        return Query.query(GridFsCriteria.whereFilename().is(name));
    }
}
