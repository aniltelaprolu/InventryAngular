package com.inventory.web.rest.controller.product.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.inventory.common.service.product.CategoryService;
import com.inventory.common.service.product.MenuGroupService;
import com.inventory.common.service.product.ProductService;
import com.inventory.common.service.product.SubCategoryService;
import com.inventory.common.service.product.VariantService;
import com.inventory.common.service.vendor.InvVendorService;
import com.inventory.web.rest.controller.product.ProductRestController;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductRestController.class, secure
= false)
@ContextConfiguration(classes={ProductRestController.class})
public class ProductRestControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProductService productService;

	@MockBean
	private VariantService variantService;

	@MockBean
	private MenuGroupService menuGroupService;
	
	@MockBean
	private CategoryService categoryService;
	
	@MockBean
	private SubCategoryService subCategoryService;

	@MockBean
	private InvVendorService vendorService;


	String productVoJson = "{\"vendorId\":\"1111\"}";

	@Test
	public void retrieveDetailsForCourse() throws Exception {

		Mockito.when(
				vendorService.findByVendorId(Mockito.anyLong())).thenReturn(null);

		// Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/service/product/addNewProduct")
				.accept(MediaType.APPLICATION_JSON).content(productVoJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatus());

	}

}

