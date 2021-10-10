package mz.mapper;

import java.util.List;

import mz.dto.FileUpload;

public interface FileUploadMapper {
	int insertFile(FileUpload file);
	int nextBrdId();
	List<FileUpload> selectFileByBrdId(int brdId);
}
