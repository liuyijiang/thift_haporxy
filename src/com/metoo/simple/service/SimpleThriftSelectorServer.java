package com.metoo.simple.service;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;

import com.metoo.thift.bean.PartsService;
import com.metoo.thift.service.PartServiceImpl;
/**
 * 使用  Selector
 * @author Administrator
 *
 */
public class SimpleThriftSelectorServer {
   
	public static final int SERVER_PORT = 8090;

	public void startServer() {
		try {
			System.out.println("SimpleThriftSelectorServer start ....");

			TProcessor tprocessor = new PartsService.Processor<PartsService.Iface>(
					new PartServiceImpl());

			//
			TNonblockingServerSocket tnbSocketTransport = new TNonblockingServerSocket(
					SERVER_PORT);
			TThreadedSelectorServer.Args tnbArgs = new TThreadedSelectorServer.Args(tnbSocketTransport);
			
			tnbArgs.processor(tprocessor);
			
			//指定协议
			tnbArgs.transportFactory(new TFramedTransport.Factory());
			//指定传输层
			tnbArgs.protocolFactory(new TCompactProtocol.Factory());

			// 使用非阻塞式IO，服务端和客户端需要指定TFramedTransport数据传输的方式
			TServer server = new TThreadedSelectorServer(tnbArgs);
			server.serve();
    
			
		} catch (Exception e) {
			System.out.println("Server start error!!!");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		SimpleThriftSelectorServer st = new SimpleThriftSelectorServer();
		st.startServer();
	} 
	
}
