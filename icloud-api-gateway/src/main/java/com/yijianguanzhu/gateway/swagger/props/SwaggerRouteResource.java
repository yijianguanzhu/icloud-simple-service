package com.yijianguanzhu.gateway.swagger.props;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yijianguanzhu 2022年06月09日
 */
@Getter
@Setter
public class SwaggerRouteResource {

	/**
	 * 文档名
	 */
	private String name;

	/**
	 * 文档所在服务地址
	 */
	private String url;

	/**
	 * 文档版本
	 */
	private String version = "1.0.0";
}
