package play.services;

import play.Logger;
import play.libs.Json;

import com.fasterxml.jackson.databind.JsonNode;

public class SampleDto {
	private int id = 1;
	private String str = "message";

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}

	public JsonNode asJson() {
		return Json.toJson(this);
	}

	public static SampleDto sendMsg() {
		Logger.info("iiiiiiiiii");
		SampleDto sd = new SampleDto();
		sd.str = "uuuuuuuuu";
		return sd;
	}

	public static JsonNode sendMsgn(SampleDto sd) {
		Logger.info("iiiiiiiiii--------" + sd);
		if (sd == null) {
			sd = new SampleDto();
			sd.str = "uuuuuuuuu **";
		}
		return sd.asJson();
	}

	public static SampleDto sendMsgn1(SampleDto sd) {
		Logger.info("iiiiiiiiii--------n1");
		sd.str = "uuuuuuuuu ** n1";
		return sd;
	}

	public static String nextMsg(SampleDto str) {
		Logger.info("iiiiiiiiii *** " + str.str);
		return "Hi r";
	}

}
