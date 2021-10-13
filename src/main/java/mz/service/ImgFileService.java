package mz.service;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import mz.dto.Board;
import mz.dto.ImageFile;
import mz.dto.Member;

@Service
public interface ImgFileService {
	
	int insertFile(Member loginUser, Board board, MultipartFile[] uploadFile, File realPath);
	int modifyFile(Member loginUser, String[] delImgId, Board board, MultipartFile[] uploadFile, File realPath);
	int deleteFile(Member member, Board board, File realPath);
	int nextBrdId();
	
	ImageFile getFileByFileId(int id);
	List<ImageFile> selectFileByBrdId(int brdId);
	List<ImageFile> selectFileByMemId(String id);
	

	
	
	
	
	

}
