package com.mchz.tool.dstest.processor;

import cn.hutool.core.lang.Assert;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Table;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class HbaseTestProcessorTest {

	private HbaseTestProcessor hbaseTestProcessor = new HbaseTestProcessor();

	@Test
	public void testHbase1() throws IOException {
		Assert.state(hbaseTestProcessor.testService("192.168.239.1", 2181));

		Configuration config = HBaseConfiguration.create();
		config.set("hbase.zookeeper.quorum", "192.168.239.1");// zookeeper地址
		config.set("hbase.zookeeper.property.clientPort", "2181");// zookeeper端口
		config.setInt("hbase.client.retries.number", 2);
		Connection connection = ConnectionFactory.createConnection(config);

		TableName[] tableNames = connection.getAdmin().listTableNames();

		System.out.println(tableNames.length);
	}

	@Test
	public void testHbase2() throws IOException {
		Assert.state(hbaseTestProcessor.testService("192.168.202.68", 2181));
		Assert.state(hbaseTestProcessor.testService("192.168.202.69", 2181));

		Configuration config = HBaseConfiguration.create();
		config.set("hbase.zookeeper.quorum", "192.168.202.69");// zookeeper地址
		config.set("hbase.zookeeper.property.clientPort", "2181");// zookeeper端口
		config.setInt("hbase.client.retries.number", 2);
		Connection connection = ConnectionFactory.createConnection(config);

		TableName[] tableNames = connection.getAdmin().listTableNames();

		System.out.println(tableNames.length);
	}
}