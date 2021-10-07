package mz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j;
import mz.dto.Board;
import mz.service.BoardService;

@Log4j
@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	
	@GetMapping("/gnr_board")
	public ModelAndView gnrList() {
		log.info("gnr_board list");
		ModelAndView mv = new ModelAndView();
		
		//List<Board> list = service.selectBoardByGroup(1);
		List<Board> all = service.selectBoardByAll();
		//System.out.println(list);
		
		//mv.addObject("list", list);
		mv.addObject("all", all);
		mv.setViewName("gnr_board");
		return mv;
	}
}
