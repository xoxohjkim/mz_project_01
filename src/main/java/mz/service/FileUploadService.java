package mz.service;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import mz.dto.Board;
import mz.dto.FileUpload;
import mz.dto.Member;

public interface FileUploadService {

	int insertFile(Member loginUser, Board board, MultipartFile[] uploadFile, File realPath);
	int nextBrdId();
	List<FileUpload> selectFileByBrdId(int brdId);
}