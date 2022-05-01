package src.main.java.com.example.springboogmp.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author baomidou
 * @since 2022-05-01
 */
@ApiModel(value = "Menu对象", description = "")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String mName;

    private String mType;

    private Double money;

    @ApiModelProperty("逻辑删除")
    private Integer mDelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }
    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
    public Integer getmDelete() {
        return mDelete;
    }

    public void setmDelete(Integer mDelete) {
        this.mDelete = mDelete;
    }

    @Override
    public String toString() {
        return "Menu{" +
            "id=" + id +
            ", mName=" + mName +
            ", mType=" + mType +
            ", money=" + money +
            ", mDelete=" + mDelete +
        "}";
    }
}
