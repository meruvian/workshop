package org.meruvian.workshop.jpa.spring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import javax.inject.Inject;

import org.meruvian.workshop.jpa.spring.entity.News;
import org.meruvian.workshop.jpa.spring.repository.NewsRepository;
import org.springframework.stereotype.Service;

@Service
public class Application {
	@Inject
	private NewsRepository newsRepository;
	
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
