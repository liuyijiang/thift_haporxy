package com.metoo.simple.service;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;

import com.metoo.thift.bean.PartsService;
import com.metoo.thift.service.PartServiceImpl;

/**
 * 半同步半异步的服务端模型，需要指定为： TFramedTransport 数据传输的方式
 * 任意客户端 只要注意传输协议一致以及指定传输方式为TFramedTransport
 * @author Administrator
 *
 */
public class SimpleThriftServerHsHa {

	public static final int SERVER_PORT = 8090;

	public void startServer() {
		try {
			System.out.println("SimpleThriftServerHsHa start ....");

			TProcessor tprocessor = new PartsService.Processor<PartsService.Iface>(
					new PartServiceImpl());

			TNonblockingServerSocket tnbSocketTransport = new TNonblockingServerSocket(
					SERVER_PORT);
			THsHaServer.Args thhsArgs = new THsHaServer.Args(tnbSocketTransport);
			thhsArgs.processor(tprocessor);
			//TFramedTransport 数据传输的方式
			thhsArgs.transportFactory(new TFramedTransport.Factory());
			thhsArgs.protocolFactory(new TBinaryProtocol.Factory());

			//半同步半异步的服务模型
			TServer server = new THsHaServer(thhsArgs);
			server.serve();

		} catch (Exception e) {
			System.out.println("Server start error!!!");
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimpleThriftServerHsHa server = new SimpleThriftServerHsHa();
		server.startServer();
	}
	
}
