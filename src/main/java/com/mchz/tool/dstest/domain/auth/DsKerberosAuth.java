package com.mchz.tool.dstest.domain.auth;

import com.mchz.tool.dstest.enums.DBAuthMode;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * soc
 * 2021/1/26 18:31
 * kerberos认证信息
 *
 * @author lanhaifeng
 * @since
 **/
public class DsKerberosAuth extends DsUsernamePasswordAuth {

	/**
	 * kerberos的配置文件krb5.conf
	 */
	private String configFile;

	/**
	 * kerberos客户端keyTab
	 */
	private String clientKeyTabFile;

	/**
	 * kerberos服务端keyTab
	 */
	private String serverKeyTabFile;

	/**
	 * kerberos的principal
	 */
	@NotEmpty(message = "kerberos认证参数principal为空")
	private String principal;

	public void setConfigFile(String configFile) {
		this.configFile = configFile;
	}

	public String getClientKeyTabFile() {
		return clientKeyTabFile;
	}

	public void setClientKeyTabFile(String clientKeyTabFile) {
		this.clientKeyTabFile = clientKeyTabFile;
	}

	public String getServerKeyTabFile() {
		return serverKeyTabFile;
	}

	public void setServerKeyTabFile(String serverKeyTabFile) {
		this.serverKeyTabFile = serverKeyTabFile;
	}

	public String getConfigFile() {
		return configFile;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	@Override
	public DBAuthMode getDBAuthMode() {
		return DBAuthMode.KERBEROS_AUTH;
	}
}
