package com.yijianguanzhu.feign.client.user;

import com.yijianguanzhu.common.api.R;
import com.yijianguanzhu.common.base.BaseResponseEntity;
import com.yijianguanzhu.user.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author yijianguanzhu 2022年06月09日
 */
@Component
public class UserFeignClientFallback implements UserFeignClient {

	@Override
	public ResponseEntity<BaseResponseEntity<User>> getAccount( String username ) {
		return R.fail( "无效账号" );
	}
}
