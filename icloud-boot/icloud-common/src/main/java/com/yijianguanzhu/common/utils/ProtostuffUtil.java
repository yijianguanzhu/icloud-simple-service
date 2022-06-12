package com.yijianguanzhu.common.utils;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * protobuf 序列化&反序列化工具类
 *
 * @author yijianguanzhu 2022年06月09日
 */
public class ProtostuffUtil {

	final static Schema<CacheValueWrapper> SCHEMA = RuntimeSchema.getSchema( CacheValueWrapper.class );

	/**
	 * 序列化指定对象
	 *
	 * @param obj 要序列化的对象
	 * @return byte[] 序列化结果
	 */
	public static byte[] serialize( Object obj ) {
		final LinkedBuffer buffer = LinkedBuffer.allocate();
		return ProtostuffIOUtil.toByteArray( new CacheValueWrapper( obj ), SCHEMA, buffer );
	}

	/**
	 * byte[]反序列化为指定类型
	 *
	 * @param byt byte[]
	 * @param <T> 泛型
	 * @return 返回指定类型的对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T deserialize( byte[] byt ) {
		CacheValueWrapper wrapper = new CacheValueWrapper();
		ProtostuffIOUtil.mergeFrom( byt, wrapper, SCHEMA );
		return ( T ) wrapper.getData();
	}

	/**
	 * 包装类
	 */
	@AllArgsConstructor
	@NoArgsConstructor
	public static class CacheValueWrapper {

		@Getter
		private Object data;
	}
}
