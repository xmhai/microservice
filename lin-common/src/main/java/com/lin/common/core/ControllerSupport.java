package com.lin.common.core;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ControllerSupport {
    protected Logger logger;

    @PostConstruct
    protected void init() {
        logger = LoggerFactory.getLogger(getClass());
    }
}
