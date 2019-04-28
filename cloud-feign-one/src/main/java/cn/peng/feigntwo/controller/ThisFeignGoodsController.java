package cn.peng.feigntwo.controller;

import cn.peng.feigntwo.model.Goods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;


/**
 * 本类是接收 feign two 的请求
 * 也就是服务端
 * @author
 */
@RefreshScope
@RestController
public class ThisFeignGoodsController {

    private static Logger log = LoggerFactory.getLogger(ThisFeignGoodsController.class);

    @Value("${goods.msg}")
    private String msg;

    @RequestMapping(value = "/feignOne/getGods")
    public Goods getGods(@RequestParam(value = "id") String id){

        Goods goods = new Goods();
        goods.setId(id);
        goods.setName(id+"_name_"+msg);

        goods.setNumber(10);
        Random random = new Random();
        int i = random.nextInt(150)%(150-50+1) + 50;
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        goods.setDescribe("id为"+id+";msg为="+msg+";Thread.sleep="+i);
        log.info("访问byId：{},线程睡眠了{}毫秒！",id,i);
        return goods;
    }
}
