package com.robosoft.models;

import io.ebean.Finder;
import io.ebean.Model;
import io.ebean.annotation.CreatedTimestamp;
import io.ebean.annotation.UpdatedTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tblnumberformat")
public class NumberFormatModel extends Model {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
	generator="numFormatSeq")
	@SequenceGenerator(name="numFormatSeq", sequenceName="tblNumberFormat_seq",allocationSize=1)
	@Column(name = "fldId")
	private int id;

	@Column(name = "fldPrefix", length = 32)
	private String prefix;

	@Column(name = "fldSuffix", length = 8)
	private String suffix;

	@Column(name = "fldNumberPartWidth")
	private Integer numberPartWidth;

	@Column(name = "fldPreFillZero")
	private boolean preFillZero;

	@Column(name = "fldStartNumber")
	private Integer startNumber;

	@Column(name = "fldType")
	private Integer type;

	@Column(name = "fldLastgenerated")
	private Integer lastGenerated;

	@Column(name = "fldActive")
	private boolean isActive;

	@Column(name = "fldRestart")
	private boolean restart;

	@Column(name = "fldEffectiveFrom")
	@Temporal(TemporalType.DATE)
	private Date effectiveFrom;

	@Column(name = "fldCreatedDateTime", updatable = false)
	@Temporal(TemporalType.DATE)
	@CreatedTimestamp
	private Date created;

	@Column(name = "fldModifiedDateTime")
	@Temporal(TemporalType.DATE)
	@UpdatedTimestamp
	private Date modified;

	public NumberFormatModel() {
		super();
	}

	public static Finder<Long, NumberFormatModel> find = new Finder<>(NumberFormatModel.class);

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getLastGenerated() {
		return lastGenerated;
	}

	public void setLastGenerated(Integer lastGenerated) {
		this.lastGenerated = lastGenerated;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public Integer getNumberPartWidth() {
		return numberPartWidth;
	}

	public void setNumberPartWidth(Integer numberPartWidth) {
		this.numberPartWidth = numberPartWidth;
	}

	public boolean isPreFillZero() {
		return preFillZero;
	}

	public void setPreFillZero(boolean preFillZero) {
		this.preFillZero = preFillZero;
	}

	public Integer getStartNumber() {
		return startNumber;
	}

	public void setStartNumber(Integer startNumber) {
		this.startNumber = startNumber;
	}

	public boolean isRestart() {
		return restart;
	}

	public void setRestart(boolean restart) {
		this.restart = restart;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Date getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(Date effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

}
