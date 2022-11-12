<%@ page import="java.util.List" %>
<%@ page import="nz.ac.sit.os.domain.product.ProductModel" %>
<!DOCTYPE html>
<html lang="en">

	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<title>Restaurant Admin Dashboard </title>
	<!-- Favicon icon -->
	<link rel="icon" type="image/png" sizes="16x16" href="./images/favicon.png">
	<link href="./vendor/bootstrap-select/dist/css/bootstrap-select.min.css" rel="stylesheet">
	<link href="./vendor/datatables/css/jquery.dataTables.min.css" rel="stylesheet">
	<link href="./css/style.css" rel="stylesheet">
	<%
		List<ProductModel> products = (List<ProductModel>)request.getAttribute("products");
	%>
</head>
	<body>

		<!--*******************
			Preloader start
		********************-->
		<div id="preloader">
			<div class="sk-three-bounce">
				<div class="sk-child sk-bounce1"></div>
				<div class="sk-child sk-bounce2"></div>
				<div class="sk-child sk-bounce3"></div>
			</div>
		</div>
		<!--*******************
			Preloader end
		********************-->

		<!--**********************************
			Main wrapper start
		***********************************-->
		<div id="main-wrapper">
			<!--**********************************
				Nav header start
			***********************************-->
			<div class="nav-header">
				<a href="fetch-order" class="brand-logo">
					<img src="images/logo.png" width="30%">
					<div class="brand-title">Café</div>
				</a>
				<div class="nav-control">
					<div class="hamburger">
						<span class="line"></span><span class="line"></span><span class="line"></span>
					</div>
				</div>
			</div>
			<!--**********************************
				Nav header end
			***********************************-->

			<!--**********************************
				Header start
			***********************************-->
			<div class="header">
				<div class="header-content">
					<nav class="navbar navbar-expand">
						<div class="collapse navbar-collapse justify-content-between">
							<div class="header-left">
								<div class="dashboard_bar">
									Add Product
								</div>
							</div>

							<ul class="navbar-nav header-right">
<%--								<li class="nav-item">--%>
<%--									<div class="input-group search-area d-xl-inline-flex d-none">--%>
<%--										<input type="text" class="form-control" placeholder="Search here...">--%>
<%--										<div class="input-group-append">--%>
<%--											<button class="input-group-text"><i class="flaticon-381-search-2"></i></button>--%>
<%--										</div>--%>
<%--									</div>--%>
<%--								</li>--%>
								<li class="nav-item dropdown header-profile">
									<a class="nav-link" href="#" role="button" data-toggle="dropdown">
										<img src="images/profile/pic1.jpg" width="20" alt=""/>
										<div class="header-info">
											<span class="fs-20 font-w500">Lucas Wang</span>
											<small>Super Admin</small>
										</div>
									</a>
									<div class="dropdown-menu dropdown-menu-right">
										<a href="./login.html" class="dropdown-item ai-icon">
											<svg id="icon-logout" xmlns="http://www.w3.org/2000/svg" class="text-danger" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"></path><polyline points="16 17 21 12 16 7"></polyline><line x1="21" y1="12" x2="9" y2="12"></line></svg>
											<span class="ml-2">Logout </span>
										</a>
									</div>
								</li>
							</ul>
						</div>
					</nav>
				</div>
			</div>
			<!--**********************************
				Header end ti-comment-alt
			***********************************-->

			<!--**********************************
				Sidebar start
			***********************************-->
			<div class="deznav">
				<div class="deznav-scroll">
					<ul class="metismenu" id="menu">
