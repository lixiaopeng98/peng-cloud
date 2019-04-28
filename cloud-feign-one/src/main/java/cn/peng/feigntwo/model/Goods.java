package cn.peng.feigntwo.model;


import lombok.Data;

@Data
public class Goods {

    private String id;
    private String name;
    /**
     * 描述
     */
    private String describe;
    private Integer number;
    private String remork;


}
