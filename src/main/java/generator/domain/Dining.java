package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName dining
 */
@TableName(value ="dining")
@Data
public class Dining implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String state;

    /**
     * 
     */
    private String dindingName;

    /**
     * 
     */
    private String tel;

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
        Dining other = (Dining) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getDindingName() == null ? other.getDindingName() == null : this.getDindingName().equals(other.getDindingName()))
            && (this.getTel() == null ? other.getTel() == null : this.getTel().equals(other.getTel()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getDindingName() == null) ? 0 : getDindingName().hashCode());
        result = prime * result + ((getTel() == null) ? 0 : getTel().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", state=").append(state);
        sb.append(", dindingName=").append(dindingName);
        sb.append(", tel=").append(tel);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}