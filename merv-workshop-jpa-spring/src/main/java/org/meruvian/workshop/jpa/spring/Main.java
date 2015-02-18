package org.meruvian.workshop.jpa.spring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.meruvian.workshop.jpa.spring.entity.News;
import org.meruvian.workshop.jpa.spring.repository.NewsRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = "org.meruvian.workshop")
public class Main {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		new AnnotationConfigApplicationContext(Main.class);
	}
	
	@Inject
	private NewsRepository newsRepository;
	
	@PostConstruct
	public void runApplication() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			News news = new News();
			news.setCreateDate(new Date());
			
			System.out.print("Title   : "); // Title : <input>
			news.setTitle(reader.readLine());
			System.out.print("Content : "); // Content : <input>
			news.setContent(reader.readLine());
			
			newsRepository.save(news);
			System.out.println("News submitted! ID: " + news.getId());
			System.out.println();
		}
	}
}
