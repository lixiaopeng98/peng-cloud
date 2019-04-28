package cn.peng.feigntwo.controller;

import cn.peng.feigntwo.client.GoToFeignTwoClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

/**
 * 本类是去请求 feign two
 * 也就是客户端
 * @author
 */
@RefreshScope
@RestController
public class GoToFeignTwoController {

    private static Logger logger = (Logger) LoggerFactory.getLogger(GoToFeignTwoController.class);

    @Value("${goods.parameterOne}")
    private String parameterOne;

    @Autowired
    public GoToFeignTwoClient goToFeignTwoClient;

    @RequestMapping(value = "/goToFeignTwo/getMsg")
    public JSONObject goToFeignTwo(@RequestParam(value = "msg") String msg){
        JSONObject jsonObject = new JSONObject();
        Object feignTwoMsg = goToFeignTwoClient.getFeignTwoMsg(msg);
        try {
            jsonObject.put("请求FeignTwo的结果集为：",feignTwoMsg);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

}
