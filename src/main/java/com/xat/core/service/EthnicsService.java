package com.xat.core.service;

import com.globits.core.domain.Ethnics;
import com.globits.core.dto.EthnicsDto;
import java.util.List;
import java.util.UUID;

public interface EthnicsService extends GenericService<Ethnics, UUID> {
   int deleteEthnicss(List<EthnicsDto> dtos);

   EthnicsDto checkDuplicateCode(String code);

   Ethnics findByCode(String code);

   EthnicsDto saveEthnics(EthnicsDto ethnics);

   EthnicsDto findEthnicsById(UUID id);
}
