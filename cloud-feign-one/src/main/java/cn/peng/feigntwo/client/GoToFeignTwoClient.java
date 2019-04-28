package cn.peng.feigntwo.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "cloud-feign-two",fallback = GoToFeignTwoClient.GoToFeignTwoClientFallback.class)
public interface GoToFeignTwoClient {

    @GetMapping(value = "/feignTwo/getMsg")
    public Object getFeignTwoMsg(String msg);

    public class GoToFeignTwoClientFallback implements GoToFeignTwoClient{

        @Override
        public Object getFeignTwoMsg(String msg) {
            return "降级处理了！";
        }
    }
}
