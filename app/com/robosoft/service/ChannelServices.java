package com.robosoft.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.robosoft.models.ChannelModel;

import com.robosoft.servicesDAO.ChannelServicesDAO;

public class ChannelServices {

	private static ChannelServices instance;
	private static Map<String, ChannelModel> channelList = null;

//	private ChannelServices() {
//		channelList = new HashMap<String, ChannelModel>();
//		getchannelList();
//	}

	public static ChannelServices getInstance() {
		if (instance == null) {
			instance = new ChannelServices();
		}
		return instance;
	}

//	public boolean validateChannelWithId(String channelId) {
//		try {
//			if (channelList.containsKey(channelId))
//				return true;
//		} catch (Exception e) {
//			ErrorLogServices.logException(e, "validate");
//		}
//		return false;
//	}
//
//	public ChannelModel getChannelWithId(String channelId) {
//		try {
//			return channelList.get(channelId);
//		} catch (Exception e) {
//			ErrorLogServices.logException(e, "get channel");
//		}
//		return null;
//	}
//
//	public boolean validateChannelWithChannel(String channel) {
//		try {
//			if (ChannelServicesDAO.getInstance().getChannelWithChannel(channel) != null)
//				return true;
//		} catch (Exception e) {
//			ErrorLogServices.logException(e, "validate");
//		}
//		return false;
//	}
//
//	public List<ChannelModel> getchannels() {
//		return ChannelServicesDAO.getInstance().getChannels();
//	}
//
//	public boolean registerChannel(String channelName, String channelId) {
//		try {
//			return ChannelServicesDAO.getInstance().saveChannel(new ChannelModel(channelName, channelId, true));
//		} catch (Exception e) {
//			ErrorLogServices.logException(e, "register channel");
//		}
//		return false;
//	}
//
//	public boolean removeChannel(ChannelModel channelModel) {
//		try {
//			boolean removeStatus = ChannelServicesDAO.getInstance().removeChannel(channelModel);
//			return removeStatus;
//		} catch (Exception e) {
//			ErrorLogServices.logException(e, "remove channel");
//		}
//		return false;
//	}
//
//	public boolean activateOrDeactivateChannel(String channelId, boolean activate) {
//		try {
//			ChannelModel channelModel = getChannelWithId(channelId);
//			channelModel.setActive(activate);
//			return ChannelServicesDAO.getInstance().saveChannel(channelModel);
//		} catch (Exception e) {
//			ErrorLogServices.logException(e, "activate or deactivate channel");
//		}
//		return false;
//	}
//
//	public void getchannelList() {
//		for (ChannelModel channel : getchannels()) {
//			channelList.put(channel.getChannelId(), channel);
//		}
//	}

}
