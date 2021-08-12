package com.atguigu.gulimall.thirdparty;

import com.aliyun.oss.OSSClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootTest
class GulimallThirdPartyApplicationTests {

    @Resource
    OSSClient ossClient;

    @Test
    public void testUpload() throws FileNotFoundException {
//        String endpoint = "oss-cn-beijing.aliyuncs.com";
//        String accessKeyId = "LTAI5tD3WWg6Cuq7jfPLwvqX";
//        String accessKeySecret = "PuxCvATGYdpuKYWzlnkwBSCIwXD7xh";
//        //创建OSSClient实例
//        OSS ossClient = new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);
        //上传文件流
        FileInputStream inputStream = new FileInputStream("C:\\Users\\Administrator\\Desktop\\图片素材\\8e71ff8c85e899acca35999f3b983a27.jpeg");
        ossClient.putObject("gulimall-hello-sss","cat.jpg",inputStream);
        //关闭OSSClient
        ossClient.shutdown();
        System.out.println("文件上传成功。");
    }

    @Test
    void contextLoads() {
    }

}
