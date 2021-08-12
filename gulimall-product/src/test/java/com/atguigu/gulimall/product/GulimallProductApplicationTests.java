package com.atguigu.gulimall.product;

//import com.aliyun.oss.OSS;
//import com.aliyun.oss.OSSClient;
//import com.aliyun.oss.OSSClientBuilder;
import com.atguigu.gulimall.product.entity.BrandEntity;
import com.atguigu.gulimall.product.service.BrandService;
import com.atguigu.gulimall.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class GulimallProductApplicationTests {

    @Autowired
    BrandService brandService;

    @Autowired
    CategoryService categoryService;

//    @Resource
//    OSSClient ossClient;

//    @Test
//    public void testUpload() throws FileNotFoundException{
////        String endpoint = "oss-cn-beijing.aliyuncs.com";
////        String accessKeyId = "LTAI5tD3WWg6Cuq7jfPLwvqX";
////        String accessKeySecret = "PuxCvATGYdpuKYWzlnkwBSCIwXD7xh";
////        //创建OSSClient实例
////        OSS ossClient = new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);
//        //上传文件流
//        FileInputStream inputStream = new FileInputStream("C:\\Users\\Administrator\\Desktop\\图片素材\\chd1.jpg");
//        ossClient.putObject("gulimall-hello-sss","chd1.jpg",inputStream);
//        //关闭OSSClient
//        ossClient.shutdown();
//        System.out.println("文件上传成功。");
//    }

    @Test
    public void testFindPath(){
        Long[] catelogPath = categoryService.findCatelogPath(225L);
        log.info("完整路径：{}", Arrays.asList(catelogPath));
    }

    @Test
    public void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setBrandId(1L);
        brandEntity.setDescript("sssssss");


//        brandEntity.setName("华为");
//        brandService.save(brandEntity);
//        System.out.println("保存成功....");

        brandService.updateById(brandEntity);
    }

}
