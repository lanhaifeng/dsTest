package com.mchz.tool.dstest;

import com.mchz.mcdatasource.core.DatasourceConstant;
import com.mchz.tool.dstest.domain.auth.DsUsernamePasswordAuth;
import com.mchz.tool.dstest.enums.DBType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class DsTestDelegateTest {

	@Before
	public void setUp() throws Exception {
		System.setProperty(DatasourceConstant.MCDATASOURCE_HOME, "F:\\mcdatasource");
	}

	@Test
	@Ignore
	public void testService() {
		DsTestDelegate dsTestDelegate = new DsTestDelegate();
		Boolean result = dsTestDelegate.testService("192.168.239.141", 8629);
		Assert.assertTrue("测试服务失败", result);
	}

	@Test
	@Ignore
	public void testMysqlConnection() {
		DsTestDelegate dsTestDelegate = new DsTestDelegate();
		DsUsernamePasswordAuth auth = new DsUsernamePasswordAuth();
		auth.setAddress("192.168.230.156");
		auth.setPort(13306);
		auth.setInstanceName("soc");
		auth.setDbType(DBType.MYSQL.getDbTypeValue());

		auth.setUserName("socusr");
		auth.setPassword("hzmc321#");

		Assert.assertTrue("测试mysql服务失败", dsTestDelegate.testService(auth.getAddress(), auth.getPort()));
		Assert.assertTrue("测试mysql连接失败", dsTestDelegate.testConnection(auth.getDbTypeDict(), auth));
	}

	@Test
	@Ignore
	public void testKdbConnection() {
		DsTestDelegate dsTestDelegate = new DsTestDelegate();
		DsUsernamePasswordAuth auth = new DsUsernamePasswordAuth();
		auth.setAddress("192.168.239.141");
		auth.setPort(8629);
		auth.setInstanceName("KDB");
		auth.setDbType(DBType.K_DB.getDbTypeValue());

		auth.setUserName("sys");
		auth.setPassword("kdb");

		Assert.assertTrue("测试kdb服务失败", dsTestDelegate.testService(auth.getAddress(), auth.getPort()));
		Assert.assertTrue("测试kdb连接失败", dsTestDelegate.testConnection(auth.getDbTypeDict(), auth));
	}

	/**
	 * DatabaseCliProcessor需要区分版本号
	 */
	@Test
	@Ignore
	public void testGbaseConnection() {
		DsTestDelegate dsTestDelegate = new DsTestDelegate();

		DsUsernamePasswordAuth auth = new DsUsernamePasswordAuth();
		auth.setAddress("192.168.238.214");
		auth.setPort(5258);
		auth.setInstanceName("ylhzmc");
		auth.setDbType(DBType.GBASE.getDbTypeValue());

		auth.setUserName("sysdba");
		auth.setPassword("gbase");

		Assert.assertTrue("测试gbase服务失败", dsTestDelegate.testService(auth.getAddress(), auth.getPort()));
		Assert.assertTrue("测试gbase连接失败", dsTestDelegate.testConnection(auth.getDbTypeDict(), auth));
	}

	@Test
	@Ignore
	public void testOracleConnection() {
		DsTestDelegate dsTestDelegate = new DsTestDelegate();

		DsUsernamePasswordAuth auth = new DsUsernamePasswordAuth();
		auth.setAddress("192.168.202.13");
		auth.setPort(1521);
		auth.setInstanceName("ora9i");
		auth.setDbType(DBType.ORACLE.getDbTypeValue());

		auth.setUserName("system");
		auth.setPassword("oracle");

		Assert.assertTrue("测试oracle服务失败", dsTestDelegate.testService(auth.getAddress(), auth.getPort()));
		Assert.assertTrue("测试oracle连接失败", dsTestDelegate.testConnection(auth.getDbTypeDict(), auth));
	}

	@Test
	@Ignore
	public void testMariadbConnection() {
		DsTestDelegate dsTestDelegate = new DsTestDelegate();

		DsUsernamePasswordAuth auth = new DsUsernamePasswordAuth();
		auth.setAddress("192.168.202.128");
		auth.setPort(3306);
		auth.setDbType(DBType.MARIADB.getDbTypeValue());

		auth.setUserName("root");
		auth.setPassword("hzmcdba");

		Assert.assertTrue("测试Mariadb服务失败", dsTestDelegate.testService(auth.getAddress(), auth.getPort()));
		Assert.assertTrue("测试Mariadb连接失败", dsTestDelegate.testConnection(auth.getDbTypeDict(), auth));
	}

	@Test
	@Ignore
	public void testSqlServerConnection() {
		DsTestDelegate dsTestDelegate = new DsTestDelegate();

		DsUsernamePasswordAuth auth = new DsUsernamePasswordAuth();
		auth.setAddress("192.168.225.30");
		auth.setPort(1434);
		auth.setDbType(DBType.SQLSERVER.getDbTypeValue());

		auth.setUserName("sa");
		auth.setPassword("Hzmc321#");

		Assert.assertTrue("测试sql server服务失败", dsTestDelegate.testService(auth.getAddress(), auth.getPort()));
		Assert.assertTrue("测试sql server连接失败", dsTestDelegate.testConnection(auth.getDbTypeDict(), auth));
	}

	@Test
	@Ignore
	public void testPGConnection() {
		DsTestDelegate dsTestDelegate = new DsTestDelegate();

		DsUsernamePasswordAuth auth = new DsUsernamePasswordAuth();
		auth.setAddress("192.168.239.66");
		auth.setPort(5432);
		auth.setDbType(DBType.POSTGRESQL.getDbTypeValue());

		auth.setInstanceName("postgres");
		auth.setUserName("postgres");
		auth.setPassword("postgres");

		Assert.assertTrue("测试PostgreSQL服务失败", dsTestDelegate.testService(auth.getAddress(), auth.getPort()));
		Assert.assertTrue("测试PostgreSQL连接失败", dsTestDelegate.testConnection(auth.getDbTypeDict(), auth));
	}

	@Test
	@Ignore
	public void testGreenplumConnection() {
		DsTestDelegate dsTestDelegate = new DsTestDelegate();

		DsUsernamePasswordAuth auth = new DsUsernamePasswordAuth();
		auth.setAddress("192.168.240.92");
		auth.setPort(5432);
		auth.setDbType(DBType.GREENPLUM.getDbTypeValue());

		auth.setInstanceName("gp_db");
		auth.setUserName("gpadmin");
		auth.setPassword("gpadmin");

		Assert.assertTrue("测试Greenplum服务失败", dsTestDelegate.testService(auth.getAddress(), auth.getPort()));
		Assert.assertTrue("测试Greenplum连接失败", dsTestDelegate.testConnection(auth.getDbTypeDict(), auth));
	}

	@Test
	@Ignore
	public void testDb2Connection() {
		DsTestDelegate dsTestDelegate = new DsTestDelegate();

		DsUsernamePasswordAuth auth = new DsUsernamePasswordAuth();
		auth.setAddress("192.168.210.80");
		auth.setPort(50003);
		auth.setDbType(DBType.DB2.getDbTypeValue());

		auth.setInstanceName("TEST");
		auth.setUserName("db2inst1");
		auth.setPassword("db2inst1");

		Assert.assertTrue("测试db2服务失败", dsTestDelegate.testService(auth.getAddress(), auth.getPort()));
		Assert.assertTrue("测试db2连接失败", dsTestDelegate.testConnection(auth.getDbTypeDict(), auth));
	}

	@Test
	@Ignore
	public void testKingbaseConnection() {
		DsTestDelegate dsTestDelegate = new DsTestDelegate();

		DsUsernamePasswordAuth auth = new DsUsernamePasswordAuth();
		auth.setAddress("192.168.238.219");
		auth.setPort(54321);
		auth.setDbType(DBType.KINGBASE.getDbTypeValue());

		auth.setInstanceName("kingbasees_instance1");
		auth.setUserName("system");
		auth.setPassword("krms");

		Assert.assertTrue("测试Kingbase服务失败", dsTestDelegate.testService(auth.getAddress(), auth.getPort()));
		Assert.assertTrue("测试Kingbase连接失败", dsTestDelegate.testConnection(auth.getDbTypeDict(), auth));
	}

	@Test
	@Ignore
	public void testSysbaseConnection() {
		DsTestDelegate dsTestDelegate = new DsTestDelegate();

		DsUsernamePasswordAuth auth = new DsUsernamePasswordAuth();
		auth.setAddress("192.168.202.53");
		auth.setPort(7000);
		auth.setDbType(DBType.SYBASE.getDbTypeValue());

		auth.setInstanceName("HZMC_SYB12");
		auth.setUserName("sa");
		auth.setPassword("hzmcdba");

		Assert.assertTrue("测试Sybase服务失败", dsTestDelegate.testService(auth.getAddress(), auth.getPort()));
		Assert.assertTrue("测试Sybase连接失败", dsTestDelegate.testConnection(auth.getDbTypeDict(), auth));
	}

	@Test
	@Ignore
	public void testDamengConnection() {
		DsTestDelegate dsTestDelegate = new DsTestDelegate();

		DsUsernamePasswordAuth auth = new DsUsernamePasswordAuth();
		auth.setAddress("192.168.242.115");
		auth.setPort(5236);
		auth.setDbType(DBType.DAMENG.getDbTypeValue());

		auth.setUserName("SYSDBA");
		auth.setPassword("dameng123");

		Assert.assertTrue("测试Dameng服务失败", dsTestDelegate.testService(auth.getAddress(), auth.getPort()));
		Assert.assertTrue("测试Dameng连接失败", dsTestDelegate.testConnection(auth.getDbTypeDict(), auth));
	}
}