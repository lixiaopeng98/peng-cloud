package cn.peng.feigntwo.test.RPC.impl;

import cn.peng.feigntwo.test.RPC.ConsumerRpc;
import cn.peng.feigntwo.test.RPC.UserService;

import java.io.IOException;

public class UserServiceImpl implements UserService {
    @Override
    public String getUser(String name) {
        ConsumerRpc consumerRpc = new ConsumerRpc();
        Object o = null;
        try {
           o = consumerRpc.callService(name);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return o.toString();
    }
}
