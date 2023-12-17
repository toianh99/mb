package com.xat.core.service.impl;

import com.xat.core.domain.PersonAddress;
import com.xat.core.service.PersonAddressService;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class PersonAddressServiceImpl extends GenericServiceImpl<PersonAddress, UUID> implements PersonAddressService {
}
