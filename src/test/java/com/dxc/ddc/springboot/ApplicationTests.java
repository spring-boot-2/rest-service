package com.dxc.ddc.springboot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.dxc.ddc.springboot.data.GeneralContentResult;
import com.dxc.ddc.springboot.data.ResultCode;
import com.dxc.ddc.springboot.data.dto.UserCreate;
import com.dxc.ddc.springboot.data.dto.UserInfo;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApplicationTests {
	
	@Autowired
	private WebTestClient webClient;

	@Test
	public void contextLoads() {
	}
	
	@Before
	public void before() {
		log.debug("##### Before");
	}
	
	@After
	public void after() {
		log.debug("##### After");
	}
	
	@BeforeClass
	public static void beforeClass() {
		log.debug("##### Before Class");
	}
	
	@AfterClass
	public static void afterClass() {
		log.debug("##### After Class");
	}
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	public void userMgmtTest() {
		log.debug("##### userMgmtTest start");
		UserCreate dto = new UserCreate();
		dto.setCreatorId("adminid");
		dto.setLoginId("admin");
		dto.setLoginPwd("123456");
		dto.setStatus((byte) 1);
		dto.setUpdateId("adminid");
		dto.setDescription("描述123");
		dto.setDisplayName("系统管理员");
		log.debug("##### userMgmtTest start request");
		@SuppressWarnings("unchecked")
		GeneralContentResult<String> postResult = this.webClient.post().uri("/users")
				.contentType(MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON_UTF8)
				.body(Mono.just(dto), UserCreate.class).exchange().expectStatus().isOk().expectHeader()
				.contentType(MediaType.APPLICATION_JSON_UTF8).expectBody(GeneralContentResult.class).returnResult()
				.getResponseBody();
		
		assertEquals(postResult.getResultCode(), ResultCode.OPERATION_SUCCESS);
		
		@SuppressWarnings("unchecked")
		GeneralContentResult<UserInfo> getResult = this.webClient.get()
				.uri("/users/{id}", postResult.getResultContent()).accept(MediaType.APPLICATION_JSON_UTF8).exchange()
				.expectStatus().isOk().expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
				.expectBody(GeneralContentResult.class).returnResult().getResponseBody();
		
		assertEquals(getResult.getResultCode(), ResultCode.OPERATION_SUCCESS);
		
		// expectBody无法指定内部类型，获取到内容为Map类型
		@SuppressWarnings("rawtypes")
		Map resultContent = (Map) getResult.getResultContent();
		UserInfo info = modelMapper.map(resultContent, UserInfo.class);
		log.debug("##### resultContent={}", resultContent);
		log.debug("##### info={}", info);
		
		// Map类型通过modelMapper.map转化之后,LocalDateTime类型的字段值会被丢掉
		info.setCreateDate(this.parseLocalDateTime((String) resultContent.get("createDate")));
		info.setUpdateDate(this.parseLocalDateTime((String) resultContent.get("updateDate")));
		log.debug("##### info fix={}", info);
		
		assertEquals(dto.getCreatorId(), info.getCreatorId());
		assertEquals(dto.getLoginId(), info.getLoginId());
		assertEquals(dto.getStatus(), info.getStatus());
		assertEquals(dto.getStatus() == 0 ? "无效" : "有效", info.getStatusName());
		assertEquals(dto.getUpdateId(), info.getUpdateId());
		assertEquals(dto.getDescription(), info.getDescription());
		assertEquals(dto.getDisplayName(), info.getDisplayName());
		assertTrue(passwordEncoder.matches(dto.getLoginPwd(), info.getLoginPwd()));
		Assert.assertNotNull(info.getCreateDate());
		Assert.assertNotNull(info.getUpdateDate());
	}

	
	private LocalDateTime parseLocalDateTime(String value) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
		return LocalDateTime.parse(value, formatter);
	}
	
}
