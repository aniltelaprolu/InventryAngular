package com.inventory.web.rest.controller.category;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.common.modal.product.Category;
import com.inventory.common.modal.product.MenuGroup;
import com.inventory.common.modal.product.SubCategory;
import com.inventory.common.service.product.CategoryService;
import com.inventory.common.service.product.MenuGroupService;
import com.inventory.common.service.product.SubCategoryService;
import com.inventory.web.rest.vo.base.ResponseVO;
import com.inventory.web.rest.vo.base.SearchResponseVO;
import com.inventory.web.rest.vo.product.CategoryVO;
import com.inventory.web.rest.vo.product.MenuGroupVO;
import com.inventory.web.rest.vo.product.ProductVO;
import com.inventory.web.rest.vo.product.SubCategoryVO;

@RestController
@RequestMapping("service/category")
public class CategoryRestController {
	private static final Logger logger = LoggerFactory.getLogger(CategoryRestController.class);

	@Autowired
	private MenuGroupService menuGroupService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private SubCategoryService subCategoryService;

	@RequestMapping(value = "/addNewMenuGroup", method = RequestMethod.POST)
	public ResponseEntity<ResponseVO<MenuGroupVO>> addNewProduct(@RequestBody MenuGroupVO menuGroupVO) {
		ResponseVO<MenuGroupVO> message = new ResponseVO<>();
		ResponseEntity<ResponseVO<MenuGroupVO>> response = new ResponseEntity<ResponseVO<MenuGroupVO>>(HttpStatus.OK);
		if (menuGroupVO == null) {
			message.setMessageId("BLANK_MENU_GROUP");
			message.setMessageDetail("Data can not be blank");
		}
		try {

			if (menuGroupVO != null) {
				MenuGroup menuGroup = new MenuGroup();
				menuGroup.setMenuGroupName(menuGroupVO.getMenuGroupName());
				// menuGroup.setMenuGroupCode(menuGroupVO.getMenuGroupCode());

				menuGroupService.saveMenuGroupData(menuGroup);
				logger.info("Menu Saved Successfully");
				message.setMessageId("MENU_CREATED");
				message.setMessageDetail("Menu created sucessfully");
				response = new ResponseEntity<ResponseVO<MenuGroupVO>>(message, HttpStatus.CREATED);
			} else {
				logger.info("Menu Saved Successfully");
				message.setMessageId("MENU_NOT_CREATED");
				message.setMessageDetail("Menu Details is Empty");
				response = new ResponseEntity<ResponseVO<MenuGroupVO>>(message, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error occurred while saving Menu");
			message.setMessageId("MENU_DATA_SAVE_FAILED");
			message.setMessageDetail("Exception occured while saving Menu Data");
			response = new ResponseEntity<ResponseVO<MenuGroupVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}

	/*
	 * Get All MenuGroup Data
	 * 
	 */

	@RequestMapping(value = "/getAllMenuGroups", method = RequestMethod.GET)
	public ResponseEntity<SearchResponseVO<MenuGroupVO>> getAllVariants(@RequestParam("pageNo") int pageNo,
			@RequestParam("pageSize") int pageSize) {
		logger.info("Fetching All MenuGroups Details");
		ResponseEntity<SearchResponseVO<MenuGroupVO>> response = new ResponseEntity<SearchResponseVO<MenuGroupVO>>(
				HttpStatus.OK);

		SearchResponseVO<MenuGroupVO> message = new SearchResponseVO<>();
		try {
			PageRequest pageRequest = new PageRequest(pageNo - 1, pageSize);
			List<MenuGroup> listMenus = menuGroupService.findAll(pageRequest);

			MenuGroupVO menuGroupVO = null;
			List<MenuGroupVO> listMenuGroupsVO = new ArrayList<>();
			if (listMenus != null) {
				for (MenuGroup menuGroup : listMenus) {
					menuGroupVO = new MenuGroupVO();
					menuGroupVO.setMenuGroupId(menuGroup.getMenuGroupId());
					menuGroupVO.setMenuGroupName(menuGroup.getMenuGroupName());
					// menuGroupVO.setMenuGroupCode(menuGroup.getMenuGroupCode());

					List<Category> listCategories = menuGroup.getCategories();
					CategoryVO categoryVO = null;
					List<CategoryVO> listCategoriesVO = new ArrayList<>();
					if (listCategories != null) {
						for (Category category : listCategories) {
							categoryVO = new CategoryVO();
							categoryVO.setCategoryId(category.getCategoryId());
							categoryVO.setCategoryName(category.getCategoryName());
							// categoryVO.setCategoryCode(category.getCategoryCode());

							List<SubCategory> listSubCategories = category.getSubCategories();
							SubCategoryVO subCategoryVO = null;
							List<SubCategoryVO> listSubCategoriesVO = new ArrayList<>();
							if (listSubCategories != null) {
								for (SubCategory subCategory : listSubCategories) {
									subCategoryVO = new SubCategoryVO();
									subCategoryVO.setSubCategoryName(subCategory.getSubCategoryName());
									// subCategoryVO.setSubCategoryCode(subCategory.getSubCategoryCode());
									listSubCategoriesVO.add(subCategoryVO);
								}

							}
							// set list of subCategory in category
							categoryVO.setSubCategory(listSubCategoriesVO);
						}
						listCategoriesVO.add(categoryVO);
					}
					menuGroupVO.setCategory(listCategoriesVO);
					listMenuGroupsVO.add(menuGroupVO);
				}
			}

			message.setMessageId("GET_ALL_DETAILS");
			message.setMessageDetail("Get All Details");
			message.setData(listMenuGroupsVO);
			response = new ResponseEntity<SearchResponseVO<MenuGroupVO>>(message, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error occured while gatting all Menu Details", e);
			e.printStackTrace();
			message.setMessageId("GET_ALL_MENU_FAILED");
			message.setMessageDetail("Error occured while performing get all Category ");
			response = new ResponseEntity<SearchResponseVO<MenuGroupVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}

	/*
	 * Add category to existing menuGroup
	 * 
	 */
	@RequestMapping(value = "/addNewCategory", method = RequestMethod.POST)
	public ResponseEntity<ResponseVO<CategoryVO>> addNewCategory(@RequestBody CategoryVO categoryVO) {
		ResponseVO<CategoryVO> message = new ResponseVO<>();
		System.out.println(categoryVO);
		ResponseEntity<ResponseVO<CategoryVO>> response = new ResponseEntity<ResponseVO<CategoryVO>>(HttpStatus.OK);

		if (categoryVO == null) {
			message.setMessageId("BLANK_CATEGORY");
			message.setMessageDetail("Category can not be blank");
			response = new ResponseEntity<ResponseVO<CategoryVO>>(HttpStatus.NOT_FOUND);
		}
		try {
			if (categoryVO != null) {

				// get menuGroup
				MenuGroup currentMenuGroup = menuGroupService.findByMenuGroupId(categoryVO.getMenuGroupId());

				if (currentMenuGroup == null) {
					logger.info("MenuGroup Not Found With Id {}" + categoryVO.getMenuGroupId());
					message.setMessageId("MENUGROUP_NOT_FOUND");
					message.setMessageDetail("MenuGroup Not Found");
					return new ResponseEntity<ResponseVO<CategoryVO>>(message, HttpStatus.NOT_FOUND);
				}

				if (currentMenuGroup != null) {
					List<Category> listCategories = currentMenuGroup.getCategories();
					Category category = new Category();
					category.setCategoryName(categoryVO.getCategoryName());
					// category.setCategoryCode(categoryVO.getCategoryCode());
					category.setMenuGroup(currentMenuGroup);
					category.setMenuGroup(currentMenuGroup);
					listCategories.add(category);
					currentMenuGroup.setCategories(listCategories);
					
					menuGroupService.saveMenuGroupData(currentMenuGroup);
					
					logger.info("Category Saved Successfully");
					message.setMessageId("CATEGORY_CREATED");
					message.setMessageDetail("Category created successfully");
					response = new ResponseEntity<ResponseVO<CategoryVO>>(message, HttpStatus.CREATED);
				}
				
			} else {
				logger.info("MenuGroup Not Found With Id {}");
				message.setMessageId("MENUGROUP_NOT_FOUND");
				message.setMessageDetail("MenuGroup Not Found");
				return new ResponseEntity<ResponseVO<CategoryVO>>(message, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error occurred while saving Category");
			message.setMessageId("CATEGORY_DATA_SAVE_FAILED");
			message.setMessageDetail("Exception occured while saving Category Data");
			return new ResponseEntity<ResponseVO<CategoryVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}

	/*
	 * Add SubCategory to existing Category
	 * 
	 */
	@RequestMapping(value = "/addNewSubCategory", method = RequestMethod.POST)
	public ResponseEntity<ResponseVO<SubCategoryVO>> addNewSubCategory(@RequestBody SubCategoryVO subCategoryVO) {
		ResponseVO<SubCategoryVO> message = new ResponseVO<>();
		ResponseEntity<ResponseVO<SubCategoryVO>> response = new ResponseEntity<ResponseVO<SubCategoryVO>>(
				HttpStatus.OK);

		if (subCategoryVO == null) {
			message.setMessageId("BLANK_SUBCATEGORY");
			message.setMessageDetail("SubCategory can not be blank");
		}
		try {
			if (subCategoryVO != null) {

				// get menuGroup
				Category currentCategory = categoryService.findByCategoryId(subCategoryVO.getCategoryId());

				if (currentCategory == null) {
					logger.info("SubCategory Not Found With Id {}" + subCategoryVO.getCategoryId());
					message.setMessageId("SUBCATEGORY_NOT_FOUND");
					message.setMessageDetail("SubCategory Not Found");
					response = new ResponseEntity<ResponseVO<SubCategoryVO>>(message, HttpStatus.NOT_FOUND);
				}

				if (currentCategory != null) {
					List<SubCategory> listSubCategories = currentCategory.getSubCategories();
					SubCategory subCategory = new SubCategory();
					subCategory.setSubCategoryName(subCategoryVO.getSubCategoryName());
					// subCategory.setSubCategoryCode(subCategoryVO.getSubCategoryCode());
					subCategory.setCategory(currentCategory);
					
					listSubCategories.add(subCategory);
					currentCategory.setSubCategories(listSubCategories);

					categoryService.saveCategoryData(currentCategory);
					logger.info("SubCategory Saved Successfully");
					message.setMessageId("SUBCATEGORY_CREATED");
					message.setMessageDetail("SubCategory created successfully");
					response = new ResponseEntity<ResponseVO<SubCategoryVO>>(message, HttpStatus.CREATED);
				} else {
					logger.info("SubCategory Not Found With Id {}");
					message.setMessageId("SUBCATEGORY_NOT_FOUND");
					message.setMessageDetail("SubCategory Not Found");
					response = new ResponseEntity<ResponseVO<SubCategoryVO>>(message, HttpStatus.NOT_FOUND);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error occurred while saving SubCategory");
			message.setMessageId("SUBCATEGORY_DATA_SAVE_FAILED");
			message.setMessageDetail("Exception occured while saving SubCategory Data");
			response = new ResponseEntity<ResponseVO<SubCategoryVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}

	/*
	 * Edit Category Details
	 * 
	 */

	@RequestMapping(value = "/editMenuGroupById", method = RequestMethod.POST)
	public ResponseEntity<ResponseVO<MenuGroupVO>> editMenuGroupById(@RequestBody MenuGroupVO menuGroupVO) {
		ResponseVO<MenuGroupVO> message = new ResponseVO<>();

		if (menuGroupVO == null) {
			message.setMessageId("BLANK_DATA");
			message.setMessageDetail("Menu can not be blank");
		}
		try {
			if (menuGroupVO != null) {
				// get menuGroup
				MenuGroup currentMenu = menuGroupService.findByMenuGroupId(menuGroupVO.getMenuGroupId());

				if (currentMenu == null) {
					logger.info("MenuGroup Not Found With Id {}" + menuGroupVO.getMenuGroupId());
					message.setMessageId("MENUGROUP_NOT_FOUND");
					message.setMessageDetail("Menu Not Found With the Id");
					return new ResponseEntity<ResponseVO<MenuGroupVO>>(message, HttpStatus.NOT_FOUND);
				}

				if (currentMenu != null) {
					// MenuGroup menuGroup = new MenuGroup();
					currentMenu.setMenuGroupName(menuGroupVO.getMenuGroupName());
					List<Category> listCategory = currentMenu.getCategories();
					List<CategoryVO> listCategoryVO = menuGroupVO.getCategory();

					for (Category category : listCategory) {
						for (CategoryVO categoryVO : listCategoryVO) {
							if (category.getCategoryId() == categoryVO.getCategoryId()) {
								category.setCategoryName(menuGroupVO.getCategoryName());
								listCategory.add(category);
							}
						}
					}
					
					// currentMenu.setMenuGroupCode(menuGroupVO.getMenuGroupCode());
					currentMenu.setCategories(listCategory);
				}

				menuGroupService.saveMenuGroupData(currentMenu);
			}
			logger.info("Menu Updated Successfully");
			message.setMessageId("MENUGROUP_UPDATED");
			message.setMessageDetail("MenuGroup Updated successfully");
			return new ResponseEntity<ResponseVO<MenuGroupVO>>(message, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error occurred while Editing MenuGroup");
			message.setMessageId("MENU_DATA_SAVE_FAILED");
			message.setMessageDetail("Exception occured while Updating MenuGroup");
			return new ResponseEntity<ResponseVO<MenuGroupVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
	}

	/*
	 * Edit CategoryById
	 * 
	 */
	@RequestMapping(value = "/editCategoryById", method = RequestMethod.POST)
	public ResponseEntity<ResponseVO<CategoryVO>> editCategoryById(@RequestBody CategoryVO categoryVO) {
		ResponseVO<CategoryVO> message = new ResponseVO<>();

		if (categoryVO == null) {
			message.setMessageId("BLANK_DATA");
			message.setMessageDetail("Category can not be blank");
		}
		try {
			if (categoryVO != null) {
				// get menuGroup
				Category currentCategory = categoryService.findByCategoryId(categoryVO.getCategoryId());

				if (currentCategory == null) {
					logger.info("Category Not Found With Id {}" + categoryVO.getCategoryId());
					message.setMessageId("CATEGORY_NOT_FOUND");
					message.setMessageDetail("Category Not Found With the Id");
					return new ResponseEntity<ResponseVO<CategoryVO>>(message, HttpStatus.NOT_FOUND);
				}

				if (currentCategory != null) {
					Category category = new Category();
					category.setCategoryName(categoryVO.getCategoryName());
					category.setCategoryCode(categoryVO.getCategoryCode());
				}

				categoryService.saveCategoryData(currentCategory);
			}
			logger.info("Category Updated Successfully");
			message.setMessageId("CATEGORY_UPDATED");
			message.setMessageDetail("Category Updated successfully");
			return new ResponseEntity<ResponseVO<CategoryVO>>(message, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error occurred while Editing Category");
			message.setMessageId("CATEGORY_DATA_EDIT_FAILED");
			message.setMessageDetail("Exception occured while Updating Category Data");
			return new ResponseEntity<ResponseVO<CategoryVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
	}

	/*
	 * Edit SubCategory
	 * 
	 */
	@RequestMapping(value = "/editSubCategoryById", method = RequestMethod.POST)
	public ResponseEntity<ResponseVO<SubCategoryVO>> editSubCategoryById(@RequestBody SubCategoryVO subCategoryVO) {
		ResponseVO<SubCategoryVO> message = new ResponseVO<>();

		if (subCategoryVO == null) {
			message.setMessageId("BLANK_DATA");
			message.setMessageDetail("SubCategory can not be blank");
		}
		try {
			if (subCategoryVO != null) {
				// get menuGroup
				SubCategory currentSubCategory = subCategoryService
						.findBySubCategoryId(subCategoryVO.getSubCategoryId());

				if (currentSubCategory == null) {
					logger.info("SubCategory Not Found With Id {}" + subCategoryVO.getSubCategoryId());
					message.setMessageId("SUBCATEGORY_NOT_FOUND");
					message.setMessageDetail("SubCategory Not Found With the Id");
					return new ResponseEntity<ResponseVO<SubCategoryVO>>(message, HttpStatus.NOT_FOUND);
				}

				if (currentSubCategory != null) {
					SubCategory subCategory = new SubCategory();
					subCategory.setSubCategoryName(subCategoryVO.getSubCategoryName());
					// subCategory.setSubCategoryCode(subCategoryVO.getSubCategoryCode());
				}

				subCategoryService.saveSubCategoryData(currentSubCategory);
			}
			logger.info("SubCategory Updated Successfully");
			message.setMessageId("SUBCATEGORY_UPDATED");
			message.setMessageDetail("SubCategory Updated successfully");
			return new ResponseEntity<ResponseVO<SubCategoryVO>>(message, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error occurred while Editing Category");
			message.setMessageId("SUBCATEGORY_DATA_EDIT_FAILED");
			message.setMessageDetail("Exception occured while Updating SubCategory Data");
			return new ResponseEntity<ResponseVO<SubCategoryVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
	}

	/*
	 * Delete MenuGroup
	 * 
	 */

	@RequestMapping(value = "/deleteMenuById/{menuGroupId}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseVO<MenuGroupVO>> deleteMenuById(@PathVariable("menuGroupId") Long menuGroupId) {
		ResponseVO<MenuGroupVO> message = new ResponseVO<>();

		if (menuGroupId == null) {
			message.setMessageId("BLANK_ID");
			message.setMessageDetail("Id can not be blank");
		}
		try {
			if (menuGroupId != null) {
				// get menuGroup
				MenuGroup menuGroup = menuGroupService.findByMenuGroupId(menuGroupId);

				if (menuGroup == null) {
					logger.info("Menu Not Found With Id {}" + menuGroupId);
					message.setMessageId("MENU_NOT_FOUND");
					message.setMessageDetail("Menu Not Found With the Id");
					return new ResponseEntity<ResponseVO<MenuGroupVO>>(message, HttpStatus.NOT_FOUND);
				}

				if (menuGroup != null) {
					menuGroupService.deleteByMenuId(menuGroup.getMenuGroupId());
				}

			}
			logger.info("Menu Deleted Successfully");
			message.setMessageId("MENU_DELETED");
			message.setMessageDetail("Menu Deleted successfully");
			return new ResponseEntity<ResponseVO<MenuGroupVO>>(message, HttpStatus.OK);

		} catch (ConstraintViolationException cve) {
			cve.printStackTrace();
			logger.error("Error occurred while Deleting Menu");
			message.setMessageId("MENU_DELETE_FAILED");
			message.setMessageDetail("Please Delete Associated Product Details Before Deleting Menu");
			return new ResponseEntity<ResponseVO<MenuGroupVO>>(message, HttpStatus.EXPECTATION_FAILED);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error occurred while Deleting Menu");
			message.setMessageId("MENU_DELETE_FAILED");
			message.setMessageDetail("Exception occured while Deleting Menu Data");
			return new ResponseEntity<ResponseVO<MenuGroupVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
	}
}
