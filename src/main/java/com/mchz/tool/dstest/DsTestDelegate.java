package com.mchz.tool.dstest;

import cn.hutool.core.lang.Assert;
import com.mchz.mcdatasource.core.DatasourceConstant;
import com.mchz.tool.dstest.constants.DsTestConstant;
import com.mchz.tool.dstest.domain.DsConnection;
import com.mchz.tool.dstest.enums.DBType;
import com.mchz.tool.dstest.processor.*;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
	private Map<DBType, DsTestProcessor> extendProcessors = new HashMap<>();

	public static void setMcDatasourceEnvironment(String mcDatasourceHome){
		System.setProperty(DatasourceConstant.MCDATASOURCE_HOME, mcDatasourceHome);
	}

	public DsTestDelegate() {
		init();
	}

	/**
	 * 2021/2/4 20:10
	 * 测试服务
	 *
	 * @param ip
	 * @param port
	 * @author lanhaifeng
	 * @return boolean
	 */
	public boolean testService(String ip, Integer port) {
		return Objects.nonNull(processors.get(JdbcTestProcessor.class)) && processors.get(JdbcTestProcessor.class).testService(ip, port);
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
	 * 2021/2/5 9:47
	 * 添加处理器
	 *
	 * @param dbType
	 * @param dsTestProcessor
	 * @author lanhaifeng
	 * @return void
	 */
	public void addProcessor(DBType dbType, DsTestProcessor dsTestProcessor){
		if(Objects.nonNull(dbType) && Objects.nonNull(dsTestProcessor)){
			extendProcessors.put(dbType, dsTestProcessor);
		}
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
		DsTestProcessor processor = null;
		if(Objects.nonNull(dbType)){
			processor = extendProcessors.get(dbType);
			if(Objects.isNull(processor) && DsTestConstant.jdbcDbTypes.contains(dbType)){
				processor = processors.get(JdbcTestProcessor.class);
			}
			if(Objects.isNull(processor) && DsTestConstant.databaseCliDbTypes.contains(dbType)){
				processor = processors.get(DatabaseCliProcessor.class);
			}
		}

		return processor;
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
		Assert.state(StringUtils.isNotBlank(System.getProperty(DatasourceConstant.MCDATASOURCE_HOME)), "MCDATASOURCE_HOME未设置");
		processors.put(JdbcTestProcessor.class, new JdbcTestProcessor());
		processors.put(DatabaseCliProcessor.class, new DatabaseCliProcessor());
		extendProcessors.put(DBType.REDIS, new RedisTestProcessor());
		extendProcessors.put(DBType.ELASTICSEARCH, new ElasticSearchTestProcessor());
		extendProcessors.put(DBType.HIVE, new HiveProcessor());
		extendProcessors.put(DBType.HBASE, new HbaseTestProcessor());
		extendProcessors.put(DBType.MONGODB, new MongoDBTestProcessor());
	}
}
