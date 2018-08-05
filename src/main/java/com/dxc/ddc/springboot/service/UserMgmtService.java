package com.dxc.ddc.springboot.service;

import java.util.List;

import com.dxc.ddc.springboot.data.GeneralContentResult;
import com.dxc.ddc.springboot.data.GeneralPagingResult;
import com.dxc.ddc.springboot.data.GeneralResult;
import com.dxc.ddc.springboot.data.dto.UserInfo;

/**
 *
 * @author Huanfeng, cai
 * @since JDK 1.8
 */
public interface UserMgmtService {
    GeneralPagingResult<List<UserInfo>> findAllByPage(String keyword, Integer page, Integer size);

    GeneralContentResult<UserInfo> getById(String id);

    GeneralContentResult<String> saveOrUpdate(UserInfo dto);

    GeneralResult delete(String id);

}
