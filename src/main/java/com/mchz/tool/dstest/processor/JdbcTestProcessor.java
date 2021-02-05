package com.mchz.tool.dstest.processor;

import com.mchz.tool.dstest.constants.DsTestConstant;
import com.mchz.tool.dstest.domain.auth.DsKerberosAuth;
import com.mchz.tool.dstest.domain.auth.DsLdapAuth;
import com.mchz.tool.dstest.domain.auth.DsUsernameAuth;
import com.mchz.tool.dstest.domain.auth.DsUsernamePasswordAuth;
import com.mchz.tool.dstest.enums.DBType;
import com.mchz.tool.dstest.exception.NotSupportDbAuthException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * soc
 * 2021/2/4 18:26
 * jdbc处理器测试数据源
 *
 * @author lanhaifeng
 * @since
 **/
public class JdbcTestProcessor extends AbstractDsTestProcessor {

	private static Logger logger = LoggerFactory.getLogger(JdbcTestProcessor.class);
	private int timeout;

	public JdbcTestProcessor(int timeout) {
		this.timeout = timeout;
	}

	public JdbcTestProcessor() {
		this(30);
	}

	@Override
	public List<DBType> getSupportDbTypes() {
		return DsTestConstant.jdbcDbTypes;
	}

	@Override
	public boolean validateUsernamePasswordAuth(DsUsernamePasswordAuth dsUsernamePasswordAuth) {
		if(Objects.isNull(dsUsernamePasswordAuth) || Objects.isNull(dsUsernamePasswordAuth.getDbTypeDict())
				|| StringUtils.isBlank(dsUsernamePasswordAuth.getDbTypeDict().getDriverClass())
				|| StringUtils.isBlank(dsUsernamePasswordAuth.getDbTypeDict().getJdbcUrlTemplate())){
			throw new NotSupportDbAuthException(dsUsernamePasswordAuth.getDbTypeDict(), dsUsernamePasswordAuth.getDBAuthMode());
		}
		Connection conn = null;
		Statement st = null;
		try {
			DriverManager.setLoginTimeout(timeout);
			Class.forName(dsUsernamePasswordAuth.getDbTypeDict().getDriverClass());
			conn = DriverManager.getConnection(getJdbcUrl(dsUsernamePasswordAuth),
					dsUsernamePasswordAuth.getUserName(), dsUsernamePasswordAuth.getPassword());
			st = conn.createStatement();
			return true;
		} catch (Exception e) {
			logger.error("测试连接失败，错误：" + ExceptionUtils.getFullStackTrace(e));
		} finally {
			try {
				if (Objects.nonNull(st)) {
					st.close();
				}
			} catch (Exception e) {
				logger.error("关闭连接失败，错误：" + ExceptionUtils.getFullStackTrace(e));
			}
			try {
				if (Objects.nonNull(conn)) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error("关闭连接失败，错误：" + ExceptionUtils.getFullStackTrace(e));
			}
		}
		return false;
	}

	private String getJdbcUrl(DsUsernamePasswordAuth dsUsernamePasswordAuth){
		return dsUsernamePasswordAuth.getDbTypeDict().getJdbcUrlTemplate()
				.replaceFirst("\\[ip\\]", dsUsernamePasswordAuth.getAddress())
				.replaceFirst("\\[port\\]", dsUsernamePasswordAuth.getPort().toString())
				.replaceFirst("\\[instanceName\\]", Optional.ofNullable(dsUsernamePasswordAuth.getInstanceName()).orElse(""))
				.replaceFirst("\\[serviceName\\]", Optional.ofNullable(dsUsernamePasswordAuth.getServiceName()).orElse(""));
	}
}
