package mz.mapper;

import java.util.List;

import mz.dto.ImageFile;

public interface ImageFileMapper {
	int insertFile(ImageFile file);
	int deleteFile(ImageFile file);
	
	int nextBrdId();
	List<ImageFile> selectFileByBrdId(int brdId);
	ImageFile getFileByFileId(int id);
	List<ImageFile> selectFileByMemId(String id);
}
