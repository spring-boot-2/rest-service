package com.dxc.ddc.springboot.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.javatuples.KeyValue;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dxc.ddc.springboot.data.GeneralContentResult;
import com.dxc.ddc.springboot.data.ResultCode;
import com.dxc.ddc.springboot.data.domain.Dictionary;
import com.dxc.ddc.springboot.data.dto.DictionaryInfo;
import com.dxc.ddc.springboot.repositories.DictionaryRepository;
import com.dxc.ddc.springboot.service.DictionaryService;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Huanfeng, cai
 * @since JDK 1.8
 */
@Service
@Slf4j
public class DictionaryServiceImpl implements DictionaryService {
    @Autowired
    private DictionaryRepository dictionaryRepository;
    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public GeneralContentResult<List<DictionaryInfo>> findAll() {
        List<Dictionary> content = dictionaryRepository.findAll();
        List<DictionaryInfo> list = content.stream().map(entity -> convertToDto(entity)).collect(Collectors.toList());
        GeneralContentResult<List<DictionaryInfo>> result = new GeneralContentResult<List<DictionaryInfo>>();
        result.setResultCode(ResultCode.OPERATION_SUCCESS);
        result.setResultContent(list);
        return result;
    }
    
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public GeneralContentResult<List<KeyValue<String, String>>> findByField(String field) {
        List<Dictionary> content = dictionaryRepository.findByFieldOrderByCodeAsc(field);
        List<KeyValue<String, String>> list = content.stream().map(entity -> convertToKeyValue(entity)).collect(Collectors.toList());
        GeneralContentResult<List<KeyValue<String, String>>> result = new GeneralContentResult<List<KeyValue<String, String>>>();
        result.setResultCode(ResultCode.OPERATION_SUCCESS);
        result.setResultContent(list);
        return result;
    }
    
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public GeneralContentResult<List<KeyValue<String, String>>> findByAttributes() {
        List<Dictionary> content = dictionaryRepository.findByAttributesNotCodeQuery("A0001");
        List<KeyValue<String, String>> list = content.stream().map(entity -> convertToKeyValue(entity)).collect(Collectors.toList());
        GeneralContentResult<List<KeyValue<String, String>>> result = new GeneralContentResult<List<KeyValue<String, String>>>();
        result.setResultCode(ResultCode.OPERATION_SUCCESS);
        result.setResultContent(list);
        return result;
    }
    
    private KeyValue<String, String> convertToKeyValue(Dictionary entity) {
        if (entity == null) {
            log.warn("entity is null.");
            return null;
        }
        return KeyValue.with(entity.getCode(), entity.getValue());
    }
    
    private DictionaryInfo convertToDto(Dictionary entity) {
        if (entity == null) {
            log.warn("entity is null.");
            return null;
        }
        return modelMapper.map(entity, DictionaryInfo.class);
    }
    //
    // private Dictionary convertToEntity(DictionaryInfo dto) {
    // return modelMapper.map(dto, Dictionary.class);
    // }
}
