package com.dxc.ddc.springboot.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dxc.ddc.springboot.data.GeneralContentResult;
import com.dxc.ddc.springboot.data.GeneralPagingResult;
import com.dxc.ddc.springboot.data.GeneralResult;
import com.dxc.ddc.springboot.data.PageableResult;
import com.dxc.ddc.springboot.data.ResultCode;
import com.dxc.ddc.springboot.data.domain.User;
import com.dxc.ddc.springboot.data.dto.UserInfo;
import com.dxc.ddc.springboot.repositories.DictionaryRepository;
import com.dxc.ddc.springboot.repositories.UserRepository;
import com.dxc.ddc.springboot.service.UserMgmtService;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Huanfeng, cai
 * @since JDK 1.8
 */
@Service
@Slf4j
public class UserMgmtServiceImpl implements UserMgmtService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DictionaryRepository dictionaryRepository;
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public GeneralPagingResult<List<UserInfo>> findAllByPage(String keyword, Integer page, Integer size) {
        GeneralPagingResult<List<UserInfo>> result = new GeneralPagingResult<>();
        Pageable pageable = PageableResult.createPageRequest(page, size, Sort.Direction.ASC, "loginId", "displayName");
        Page<User> pageDomains = userRepository
                .findByLoginIdIgnoreCaseContainingOrDisplayNameIgnoreCaseContaining(keyword, keyword, pageable);
        List<User> content = pageDomains.getContent();

        List<UserInfo> list = content.stream().map(entity -> convertToDto(entity)).collect(Collectors.toList());
        PageableResult<List<UserInfo>> pageableResult = new PageableResult<>(pageable.getPageNumber(),
                pageable.getPageSize(), pageDomains.getTotalElements(), list);
        result.setResultCode(ResultCode.OPERATION_SUCCESS);
        result.setResultContent(pageableResult.getContent());
        result.setPageInfo(pageableResult.getPageInfo());
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public GeneralContentResult<UserInfo> getById(String id) {
        UserInfo dto = userRepository.findById(id).map(e->convertToDto(e)).orElse(null);
        GeneralContentResult<UserInfo> result = new GeneralContentResult<UserInfo>();
        result.setResultCode(ResultCode.OPERATION_SUCCESS);
        result.setResultContent(dto);
        return result;
    }

    @Override
    @Modifying
    @Transactional(propagation = Propagation.REQUIRED)
    public GeneralContentResult<String> saveOrUpdate(UserInfo dto) {
        User entity = convertToEntity(dto);
    	entity.setLoginPwd(passwordEncoder.encode(entity.getLoginPwd()));
        entity = userRepository.saveAndFlush(entity);
        GeneralContentResult<String> result = new GeneralContentResult<String>();
        result.setResultCode(ResultCode.OPERATION_SUCCESS);
        result.setResultContent(entity.getId());
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public GeneralResult delete(String id) {
        userRepository.deleteById(id);
        GeneralResult result = new GeneralResult();
        result.setResultCode(ResultCode.OPERATION_SUCCESS);
        return result;
    }

    private UserInfo convertToDto(User entity) {
        if (entity == null) {
            log.warn("entity is null.");
            return null;
        }

        UserInfo dto = modelMapper.map(entity, UserInfo.class);
        dto.setStatusName(Optional.ofNullable(dto.getStatus())
                .map(code -> dictionaryRepository.findByCode(String.valueOf(code))).map(dic -> dic.getValue()).orElse(""));
        return dto;
    }

    private User convertToEntity(UserInfo dto) {
        return modelMapper.map(dto, User.class);
    }
}
