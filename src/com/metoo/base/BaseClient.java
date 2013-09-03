package com.metoo.base;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.metoo.thift.bean.PartsEntity;
import com.metoo.thift.bean.PartsService;

public class BaseClient {

	public static final String SERVER_IP = "192.168.2.127";
	public static final int SERVER_PORT = 1180;
	public static final int TIMEOUT = 30000;

	
	/**
	 *
	 * @param userName
	 */
	public void startClient(String userName) {
		
//		TProtocol protocol = new TBinaryProtocol(connectionManager.getSocket());
//        PushRPCService.Client client = new PushRPCService.Client(protocol);
		
		TTransport transport = null;
		try {
			transport = new TFramedTransport(new TSocket(SERVER_IP,
					SERVER_PORT, TIMEOUT));
			// 协议要和服务端一致
			TProtocol protocol = new TCompactProtocol(transport);
			final PartsService.Client client = new PartsService.Client(
					protocol);
			transport.open();
			
			
//			PartsEntity pe = new PartsEntity();
//			pe.setName("刘一江");
//			pe.setComments(12L);
//			pe.setId("xxxs");
//			pe.setLike(1);
//			boolean bo = client.savePartsEntity(pe);
			System.out.println(client.getPort());
			
//			new Thread(new Runnable() {
//				
//				@Override
//				public void run() {
//					try {
//						System.out.println(Thread.currentThread().getName()+"获得数据："+client.getPort());
//						Thread.sleep(1000);
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					
//					
//				}
//			}).start();
			
		//	new Thread(new TestThread(client)).start();
			
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != transport) {
				//transport.close();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BaseClient client = new BaseClient();
		client.startClient("Michael");

	}
}
