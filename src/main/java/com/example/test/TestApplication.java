package com.example.test;

import com.example.test.Service.TaskGenerator;
import com.example.test.Service.TaskLoader;
import com.example.test.Service.TaskManager;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TestApplication {
	public static void main(String[] args) {
		ApplicationContext ctx =  SpringApplication.run(TestApplication.class, args);

		TaskManager taskManager = ctx.getBean(TaskManager.class);
		TaskGenerator taskGenerator = ctx.getBean(TaskGenerator.class, taskManager);

		TaskLoader taskLoaderOne = ctx.getBean(TaskLoader.class, taskManager);
		TaskLoader taskLoaderTwo = ctx.getBean(TaskLoader.class, taskManager);
		TaskLoader taskLoaderThree = ctx.getBean(TaskLoader.class, taskManager);

		ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		service.execute(taskGenerator);
		service.execute(taskLoaderOne);
		service.execute(taskLoaderTwo);
		service.execute(taskLoaderThree);
		service.shutdown();
	}

}
