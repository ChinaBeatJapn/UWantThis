package com.swordzpp.downP;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class Test {
  
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		
		for(int i =1 ;i <= 31980;i++){
			executor.execute(new DownByPageThread(i));
		}
	}
}
