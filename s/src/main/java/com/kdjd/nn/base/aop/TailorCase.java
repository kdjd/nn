package com.kdjd.nn.base.aop;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.kdjd.nn.base.aop.annotation.Tailor;
import com.kdjd.nn.base.aop.annotation.Tailors;
import com.kdjd.nn.base.filter.SimpleTailorFilter;
import com.kdjd.nn.base.filter.TailorFilter;

@Aspect
@Component
public class TailorCase {
	static Log log = LogFactory.getLog(TailorCase.class);

	@Around("execution(* com.kdjd.nn.base.controller.*.*(..))")
	public Object doDaoCall(ProceedingJoinPoint pjp) throws Throwable {
		try {
			Object r = pjp.proceed();
			StaticPart part = pjp.getStaticPart();
			Signature signature = part.getSignature();
			MethodSignature methodSignature = (MethodSignature) signature;
			Method method = methodSignature.getMethod();
			SimpleTailorFilter[] filters = null;

			if (method.getAnnotation(Tailors.class) != null) {
				Tailor[] tailors = method.getAnnotation(Tailors.class).tailors();
				filters = new SimpleTailorFilter[tailors.length];
				int i = 0;
				for (Tailor tailor : tailors) {
					SimpleTailorFilter filter = new SimpleTailorFilter(tailor.target(), tailor.include(),
							tailor.properties());
					filters[i] = filter;
					i++;
				}
			} else if (method.getAnnotation(Tailor.class) != null) {
				Tailor tailor = method.getAnnotation(Tailor.class);
				SimpleTailorFilter filter = new SimpleTailorFilter(tailor.target(), tailor.include(),
						tailor.properties());
				filters = new SimpleTailorFilter[] { filter };
			} else {
				return r;
			}
			r = TailorFilter.tailor(r, method, filters);
			return r;
		} catch (Throwable e) {
			throw new Exception(e.getMessage(), e);
		}
	}

}
