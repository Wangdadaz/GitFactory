package com.example.springboogmp.dao.base;//wangDD

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.Scope;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import org.springframework.stereotype.Repository;


//2022-02-2022/2/1-22:34
@Repository
@ApiModel("菜单实体类")
public class Menu {
    /**
     * id int primary key auto_increment,
     * m_name varchar(20) unique not null,
     * m_type varchar(10) not null,
     * money double(7,1) not null
     */
    @ApiModelProperty("菜单编号")
    private Integer id;
    @ApiModelProperty("菜品名称")
    private String m_name;
    @ApiModelProperty("菜品类型")
    private String m_type;
    @ApiModelProperty("金额")
    private Double money;

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", m_name='" + m_name + '\'' +
                ", m_type='" + m_type + '\'' +
                ", money=" + money +
                '}';
    }

    public Menu() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public String getM_type() {
        return m_type;
    }

    public void setM_type(String m_type) {
        this.m_type = m_type;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Menu(Integer id, String m_name, String m_type, Double money) {
        this.id = id;
        this.m_name = m_name;
        this.m_type = m_type;
        this.money = money;
    }
}


