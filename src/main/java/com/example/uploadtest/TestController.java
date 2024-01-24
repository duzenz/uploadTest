package com.example.uploadtest;

import org.springframework.http.MediaType;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

@RestController
public class TestController
{

    @PostMapping(value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String upload(@RequestParam("file") MultipartFile file) throws IOException
    {
        //path to a big zip file which is bigger than 1GB
        File testFile = new File("C:\\path\\to\\big-zip-file\\big.zip");
        //File testFile = new File("C:\\Users\\Dell\\Downloads\\big.7z");
        byte[] bytes = Files.readAllBytes(testFile.toPath());
        System.out.println(DigestUtils.md5DigestAsHex(bytes));
        System.out.println(DigestUtils.md5DigestAsHex(file.getBytes()));
        System.out.println(Arrays.equals(bytes, file.getBytes()));
        return "ok";
    }
}
