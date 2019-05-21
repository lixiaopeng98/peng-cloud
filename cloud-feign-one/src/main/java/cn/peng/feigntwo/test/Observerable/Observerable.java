package cn.peng.feigntwo.test.Observerable;

/**
 * 实现观察者模式的 抽象被观察者接口
 * 声明了添加、删除、通知观察者方法
 */
public interface Observerable {

    public void registerObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notifyObserver();

}
