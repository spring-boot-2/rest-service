package com.dxc.ddc.springboot.web;

import java.util.List;

import org.javatuples.KeyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.ddc.springboot.data.GeneralContentResult;
import com.dxc.ddc.springboot.data.dto.DictionaryInfo;
import com.dxc.ddc.springboot.service.DictionaryService;

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
@RequestMapping(value = "/dictionary")
@Api(tags = {"字典管理"})
public class DictionaryController {
    @Autowired
    private DictionaryService dictionaryService;

    @ApiOperation(value = "字典列表", notes = "字典列表")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public GeneralContentResult<List<DictionaryInfo>> findAll() {
        return dictionaryService.findAll();
    }

    @ApiOperation(value = "字典列表(具体字段)", notes = "根据输入的字段查询字典列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "field", value = "关键字", required = true, dataType = "String", paramType = "path") })
    @RequestMapping(value = "/field/{field}", method = RequestMethod.GET)
    @ResponseBody
    public GeneralContentResult<List<KeyValue<String, String>>> findByField(@PathVariable("field") String field) {
        return dictionaryService.findByField(field);
    }
    
    @ApiOperation(value = "字典列表(属性(不包含类别))")
    @RequestMapping(value = "/attributes", method = RequestMethod.GET)
    @ResponseBody
    public GeneralContentResult<List<KeyValue<String, String>>> findByAttributes() {
        return dictionaryService.findByAttributes();
    }
}
