package com.xat.core.service;

import com.globits.core.domain.GlobalProperty;
import com.globits.core.dto.GlobalPropertyDto;
import org.springframework.data.domain.Page;

public interface GlobalPropertyService {
   Page<GlobalProperty> findByPage(int pageIndex, int pageSize);

   GlobalProperty save(GlobalProperty globalProperty) throws RuntimeException;

   GlobalProperty updateGlobalProperty(GlobalProperty user) throws RuntimeException;

   GlobalProperty findByProperty(String property);

   GlobalProperty delete(String property) throws RuntimeException;

   GlobalPropertyDto findDtoByProperty(String property);
}
