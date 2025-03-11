package com.malkoc.costumerservice.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String,String> errors
) {
}
