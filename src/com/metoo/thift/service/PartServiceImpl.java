package com.metoo.thift.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

import com.metoo.thift.bean.PartsEntity;
import com.metoo.thift.bean.PartsService;

public class PartServiceImpl implements PartsService.Iface  {

	public PartServiceImpl(){
		System.out.println("start");
	}
	
	@Override
	public List<PartsEntity> getPartsEntitys(String id) throws TException {
		List<PartsEntity> list = new ArrayList<PartsEntity>();
		PartsEntity p1 = new PartsEntity();
		p1.setComments(12L);
		p1.setId("xxx1122");
		p1.setLike(23);
		p1.setName("刘一江");
		
		PartsEntity p2 = new PartsEntity();
		p2.setComments(13L);
		p2.setId("xxx33344");
		p2.setLike(24);
		p2.setName("刘大江");
		list.add(p1);
		list.add(p2);
		return list;
	}

	@Override
	public boolean savePartsEntity(PartsEntity entity) throws TException {
		if(entity != null){
			System.out.println(entity.getName());
			System.out.println(entity.getComments());
		}
		return true;
	}

	@Override
	public int getPort() throws TException {
		return 28080;
	}
 

}
