package com.yijianguanzhu.gateway.controller;

import com.yijianguanzhu.common.api.R;
import com.yijianguanzhu.common.base.BaseResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yijianguanzhu 2022年06月09日
 */
@RestController
public class FallBackController {

	@RequestMapping(value = "/fallback")
	public ResponseEntity<BaseResponseEntity<String>> fallback() {
		return R.fail( HttpStatus.SERVICE_UNAVAILABLE, "服务器繁忙，请稍后再试" );
	}
}
