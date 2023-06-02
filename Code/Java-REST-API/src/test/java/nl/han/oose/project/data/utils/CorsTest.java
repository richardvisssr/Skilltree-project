package nl.han.oose.project.data.utils;

import static org.mockito.Mockito.*;

import jakarta.ws.rs.core.MultivaluedMap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class CorsTest {

    @Mock
    private ContainerRequestContext requestContext;

    @Mock
    private ContainerResponseContext responseContext;

    @Mock
    private MultivaluedMap<String, Object> headers;

    private Cors corsFilter = new Cors();

    @Before
    public void setup() {
        // Mock the behavior of responseContext.getHeaders() to return the mocked headers object
        when(responseContext.getHeaders()).thenReturn(headers);
    }

    @Test
    public void testFilter() throws IOException {
        corsFilter.filter(requestContext, responseContext);

        // Verify that the necessary headers are added
        verify(headers).add("Access-Control-Allow-Origin", "*");
        verify(headers).add("Access-Control-Allow-Credentials", "true");
        verify(headers).add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        verify(headers).add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }
}
