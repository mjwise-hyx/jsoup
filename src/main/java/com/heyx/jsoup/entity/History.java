package com.heyx.jsoup.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Configuration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @description: 历史数据
 * @author: heyx
 * @create: 2019-04-11 18:41
 * @email; 1064042411@qq.com
 */
@Entity
public class History {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    @Column(name = "id", length = 64)
    private String id;

    /**
     * 编号
     */
    @Column
    private String code;

    @Column
    private String num1;

    @Column
    private String num2;

    @Column
    private String num3;

    @Column
    private String num4;

    @Column
    private String num5;

    @Column
    private String num6;


    @Column
    private String bule;

    public History() {
    }

    public History(String code, String num1, String num2, String num3, String num4, String num5, String num6, String bule) {
        this.code = code;
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
        this.num4 = num4;
        this.num5 = num5;
        this.num6 = num6;
        this.bule = bule;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNum1() {
        return num1;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public String getNum2() {
        return num2;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }

    public String getNum3() {
        return num3;
    }

    public void setNum3(String num3) {
        this.num3 = num3;
    }

    public String getNum4() {
        return num4;
    }

    public void setNum4(String num4) {
        this.num4 = num4;
    }

    public String getNum5() {
        return num5;
    }

    public void setNum5(String num5) {
        this.num5 = num5;
    }

    public String getNum6() {
        return num6;
    }

    public void setNum6(String num6) {
        this.num6 = num6;
    }

    public String getBule() {
        return bule;
    }

    public void setBule(String bule) {
        this.bule = bule;
    }

    @Override
    public String toString() {
        return "History{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", num1='" + num1 + '\'' +
                ", num2='" + num2 + '\'' +
                ", num3='" + num3 + '\'' +
                ", num4='" + num4 + '\'' +
                ", num5='" + num5 + '\'' +
                ", num6='" + num6 + '\'' +
                ", bule='" + bule + '\'' +
                '}';
    }
}
