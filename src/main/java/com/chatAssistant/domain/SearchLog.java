package com.chatAssistant.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * @TableName search_log
 */
@TableName(value ="search_log")
@Data
@AllArgsConstructor
public class SearchLog implements Serializable {
    /**
     * 
     */
    @TableId
    private String searchId;

    /**
     * 
     */
    private String searchTime;

    /**
     * 
     */
    private Integer uid;


    private String describe;

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
        SearchLog other = (SearchLog) that;
        return (this.getSearchId() == null ? other.getSearchId() == null : this.getSearchId().equals(other.getSearchId()))
            && (this.getSearchTime() == null ? other.getSearchTime() == null : this.getSearchTime().equals(other.getSearchTime()))
            && (this.getDescribe() == null ? other.getDescribe() == null : this.getDescribe().equals(other.getDescribe()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSearchId() == null) ? 0 : getSearchId().hashCode());
        result = prime * result + ((getSearchTime() == null) ? 0 : getSearchTime().hashCode());
        result = prime * result + ((getDescribe() == null) ? 0 : getDescribe().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", searchId=").append(searchId);
        sb.append(", searchTime=").append(searchTime);
        sb.append(", uid=").append(uid);
        sb.append(", describe=").append(describe);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}