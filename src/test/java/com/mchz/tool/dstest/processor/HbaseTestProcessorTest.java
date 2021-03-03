package com.mchz.tool.dstest.processor;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.file.FileReader;
import com.mchz.datasource.cli.DatasourceDatabaseCli;
import com.mchz.mcdatasource.core.DataBaseType;
import com.mchz.mcdatasource.core.DatasourceConstant;
import com.mchz.tool.dstest.domain.auth.DsKerberosAuth;
import com.mchz.tool.dstest.domain.auth.DsUsernamePasswordAuth;
import com.mchz.tool.dstest.enums.DBType;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

public class HbaseTestProcessorTest {

	private HbaseTestProcessor hbaseTestProcessor = new HbaseTestProcessor();
	private DsUsernamePasswordAuth dsUsernamePasswordAuth = new DsUsernamePasswordAuth();
	private DsKerberosAuth dsKerberosAuth = new DsKerberosAuth();

	@Before
	public void setUp() throws Exception {
		System.setProperty(DatasourceConstant.MCDATASOURCE_HOME, "F:\\mcdatasource");
	}

	@Test
	public void testHbase1() throws Exception {
		Properties properties = new Properties();

		// 获得File对象，当然也可以获取输入流对象
		String keytab = Base64.encode(new FileReader(HbaseTestProcessorTest.class.getClassLoader().getResource("hbase/hbase.keytab").getFile()).readBytes());
		String krb5 = Base64.encode(new FileReader(HbaseTestProcessorTest.class.getClassLoader().getResource("hbase/krb5.conf").getFile()).readBytes());
		properties.setProperty(DatasourceConstant.KEY_DB_LOGIN_KEYTAB_CONTENT, keytab);
		properties.setProperty(DatasourceConstant.KEY_DB_KRB5_CONTENT, krb5);
		properties.setProperty(DatasourceConstant.KEY_DB_LOGIN_PRINCIPAL, "hbase/cdh01@CDH167.COM");
		DatasourceDatabaseCli datasourceDatabase =
				new DatasourceDatabaseCli(DataBaseType.HBASE.id, "192.168.200.167", "", "2181", "", "", true,properties);
		datasourceDatabase.connect(true);
	}

	@Test
	public void testHbase2() throws IOException {
		Configuration config = HBaseConfiguration.create();
		config.set("hbase.zookeeper.quorum", "192.168.239.1");// zookeeper地址
		config.set("hbase.zookeeper.property.clientPort", "2181");// zookeeper端口
		config.setInt("hbase.client.retries.number", 2);
		Connection connection = ConnectionFactory.createConnection(config);

		connection.getAdmin().listTableNames();
	}

	@Test
	public void testHbase3() throws Exception {
		DatasourceDatabaseCli datasourceDatabase =
				new DatasourceDatabaseCli(DataBaseType.HBASE.id, "192.168.239.1",
						"", "2181", "", "", true);
		datasourceDatabase.connect(true);
	}

	@Test
	public void testHbase4() {
		dsKerberosAuth.setAddress("192.168.200.167");
		dsKerberosAuth.setPort(2181);
		dsKerberosAuth.setDbType(DBType.HBASE.getDbTypeValue());

		dsKerberosAuth.setUserName("hbase/cdh01@CDH167.COM");
		dsKerberosAuth.setClientKeyTabFile(HbaseTestProcessorTest.class.getClassLoader().getResource("hbase/hbase.keytab").getFile());
		dsKerberosAuth.setConfigFile(HbaseTestProcessorTest.class.getClassLoader().getResource("hbase/krb5.conf").getFile());

		org.junit.Assert.assertTrue("hbase测试连接失败", hbaseTestProcessor.validateNoAuth(dsKerberosAuth));
	}

	@Test
	public void testHbase5() {
		dsUsernamePasswordAuth.setAddress("192.168.239.1");
		dsUsernamePasswordAuth.setPort(2181);
		dsUsernamePasswordAuth.setDbType(DBType.HBASE.getDbTypeValue());

		org.junit.Assert.assertTrue("hbase测试连接失败", hbaseTestProcessor.validateUsernamePasswordAuth(dsUsernamePasswordAuth));
	}
}
