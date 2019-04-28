package cn.peng.feigntwo.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cloud-feign-two",fallback = GoToFeignTwoClient.GoToFeignTwoClientFallback.class)
public interface GoToFeignTwoClient {

    @GetMapping(value = "/goToFeignTwo/getMsg")
    public Object getFeignTwoMsg(@RequestParam(value = "msg") String msg);

    @Component
    public class GoToFeignTwoClientFallback implements GoToFeignTwoClient{


        @Override
        public Object getFeignTwoMsg(String msg) {
            return "降级处理了！";
        }
    }
}
