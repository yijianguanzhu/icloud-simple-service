package com.yijianguanzhu.user.model;

import lombok.*;

/**
 * @author yijianguanzhu 2022年06月09日
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class User {

	private Long id;

	private String username;

	private String password;
}
