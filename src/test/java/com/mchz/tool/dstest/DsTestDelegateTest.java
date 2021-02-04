package com.mchz.tool.dstest;

import com.mchz.tool.dstest.domain.auth.DsUsernamePasswordAuth;
import com.mchz.tool.dstest.enums.DBType;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DsTestDelegateTest {

	@Test
	public void testConnection() {
		DsTestDelegate dsTestDelegate = new DsTestDelegate();
		DsUsernamePasswordAuth dsUsernamePasswordAuth = buildDsUsernamePasswordAuth();
		Boolean result = dsTestDelegate.testConnection(dsUsernamePasswordAuth.getDbTypeDict(), dsUsernamePasswordAuth);

		Assert.assertTrue("测试mysql连接失败", result);
	}

	private DsUsernamePasswordAuth buildDsUsernamePasswordAuth(){
		DsUsernamePasswordAuth dsUsernamePasswordAuth = new DsUsernamePasswordAuth();
		dsUsernamePasswordAuth.setAddress("192.168.230.156");
		dsUsernamePasswordAuth.setPort(13306);
		dsUsernamePasswordAuth.setInstanceName("soc");
		dsUsernamePasswordAuth.setDbType(DBType.MYSQL.getDbTypeValue());

		dsUsernamePasswordAuth.setUserName("socusr");
		dsUsernamePasswordAuth.setPassword("hzmc321#");

		return dsUsernamePasswordAuth;
	}
}