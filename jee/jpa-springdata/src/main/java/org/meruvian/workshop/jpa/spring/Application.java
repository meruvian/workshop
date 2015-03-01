package org.meruvian.workshop.jpa.spring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.meruvian.workshop.jpa.spring.entity.News;
import org.meruvian.workshop.jpa.spring.repository.NewsRepository;
import org.springframework.stereotype.Service;

@Service
public class Application {
	@Inject
	private NewsRepository newsRepository;
	
	@PostConstruct
	public void runApplication() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("1. Search news");
			System.out.println("2. Add news");
			System.out.print("Your choice (1): ");
			
			String answer = reader.readLine();
			if (answer.equalsIgnoreCase("2")) {
				addNews(reader);
			} else {
				findNewsByTitle(reader);
			}
		}
	}
	
	private void addNews(BufferedReader reader) throws IOException {
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
	
	private void findNewsByTitle(BufferedReader reader) throws IOException {
		System.out.print("Search : ");
		List<News> news = newsRepository.findByTitleContains(reader.readLine());
		
		for (News n : news) {
			System.out.println("ID      : " + n.getId());
			System.out.println("TITLE   : " + n.getTitle());
			System.out.println("CONTENT : " + n.getContent());
			System.out.println();
		}
	}
}
