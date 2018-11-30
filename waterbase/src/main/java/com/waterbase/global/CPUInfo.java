package com.waterbase.global;

/**
 * © 2012 amsoft.cn 
 * 名称：AbCPUInfo.java 
 * 描述：CPU信息
 * 
 * @author LiuQi
 */
public class CPUInfo {
	public String User;

	public String System;

	public String IOW;

	public String IRQ;

	public CPUInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CPUInfo(String user, String system, String iOW, String iRQ) {
		super();
		User = user;
		System = system;
		IOW = iOW;
		IRQ = iRQ;
	}
}
