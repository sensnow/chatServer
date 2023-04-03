package com.chatAssistant.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author sensnow
 */
@Data
@AllArgsConstructor
public class Result<T> implements Serializable {

    private Integer code;

    private Object data;

    private String msg;

    private static final long serialVersionUID = 1L;
}
