package com.yijianguanzhu.common.result;

import io.swagger.annotations.ApiModel;
import lombok.*;

import java.io.Serializable;

/**
 * @author yijianguanzhu 2022年06月08日
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "用户信息")
public class UserResult implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long userId;

	private String username;
}
