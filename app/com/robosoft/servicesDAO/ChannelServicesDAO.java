package com.robosoft.servicesDAO;


import static com.robosoft.constants.StringConstants.STR_IS_ACTIVE;
import io.ebean.Ebean;

import java.util.List;

import com.robosoft.models.ChannelModel;

import com.robosoft.service.ErrorLogServices;

public class ChannelServicesDAO {

	private static ChannelServicesDAO instance;
	public static ChannelServicesDAO getInstance() {
		if (instance == null)
			instance = new ChannelServicesDAO();
		return instance;
	}

	private ChannelServicesDAO() {
	}
//
//	public boolean saveChannel(ChannelModel channel) {
//		try {
//			Ebean.save(channel);
//			return true;
//		} catch (Exception e) {
//			ErrorLogServices.logException(e, "save channel");
//		}
//		return false;
//	}
//
//	public boolean removeChannel(ChannelModel channel) {
//		try {
//			Ebean.delete(channel);
//			return true;
//		} catch (Exception e) {
//			ErrorLogServices.logException(e, "remove channel");
//		}
//		return false;
//	}
//
//	public ChannelModel getChannelWithChannel(String channel) {
//		try {
//			return Ebean.find(ChannelModel.class).where().eq("channel", channel).eq(STR_IS_ACTIVE, true).findOne();
//		} catch (Exception e) {
//			ErrorLogServices.logException(e, "get channel with channel");
//		}
//		return null;
//	}
//
//	public ChannelModel getChannelWithChannelId(String channelId) {
//		try {
//			return Ebean.find(ChannelModel.class).where().eq("channelId", channelId).eq(STR_IS_ACTIVE, true).findOne();
//		} catch (Exception e) {
//			ErrorLogServices.logException(e, "get channel with id");
//		}
//		return null;
//	}
//
//	public List<ChannelModel> getChannels() {
//		try {
//			return Ebean.find(ChannelModel.class).where().eq(STR_IS_ACTIVE, true).findList();
//		} catch (Exception e) {
//			ErrorLogServices.logException(e, "get channel");
//		}
//		return null;
//	}

}
