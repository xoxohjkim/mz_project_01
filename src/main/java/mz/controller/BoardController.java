package mz.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j;
import mz.dto.Board;
import mz.dto.BoardGroup;
import mz.dto.Criteria;
import mz.dto.Member;
import mz.dto.PageMaker;
import mz.service.BoardService;
import oracle.net.aso.b;

@Log4j
@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	
	@GetMapping("/main")
	public String redirectGnr() {
		return "redirect:/board?kind=gnr";
	}
	//게시판 리스트
	@GetMapping("/board")
	public ModelAndView list(Criteria cri, @Nullable @RequestParam("kind") String kind, @Nullable @RequestParam("id") String id) {
		log.info("board list");

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		cri.setPerPageNum(10);
		
		System.out.println(pageMaker);
		ModelAndView mv = new ModelAndView();
		
		BoardGroup bg = service.getBoardByGroupKey(kind);
		
		
		if(kind != null) {
			//id 있을 시 글 상세 
			if(id != null) {
				//List<Board> list = service.selectBoardByGroup(bg.getId());
				List<Board> list = service.pagingSelectBoardByAll(cri);
				int num = service.countSelectBoardByAll();
				pageMaker.setTotalCount(num);
				Board board = service.selectBoardById(Integer.parseInt(id));
				mv.addObject("board", board);
				mv.addObject("list", list);
				mv.setViewName(kind + "_board/" + kind + "_board_detail");
				
			}else {	
				// board?kind=gnr
				// key로 board group의 id
				List<Board> list = service.selectBoardByGroup(bg.getId());
				System.out.println(list);
				mv.addObject("list", list);
				mv.setViewName(kind + "_board/" + kind + "_board_list");
			}
		}
		
		mv.addObject("kind", kind);
		mv.addObject("pageMaker", pageMaker);
		
		return mv;
		
	}
	
	//form 창띄우기
	@GetMapping("/form")
	public ModelAndView Form(@Nullable @RequestParam("id") String id, @RequestParam("kind") String kind, @RequestParam("act") String act, HttpSession session, HttpServletRequest request) {
		log.info("form");

		session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");
		log.info("현재 로그인 - " + loginUser);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("kind", kind);
		mv.addObject("act", act);
		
		if(id != null) {
			Board board = service.selectBoardById(Integer.parseInt(id));
			mv.addObject("board", board);
		}
		
		if(act.equals("write")) {
			mv.setViewName(kind + "_board/" + kind + "_write_form");
			
		}else if(act.equals("modify")) {
			mv.setViewName(kind + "_board/" + kind + "_modify_form");
		}
		return mv;
	}
	
	//form - post
	//write
	@ResponseBody
	@PostMapping("/form/{kind}/{act}")
	public int write(Board board, @PathVariable String kind, @PathVariable String act, HttpSession session, HttpServletRequest request) {
		log.info("insert board");
		
		session = request.getSession();
		System.out.println(board);
		//인증객체로 바꿔야됨
		Member loginUser = (Member) session.getAttribute("loginUser");
		
		log.info("현재 로그인 - " + loginUser);
		BoardGroup bgr = service.getBoardByGroupKey(kind);
		board.setBgr(bgr);
		board.setMember(loginUser);
		System.out.println(board);
		
		int res = service.insertGnrBoard(board);
		return res;
	}
	
	//form - post
	// modify, delete
	@ResponseBody
	@PostMapping("/form/{kind}/{act}/{id}")
	public int modifyAndDelete(Board board, @PathVariable String kind, @PathVariable String act, @PathVariable int id, HttpSession session, HttpServletRequest request) {
		log.info("update, delete board");
		log.info(kind +"/"+ act +"/"+ id);
		//인증객체로 바꿔야됨
		session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");
		
		log.info("현재 로그인 - " + loginUser);
		
		Board b = service.selectBoardById(id);
		
		int res = 0;
		if(act.equals("modify")) {
			log.info("게시물 수정");
			b.setTitle(board.getTitle());
			b.setContent(board.getContent());
			res = service.modifyGnrBoard(board);
			
		}else if(act.equals("delete")) {
			log.info("게시물 삭제");
			res = service.deleteGnrBoard(board);
		}
		System.out.println(board);
		
		
		return res;
	}
}
