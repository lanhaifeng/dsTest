package com.mchz.tool.dstest.domain.auth;

import com.mchz.tool.dstest.domain.DsConnection;
import com.mchz.tool.dstest.enums.DBAuthMode;

/**
 * soc
 * 2021/1/26 18:21
 * 用户名认证信息
 *
 * @author lanhaifeng
 * @since
 **/
public class DsUsernameAuth extends DsConnection {

	/**
	 * 用户名
	 */
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public DBAuthMode getDBAuthMode() {
		return DBAuthMode.USERNAME_AUTH;
	}

}
