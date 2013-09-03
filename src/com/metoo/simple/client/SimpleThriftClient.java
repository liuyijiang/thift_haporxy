package com.metoo.simple.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.metoo.thift.bean.PartsService;

/**
 * 测试  SimpleThriftServer
 * @author Administrator
 *
 */
public class SimpleThriftClient {

	public static final String SERVER_IP = "localhost";
	public static final int SERVER_PORT = 8090;
	public static final int TIMEOUT = 30000;
	
	public void startClient() {
		TTransport transport = null;
		try {
			transport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT);
			// 协议要和服务端一致
			TProtocol protocol = new TBinaryProtocol(transport);
			// TProtocol protocol = new TCompactProtocol(transport);
			// TProtocol protocol = new TJSONProtocol(transport);
			
			//PartsService 是生成的java接口
			PartsService.Client client = new PartsService.Client(
					protocol);
			transport.open();
			
			System.out.println(client.getPort());//调用方法
			
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		} finally {
			if (null != transport) {
				transport.close();
			}
		}
	}
	
	public static void main(String[] args) {
		SimpleThriftClient sc = new SimpleThriftClient();
		sc.startClient();
	}
	
}
