package bank.aop;

import bank.exception.NotFoundException;
import bank.service.dto.responseDto.CustomErrorDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerExceptionAspect {
    @Around("execution (* bank.controller.*.* (..))")
    public Object checkForException(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (NotFoundException nex) {
            CustomErrorDto errorDto = new CustomErrorDto(nex.getMessage());
            return new ResponseEntity(errorDto, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            CustomErrorDto errorDto = new CustomErrorDto(ex.getMessage());
            return new ResponseEntity(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
