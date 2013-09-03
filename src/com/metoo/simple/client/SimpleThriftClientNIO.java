package com.metoo.simple.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.metoo.thift.bean.PartsService;

/**
 * 使用非阻塞式IO 客户端 对应 SimpleThriftServerNIO HsHa
 * @author Administrator
 *
 */
public class SimpleThriftClientNIO {

	public static final String SERVER_IP = "localhost";
	public static final int SERVER_PORT = 8090;
	public static final int TIMEOUT = 30000;

	public void startClient() {
		TTransport transport = null;
		try {
			transport = new TFramedTransport(new TSocket(SERVER_IP,
					SERVER_PORT, TIMEOUT));
			// 协议要和服务端一致
			TProtocol protocol = new TCompactProtocol(transport);
			
			PartsService.Client client = new PartsService.Client(protocol);
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimpleThriftClientNIO client = new SimpleThriftClientNIO();
		client.startClient();

	}
	
}
