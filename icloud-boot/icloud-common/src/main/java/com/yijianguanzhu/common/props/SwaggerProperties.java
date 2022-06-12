package com.yijianguanzhu.common.props;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author yijianguanzhu 2022年06月09日
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "icloud.swagger")
public class SwaggerProperties {

	/**
	 * 是否开启Swagger
	 */
	private boolean enabled;
	/**
	 * 标题
	 **/
	private String title = "spring cloud示例项目";
	/**
	 * 描述
	 **/
	private String description = "spring cloud示例项目";
	/**
	 * 版本
	 **/
	private String version = "1.0.0";
	/**
	 * 联系人信息
	 */
	private Contact contact = new Contact();

	@Data
	@NoArgsConstructor
	public static class Contact {

		/**
		 * 联系人
		 **/
		private String name = "yijianguanzhu";
		/**
		 * 联系人url
		 **/
		private String url = "";
		/**
		 * 联系人email
		 **/
		private String email = "yijianguanzhu@hotmail.com";

	}
}
