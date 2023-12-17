package com.xat.core.service.impl;

import com.globits.core.domain.FileDescription;
import com.globits.core.dto.CommonSearchDto;
import com.globits.core.dto.FileDescriptionDto;
import com.globits.core.repository.FileDescriptionRepository;
import com.globits.core.service.FileDescriptionService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class FileDescriptionServiceImpl extends GenericServiceImpl<FileDescription, UUID> implements FileDescriptionService {
   @Autowired
   FileDescriptionRepository fileDescriptionRepository;

   public Page<FileDescriptionDto> search(CommonSearchDto searchObject) {
      Pageable pageable = PageRequest.of(searchObject.getPageIndex() - 1, searchObject.getPageSize());
      String keyword = "%" + searchObject.getKeyword() + "%";
      return this.fileDescriptionRepository.searchByPage(keyword, pageable);
   }
}
