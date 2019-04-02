package cn.peng.feigntwo.client;

import cn.peng.feigntwo.model.Goods;
import feign.Param;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "spring-feign-one",fallback = GoodsClient.GoodsClientFallBack.class)
public interface GoodsClient {

    @GetMapping(value = "/goods/byId")
    Goods byId(@Param(value = "id") String id);

    @Component
    public class GoodsClientFallBack implements GoodsClient{

        @Override
        public Goods byId(String id) {
            Goods goods = new Goods();
            goods.setDescribe("降级了");
            return goods;
        }
    }
}
