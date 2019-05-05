package com.heyx.jsoup.entity.net;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 神经网络
 */
@Entity
@Table(name = "net_network")
public class Network {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    @Column(name = "id", length = 64)
    private String id;

    /**
     * 有可能出现异构网络，所以 多个网络对应一种信息。
     */
    @ManyToOne
    @JoinColumn
    private Info info;

    /**
     * 迭代次数
     */
    @Column(columnDefinition = "INTEGER default 0")
    private Integer trainNum = 0;

    /**
     * 正确率
     */
    @Column(columnDefinition = "DOUBLE default 0.0")
    private Double rightRate = 0.0;

    public Network() {
    }

    public Network(Info info) {
        this.info = info;
    }

    public Network(Info info, Integer trainNum, Double rightRate) {
        this.info = info;
        this.trainNum = trainNum;
        this.rightRate = rightRate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTrainNum() {
        return trainNum;
    }

    public void setTrainNum(Integer trainNum) {
        this.trainNum = trainNum;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public Info getInfo() {
        return info;
    }

    public Double getRightRate() {
        return rightRate;
    }

    public void setRightRate(Double rightRate) {
        this.rightRate = rightRate;
    }

}
