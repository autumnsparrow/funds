/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Nov 3, 2013
 *
 */
package com.palmcommerce.funds.id.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.palmcommerce.funds.util.DateConvertor;

/**
 * @author sparrow
 *
 */
public class RedisLocalAndRemoteRelationshipService{
	
	@Autowired
	RedisTemplate<String,String> redisTemplate;
	
	
	public static final class IdMap implements java.io.Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		String local;
		String remote;
		String createTime;
		/**
		 * @return the local
		 */
		public String getLocal() {
			return local;
		}
		/**
		 * @param local the local to set
		 */
		public void setLocal(String local) {
			this.local = local;
		}
		/**
		 * @return the remote
		 */
		public String getRemote() {
			return remote;
		}
		/**
		 * @param remote the remote to set
		 */
		public void setRemote(String remote) {
			this.remote = remote;
		}
		/**
		 * @return the createTime
		 */
		public String getCreateTime() {
			return createTime;
		}
		/**
		 * @param createTime the createTime to set
		 */
		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}
		
		
		
	}
	
	
	

	/**
	 * 
	 */
	public RedisLocalAndRemoteRelationshipService() {
		// TODO Auto-generated constructor stub
	}
	
	private static final String REMOTE_KEYS="remote.keys";
	private static final String LOCAL_KEYS="local.keys";
	
	
	
	
	public void persist(final IdMap idMap){
		
//		SessionCallback<String> callback=new SessionCallback<String>() {
//
//			@Override
//			public <K, V> String execute(RedisOperations<K, V> operations)
//					throws DataAccessException {
//				// TODO Auto-generated method stub
//				@SuppressWarnings("unchecked")
//				RedisOperations<String, String> ops=(RedisOperations<String, String>)operations;
//				
//				ops.multi();
//				SetOperations<String, String> setOps=ops.opsForSet();
//				
//				if(!setOps.isMember(REMOTE_KEYS, idMap.getRemote())){
//					
//					setOps.add(REMOTE_KEYS, idMap.getRemote());
//					
//					BoundHashOperations<String,String,String> hashOps= redisTemplate.boundHashOps(REMOTE_KEYS);
//					hashOps.put( idMap.getRemote(),idMap.getLocal());
//				
//				}
//				
//				if(!setOps.isMember(LOCAL_KEYS, idMap.getLocal())){
//					setOps.add(LOCAL_KEYS, idMap.getLocal());
//					BoundHashOperations<String,String,String>  hashOps= redisTemplate.boundHashOps(LOCAL_KEYS);
//					hashOps.put( idMap.getLocal(),idMap.getRemote());
//				
//				}
//				
//				ops.exec();
//				
//				return null;
//			}
//		};
////		
////		
//		redisTemplate.execute(callback);

		SetOperations<String, String> setOps=redisTemplate.opsForSet();
		if(!setOps.isMember(REMOTE_KEYS, idMap.getRemote())){
			
			setOps.add(REMOTE_KEYS, idMap.getRemote());
			
			BoundHashOperations<String,String,String> ops= redisTemplate.boundHashOps(idMap.getRemote());
			ops.put("local", idMap.getLocal());
			ops.put("remote", idMap.getRemote());
			ops.put("createTime", DateConvertor.getTradeTime());
		}
		
		if(!setOps.isMember(LOCAL_KEYS, idMap.getLocal())){
			setOps.add(LOCAL_KEYS, idMap.getLocal());
			BoundHashOperations<String,String,String>  ops= redisTemplate.boundHashOps(idMap.getLocal());
			ops.put("local", idMap.getLocal());
			ops.put("remote", idMap.getRemote());
			ops.put("createTime", DateConvertor.getTradeTime());
		
		}
		//return true;
		
	}
	
	
	public String findLocalByRemote(String remote){
		String local=null;
		if(redisTemplate.opsForSet().isMember(REMOTE_KEYS, remote)){
			BoundHashOperations<String,String,String> hashOps= redisTemplate.boundHashOps(remote);
			local=hashOps.get("local");
		}
		return local;
		
	}
	
	
	public String findRemoteByLocal(String local){
		String remote=null;
		if(redisTemplate.opsForSet().isMember(LOCAL_KEYS, local)){
			BoundHashOperations<String,String,String> hashOps= redisTemplate.boundHashOps(local);
			remote=hashOps.get("remote");
		}
		return remote;
	}
	
	
	

}
