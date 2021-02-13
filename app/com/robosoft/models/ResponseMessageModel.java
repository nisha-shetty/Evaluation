package com.robosoft.models;

import io.ebean.Model;

import javax.persistence.*;

//@CacheStrategy(useBeanCache = true)
@Entity
@Table(name = "tblresponsemessages")
public class ResponseMessageModel extends Model {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ResponseMessagesSeq")
	@SequenceGenerator(name = "ResponseMessagesSeq", sequenceName = "tblResponseMessages_SEQ", allocationSize = 1)
	@Column(name = "fldId")
	private Integer id;

	@Column(name = "fldKey")
	private Integer key;

	@Column(name = "fldCode")
	private String code;

	@Column(name = "fldMessage")
	private String message;

	@Column(name = "fldIsLazy")
	private boolean isLazy;

	public ResponseMessageModel() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isLazy() {
		return isLazy;
	}

	public void setLazy(boolean isLazy) {
		this.isLazy = isLazy;
	}


}
