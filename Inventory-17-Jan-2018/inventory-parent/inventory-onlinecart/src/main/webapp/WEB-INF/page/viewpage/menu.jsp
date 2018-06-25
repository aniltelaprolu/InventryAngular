<link rel="stylesheet" href="resources/css/menu.css" />
<div class="row">
	<div class="col-md-9 topmenu">
		<%-- <ul class="topnavlist">
			<c:forEach items="${menus}" var="menu">
				<li><a href="${contextRoot}/show/category/${menu.menuGroupId}">${menu.menuGroupName}</a></li>
			</c:forEach>
		</ul> --%>

		<c:forEach items="${menus}" var="menu">
			<div class="dropdown">
				<button class="dropbtn">${menu.menuGroupName}</button>
				<div class="dropdown-content">
					<c:forEach items="${categories}" var="category">
						<c:if test="${menu.menuGroupId eq category.menuGroupId}">
							<h4>${category.categoryName}</h4>
							<c:forEach items="${subcategories}" var="subcategory">
								<c:if test="${category.categoryId eq subcategory.categoryId}">
									<a href="#"><h6>${subcategory.subCategoryName}</h6></a>
								</c:if>
							</c:forEach>
						</c:if>
					</c:forEach>
				</div>
			</div>
		</c:forEach>


	</div>
	<div class="col-md-3">
		<form>
			<div class="input-group add-on">
				<div class="input-group-btn">
					<button class="btn btn-default search" type="submit">
						<i class="glyphicon glyphicon-search"></i>
					</button>
				</div>
				<input class="form-control" placeholder="Search" name="srch-term"
					id="srch-term" type="text">

			</div>
		</form>
	</div>
</div>