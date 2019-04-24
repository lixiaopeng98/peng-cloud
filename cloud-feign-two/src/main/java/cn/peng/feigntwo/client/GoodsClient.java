package cn.peng.feigntwo.client;

import cn.peng.feigntwo.model.Goods;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cloud-feign-one",fallback = GoodsClient.GoodsClientFallBack.class)
public interface GoodsClient {

    @GetMapping(value = "/goods/byId")
    Goods byId(@RequestParam(value = "id") String id);

    @Component
    class GoodsClientFallBack implements GoodsClient{

        @Override
        public Goods byId(String id) {
            Goods goods = new Goods();
            goods.setDescribe("降级了" + id);
            return goods;
        }
    }
}
