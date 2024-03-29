package com.metoo.simple.service;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;

import com.metoo.thift.bean.PartsService;
import com.metoo.thift.service.PartServiceImpl;
/**
 * 单线程 thrift 服务  对应 SimpleThriftClient
 * @author Administrator
 *
 */
public class SimpleThriftServer {
    
	//定义一个端口
	public static final int SERVER_PORT = 8090;
	
	public void startServer() {
		try {
			System.out.println("SimpleThriftServer start ....");
            //PartsService
			TProcessor tprocessor = new PartsService.Processor<PartsService.Iface>(
					new PartServiceImpl());

			// 简单的单线程服务模型，一般用于测试
			TServerSocket serverTransport = new TServerSocket(SERVER_PORT);
			TServer.Args tArgs = new TServer.Args(serverTransport);
			tArgs.processor(tprocessor);
			//通讯协议
			tArgs.protocolFactory(new TBinaryProtocol.Factory());
			// tArgs.protocolFactory(new TCompactProtocol.Factory());
			// tArgs.protocolFactory(new TJSONProtocol.Factory());
			// tArgs.protocolFactory(new TSimpleJSONProtocol.Factory());
			// tArgs.protocolFactory(new TDebugProtocol.Factory());
			
			TServer server = new TSimpleServer(tArgs);
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
		SimpleThriftServer server = new SimpleThriftServer();
		server.startServer();
	}
	
}
