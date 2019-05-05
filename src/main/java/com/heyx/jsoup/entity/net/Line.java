package com.heyx.jsoup.entity.net;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "net_line")
public class Line {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    @Column(name = "id", length = 64)
    private String id;

    /**
     * 输入节点
     */
    @ManyToOne
    @JoinColumn
    private Node input;

    /**
     * 输出节点
     */
    @ManyToOne
    @JoinColumn
    private Node output;

    /**
     * 偏置放大 1000 倍
     */
    @Column(columnDefinition = "INTEGER default 1000")
    private Integer weight = 1000;

    public Line() {
    }

    public Line(Node input, Node output, Integer weight) {
        this.input = input;
        this.output = output;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Node getInput() {
        return input;
    }

    public void setInput(Node input) {
        this.input = input;
    }

    public Node getOutput() {
        return output;
    }

    public void setOutput(Node output) {
        this.output = output;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Line{" +
                "id='" + id + '\'' +
                ", input=" + input +
                ", output=" + output +
                ", weight=" + weight +
                '}';
    }
}
