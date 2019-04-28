package cn.peng.feigntwo.controller;


import com.alibaba.fastjson.JSONObject;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoToFeignOneController {

    private static Logger logger = LoggerFactory.getLogger(GoToFeignOneController.class);

    @RequestMapping(value = "/goToFeignTwo/getMsg")
    public Object getMst(@RequestParam(value = "msg") String msg){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("数据","feignTwo 返回"+msg);
        return jsonObject;
    }
}
