package com.example.springboogmp.dao.base;//wangDD

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.Scope;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import lombok.Data;
import org.springframework.stereotype.Repository;


//2022-02-2022/2/1-22:34
@Repository
@ApiModel("菜单实体类")
@Data
public class Menu {
    /**
     * id int primary key auto_increment,
     * m_name varchar(20) unique not null,
     * m_type varchar(10) not null,
     * money double(7,1) not null
     */
    @ApiModelProperty("菜单编号")
    private Long id;
    @ApiModelProperty("菜品名称")
    private String m_name;
    @ApiModelProperty("菜品类型")
    private String m_type;
    @ApiModelProperty("金额")
    private Double money;
    @TableLogic
    private Integer m_delete;
    public Menu() {
    }

}


