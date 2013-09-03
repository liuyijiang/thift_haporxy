package com.metoo.pool;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.thrift.transport.TSocket;

public class ConnectionManager implements MethodInterceptor
{
    /** 保存local对象 */
    ThreadLocal<TSocket> socketThreadSafe = new ThreadLocal<TSocket>();
    /** 连接提供池 */
    public ConnectionProvider connectionProvider;
    @Override
    public Object invoke(MethodInvocation arg0) throws Throwable
    {
        TSocket socket = null;
        try
        {
            socket = connectionProvider.getConnection();
            socketThreadSafe.set(socket);
            Object ret = arg0.proceed();
            return ret;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception(e);
        }
        finally
        {
            connectionProvider.returnCon(socket);
            socketThreadSafe.remove();
        }
    }
    /**
     * 取socket
     * 
     * @return
     */
    public TSocket getSocket()
    {
        return socketThreadSafe.get();
    }
    public ConnectionProvider getConnectionProvider()
    {
        return connectionProvider;
    }
    public void setConnectionProvider(ConnectionProvider connectionProvider)
    {
        this.connectionProvider = connectionProvider;
    }
}