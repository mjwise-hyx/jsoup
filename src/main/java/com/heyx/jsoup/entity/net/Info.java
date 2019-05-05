package com.heyx.jsoup.entity.net;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "net_info")
public class Info {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    @Column(name = "id", length = 64)
    private String id;

    /**
     * 层个数
     */
    @Column(columnDefinition = "INTEGER default 0")
    private Integer layerNum;

    /**
     * 层信息 边数和节点数
     * 1.边和点之间用:   边数：点数
     * 2.层之间用 "-" 分隔
     * 3.如出现多种情况的时候 用@i 表示第i种情况 如：@1
     * 如： 1:3@1-2:5@1
     */
    @Column
    private String layerInfo;

    /**
     * 是否生成过网络
     */
    @Column(columnDefinition = "boolean default false")
    private Boolean isUsed = false;

    public Info() {
    }

    public Info(Integer layerNum, String layerInfo) {
        this.layerNum = layerNum;
        this.layerInfo = layerInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getLayerNum() {
        return layerNum;
    }

    public void setLayerNum(Integer layerNum) {
        this.layerNum = layerNum;
    }

    public String getInfo() {
        return layerInfo;
    }

    public void setInfo(String layerInfo) {
        this.layerInfo = layerInfo;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }

    @Override
    public String toString() {
        return "Info{" +
                "id='" + id + '\'' +
                ", layerNum=" + layerNum +
                ", layerInfo='" + layerInfo + '\'' +
                ", isUsed=" + isUsed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Info info1 = (Info) o;
        return Objects.equals(layerNum, info1.layerNum) &&
                Objects.equals(layerInfo, info1.layerInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(layerNum, layerInfo);
    }
}
