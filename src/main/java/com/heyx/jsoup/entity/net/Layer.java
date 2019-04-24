package com.heyx.jsoup.entity.net;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 神经层
 */
@Entity
public class Layer {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    @Column(name = "id", length = 64)
    private String id;

    /**
     * 所在网络
     */
    @ManyToOne
    @JoinColumn
    private Network network;

    /**
     * 节点个数
     */
    @Column
    private Integer nodeNum;

    public Layer() {
    }

    public Layer(Network network, Integer nodeNum) {
        this.network = network;
        this.nodeNum = nodeNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public Integer getNodeNum() {
        return nodeNum;
    }

    public void setNodeNum(Integer nodeNum) {
        this.nodeNum = nodeNum;
    }
}
