package com.heyx.jsoup.entity.net;

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
     * 摘要值
     */
    @Column
    private String md5;

    /**
     * 迭代次数
     */
    @Column
    private Integer trainNum;

    /**
     * 层个数
     */
    @Column
    private Integer layerNum;

    /**
     * 正确率
     */
    @Column
    private Double rightRate;

    public Network() {
    }

    public Network(String md5, Integer trainNum, Integer layerNum) {
        this.md5 = md5;
        this.trainNum = trainNum;
        this.layerNum = layerNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
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
