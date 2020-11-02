package com.hgits.hotc.web.controller;

import com.hgits.hotc.service.impl.news.SysDeptServiceImpl;
import com.hgits.hotc.service.impl.old.OldUnitServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
public class MasterController {

    @Resource
    OldUnitServiceImpl oldUnitService;

    @Resource
    SysDeptServiceImpl sysDeptService;

    @GetMapping("/master")
    public void master() {
        oldUnitService.echo();
    }

    @GetMapping("/slave")
    public void slave() {
        sysDeptService.echo();
    }
}
