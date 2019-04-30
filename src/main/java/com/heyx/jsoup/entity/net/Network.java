package com.heyx.jsoup.entity.net;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 神经网络
 */
@Entity
public class Network {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    @Column(name = "id", length = 64)
    private String id;

    /**
     * 迭代次数
     */
    @Column
    @ColumnDefault("INT default 0")
    private Integer trainNum = 0;

    /**
     * 层个数
     */
    @Column
    @ColumnDefault("INT default 0")
    private Integer layerNum = 0;

    /**
     * 正确率
     */
    @Column
    @ColumnDefault("DOUBLE default 0.0")
    private Double rightRate = 0.0;

    public Network() {
    }

    public Network(Integer trainNum, Integer layerNum, Double rightRate) {
        this.trainNum = trainNum;
        this.layerNum = layerNum;
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

    public Integer getLayerNum() {
        return layerNum;
    }

    public void setLayerNum(Integer layerNum) {
        this.layerNum = layerNum;
    }

    public Double getRightRate() {
        return rightRate;
    }

    public void setRightRate(Double rightRate) {
        this.rightRate = rightRate;
    }
}
