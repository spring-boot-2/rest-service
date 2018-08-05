package com.dxc.ddc.springboot.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.ddc.springboot.data.GeneralContentResult;
import com.dxc.ddc.springboot.data.GeneralPagingResult;
import com.dxc.ddc.springboot.data.GeneralResult;
import com.dxc.ddc.springboot.data.dto.UserCreate;
import com.dxc.ddc.springboot.data.dto.UserInfo;
import com.dxc.ddc.springboot.data.dto.UserUpdate;
import com.dxc.ddc.springboot.service.UserMgmtService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 *
 * @author Huanfeng, cai
 * @since JDK 1.8
 */
@RestController
@RequestMapping(value = "/users")
@Api(tags = {"用户管理"})
public class UserMgmtController {

    @Autowired
    private UserMgmtService metadataService;

    @ApiOperation(value = "用户列表", notes = "根据输入的关键字分页获取用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "size", value = "每页显示的件数", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "keyword", value = "关键字", required = false, dataType = "String") })
    @RequestMapping(value = "/all/page/{page}/size/{size}", method = RequestMethod.GET)
    @ResponseBody
    public GeneralPagingResult<List<UserInfo>> findAllByPage(@PathVariable("page") Integer page,
            @PathVariable("size") Integer size,
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
        return metadataService.findAllByPage(keyword, page, size);
    }

    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "dto", value = "用户信息", required = true, dataType = "UserCreate")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public GeneralContentResult<String> save(@Valid @RequestBody UserCreate dto) {
        return metadataService.saveOrUpdate(dto);
    }

    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public GeneralContentResult<UserInfo> getById(@PathVariable String id) {
        return metadataService.getById(id);
    }

    @ApiOperation(value = "更新用户详细信息", notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "dto", value = "用户信息", required = true, dataType = "UserUpdate") })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public GeneralContentResult<String> update(@PathVariable String id, @Valid @RequestBody UserUpdate dto) {
        dto.setId(id);
        return metadataService.saveOrUpdate(dto);
    }

    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public GeneralResult delete(@PathVariable String id) {
        return metadataService.delete(id);
    }
}
