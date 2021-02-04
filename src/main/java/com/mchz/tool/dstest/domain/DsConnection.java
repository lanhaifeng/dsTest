package com.mchz.tool.dstest.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mchz.tool.dstest.enums.DBAuthMode;
import com.mchz.tool.dstest.enums.DBType;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * soc
 * 2021/1/26 15:11
 * 数据源连接信息
 *
 * @author lanhaifeng
 * @since
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class DsConnection implements Serializable {

	/**
	 * 地址
	 */
	private String address;

	/**
	 * 端口
	 */
	private Integer port;

	/**
	 * 服务名
	 */
	private String serviceName;

	/**
	 * 实例名或库名
	 */
	private String instanceName;

	/**
	 * 数据库类型
	 */
	private String dbType;

	@NotEmpty(message = "ip或域名为空")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@NotNull(message = "端口为空")
	@Range(min = 1, max = 65535, message = "端口非法")
	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getInstanceName() {
		return instanceName;
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	@NotEmpty(message = "数据库类型为空")
	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	@NotNull(message = "数据库类型为空")
	public DBType getDbTypeDict(){
		return DBType.getDBType(getDbType());
	}

	public abstract DBAuthMode getDBAuthMode();
}
