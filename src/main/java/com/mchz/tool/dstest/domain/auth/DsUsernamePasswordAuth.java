package com.mchz.tool.dstest.domain.auth;

import com.mchz.tool.dstest.enums.DBAuthMode;

/**
 * soc
 * 2021/1/26 18:21
 * 用户名密码认证信息
 *
 * @author lanhaifeng
 * @since
 **/
public class DsUsernamePasswordAuth extends DsUsernameAuth {

	/**
	 * 密码
	 */
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public DBAuthMode getDBAuthMode() {
		return DBAuthMode.USERNAME_PASSWORD_AUTH;
	}
}
