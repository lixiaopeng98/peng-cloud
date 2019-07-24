package cn.peng.feigntwo.test.Observerable;

public class Test {

    public static void main(String[] args) {
        Observer li = new User("li");
        Observer zhang = new User("zhang");
        Observer wang = new User("wang");

        WechatServer server = new WechatServer();
        server.registerObserver(li);
        server.registerObserver(zhang);
        server.registerObserver(wang);

        server.setInfomation("CNMD");

        li.update("NDYD");
        server.removeObserver(li);
    }
}
