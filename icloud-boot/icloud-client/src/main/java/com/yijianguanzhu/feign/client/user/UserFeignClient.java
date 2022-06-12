package com.yijianguanzhu.feign.client.user;

import com.yijianguanzhu.common.base.BaseResponseEntity;
import com.yijianguanzhu.user.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 账号模块feign client
 *
 * @author yijianguanzhu 2022年06月09日
 */
@FeignClient(value = "user", fallback = UserFeignClientFallback.class)
public interface UserFeignClient {

	/**
	 * 获取账号信息
	 */
	@RequestMapping(value = "/account", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<BaseResponseEntity<User>> getAccount( @RequestParam("username") String username );
}
