/*
package com.erp.control;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.erp.dto.*;
import com.erp.vo.*;

@Controller
public class ERPController 
{
	@Autowired
	userDTO userdto;
	
	@Autowired
    noticeDTO noticedto;
	
	final static String uploadPath = "D:\\sj\\springws\\ERP03\\upload";
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String Login()
	{
		
		return "redirect:/login/login.do";
	}
	
	@RequestMapping(value = "/login/login.do", method = RequestMethod.GET)
    public String showLogin() {
        return "login/login";
    }
	
	//로그인 로직
	@RequestMapping(value = "/login/login.do", method = RequestMethod.POST)
	public String DoLogin(userVO inputUser, HttpSession session, Model model) {
		userVO loginUser = userdto.login(inputUser.getUsernum(), inputUser.getUserpw(), inputUser.isFirstlogin());

		if (loginUser == null) {
			// 로그인 실패
			model.addAttribute("loginError", "아이디 또는 비밀번호가 올바르지 않습니다.");
			return "login/login";
		} 

		// 로그인 성공 → 세션에 저장
		session.setAttribute("loginUser", loginUser);

		// 첫 로그인인 경우 비밀번호 변경 페이지로 리다이렉트
		if (loginUser.isFirstlogin()) {
			return "redirect:/login/changepw.do";
		} else {
			return "redirect:/main.do";
		}
	}
	
	//비밀번호 변경 페이지
	@RequestMapping(value = "/changepw.do", method = RequestMethod.GET)
	public String changePwPage() {
		return "login/changepw";
	}
	
	
	//처음 로그인 한 경우 비밀번호 변경 로직
	@RequestMapping(value = "/login/changepw.do", method = RequestMethod.POST)
	public String DoChangePw(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		userVO loginUser = (userVO) session.getAttribute("loginUser");
		
		String newpw = request.getParameter("newpw");
		String newpw2 = request.getParameter("newpw2");
		
		//새비밀번호가 비어있거나 새비밀번호와 확인비밀번호가 일치하지 않는 경우
		if(newpw == null || !(newpw.equals(newpw2))) {
			model.addAttribute("pwerror", "비밀번호가 일치하지 않았습니다");
			return "login/changepw";
		}
		
		//비밀번호 변경이 실패했을 경우
		if(!(userdto.changePassword(loginUser.getUsernum(), newpw))) {
			model.addAttribute("pwError", "비밀번호 변경 실패");
			return "login/changepw";
		}
		
		loginUser.setFirstlogin(false);
		loginUser.setUserpw(newpw);
		session.setAttribute("loginUser", loginUser);
		
		
		return "redirect:/main.do";
	}

	
	
	//메인 화면
	@RequestMapping(value = "/main.do", method = RequestMethod.GET)
	public String Main()
	{
		return "main";
	}	
	
	//결재관리 (대기,반려,진행,승인)
	@RequestMapping(value = "/approval/list.do", method = RequestMethod.GET)
	public String ApprovalList()
	{
		return "approval/list";
	}	
	
	//결재수신
	@RequestMapping(value = "/approval/recv.do", method = RequestMethod.GET)
	public String ApprovalRecv()
	{
		return "approval/recv";
	}
	
	//결재수신 뷰
	@RequestMapping(value = "/approval/recv_view.do", method = RequestMethod.GET)
	public String ApprovalRecvView()
	{
		return "approval/recv_view";
	}
	
	@RequestMapping(value = "/approval/recv_view.do", method = RequestMethod.POST)
	public String DoApprovalComment()
	{
		return "approval/recv";
	}
	
	//결재작성(기안서 보기)
	@RequestMapping(value = "/approval/view.do", method = RequestMethod.GET)
	public String ApprovalView()
	{
		return "approval/view";
	}
	
	
	
	//결재작성
	@RequestMapping(value = "/approval/write.do", method = RequestMethod.GET)
	public String ApprovalWrite()
	{
		return "approval/write";
	}
	
	//결재작성 (등록)
	@RequestMapping(value = "/approval/write.do", method = RequestMethod.POST)
	public String DoApprovalWrite()
	{
		return "redirect:/approval/view.do";
	}
	
	//결재수정
	@RequestMapping(value = "/approval/modify.do", method = RequestMethod.GET)
	public String ApprovalModify()
	{
		return "approval/modify";
	}
	
	//결재수정 (POST)
	@RequestMapping(value = "/approval/modify.do", method = RequestMethod.POST)
	public String ApprovalDoModify()
	{
		return "redirect:/approval/view.do";
	}
	
	//전제 승인 내역
	@RequestMapping(value = "/approval/allok.do", method = RequestMethod.GET)
	public String ApprovalAllok()
	{
		return "approval/allok";
	}
	
	//공지사항 리스트
	@RequestMapping(value = "/notice/list.do", method = RequestMethod.GET)
	public String NoticeList(@RequestParam(defaultValue = "1") int page,
				             @RequestParam(required = false) String searchKey,
				             @RequestParam(required = false) String searchWord,
				             @RequestParam(required = false) String notice_team,
				             HttpSession session,Model model)
	{
		searchVO search = new searchVO();
		search.setPageno(page);
        search.setSearchKey(searchKey);
        search.setSearchWord(searchWord);
        search.setNotice_team(notice_team);
        
        int total = noticedto.GetTotal(search);
        List<noticeVO> list = noticedto.noticeList(search);
        List<notice_teamVO> teamList = noticedto.noticeTeamList();
        
        model.addAttribute("total", total);
        model.addAttribute("list", list);
        model.addAttribute("search", search);
        model.addAttribute("teamList", teamList);
        
		return "notice/list";
	}
	
	//공지사항 상세보기
	@RequestMapping(value = "/notice/view.do", method = RequestMethod.GET)
	public String NoticeView(@RequestParam int notice_no, Model model)
	{
		noticeVO vo = noticedto.getNoticeInfo(notice_no);
		
		vo.setNotice_content(com.erp.util.WebUtil.Text2HTML(vo.getNotice_content()));
		
		model.addAttribute("vo", vo);
		
		return "notice/view";
	}
	
	//공지사항 등록
	@RequestMapping(value = "/notice/write.do", method = RequestMethod.GET)
	public String NoticeWrite()
	{
		return "notice/view";
	}
	
	//공지사항 등록완료 (POST)
	@RequestMapping(value = "/notice/write.do", method = RequestMethod.POST)
	public void DoNoticeWrite(noticeVO vo, @RequestParam("file") MultipartFile file,HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		userVO login = (userVO) request.getSession().getAttribute("login");
		
		if(file != null)
		{			
			String originalFileName = file.getOriginalFilename();
			System.out.println("originalFileName:" + originalFileName);
			
			//파일 이름이 중복되지 않도록 파일 이름 변경 : 서버에 저장할 이름
			// UUID 클래스 사용
			UUID uuid = UUID.randomUUID();
			String savedFileName = uuid.toString();
			
			//파일 생성
			File newFile = new File(uploadPath + savedFileName);
			
			//서버로 전송
			file.transferTo(newFile);
			
			vo.setFname(originalFileName);
			vo.setPname(savedFileName);
		}else
		{
			System.out.println("File not upload..");
		}
		
		vo.setUsernum(login.getUsernum());
		
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		vo.setnoticedate(today);
		
		noticedto.noticeInsert(vo);
		
		response.sendRedirect("/notice/view.do?notice_no=" + vo.getNotice_no());
	}
	
	// 공지사항 삭제
	@RequestMapping(value = "/notice/delete.do", method = RequestMethod.POST)
    public void NoticeDelete(@RequestParam(required = true) int notice_no, HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
		if( request.getSession().getAttribute("login") == null)
		{
			//로그인하지 않음.
			response.sendRedirect("list.do");
		}	
		noticedto.noticeDelete(notice_no);
		response.sendRedirect("redirect:/notice/list.do");
    }
	
	// 사원관리 - 전체 사원 목록 조회
	@RequestMapping(value = "/user/list.do", method = RequestMethod.GET)
	public String UserList(@ModelAttribute("searchVO") searchVO vo, Model model) {

		// 전체 사원 목록 조회
		List<userVO> userList = userdto.getAllUserList(vo);

		// 결과를 모델에 담아 전달
		model.addAttribute("userList", userList);

		// 검색 조건도 같이 전달 (JSP에서 입력값 유지 용도)
		model.addAttribute("searchVO", vo);

		return "user/list";
	}
	
	//모달창 구성원 목록 조회
	@RequestMapping(value = "/user/modal.do", method = RequestMethod.GET)
	public String UserModalList(Model model) {
	    // 조건 없이 전체 사원 목록 가져오기 (정렬 포함)
	    List<userVO> userList = userdto.getModalList(new userVO());

	    // 사원 목록을 모델에 담아서 JSP로 전달
	    model.addAttribute("userList", userList);

	    return "user/modal"; // user/modal.jsp에 모달창 구성
	}

	
	//사원 세부정보
	@RequestMapping(value = "/user/view.do", method = RequestMethod.GET)
	public String UserView(@RequestParam(required = true) String usernum, Model model)
	{
		userVO vo = userdto.getUserInfo(usernum);
		
		model.addAttribute("user", vo);
		
		return "user/view";
	}
	
	//사원수정
	@RequestMapping(value = "/user/modify.do", method = RequestMethod.GET)
	public String UserModify(@RequestParam(required = true) String usernum, Model model)
	{
		userVO vo = userdto.getUserInfo(usernum);
		
		model.addAttribute("user", vo);
		
		
		return "user/modify";
	}
	
	//사원수정 처리 로직(POST) 
	@RequestMapping(value = "/user/modify.do", method = RequestMethod.POST)
	public String DoUserModify(HttpServletRequest request, userVO vo, Model model)
	{
		HttpSession session = request.getSession(); 
		userVO loginUser = (userVO)session.getAttribute("loginUser");
		
		//관리자가 아니면 사원 등록 실패
		if(loginUser == null || !(loginUser.isAuthority())) {
			model.addAttribute("error", "관리자만 사원을 수정할 수 있습니다");
			return "user/modify";
		}
		
		// 사원 등록 수행
	    boolean result = userdto.updateUserInfo(vo);
	    if (!result) {
	        model.addAttribute("error", "사원 수정에 실패했습니다.");
	        return "user/modify";
	    }
		
		return "redirect:/user/list";
	}
	
	//사원등록
	@RequestMapping(value = "/user/write.do", method = RequestMethod.GET)
	public String UserWrite(HttpServletRequest request)
	{
		
		return "user/write";
	}
	
	//사원등록 처리 로직 (POST)
	@RequestMapping(value = "/user/write.do", method = RequestMethod.POST)
	public String DoUserWrite(HttpServletRequest request, userVO vo, Model model)
	{
		HttpSession session = request.getSession(); 
		userVO loginUser = (userVO)session.getAttribute("loginUser");
		
		//관리자가 아니면 사원 등록 실패
		if(loginUser == null || !(loginUser.isAuthority())) {
			model.addAttribute("error", "관리자만 사원을 등록할 수 있습니다");
			return "user/write";
		}
		
		// 사원 등록 수행
	    boolean result = userdto.insertUser(loginUser, vo);
	    if (!result) {
	        model.addAttribute("error", "사원 등록에 실패했습니다.");
	        return "user/write";
	    }
		
		return "redirect:/user/list";
	}
	
	//내 정보
	@RequestMapping(value = "/user/myinfo.do", method = RequestMethod.GET)
	public String UserMyinfo(@RequestParam(required = true) String usernum, Model model)
	{
		userVO vo = userdto.getUserInfo(usernum);
		
		model.addAttribute("user", vo);
		
		return "user/myinfo";
	}
}
*/
