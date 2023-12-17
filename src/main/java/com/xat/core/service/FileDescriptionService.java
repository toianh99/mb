package com.xat.core.service;

import com.globits.core.domain.FileDescription;
import com.globits.core.dto.CommonSearchDto;
import com.globits.core.dto.FileDescriptionDto;
import java.util.UUID;
import org.springframework.data.domain.Page;

public interface FileDescriptionService extends GenericService<FileDescription, UUID> {
   Page<FileDescriptionDto> search(CommonSearchDto seachObject);
}
