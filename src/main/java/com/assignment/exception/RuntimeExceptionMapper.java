package com.assignment.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

/**
 * Created by kalvichandrana on 08/02/2018.
 */
public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RuntimeExceptionMapper.class);

    /**
     * All the exception will be handled here based on exception thrown by application
     *
     * @param exception
     * @return
     */
    @Override
    public Response toResponse(RuntimeException exception) {
        LOGGER.error("Unexpected runtime exception", exception);
        return Response.status(INTERNAL_SERVER_ERROR).build();
    }
}
