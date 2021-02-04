package com.mchz.tool.dstest;

import com.mchz.tool.dstest.constants.DsTestConstant;
import com.mchz.tool.dstest.domain.DsConnection;
import com.mchz.tool.dstest.enums.DBAuthMode;
import com.mchz.tool.dstest.enums.DBType;
import com.mchz.tool.dstest.processor.DsTestProcessor;
import com.mchz.tool.dstest.processor.JdbcTestProcessor;

import java.util.*;

/**
 * soc
 * 2021/2/4 16:38
 * 数据源测试委托类
 *
 * @author lanhaifeng
 * @since
 **/
public class DsTestDelegate {

	private Map<Class, DsTestProcessor> processors = new HashMap<>();

	public DsTestDelegate() {
		init();
	}

	/**
	 * 2021/2/4 20:10
	 * 测试服务
	 *
	 * @param dbType
	 * @param ip
	 * @param port
	 * @author lanhaifeng
	 * @return boolean
	 */
	public boolean testService(DBType dbType, String ip, Integer port) {
		return Objects.nonNull(getDsTestProcessor(dbType)) && getDsTestProcessor(dbType).testService(ip, port);
	}

	/**
	 * 2021/2/4 20:10
	 * 测试连接
	 *
	 * @param dbType
	 * @param dbAuthMode
	 * @param dsConnection
	 * @author lanhaifeng
	 * @return boolean
	 */
	public boolean testConnection(DBType dbType, DsConnection dsConnection) {
		return Objects.nonNull(getDsTestProcessor(dbType)) &&
				getDsTestProcessor(dbType).testConnection(dsConnection.getDBAuthMode(), dsConnection);
	}

	/**
	 * 2021/2/4 20:12
	 * 获取处理器
	 *
	 * @param dbType
	 * @author lanhaifeng
	 * @return com.mchz.tool.dstest.processor.DsTestProcessor
	 */
	private DsTestProcessor getDsTestProcessor(DBType dbType){
		if(Objects.nonNull(dbType)){
			if(DsTestConstant.jdbcDbTypes.contains(dbType)){
				return processors.get(JdbcTestProcessor.class);
			}
		}

		return null;
	}

	/**
	 * 2021/2/4 20:11
	 * 初始化处理器
	 *
	 * @param
	 * @author lanhaifeng
	 * @return void
	 */
	private void init(){
		processors.put(JdbcTestProcessor.class, new JdbcTestProcessor());
	}
}
