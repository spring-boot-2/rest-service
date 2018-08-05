package com.dxc.ddc.springboot.data.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

/**
 *
 * @author Huanfeng, cai
 * @since JDK 1.8
 */
@Data
@Entity
@Table(name = "DDC_USER")
@NamedQuery(name = "User.findAll", query = "select u from User u order by u.updateDate desc")
@DynamicUpdate
public class User implements Serializable {

	private static final long serialVersionUID = 7473400054623813134L;

	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "ID")
    private String id;

    @Column(name = "LOGIN_ID")
    private String loginId;

    @Column(name = "LOGIN_PWD")
    private String loginPwd;

	@Column(name="STATUS")
	private byte status;

    @Column(name = "DISPLAY_NAME")
    private String displayName;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "DESCRIPTION", nullable = false, columnDefinition = "Text")
    private String description;

    @Column(name = "CREATE_DATE", insertable = false, updatable = false)
    private LocalDateTime createDate;

    @Column(name = "CREATOR_ID")
    private String creatorId;

    @Column(name = "UPDATE_DATE", insertable = false, updatable = false)
    private LocalDateTime updateDate;

    @Column(name = "UPDATE_ID")
    private String updateId;
}
