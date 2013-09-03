package com.metoo.simple.service;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;

import com.metoo.thift.bean.PartsService;
import com.metoo.thift.service.PartServiceImpl;

/**
 * 使用标准的阻塞式IO 服务端 可以使用 SimpleThrifClient客户端 调用
 * @author Administrator
 *
 */
public class SimpleThriftServerIO {

	//定义一个端口
    public static final int SERVER_PORT = 8090;
	
	public void startServer() {
		try {
			System.out.println("SimpleThriftServerIO start ....");
            //通用
			TProcessor tprocessor = new PartsService.Processor<PartsService.Iface>(
					new PartServiceImpl());

			 TServerSocket serverTransport = new TServerSocket(SERVER_PORT);
			 TThreadPoolServer.Args ttpsArgs = new TThreadPoolServer.Args(
			 serverTransport);
			 ttpsArgs.processor(tprocessor);
			 
			//通讯协议
			 ttpsArgs.protocolFactory(new TBinaryProtocol.Factory());

			// 线程池服务模型，使用标准的阻塞式IO，预先创建一组线程处理请求。
			 TServer server = new TThreadPoolServer(ttpsArgs);
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
		SimpleThriftServerIO server = new SimpleThriftServerIO();
		server.startServer();
	}
	
}
