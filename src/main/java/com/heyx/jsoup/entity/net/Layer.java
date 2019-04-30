package com.heyx.jsoup.entity.net;

import org.hibernate.annotations.ColumnDefault;
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
     * 上一层 id
     */
    @Column
    private String parentId;

    /**
     * 边的数量
     */
    @Column
    @ColumnDefault("INT default 0")
    private Integer lineNum = 0;

    /**
     * 节点个数
     */
    @Column
    @ColumnDefault("INT default 0")
    private Integer nodeNum = 0;

    public Layer() {
    }

    public Layer(Network network, String parentId, Integer lineNum, Integer nodeNum) {
        this.network = network;
        this.parentId = parentId;
        this.lineNum = lineNum;
        this.nodeNum = nodeNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
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
