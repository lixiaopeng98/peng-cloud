package cn.peng.feigntwo.controller;

import cn.peng.feigntwo.model.Goods;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RequestMapping(value = "/goods")
public class GoodsController {

    @RequestMapping(value = "/byId",method = RequestMethod.GET)
    public Goods byId(String id){
        Goods goods = new Goods();
        goods.setId(id);
        goods.setName(id+"_name");
        goods.setDescribe("id为"+id);
        goods.setNumber(10);
        return goods;
    }
}
