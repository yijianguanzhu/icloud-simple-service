package com.yijianguanzhu.core.launch.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * sentinel 统一限流策略
 * 
 * @author yijianguanzhu 2023年01月02日
 * @see com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.DefaultBlockExceptionHandler
 */
public class ICloudBlockExceptionHandler implements BlockExceptionHandler {

	@Override
	public void handle( HttpServletRequest request, HttpServletResponse response, BlockException e ) throws Exception {
		response.setStatus( HttpStatus.OK.value() );
		response.setContentType( MediaType.APPLICATION_JSON_VALUE );
		response.getWriter().print( "{\"code\":-1, \"msg\":\"当前访问人数过多，请稍后再试\"}" );
	}
}
