package com.dxc.ddc.springboot.service;

import java.util.List;

import org.javatuples.KeyValue;

import com.dxc.ddc.springboot.data.GeneralContentResult;
import com.dxc.ddc.springboot.data.dto.DictionaryInfo;

/**
 *
 * @author Huanfeng, cai
 * @since JDK 1.8
 */
public interface DictionaryService {
    GeneralContentResult<List<DictionaryInfo>> findAll();
    
    GeneralContentResult<List<KeyValue<String, String>>> findByField(String field);
}
