package com.heyx.jsoup.entity.dot;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @description:
 * @author: heyx
 * @create: 2019-04-11 18:47
 * @email; 1064042411@qq.com
 */
@Entity
public class Socks {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    @Column(name = "id", length = 64)
    private String id;

    @Column
    private String name;

    @Column
    private String cron;

    public Socks() {
    }

    public Socks(String name, String cron) {
        this.name = name;
        this.cron = cron;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }
}
