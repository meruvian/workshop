package org.meruvian.workshop.jaxrs.service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.meruvian.workshop.jaxrs.entity.News;

@Path("/news")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "/news")
public interface NewsService {
	@GET
	@Path("/{id}")
	@ApiOperation(value = "Find news by ID", response = News.class)
	News getNewsById(@PathParam("id") @ApiParam("News ID") long id);
	
	@GET
	@ApiOperation(value = "Find news", response = News.class, responseContainer = "List")
	List<News> findNewsByTitle(@QueryParam("title") @DefaultValue("") String title);
	
	@POST
	@ApiOperation(value = "Save news", response = News.class)
	News saveNews(News news);
	
	@PUT
	@Path("/{id}")
	@ApiOperation(value = "Update news", response = News.class)
	News updateNews(@PathParam("id") @ApiParam("News ID") long id, News news);
	
	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete news")
	void deleteNews(@PathParam("id") @ApiParam("News ID") long id);
}
