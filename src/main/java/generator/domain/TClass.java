package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName t_class
 */
@TableName(value ="t_class")
@Data
public class TClass implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer son;

    /**
     * 
     */
    private String classname;

    /**
     * 
     */
    private Double money;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TClass other = (TClass) that;
        return (this.getSon() == null ? other.getSon() == null : this.getSon().equals(other.getSon()))
            && (this.getClassname() == null ? other.getClassname() == null : this.getClassname().equals(other.getClassname()))
            && (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSon() == null) ? 0 : getSon().hashCode());
        result = prime * result + ((getClassname() == null) ? 0 : getClassname().hashCode());
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", son=").append(son);
        sb.append(", classname=").append(classname);
        sb.append(", money=").append(money);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}