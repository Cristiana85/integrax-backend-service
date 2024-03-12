package com.integrax.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.LockModeType;

@Lock(LockModeType.OPTIMISTIC)
@Transactional(rollbackFor = Exception.class)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LockOptimisticTransactionalRollback {
	
}
