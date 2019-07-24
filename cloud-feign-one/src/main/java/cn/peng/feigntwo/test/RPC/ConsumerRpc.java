package cn.peng.feigntwo.test.RPC;



import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConsumerRpc {

    public Object callService(Object o) throws IOException, ClassNotFoundException {

        Socket socket = new Socket("127.0.0.1",8585);

        //序列化 请求
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        //发送请求
        objectOutputStream.writeObject(o.toString());
        //系列化 响应
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Object response = objectInputStream.readObject();
        System.out.println("响应数据为："+response);
        return response;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ConsumerRpc consumerRpc = new ConsumerRpc();
        consumerRpc.callService("GRD ");
    }
}
