package cn.peng.feigntwo.controller;

import cn.peng.feigntwo.client.GoodsClient;
import cn.peng.feigntwo.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping(value = "/goods")
public class GoodsController {

    @Autowired
    GoodsClient goodsClient;

    @ResponseBody
    @RequestMapping(value = "/list")
    public Goods list(String id){
        return goodsClient.byId(id);
    }
}
