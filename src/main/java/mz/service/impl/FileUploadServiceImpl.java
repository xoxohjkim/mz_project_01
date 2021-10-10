package mz.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;
import mz.dto.Board;
import mz.dto.FileUpload;
import mz.dto.Member;
import mz.mapper.BoardMapper;
import mz.mapper.FileUploadMapper;
import mz.service.FileUploadService;

@Log4j
@Service
@Transactional
public class FileUploadServiceImpl implements FileUploadService {
	
	private static final String UPLOAD_PATH = "resources" + File.separator + "upload" + File.separator + "img" ;
	
	
	@Autowired
	private FileUploadMapper mapper;
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public int insertFile(Member loginUser, Board board, MultipartFile[] uploadFile, File realPath) {
		
		int res = boardMapper.insertGnrBoard(board);
		
		/* 업로드할 폴더 지정. 폴더가 없는 경우 생성 */
		File dir = new File(realPath, UPLOAD_PATH + "/" + loginUser.getId());
		
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		int no = mapper.nextBrdId();
		System.out.println("board max id : " + no);
		

		System.out.println("이미지업로드");
		for (MultipartFile  multipartFile : uploadFile) {
			 
			System.out.println("Upload File Name: " + multipartFile.getOriginalFilename());
			System.out.println("Upload File Size: " + multipartFile.getSize());
				
			String uploadFileName = multipartFile.getOriginalFilename();
			System.out.println("only file name: " + uploadFileName);
			
			//확장자 구하기
			String exc = uploadFileName.substring(uploadFileName.lastIndexOf(".")+1, uploadFileName.length());
			
			//랜덤이름
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			
			System.out.println(uploadFileName);
			File saveFile = new File(dir, uploadFileName);

			//String path = "upload/img/"+ uploadFileName+ "." + exc;
			
			try {
				multipartFile.transferTo(saveFile);
			} catch(Exception e) {
				e.getMessage();
			}
			
			
			String path = "upload/img/"+ loginUser.getId() + "/" + uploadFileName;
			
			System.out.println("저장될 path: " + path);
		
			
			FileUpload file = new FileUpload();
			file.setBrdId(no);
			file.setMember(loginUser);
			file.setPath(path);
	
			res += mapper.insertFile(file);
			
		 }
		
		return res;
	}

	@Override
	public int nextBrdId() {
		return mapper.nextBrdId();
	}

	@Override
	public List<FileUpload> selectFileByBrdId(int brdId) {
		return mapper.selectFileByBrdId(brdId);
	}

	
}
