package com.mchz.tool.dstest.enums;

import java.util.Objects;

/**
 * soc
 * 2021/2/4 17:07
 * 认证方式枚举
 *
 * @author lanhaifeng
 * @since
 **/
public enum DBAuthMode {
	UNKNOWN(-1, "未知"),
	NO_AUTH(0, "不认证"),
	USERNAME_AUTH(1, "username认证"),
	USERNAME_PASSWORD_AUTH(2, "usernamePassword认证"),
	KERBEROS_AUTH(3, "kerberos认证"),
	LDAP_AUTH(4, "ldap认证");

	private Integer  value;

	private String  label;

	private DBAuthMode(Integer value, String label){
		this.value = value;
		this.label = label;
	}

	public static DBAuthMode get(Integer value, DBAuthMode defaultAuthMode) {
		if(Objects.isNull(value)){
			return defaultAuthMode;
		}
		for (DBAuthMode authMode : values()) {
			if (authMode.value.equals(value)){
				return authMode;
			}
		}
		return defaultAuthMode;
	}

	public Integer getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}
}
