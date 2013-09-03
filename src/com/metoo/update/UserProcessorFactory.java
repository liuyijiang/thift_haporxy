package com.metoo.update;

import org.apache.commons.pool.impl.StackObjectPool;
import org.apache.thrift.TProcessor;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.transport.TTransport;
import org.springframework.context.ApplicationContext;

public class UserProcessorFactory extends TProcessorFactory {  
  
    private StackObjectPool<TProcessor> sop;  
  
    public UserProcessorFactory(ApplicationContext applicationContext, Integer poolSize) {  
        super(null);  
//        sop = new StackObjectPool<TProcessor>(new USPoolFactory(applicationContext) {  
//            @Override  
//            public TProcessor selfMakeObject() {  
//                return new UserAcceptor.Processor<UserAcceptorService>(applicationContext.getBean(UserAcceptorService.class));  
//            }  
//        }, poolSize + 2);  
    }  
  
    @Override  
    public TProcessor getProcessor(TTransport trans) {  
        try {  
            return sop.borrowObject();  
        } catch (Exception e) {  
        	e.printStackTrace();
            System.out.println("获取UserAcceptorService时出错!");  
            return null;  
        }  
    }  
  
    public void returnProcessor(TProcessor _tprocessor) {  
        try {  
            sop.returnObject(_tprocessor);  
        } catch (Exception e) {  
        	System.out.println("归还UserAcceptorService时出错");  
        }  
    }  
  
}  
