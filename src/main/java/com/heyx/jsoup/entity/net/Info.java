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
     * 1.边和点之间用:
     * 2.层之间用 "-" 分隔
     * 3.如出现多种情况的时候 用@i 表示第i种情况 如：@1
     * 如： 1:3-2:5@1
     */
    @Column
    private String info;

    /**
     * 是否生成过网络
     */
    @Column(columnDefinition = "boolean default false")
    private Boolean isUsed = false;

    public Info() {
    }

    public Info(Integer layerNum, String info) {
        this.layerNum = layerNum;
        this.info = info;
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
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Info info1 = (Info) o;
        return Objects.equals(layerNum, info1.layerNum) &&
                Objects.equals(info, info1.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(layerNum, info);
    }
}
