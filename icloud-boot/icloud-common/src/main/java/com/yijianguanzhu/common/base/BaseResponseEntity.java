package com.yijianguanzhu.common.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author yijianguanzhu 2022年06月09日
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "基础响应信息")
public class BaseResponseEntity<T> {

	@ApiModelProperty(value = "状态码", dataType = "Integer")
	private Integer code;

	@ApiModelProperty(value = "返回消息", dataType = "String")
	private String msg;

	@ApiModelProperty(value = "承载数据", dataType = "Object")
	private T data;
}
