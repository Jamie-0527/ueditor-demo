package com.example.ueditordemo;

import com.example.ueditordemo.ueditor.ActionEnter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jamie
 */
@RestController
@CrossOrigin
public class UEditorController {

    @Autowired
    private UEditorUpload uEditorUpload;

    @RequestMapping("/config")
    public String exec(HttpServletRequest request,
                       HttpServletResponse response,
                       @RequestParam(value = "action") String action,
                       @RequestParam(value = "upfile", required = false) MultipartFile upfile) throws Exception {
        if (action.equals("config")) {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html");
            String rootPath = request.getSession().getServletContext().getRealPath("/");
            return new ActionEnter(request, rootPath).exec();
        } else if (action.equals("uploadimage")) {
            UEditorFile uEditorFile = uEditorUpload.uploadImage(upfile);
            JSONObject jsonObject = new JSONObject(uEditorFile);
            return jsonObject.toString();
        }
        return "无效Action";
    }

}
