package com.thoughtworks.fixed.assets.api.filter;

import org.glassfish.jersey.oauth1.signature.OAuth1Parameters;
import org.glassfish.jersey.oauth1.signature.OAuth1Secrets;
import org.glassfish.jersey.oauth1.signature.OAuth1Signature;
import org.glassfish.jersey.server.oauth1.internal.OAuthServerRequest;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import java.io.IOException;

public class SecurityFilter implements ContainerRequestFilter {

    @Inject
    private OAuth1Signature oAuth1Signature;
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String path = requestContext.getUriInfo().getPath();
        OAuthServerRequest oauthRequest = new OAuthServerRequest(requestContext);
        
        OAuth1Parameters params = new OAuth1Parameters();
        params.readRequest(oauthRequest);
        params.getConsumerKey();
        OAuth1Secrets secrets = new OAuth1Secrets().consumerSecret("secret");
        
        try {
            if (!oAuth1Signature.verify(oauthRequest, params, secrets)) {
                throw new WebApplicationException(401);
            }
        } catch(Exception e) {
            e.printStackTrace();
            throw new WebApplicationException(401);
        }
    }
}
