
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class OAuthTest {
    @Test
    public void should_(){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/admin").path("users");

        Response response = target.request().get();

//        OAuthParameters params = new OAuthParameters().signature("HAMC-SHA1").consumerKey("key");
//        OAuthSecrets secrets = new OAuthSecrets().consumerSecret("secret");
//        Client client = Client.create();
//        OAuthClientFilter filter = new OAuthClientFilter(client.getProviders(), params, secrets);
//        WebResource resource = client.resource("http://localhost:4080/simple/products");
    }
}
