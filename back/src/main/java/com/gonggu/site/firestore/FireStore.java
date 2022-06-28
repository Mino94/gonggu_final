package com.gonggu.site.firestore;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.Arrays;
import java.util.UUID;

@Component
public class FireStore {
   @Value("${app.firebase-configuration-file}")
   private String firebaseConfigPath;

   @Value("${app.firebase-bucket}")
   private String firebaseBucket;

   @PostConstruct
   public void firebaseInit() {
       try {
           FileInputStream serviceAccount  = new FileInputStream(firebaseConfigPath);
           FirebaseOptions options = new FirebaseOptions.Builder()
                   .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                   .build();
           FirebaseApp.initializeApp(options);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

   public String uploadFiles(MultipartFile file, String fileName) throws IOException {

       Bucket bucket = StorageClient.getInstance().bucket(firebaseBucket);
       InputStream fileContent = new ByteArrayInputStream(file.getBytes());
       String randomFileName = getRandomFileName(fileName);
       bucket.create(randomFileName, fileContent, file.getContentType());
       return randomFileName;

   }

   /**
    * 파일 이름 중복을 막기 위해 UUID를 이용해 랜덤 파일명을 만들어서 리턴해준다.
    * @param originName 원본 이름
    * @return 랜덤으로 생성된 이름
    */
   private String getRandomFileName(String originName) {
       UUID uuid = UUID.randomUUID();

       String randomName = uuid.toString() + "_" + originName;
       return randomName;
   }

   public GoogleCredential getCredentials() throws IOException {
       String MESSAGING_SCOPE = "https://www.googleapis.com/auth/firebase.messaging";
       String[] SCOPES = { MESSAGING_SCOPE };

       GoogleCredential credentials = GoogleCredential
               .fromStream(new FileInputStream(firebaseConfigPath))
               .createScoped(Arrays.asList(SCOPES));
       credentials.refreshToken();

       return credentials;
   }
}
