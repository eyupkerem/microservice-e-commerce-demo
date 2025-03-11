package com.malkoc.costumerservice.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CostumerNotFoundException extends RuntimeException {
    private final String msg;

}
