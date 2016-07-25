package com.zzz;

import java.io.IOException;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.infinispan.Cache;
import org.infinispan.manager.EmbeddedCacheManager;

@WebServlet("/i")
public class Infinispan extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(lookup = "java:jboss/infinispan/container/web")
	private EmbeddedCacheManager cacheManager;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println(cacheManager.getCacheNames());
		
		Cache<Object, Object> defaultCache = cacheManager.getCache();
		System.out.println(defaultCache);
		System.out.println(defaultCache.getName());
		System.out.println(defaultCache.getCacheConfiguration().eviction().maxEntries());
		System.out.println(defaultCache.getCacheConfiguration().eviction().strategy());
		if(defaultCache.get("zzz") == null){
			defaultCache.put("zzz", UUID.randomUUID().toString());
			System.out.println("add cache");
		}else{
			System.out.println("get cache " + defaultCache.get("zzz"));
		}


	}
}
