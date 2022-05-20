package com.ps.datastreamingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.ps.datastreamingapp.debeziumconfig.CustomDebeziumConfig;

@SpringBootApplication
public class DatastreamingappApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(DatastreamingappApplication.class, args);
		CustomDebeziumConfig customDebeziumConfig= applicationContext.getBean(CustomDebeziumConfig.class);
		System.out.println("inside debe");
//		customDebeziumConfig.DebeziumListener();
		System.out.println("inside debe");
	}
}
