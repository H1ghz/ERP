<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
pageContext.setAttribute("menu", "allok");
%>     
<%@ include file="../include/header.jsp" %>
<script>
function submitFilter() 
{
	document.getElementById('filterForm').submit()
}
</script>
<!----------------------------------------------------결재 승인 목록 시작------------------------------------------------------------------>
<main class="page-content">
	<div class="row mb-4">
		<div class="col-md-12">
			<div class="documentlist_title">
				<div class="cteam_subtitle">
					<h2 class="cteam_head_maintitle">전체승인내역</h2>&nbsp;
				</div>
				<hr>
				<form action="/ERP/approval/allok.do" method="get" id="filterForm">
					<div class="cteam_filters">
						<select class="cteam_filter" name="kind" onchange="document.getElementById('filterForm').submit();">
							<option value="">문서구분</option>
							<option value="품의서" ${param.kind =='품의서' ? 'selected':''}>품의서</option>
							<option value="기안서" ${param.kind =='기안서' ? 'selected':''}>기안서</option>
							<option value="연차신청서" ${param.kind =='연차신청서' ? 'selected':''}>연차신청서</option>
						</select> 
						<select class="cteam_filter" name="team" onchange="document.getElementById('filterForm').submit();">
							<option value="">부서</option>
							<option value="경영지원" ${param.team =='경영지원' ? 'selected':''}>경영지원</option>
							<option value="개발" ${param.team =='개발' ? 'selected':''}>개발</option>
							<option value="디자인" ${param.team =='디자인' ? 'selected':''}>디자인</option>
						</select>
						<div class="cteam_search">
							<input type="text" id="searchInput" name="keyword" placeholder="검색" value="${param.keyword}">
							<button id="searchButton" class="cteam_searchButton" onclick="submitFilterForm();">🔍</button>
						</div>
					</div>
				</form>
			</div>
			<c:forEach var="app" items="${ListAll}">
				<div class="approval_list">
					<div class="cteam_approval_item_inner">
						<a href="view.do?approval_no=${app.approval_no}&menu=allok" class="cteam_card_link">
							<div class="cteam_info_group">
								<div class="cteam_left">
									<div class="cteam_icon">📄</div>
									<div class="cteam_title">
										기본 양식<br>${app.kind}
									</div>
									<div class="info">
										<strong>${app.drafter.name}</strong><br> ${app.drafter.level} · ${app.drafter.team}
									</div>
								</div>
								<div class="cteam_desc" style="margin-left: 20px;">${app.approval_title}</div>
							</div>
							<div class="cteam_right">
								<div class="cteam_date">${app.writedate}</div>
								<div class="cteam_status_approval">${app.document_status}</div>
							</div>
						</a>
					</div>
				</div>
			</c:forEach>
			<c:if test="${empty ListAll}">
				<div class="approval_list">
					<div class="cteam_approval_item_inner">
						<p>결재 문서가 없습니다.</p>
					</div>
				</div>
			</c:if>
			<div class="list-footer">
				<nav aria-label="..." class="pagination-nav">
					<div class="pagination-nav">
						<ul class="pagination">
							<c:forEach var="i" begin="${startbk}" end="${endbk}">
								<li class="page-item ${i == page ? 'active' : ''}">
									<a class="page-link" href="?page=${i}&kind=${kind}&status=${status}&keyword=${keyword}&mode=${mode}">${i}</a>
								</li>
							</c:forEach>
						</ul>
					</div>
				</nav>
			</div>
		</div>
	</div>
</main>
<!----------------------------------------------------결재 승인 목록 종료------------------------------------------------------------------>
<%@ include file="../include/footer.jsp" %>