package cn.peng.feigntwo.controller;

import cn.peng.feigntwo.model.Goods;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

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
        Random random = new Random();
        int i = random.nextInt(150)%(150-50+1) + 50;
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("访问byId："+id+",线程睡眠了"+i+"毫秒！");
        return goods;
    }
}
