package com.mchz.tool.dstest.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.IntRange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Objects;

/**
 * soc
 * 2021/2/4 17:25
 * ip工具类
 *
 * @author lanhaifeng
 * @since
 **/
public class IpUtils {

	private static Logger logger = LoggerFactory.getLogger(IpUtils.class);

	/**
	 * 2021/2/4 17:29
	 * 检测Ip和端口是否可用
	 *
	 * @param ip
	 * @param port
	 * @author lanhaifeng
	 * @return boolean
	 */
	public static boolean checkIpPort(String ip, Integer port) {
		if(StringUtils.isBlank(ip) || StringUtils.isBlank(ip.trim()) ||
				Objects.isNull(port) || !new IntRange(1, 65535).containsInteger(port)){
			return false;
		}
		Socket socket = new Socket();
		try {
			socket.connect(new InetSocketAddress(ip,port),3000);
			return true;
		} catch (Exception e) {
			logger.warn("地址和端口号不可用 {}:{}", ip, port);
			return false;
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {

				}
			}
		}
	}

	/**
	 * 检测Ip地址
	 *
	 * @param ip
	 * @return
	 */
	public static boolean checkIp(String ip){
		try {
			InetAddress.getByName(ip).isReachable(3000);
			return true;
		} catch (IOException e) {
			logger.warn("Ip不可用 {}", ip);
			return false;
		}
	}
}
