package com.yijianguanzhu.user.controller;

import com.yijianguanzhu.common.api.R;
import com.yijianguanzhu.common.base.BaseResponseEntity;
import com.yijianguanzhu.user.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author yijianguanzhu 2022年06月09日
 */
@RestController
@Slf4j
@Api(value = "账号接口", tags = "账号接口")
public class UserController {

	final String name = "admin";

	final String password = "admin";

	@ApiOperation( value = "获取账号信息")
	@GetMapping("/account")
	public ResponseEntity<BaseResponseEntity<User>> account(
			@RequestParam(value = "username", required = false) String username ) {
		if ( !Objects.equals( username, name ) ) {
			return R.fail( "无效账号" );
		}
		return R.data( User.builder()
				.username( name ).password( password )
				.id( 1000L ).build() );
	}

}
