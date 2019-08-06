package com.sccdrs.work.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @author wcy
 * @date 2019/7/29 14:46
 * @Description:
 */
@Data
public class User implements Serializable {
    private Integer id;
    private String accountId;
    private String name;
    private Integer sex;
    private String position;
    private String password;
    private Integer status;
    private Integer roleId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp createdAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp lastLoginTime;

}
