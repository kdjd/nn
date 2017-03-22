package com.kdjd.nn.base.filter;

import java.lang.reflect.Method;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class TailorFilter {

	public static Object tailor(Object object, Method method, SimpleTailorFilter[] filters) {

		SerializerFeature[] serializerFeatures = { SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullBooleanAsFalse,
				SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullNumberAsZero,
				SerializerFeature.WriteNullStringAsEmpty

		};
		return JSON.toJSONString(object, filters, serializerFeatures);
	}

}
