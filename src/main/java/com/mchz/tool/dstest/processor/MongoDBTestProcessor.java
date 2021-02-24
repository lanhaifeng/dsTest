package com.mchz.tool.dstest.processor;

import com.mchz.tool.dstest.domain.DsConnection;
import com.mchz.tool.dstest.domain.auth.DsUsernamePasswordAuth;
import com.mchz.tool.dstest.enums.DBType;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * soc
 * 2021/2/22 10:24
 * MongoDB测试连接
 *
 * @author lanhaifeng
 * @since
 **/
public class MongoDBTestProcessor extends AbstractDsTestProcessor {

	@Override
	public List<DBType> getSupportDbTypes() {
		return Arrays.asList(DBType.MONGODB);
	}

	@Override
	protected boolean validateNoAuth(DsConnection dsConnection) {
		MongoClient mongoClient = null;
		try {
			mongoClient = new MongoClient(dsConnection.getAddress(), dsConnection.getPort());
			String dbName = dsConnection.getInstanceName();
			if(StringUtils.isBlank(dbName)){
				dbName = "default";
			}
			DB db = mongoClient.getDB(dbName);
			db.getCollectionNames();
			return true;
		} catch (Exception e) {
			logger.error("mongodb关闭连接错误：" + ExceptionUtils.getFullStackTrace(e));
			return false;
		} finally {
			if (Objects.nonNull(mongoClient)) {
				mongoClient.close();
			}
		}
	}

	@Override
	protected boolean validateUsernamePasswordAuth(DsUsernamePasswordAuth dsUsernamePasswordAuth) {
		MongoClient mongoClient = null;
		try {
			ServerAddress serverAddress = new ServerAddress(dsUsernamePasswordAuth.getAddress(), dsUsernamePasswordAuth.getPort());
			String dbName = dsUsernamePasswordAuth.getInstanceName();
			MongoCredential credential = MongoCredential.createCredential(dsUsernamePasswordAuth.getUserName(),
					dbName, dsUsernamePasswordAuth.getPassword().toCharArray());
			mongoClient = new MongoClient(serverAddress, Arrays.asList(credential));

			if (StringUtils.isBlank(dbName)) {
				dbName = "default";
			}
			DB db = mongoClient.getDB(dbName);
			db.getCollectionNames();
			return true;
		} catch (Exception e) {
			logger.error("mongodb关闭连接错误：" + ExceptionUtils.getFullStackTrace(e));
			return false;
		} finally {
			if (Objects.nonNull(mongoClient)) {
				mongoClient.close();
			}
		}
	}
}
