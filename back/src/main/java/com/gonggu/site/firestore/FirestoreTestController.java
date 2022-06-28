package com.gonggu.site.firestore;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class FirestoreTestController {

   @Autowired
   FireStore fireStore;

   @PostMapping("/file")
   public Map<String, String> uploadFile(@RequestParam("file")MultipartFile file, String fileName) {
       Map<String, String> map = new HashMap<>();
       System.out.println("file : " + file);
       System.out.println("name : " + fileName);

       if(file.isEmpty()) {
           map.put("message", "is empty");
       }

       try {
           String url = fireStore.uploadFiles(file, fileName);
           map.put("url", url);
           map.put("message", "success");
       } catch (IOException e) {
           map.put("message", "fail : " + e.getMessage());
       }
       return map;
   }

}
