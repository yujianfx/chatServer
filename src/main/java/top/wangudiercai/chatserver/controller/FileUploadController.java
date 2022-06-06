package top.wangudiercai.chatserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.wangudiercai.chatserver.common.Result;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Enumeration;
import java.util.UUID;

@Slf4j
@RestController
public class FileUploadController {
  @PostMapping("/api/upload")
  public Result<String> fileUpload(@RequestParam(value = "file") MultipartFile file) {
    if (file.isEmpty()) {
      System.out.println("文件为空空");
    }

    // BMP、JPG、JPEG、PNG、GIF
    String fileName = file.getOriginalFilename(); // 文件名
    log.info("上传文件名：" + fileName);
    String suffixName = fileName.substring(fileName.lastIndexOf(".")); // 后缀名
    // 验证上传的文件是否图片
    if (!".bmp".equalsIgnoreCase(suffixName)
        && !".jpg".equalsIgnoreCase(suffixName)
        && !".jpeg".equalsIgnoreCase(suffixName)
        && !".png".equalsIgnoreCase(suffixName)
        && !".gif".equalsIgnoreCase(suffixName)) {
      return Result.error("上传失败，请选择BMP、JPG、JPEG、PNG、GIF文件！");
    }

    fileName = UUID.randomUUID() + suffixName; // 新文件名
    File dest = new File(fileName);
    // 如果文件的父路径不存在，则创建
    if (fileName.startsWith("/") && !dest.getParentFile().exists()) {
      dest.getParentFile().mkdirs();
    }
    // 开始存放文件到指定目录去
    try {
      file.transferTo(dest);

      // 返回图片url
      return Result.success("http://"+ InetAddress.getLocalHost().getHostAddress()+":10800/avatars/" + fileName);
    } catch (IOException e) {
      e.printStackTrace();
      return Result.error("上传失败！");
    }
  }
}
