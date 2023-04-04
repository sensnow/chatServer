package com.chatAssistant.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;


/**
 * @author sensnow
 */
@Data
@AllArgsConstructor
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer uid;
    private String userName;
    private String password;
    private Integer isAvailable;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
