package org.meruvian.workshop.jaxrs.service;

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

import org.meruvian.workshop.jaxrs.entity.Category;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Path("/category")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "/category")
public interface CategoryService {
	@GET
	@Path("/{id}")
	@ApiOperation(value = "Find Category by ID", response = Category.class)
	Category getCategoryById(@PathParam("id") @ApiParam("Category ID") long id);
	
	@GET
	@ApiOperation(value = "Find Category by Name", response = Category.class, responseContainer = "List")
	List<Category> findCategoryByName(@QueryParam("title") @DefaultValue("") String title);
	
	@POST
	@ApiOperation(value = "Save Category", response = Category.class)
	Category saveCategory(Category category);
	
	@PUT
	@Path("/{id}")
	@ApiOperation(value = "Update Category", response = Category.class)
	Category updateCategory(@PathParam("id") @ApiParam("Category ID") long id, Category category);
	
	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete Category")
	void deleteCategory(@PathParam("id") @ApiParam("Category ID") long id);
}
