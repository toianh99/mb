package com.xat.core.service.impl;

import com.globits.core.domain.PersonAddress;
import com.globits.core.service.PersonAddressService;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class PersonAddressServiceImpl extends GenericServiceImpl<PersonAddress, UUID> implements PersonAddressService {
}
