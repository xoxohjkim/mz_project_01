package mz.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j;
import mz.dto.Board;
import mz.dto.Comment;
import mz.dto.Criteria;
import mz.dto.ImageFile;
import mz.dto.Member;
import mz.dto.PageMaker;
import mz.service.BoardService;
import mz.service.CommentService;
import mz.service.ImgFileService;
import mz.service.MemberService;
import mz.service.MypageService;
import mz.util.SHA256Util;


@Controller
@Log4j
public class MypageController {
	
	@Autowired
	private MypageService mypageService;
	
	@Autowired
	private ImgFileService fileService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private MemberService memberService;
	
	
	@GetMapping("/mypage/boardList")
	public ModelAndView myBoardList(Criteria cri, HttpSession session, HttpServletRequest request) {
		log.info("mypage - b list");
		session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		
		ModelAndView mv = new ModelAndView();
		List<Board> list = mypageService.pagingSelectMyBoardList(cri, loginUser.getId());
		int totalCnt = mypageService.countSelectMyBoardList(cri, loginUser.getId());
		
		List<ImageFile> fileList = null;
		Board board = null;
		
		for(Board b : list) {
			fileList = fileService.selectFileByBrdId(b.getId());
			int cmtCnt = commentService.countByBrdId(b.getId());
			
			b.setFileList(fileList);
			b.setCmtCnt(cmtCnt);
		}
		
		
		pageMaker.setTotalCount(totalCnt);
		mv.addObject("board", board);
		mv.addObject("list", list);
		mv.addObject("totalCnt", totalCnt);
		mv.addObject("pageMaker", pageMaker);
		mv.setViewName("/mypage/mypage_board_list");
		
		return mv;
	}
	
	@GetMapping("/mypage/commentList")
	public ModelAndView myCmtList(Criteria cri, HttpSession session, HttpServletRequest request) {
		
		log.info("mypage - comment list");
		
		session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		
		ModelAndView mv = new ModelAndView();
		List<Comment> list = mypageService.pagingSelectMyCmtList(cri, loginUser.getId());
		int totalCnt = mypageService.countSelectMyCmtList(cri, loginUser.getId());
		
		List<ImageFile> fileList = null;
	
		for(Comment c : list) {
			fileList = fileService.selectFileByBrdId(c.getBoard().getId());
			int cmtCnt = commentService.countByBrdId(c.getBoard().getId());
			c.getBoard().setFileList(fileList);
			c.getBoard().setCmtCnt(cmtCnt);
		}
		
		System.out.println("total:" + totalCnt);
		
		pageMaker.setTotalCount(totalCnt);
		mv.addObject("list", list);
		mv.addObject("totalCnt", totalCnt);
		mv.addObject("pageMaker", pageMaker);
		mv.setViewName("/mypage/mypage_comment_list");
		return mv;
	}
	
	
	@GetMapping("/mypage/myInfo")
	public String myInfo() {
		return "/mypage/mypage_member_info";
	}

	@ResponseBody	
	@PostMapping("/infoConfirm")
	public int updateInfoConfirm(@RequestBody Map<String, String> map) {
		log.info("info - confirm");
		
		String condition = map.get("cond").toString();
		String keyword = map.get("key").toString();
		
		System.out.println(condition + "/" +  keyword);
		int res = memberService.infoCheck(condition, keyword);
		log.info(memberService.infoCheck(condition, keyword));
		//res = 1이면 존재, 0이면 없음
		return res;
	}
	
	@ResponseBody
	@PostMapping("/updateInfo")
	public int updateMemberInfo(Member member, HttpSession session) {
		log.info("member - update");
		int res = memberService.updateMember(member);
		
		//session 재설정
		session.setAttribute("loginUser", member);
		
		return res;
	}
	

	

}
