package com.yijianguanzhu.core.launch.holder;

import brave.baggage.BaggageField;
import com.yijianguanzhu.common.constant.TokenConstant;
import com.yijianguanzhu.common.result.UserResult;
import com.yijianguanzhu.common.utils.ProtostuffUtil;

import java.util.Base64;

/**
 * @author yijianguanzhu 2022年06月09日
 */
public class UserHolder {

	public static UserResult getUser() {
		try {
			BaggageField baggageField = BaggageField.getByName( TokenConstant.USER_INFO );
			return ProtostuffUtil.<UserResult>deserialize( Base64.getDecoder().decode( baggageField.getValue() ) );
		}
		catch ( Exception e ) {
			throw new RuntimeException( "用户未登录", e );
		}
	}
}
