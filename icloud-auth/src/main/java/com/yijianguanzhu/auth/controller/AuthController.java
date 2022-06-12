package com.yijianguanzhu.auth.controller;

import com.yijianguanzhu.common.api.R;
import com.yijianguanzhu.common.base.BaseResponseEntity;
import com.yijianguanzhu.common.result.UserResult;
import com.yijianguanzhu.common.utils.JwtUtil;
import com.yijianguanzhu.core.launch.holder.UserHolder;
import com.yijianguanzhu.feign.client.user.UserFeignClient;
import com.yijianguanzhu.user.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * @author yijianguanzhu 2022年06月09日
 */
@RestController
@Validated
@Slf4j
@Api(value = "授权接口", tags = "授权接口")
public class AuthController {

	@Autowired
	private UserFeignClient userFeignClient;

	@ApiOperation(value = "登录")
	@PostMapping("/login")
	public ResponseEntity<BaseResponseEntity<String>> account(
			@RequestParam(value = "username", required = false) @NotBlank(message = "请输入账号") String username,
			@RequestParam(value = "password", required = false) @NotBlank(message = "请输入密码") String password ) {
		ResponseEntity<BaseResponseEntity<User>> account = userFeignClient.getAccount( username );
		if ( account.getStatusCode() != HttpStatus.OK ) {
			return R.fail( account.getBody().getMsg() );
		}
		User user = account.getBody().getData();
		if ( !Objects.equals( user.getPassword(), password ) ) {
			return R.fail( "密码错误" );
		}
		UserResult userResult = UserResult.builder()
				.userId( user.getId() ).username( user.getUsername() )
				.build();
		return R.data( JwtUtil.token( userResult ) );
	}

	@ApiOperation(value = "token检验")
	@GetMapping("/ping")
	public ResponseEntity<BaseResponseEntity<UserResult>> ping() {
		return R.data( UserHolder.getUser() );
	}
}
