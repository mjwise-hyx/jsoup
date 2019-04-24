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
}
