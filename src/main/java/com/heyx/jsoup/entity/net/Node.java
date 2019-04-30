package com.heyx.jsoup.entity.net;

import org.hibernate.annotations.ColumnDefault;
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
    @ColumnDefault("INT default 0")
    private Integer size = 0;

    /**
     * 偏置
     */
    @Column
    @ColumnDefault("INT default 0")
    private Integer bias = 0;

    public Node() {
    }

    public Node(Layer layer, Integer size, Integer bias) {
        this.layer = layer;
        this.size = size;
        this.bias = bias;
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

    public Integer getBias() {
        return bias;
    }

    public void setBias(Integer bias) {
        this.bias = bias;
    }
}
