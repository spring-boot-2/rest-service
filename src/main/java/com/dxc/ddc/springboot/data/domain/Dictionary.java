package com.dxc.ddc.springboot.data.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

/**
 *
 * @author Huanfeng, cai
 * @since JDK 1.8
 */
@Data
@Entity
@Table(name = "DDC_DICTIONARY")
@NamedQuery(name = "Dictionary.findAll", query = "select d from Dictionary d order by d.field, d.code")
public class Dictionary implements Serializable {

    private static final long serialVersionUID = 3731625845464774169L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIELD")
    private String field;

    @Column(name = "CODE")
    private String code;

    @Column(name = "VALUE")
    private String value;

    @Column(name = "CREATE_DATE", insertable = false, updatable = false)
    private LocalDateTime createDate;

    @Column(name = "CREATOR_ID")
    private String creatorId;

    @Column(name = "UPDATE_DATE", insertable = false, updatable = false)
    private LocalDateTime updateDate;

    @Column(name = "UPDATE_ID")
    private String updateId;
}
