package com.thoughtworks.fixed.assets.api.filter;

import org.apache.ibatis.session.SqlSessionManager;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import java.io.IOException;

public class OpenSessionInViewRequestFilter implements ContainerRequestFilter{
    @Inject
    private SqlSessionManager sqlSessionManager;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (!sqlSessionManager.isManagedSessionStarted()) {
            sqlSessionManager.startManagedSession();
        }
    }
}
