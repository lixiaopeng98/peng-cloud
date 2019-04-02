package cn.peng.feigntwo.controller;

import cn.peng.feigntwo.client.GoodsClient;
import cn.peng.feigntwo.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/goods")
public class GoodsController {

    @Autowired
    GoodsClient goodsClient;

    @RequestMapping(value = "/list")
    public Goods list(String id){
        return goodsClient.byId(id);
    }
}
