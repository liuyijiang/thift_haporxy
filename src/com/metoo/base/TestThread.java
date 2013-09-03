package com.metoo.base;


import com.metoo.thift.bean.PartsService;

public class TestThread  implements Runnable {
    
	private PartsService.Client client;
	
	public TestThread(PartsService.Client client){
		this.client = client;
	}
	
	
	@Override
	public void run() {
		for(int i=0;i<10;i++){
			try {
				System.out.println(Thread.currentThread().getName()+"获得数据："+client.getPort());
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
