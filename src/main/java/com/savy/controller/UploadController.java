package com.savy.controller;

import com.alibaba.druid.util.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.savy.service.FileUploadUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/Upload")
@Controller
public class UploadController{

    //上传
    @ResponseBody
    @RequestMapping(value ="/upload")
    public Map upload(HttpServletRequest request, @RequestParam(value = "myFile", required = false) MultipartFile[] files) {
        Map map =new HashMap();
        try {
            for(int i=0;i<files.length;i++){
                FileUploadUtils.upload(request, files[i]);
            }
            map.put("code","1");
            map.put("msg","上传成功！");
        } catch (Exception e) {
            map.put("code","0");
            map.put("msg","上传失败！");
            e.printStackTrace();
        }

        return map;
    }
    @RequestMapping(value = "/up",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public void up(HttpServletResponse request){
       // List<MultipartFile> files=(Mul)(request)
    }
    /*public String up(@RequestParam  MultipartFile file){
        if(file.isEmpty()){
            return "文件为空";
        }
        String fileName=file.getOriginalFilename();
        String filePath="E:\\test_load";
        File dest=new File(filePath+fileName);
        try{
            file.transferTo(dest);
            return "上传成功";
        }catch (IOException e){

        }
        return "上传失败";

    }*/

    //下载
    @ResponseBody
    @RequestMapping("/getMp4")
    public void getMp4(String cateogry,
                       HttpServletRequest request,
                       HttpServletResponse response) throws IOException {

        if(StringUtils.isEmpty(cateogry)) {
            cateogry = "";
        }
        String os = System.getProperty("os.name");
        String path="";
        if(os.toLowerCase().startsWith("win")){
            path="D:/";
        }else{
            path="/home/work/";
        }

        String fileName = path+cateogry;

        File file = new File(fileName);

        //判断文件是否存在如果不存在就返回默认图标
        if(!(file.exists() && file.canRead())) {
            file = new File(path+"company/root.png");
        }

        FileInputStream inputStream = new FileInputStream(file);
        byte[] data = new byte[(int)file.length()];
        int length = inputStream.read(data);
        inputStream.close();
        response.setContentType("video/mpeg4");// 设定输出的类型
        response.setHeader("Content-Disposition", "attachment;filename="  + fileName);

        OutputStream stream = response.getOutputStream();
        stream.write(data);
        stream.flush();
        stream.close();
    }
}
