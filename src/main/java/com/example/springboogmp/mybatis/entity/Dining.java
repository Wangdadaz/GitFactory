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
@ApiModel(value = "Dining对象", description = "")
public class Dining implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String state;

    private String dindingName;

    private String tel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    public String getDindingName() {
        return dindingName;
    }

    public void setDindingName(String dindingName) {
        this.dindingName = dindingName;
    }
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Dining{" +
            "id=" + id +
            ", state=" + state +
            ", dindingName=" + dindingName +
            ", tel=" + tel +
        "}";
    }
}
