package com.mchz.tool.dstest.processor;

import com.mchz.tool.dstest.domain.DsConnection;
import com.mchz.tool.dstest.enums.DBType;
import com.mchz.tool.dstest.enums.DBAuthMode;

/**
 * soc
 * 2021/2/4 16:42
 * 数据源测试处理器
 *
 * @author lanhaifeng
 * @since
 **/
public interface DsTestProcessor {

	/**
	 * 2021/2/4 16:42
	 * 测试服务
	 *
	 * @param ip
	 * @param port
	 * @author lanhaifeng
	 * @return boolean
	 */
	boolean testService(String ip, Integer port);

	/**
	 * 2021/2/4 18:36
	 * 测试验证
	 *
	 * @param dsAuthMode
	 * @param dsConnection
	 * @author lanhaifeng
	 * @return boolean
	 */
	boolean testConnection(DBAuthMode dsAuthMode, DsConnection dsConnection);

	/**
	 * 2021/2/4 18:55
	 * 该处理器支持的类型
	 *
	 * @param
	 * @author lanhaifeng
	 * @return boolean
	 */
	boolean support(DBType dbType);

}
