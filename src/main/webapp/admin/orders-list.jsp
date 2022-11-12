<%@ page import="nz.ac.sit.os.domain.order.MercOrderModel" %>
<%@ page import="java.util.List" %>
<%@ page import="nz.ac.sit.os.common.util.DateUtil" %>
<%@ page import="java.math.BigDecimal" %>
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
				<a href="/admin/fetch-order" class="brand-logo">
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
									Order List
								</div>
							</div>

							<ul class="navbar-nav header-right">
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
					<div class="row">
						<div class="col-xl-12">
							<div class="card bg-transparent shadow-none">
								<div class="card-header flex-wrap border-0 p-0 justify-content-start">
									<div class="table-tabs mr-2 mb-3 mr-auto">
										<ul class="nav nav-tabs" role="tablist">
											<li class="nav-item">
												<a class="nav-link active" data-toggle="tab" href="#AllStatus" aria-expanded="false">
													All Status
												</a>
											</li>
										</ul>
									</div>
									<select class="form-control style-2 mr-3 mb-3 default-select ">
										<option>Filter</option>
										<option>Date</option>
									</select>
									<select class="form-control style-2 mb-3 default-select ">
										<option>Newest</option>
										<option>Oldest</option>
									</select>
								</div>
								<div class="card-body p-0">
									<div class="tab-content" id="Tab">
										<div class="tab-pane fade active show" id="AllStatus">
											<div class="table-responsive rounded table-hover">
												<table class="table text-black card-table  mb-4 table-responsive-lg dataTablesCard" id="dataTable1">
													<thead class="bg-primary">
													<tr>
														<th>Order ID</th>
														<th>Date</th>
														<th>Table Number</th>
														<th>Amount</th>
														<th>Order Status</th>
														<th>Cooking Status</th>
														<th style="background-image:none;"></th>
													</tr>
													</thead>
													<tbody>
													<%
														List<MercOrderModel> orders = (List<MercOrderModel>)request.getAttribute("orders");
														for (MercOrderModel order : orders) {
															String cookingStatus = "PENDING";
															if("1".equals(order.getCookingStatus())) {
																cookingStatus = "DELIVERED";
															}else if("2".equals(order.getCookingStatus())) {
																cookingStatus = "CANCELED";
															}

															String orderStatus = "PRE-CREATED";
															if("1".equals(order.getOrderStatus())) {
																orderStatus = "CREATED";
															}else if("2".equals(order.getOrderStatus())) {
																orderStatus = "FINISHED";
															}else if("3".equals(order.getOrderStatus())) {
																orderStatus = "CANCELLED";
															}
													%>
													<tr class="alert alert-dismissible border-0">
														<td><a href="/admin/fetch-order-product?orderNo=<%=order.getOrderNo()%>"><%=order.getOrderNo()%></a></td>
														<td><%=DateUtil.getDateTime(order.getCreatedDate()+order.getCreatedTime())%></td>
														<td>No.<%=order.getTableNo()%></td>
														<td>$<%=new BigDecimal(order.getOrderAmount()).divide(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP)%></td>
														<td>
															<a class="btn text-warning bgl-warning" href="javascript:void(0);"><%=orderStatus%></a>
														</td>
														<td>
															<a class="btn text-warning bgl-warning" href="javascript:void(0);"><%=cookingStatus%></a>
														</td>
														<td>
															<div class="dropdown">
																<a class="btn-link" href="javascript:void(0)" data-toggle="dropdown" aria-expanded="false">
																	<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
																		 width="24px" height="24px" viewBox="0 0 24 24" version="1.1">
																		<g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
																			<rect x="0" y="0" width="24" height="24"></rect>
																			<circle fill="#000000" cx="12" cy="5" r="2"></circle><circle fill="#000000" cx="12" cy="12" r="2"></circle>
																			<circle fill="#000000" cx="12" cy="19" r="2"></circle></g></svg>
																</a>
															</div>
														</td>
													</tr>

													<%
														}
													%>
													</tbody>
												</table>
											</div>
										</div>
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
</body>
</html>