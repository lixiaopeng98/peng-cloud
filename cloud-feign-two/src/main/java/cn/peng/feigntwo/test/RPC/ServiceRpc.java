package cn.peng.feigntwo.test.RPC;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServiceRpc  {

    public void receiveClient() throws IOException {
//        Socket
        ServerSocket serverSocket = null;
        Socket accept = null;
        try {
            serverSocket = new ServerSocket(8585);
            accept = serverSocket.accept();
            ObjectInputStream objectInputStream = new ObjectInputStream(accept.getInputStream());
            System.out.println("信息发送过来，无反序列化为："+objectInputStream.toString());
            Object response = objectInputStream.readObject();
            System.out.println("信息发送过来，反序列化为："+response);
            String s = this.addStr(response.toString());
            //序列化 请求
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(accept.getOutputStream());
            //发送请求
            objectOutputStream.writeObject(s);
            System.out.println("信息发送过来，返回信息为："+s);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (accept!=null) accept.close();
            if (serverSocket!=null) serverSocket.close();
        }
    }

    public String addStr(String message){
        return message+"CNM";
    }

    public static void main(String[] args) throws IOException {
        ServiceRpc serviceRpc = new ServiceRpc();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    serviceRpc.receiveClient();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
