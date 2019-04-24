package com.heyx.jsoup.entity.net;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Node {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    @Column(name = "id", length = 64)
    private String id;

    /**
     * 所在层
     */
    @ManyToOne
    @JoinColumn
    private Layer layer;

    /**
     * 顺序
     */
    @Column
    private Integer size;

    /**
     * 节点类型
     */
    @Column
    private String type;

    /**
     * 偏置
     */
    @Column
    private Integer bias;

    public Node() {
    }

    public Node(Layer layer, Integer size, String type) {
        this.layer = layer;
        this.size = size;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Layer getLayer() {
        return layer;
    }

    public void setLayer(Layer layer) {
        this.layer = layer;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
