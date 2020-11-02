package com.hgits.hotc.web.controller;

import com.hgits.hotc.entity.news.BlackDeliveryNoList;
import com.hgits.hotc.service.impl.news.BlackDeliveryNoListServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class TransferController implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(TransferController.class);

    @Resource
    private BlackDeliveryNoListServiceImpl blackDeliveryNoListService;


    @Override
    public void run(ApplicationArguments args) throws Exception {
       /* BlackDeliveryNoList deliveryNo = new BlackDeliveryNoList().setDeliveryNo("456");
        logger.info("添加项目黑名单，项目编号为：{}", deliveryNo.getDeliveryNo());
        int save = blackDeliveryNoListService.save(deliveryNo);
        System.out.println(save == 1 ? "入库成功" : "入库失败");*/
    }


}
