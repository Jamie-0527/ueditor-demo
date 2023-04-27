package com.example.ueditordemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class UEditorUpload {
    private Logger log = LoggerFactory.getLogger(UEditorUpload.class);
    private String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();

    public UEditorFile uploadImage(MultipartFile file) throws IOException {
        log.info("UEditor开始上传文件");
        //获取文件名
        String fileName = file.getOriginalFilename();
        //Ueditor的config.json规定的返回路径格式
        String returnPath = "/image/upload/ueditor/"+System.currentTimeMillis()+"/"+fileName;
        File saveFile = new File(path+"static"+returnPath);
        if (!saveFile.exists()){
            saveFile.mkdirs();
        }
        //将临时文件移动到保存路径
        file.transferTo(saveFile);
        log.info("UEditor上传文件成功，保存路径："+saveFile.getAbsolutePath());
        UEditorFile uEditorFile = new UEditorFile();
        uEditorFile.setState("SUCCESS");
        //访问URL
        uEditorFile.setUrl(returnPath);
        uEditorFile.setTitle(fileName);
        uEditorFile.setOriginal(fileName);
        return uEditorFile;
    }

}
