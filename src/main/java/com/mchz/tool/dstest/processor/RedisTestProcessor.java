package com.mchz.tool.dstest.processor;

import com.mchz.tool.dstest.domain.DsConnection;
import com.mchz.tool.dstest.domain.auth.DsUsernamePasswordAuth;
import com.mchz.tool.dstest.enums.DBType;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * soc
 * 2021/2/22 9:40
 * redis测试连接
 *
 * @author lanhaifeng
 * @since
 **/
public class RedisTestProcessor extends AbstractDsTestProcessor {

	private static String SUCCESS = "PONG";

	@Override
	public List<DBType> getSupportDbTypes() {
		return Arrays.asList(DBType.REDIS);
	}

	@Override
	protected boolean validateNoAuth(DsConnection dsConnection) {
		if(Objects.isNull(dsConnection)){
			return false;
		}
		Jedis jedis = null;
		try {
			jedis = new Jedis(dsConnection.getAddress(), dsConnection.getPort());
			if(dsConnection instanceof DsUsernamePasswordAuth
					&& StringUtils.isNotBlank(((DsUsernamePasswordAuth) dsConnection).getPassword())){
				jedis.auth(((DsUsernamePasswordAuth) dsConnection).getPassword());
			}
			return SUCCESS.equalsIgnoreCase(jedis.ping());
		} catch (Exception e) {
			logger.error("连接redis失败，错误：" + ExceptionUtils.getFullStackTrace(e));
			return false;
		} finally {
			if (Objects.nonNull(jedis)){
				jedis.close();
			}
		}
	}

	@Override
	protected boolean validateUsernamePasswordAuth(DsUsernamePasswordAuth dsUsernamePasswordAuth) {
		return validateNoAuth(dsUsernamePasswordAuth);
	}
}
