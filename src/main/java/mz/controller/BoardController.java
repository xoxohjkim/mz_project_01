package mz.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j;
import mz.dto.Board;
import mz.dto.BoardGroup;
import mz.dto.Comment;
import mz.dto.Member;
import mz.dto.PageMaker;
import mz.dto.SearchCriteria;
import mz.service.BoardService;
import mz.service.CommentService;

@Log4j
@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private CommentService commentService;
	
	
	@GetMapping("/main")
	public String redirectGnr() {
		return "redirect:/board?kind=gnr";
	}
	//게시판 리스트
	@GetMapping("/board")
	public ModelAndView list(SearchCriteria cri, @Nullable @RequestParam String kind, @Nullable @RequestParam String id,
										@Nullable @RequestParam String cond, @Nullable @RequestParam String keyword) throws IOException {
		//parameter 한글안넘어감
		HttpHeaders responseHeaders = new HttpHeaders(); 
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
	
		log.info("board list");
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		
		ModelAndView mv = new ModelAndView();
		BoardGroup bg = boardService.getBoardByGroupKey(kind);
		
		List<Board> list = null;
		
		if(kind != null) {
			if(id != null) {
				log.info("게시글 상세");
				
				list = boardService.selectBoardByGroup(bg.getId());
				Board board = boardService.selectBoardById(Integer.parseInt(id));
				mv.addObject("board", board);
				mv.setViewName(kind + "_board/" + kind + "_board_detail");
				
			}else {	
				log.info("게시글 리스트");
				// board?kind=gnr / key로 board group의 id
				list = boardService.pagingSelectBoardByAll(cri, String.valueOf(bg.getId()), cond, keyword);
				pageMaker.setTotalCount(boardService.countSelectBoardByAll(cri, String.valueOf(bg.getId()), cond, keyword));
				
				mv.addObject("totalCnt", boardService.countSelectBoardByAll(cri, String.valueOf(bg.getId()), cond, keyword));
				mv.setViewName(kind + "_board/" + kind + "_board_list");
				
			}
		}
		mv.addObject("cond", cond);
		mv.addObject("keyword", keyword);
		mv.addObject("list", list);
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
			Board board = boardService.selectBoardById(Integer.parseInt(id));
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
		BoardGroup bgr = boardService.getBoardByGroupKey(kind);
		board.setBgr(bgr);
		board.setMember(loginUser);
		System.out.println(board);
		
		int res = boardService.insertGnrBoard(board);
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
		
		Board b = boardService.selectBoardById(id);
		
		int res = 0;
		if(act.equals("modify")) {
			log.info("게시물 수정");
			b.setTitle(board.getTitle());
			b.setContent(board.getContent());
			res = boardService.modifyGnrBoard(board);
			
		}else if(act.equals("delete")) {
			log.info("게시물 삭제");
			res = boardService.deleteGnrBoard(board);
		}
		System.out.println(board);
		return res;
	}
	
	
	//댓글리스트 post
	@ResponseBody
	@PostMapping("/board/comment/list")
	public List<Comment> commentList(@RequestBody Map<String, Object> map) {
		log.info("댓글 리스트 - post");
		int id = (int) map.get("id");
		List<Comment> commentList = commentService.selectCmtListByBrdId(id);
		return commentList;
	}
	
	
	//댓글 추가
	@ResponseBody
	@PostMapping("/comment/add")
	public int commentAct(@RequestBody Map<String, Object> map, HttpSession session, HttpServletRequest request) {
		session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");
		
		log.info("댓글 추가");
		String bid = map.get("bid").toString();
		String content = map.get("content").toString();
		
		Comment comment = new Comment();
		comment.setMember(loginUser);
		comment.setBoard(boardService.selectBoardById(Integer.parseInt(bid)));
		comment.setContent(content);
		int res = commentService.insertComment(comment);
		return res;
	}
	

	//댓글 삭제
	@ResponseBody
	@PostMapping("/comment/delete")
	public int commentDelete(@RequestBody Map<String, Object> map, HttpSession session, HttpServletRequest request) {
		session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");
		
		log.info("댓글 삭제");
		
		String cid = map.get("id").toString();
		Comment comment = commentService.getCommentById(Integer.parseInt(cid));
		int res = commentService.deleteComment(comment);
		
		return res;
	}
	
	//댓글 수정
	@ResponseBody
	@PostMapping("/comment/modify")
	public int commentModify(@RequestBody Map<String, Object> map, HttpSession session, HttpServletRequest request) {
		log.info("댓글 수정");
		String cid = map.get("id").toString();
		String content = map.get("content").toString();
		
		Comment comment = commentService.getCommentById(Integer.parseInt(cid));
		comment.setContent(content);
		int res = commentService.updateComment(comment);
		return res;
	}
	
	//댓글가져오기
	@ResponseBody
	@PostMapping("/comment/{id}")
	public Comment getCmt(@PathVariable int id) {
		System.out.println(id);
		Comment comment = commentService.getCommentById(id);
		System.out.println(comment);
		return comment;
		
	}
}
