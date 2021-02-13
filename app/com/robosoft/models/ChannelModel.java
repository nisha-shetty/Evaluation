package com.robosoft.models;

import io.ebean.Finder;
import io.ebean.Model;
import io.ebean.annotation.CreatedTimestamp;
import io.ebean.annotation.UpdatedTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tblchannel")
public class ChannelModel extends Model {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
	generator="channelSeq")
	@SequenceGenerator(name="channelSeq", sequenceName="tblChannel_SEQ",allocationSize=1)
	@Column(name = "fldId")
	private Integer id;

	@Column(name = "fldChannel", length = 16)
	private String channel;

	@Column(name = "fldChannelId", length = 16)
	private String channelId;

	@Column(name = "fldIsActive")
	private boolean isActive;

	@Column(name = "fldCreatedDateTime", updatable = false)
	@Temporal(TemporalType.DATE)
	@CreatedTimestamp
	private Date created;

	@Column(name = "fldModifiedDateTime")
	@Temporal(TemporalType.DATE)
	@UpdatedTimestamp
	private Date modified;

	public ChannelModel() {
		super();
	}

	public ChannelModel(String channel, String channelId, boolean isActive) {
		super();
		this.channel = channel;
		this.channelId = channelId;
		this.isActive = isActive;
	}

	public static Finder<Long, ChannelModel> find = new Finder<>(ChannelModel.class);

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
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
