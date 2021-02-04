package com.mchz.tool.dstest.domain.auth;

import com.mchz.tool.dstest.enums.DBAuthMode;

/**
 * soc
 * 2021/1/26 18:23
 * ldap认证信息
 *
 * @author lanhaifeng
 * @since
 **/
public class DsLdapAuth extends DsUsernamePasswordAuth {

	@Override
	public DBAuthMode getDBAuthMode() {
		return DBAuthMode.LDAP_AUTH;
	}
}
