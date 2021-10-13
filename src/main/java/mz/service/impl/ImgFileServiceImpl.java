package mz.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import mz.dto.Board;
import mz.dto.ImageFile;
import mz.dto.Member;
import mz.mapper.ImageFileMapper;
import mz.service.BoardService;
import mz.service.ImgFileService;

@Service
public class ImgFileServiceImpl implements ImgFileService {
	
	private static final String UPLOAD_PATH = "resources" + File.separator + "upload" + File.separator + "img" ;
	
	
	@Autowired
	private ImageFileMapper mapper;
	
	@Autowired
	private BoardService boardService;
	
	@Transactional
	@Override
	public int insertFile(Member loginUser, Board board, MultipartFile[] uploadFile, File realPath) {
		
		int res = boardService.insertGnrBoard(board);
		
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
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			Date nowdate = new Date();
			uploadFileName = formatter.format(nowdate) + "_" + uploadFileName;
			
			System.out.println(uploadFileName);
			File saveFile = new File(dir, uploadFileName);

			//String path = "upload/img/"+ uploadFileName+ "." + exc;
			
			try {
				multipartFile.transferTo(saveFile);
			} catch(Exception e) {
				e.getMessage();
			}
			
			//String path = "upload/img/"+ loginUser.getId() + "/" + uploadFileName;
			
			ImageFile file = new ImageFile();
			file.setBrdId(no);
			file.setMember(loginUser);
			file.setName(uploadFileName);
	
			res += mapper.insertFile(file);
			
		 }
		
		return res;
	}

	@Override
	public int nextBrdId() {
		return mapper.nextBrdId();
	}

	@Override
	public List<ImageFile> selectFileByBrdId(int brdId) {
		return mapper.selectFileByBrdId(brdId);
	}
	
	@Transactional
	@Override
	public int modifyFile(Member loginUser, String[] delImgId, Board board, MultipartFile[] uploadFile, File realPath) {
	
		// 1.삭제할 이미지id 삭제
		int res = 0;
		ImageFile file = null;
		int[] nums = null;
		
		File dir = new File(realPath, UPLOAD_PATH + "/" + loginUser.getId() + "/");
		File files[] = dir.listFiles();
	
		for(String id : delImgId) {
			if(!id.equals("")){
				
				nums = Arrays.stream(delImgId).mapToInt(Integer::parseInt).toArray();

				file = getFileByFileId(Integer.parseInt(id));
				
				res += mapper.deleteFile(file);

				//dir 파일목록 -  받아온 파일 이름이 같을 시 삭제
				for(int i=0; i<files.length; i++) {
					File uploadedFile = files[i];
					
					String fileName = file.getName();
					if(fileName.equals(uploadedFile.getName())) {
						uploadedFile.delete();
				}	
			}
		}
		
		//2. 새로운 첨부파일 받아오기
		for (MultipartFile  multipartFile : uploadFile) {
			 
			//System.out.println("Upload File Name: " + multipartFile.getOriginalFilename());
			//System.out.println("Upload File Size: " + multipartFile.getSize());
				
			String uploadFileName = multipartFile.getOriginalFilename();
			//System.out.println("only file name: " + uploadFileName);
			
			System.out.println("board:" + board);

			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			Date nowdate = new Date();
			uploadFileName = formatter.format(nowdate) + "_" + uploadFileName;
			
			File saveFile = new File(dir, uploadFileName);
			
			try {
				multipartFile.transferTo(saveFile);
			} catch(Exception e) {
				e.getMessage();
			}

			ImageFile newImg = new ImageFile();
			newImg.setBrdId(board.getId());
			newImg.setMember(loginUser);
			newImg.setName(uploadFileName);
	
			res += mapper.insertFile(newImg);

		}
			
		// 3. 수정한 board의 제목 내용
		res = boardService.modifyGnrBoard(board);
		
		}
		return res;
		
	}


	@Override
	public ImageFile getFileByFileId(int id) {
		return mapper.getFileByFileId(id);
	}

	@Transactional
	@Override
	public int deleteFile(Member loginUser, Board board, File realPath) {
		List<ImageFile> imgList = selectFileByBrdId(board.getId());
		int res = 0;;
	
		File dir = new File(realPath, UPLOAD_PATH + "/" + loginUser.getId() + "/");
		File files[] = dir.listFiles();
		

		for(ImageFile f : imgList) {
			res += mapper.deleteFile(f);
			
			//dir 파일목록 -  받아온 파일 이름이 같을 시 삭제
			for(int i=0; i<files.length; i++) {
				File uploadedFile = files[i];
				
				String fileName = f.getName();
				if(fileName.equals(uploadedFile.getName())) {
					uploadedFile.delete();
				}	
			}
		}
		
		
		return res;
	}

	@Override
	public List<ImageFile> selectFileByMemId(String id) {
		return mapper.selectFileByMemId(id);
	}
}
