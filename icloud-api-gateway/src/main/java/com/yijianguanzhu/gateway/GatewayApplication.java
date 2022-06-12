package com.yijianguanzhu.gateway;

import com.yijianguanzhu.core.launch.AppLauncher;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author yijianguanzhu 2022年06月08日
 */
@SpringCloudApplication
public class GatewayApplication {

	public static void main( String[] args ) {
		AppLauncher.run( GatewayApplication.class, args );
	}
}