<%--						<li><a class=" ai-icon" href="#" aria-expanded="false">--%>
<%--								<i class="flaticon-046-home"></i>--%>
<%--								<span class="nav-text">Home Page</span>--%>
<%--							</a>--%>
<%--						</li>--%>
						<li><a class=" ai-icon" href="/admin/fetch-order" aria-expanded="false">
							<i class="flaticon-044-menu"></i>
							<span class="nav-text">Orders List</span>
						</a>
						</li>
						<li><a class=" ai-icon" href="/admin/fetch-product" aria-expanded="false">
							<i class="flaticon-031-ellipsis"></i>
							<span class="nav-text">Product List</span>
						</a>
						</li>
						<li><a class=" ai-icon" href="/admin/fetch-latest-waited-order" aria-expanded="false">
							<i class="flaticon-044-menu"></i>
							<span class="nav-text">Screen Display</span>
						</a>
						</li>
					</ul>
				</div>
			</div>
			<!--**********************************
				Sidebar end
			***********************************-->

			<!--**********************************
				Content body start
			***********************************-->
			<div class="content-body">
				<!-- row -->
				<div class="container-fluid">
					<div class="d-flex mb-3 mb-lg-4 align-items-center">
						<div class="mr-auto d-none d-lg-block">
							<div class="d-flex flex-wrap align-items-center">
								<div class="welcome-text">
									<h4>Hey, welcome back!</h4>
									<p class="mb-0">Add new products here</p>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12">
							<div class="card">
<%--								<div class="card-header">--%>
<%--									<h4 class="card-title">Vertical Forms with icon</h4>--%>
<%--								</div>--%>
								<div class="card-body">
									<div class="basic-form">
										<form class="form-valide-with-icon" action="/admin/add-product" method="post">
											<div class="form-group">
												<label class="text-label">Name *</label>
												<div class="input-group">
													<div class="input-group-prepend">
													</div>
													<input type="text" class="form-control" id="val-name" name="name" placeholder="Enter a product name..">
												</div>
											</div>
											<div class="form-group">
												<label class="text-label">Price *</label>
												<div class="input-group">
													<div class="input-group-prepend">
													</div>
													<input type="text" class="form-control" id="val-price" name="price" placeholder="Enter a product price..">
												</div>
											</div>
											<div class="form-group">
												<label class="text-label">Photo *</label>
												<div class="input-group mb-3">
													<div class="custom-file">
														<input type="hidden" name="imagePath" id="imagePath" value="">
														<input type="file" class="custom-file-input" id="image_file">
														<label class="custom-file-label" id="imagePathShown">Choose file</label>
													</div>
													<div class="input-group-append">
														<span class="input-group-text" onclick="upload()">Upload</span>
													</div>
												</div>
											</div>
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="val-description">Description <span
														class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<textarea class="form-control" id="val-description" name="desc" rows="5" placeholder="Type detail information of products here."></textarea>
												</div>
											</div>

											<button type="submit" class="btn mr-2 btn-primary">Submit</button>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--**********************************
				Content body end
			***********************************-->
		</div>
		<!--**********************************
			Main wrapper end
		***********************************-->

<!--**********************************
    Scripts
***********************************-->
<!-- Required vendors -->
<script src="./vendor/global/global.min.js"></script>
<script src="./vendor/bootstrap-select/dist/js/bootstrap-select.min.js"></script>
<script src="./vendor/datatables/js/jquery.dataTables.min.js"></script>
<script src="./js/plugins-init/datatables.init.js"></script>
<script src="./js/custom.js"></script>
<script src="./js/deznav-init.js"></script>
<script src="./js/demo.js"></script>
<!--<script src="./js/styleSwitcher.js"></script>-->
		<script type="text/javascript">
			$("form").submit(function(e){
				var para = $("#val-name").val();
				var para2 = $("#val-price").val();
				var para3 = $("#imagePath").val();
				var para4 = $("#val-description").val();
				if(para == ""
					|| para2 == ""
						|| para3 == ""
						|| para4 == "") {
					alert("Fields can not be empty.");
					return false;
				}
			});

			function upload() {
				var files = $("#image_file")[0].files
				if (files.length <= 0) {
					return alert("Please choose a photo first.");
				}
				var fd = new FormData()
				fd.append('avatar', files[0])

				$.ajax({
					method: 'POST',
					url: '/admin/upload-dish-photo',
					data: fd,
					contentType: false,
					processData: false,
					success: function(res) {
						alert("Upload successful!");
						$("#imagePath").val(res);
					}
				})
			}
		</script>
</body>
</html>