package cn.peng.feigntwo.controller;

import cn.peng.feigntwo.model.Goods;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RefreshScope
@RestController
@RequestMapping(value = "/goods")
public class GoodsController {

    @RequestMapping(value = "/byId")
    public Goods byId(@RequestParam(value = "id") String id){

        Goods goods = new Goods();
        goods.setId(id);
        goods.setName(id+"_name");
        goods.setDescribe("id为"+id);
        goods.setNumber(10);

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return goods;
    }
}
