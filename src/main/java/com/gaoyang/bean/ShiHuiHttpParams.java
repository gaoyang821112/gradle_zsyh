package com.gaoyang.bean;

public class ShiHuiHttpParams {
	
	public ShiHuiHttpParams(String authorization, String ndeviceid, String community_id, String version) {
		super();
		this.authorization = authorization;
		this.ndeviceid = ndeviceid;
		this.community_id = community_id;
		this.version = version;
	}

	private String authorization;
	
	private String ndeviceid;
	
	private String community_id;
	
	private String version;

	public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

	public String getNdeviceid() {
		return ndeviceid;
	}

	public void setNdeviceid(String ndeviceid) {
		this.ndeviceid = ndeviceid;
	}

	public String getCommunity_id() {
		return community_id;
	}

	public void setCommunity_id(String community_id) {
		this.community_id = community_id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
}
