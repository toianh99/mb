package com.xat.core.service;

import com.xat.core.domain.FileDescription;
import com.xat.core.dto.CommonSearchDto;
import com.xat.core.dto.FileDescriptionDto;
import java.util.UUID;
import org.springframework.data.domain.Page;

public interface FileDescriptionService extends GenericService<FileDescription, UUID> {
   Page<FileDescriptionDto> search(CommonSearchDto seachObject);
}
