package net.eldeen.batch;

import com.sun.jersey.api.core.ExtendedUriInfo;
import com.sun.jersey.api.core.ResourceContext;
import com.sun.jersey.api.model.AbstractResourceMethod;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Batch'em
 * User: heldeen
 * Date: 3/6/14
 */
@Path("/batch")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BatchResource {

  @POST
  public Response batch(@Context ResourceContext resourceContext, List<BatchRequest> requests) {
    List<BatchResponse> responses = new ArrayList<>();

    for (BatchRequest request : requests) {
      ExtendedUriInfo extendedUriInfo = resourceContext.matchUriInfo(URI.create(request.getUri()));
      AbstractResourceMethod matchedMethod = extendedUriInfo.getMatchedMethod();
      //TODO apply the request to the method and execute the to get the response
      Object[] inputs = new Object[] {};

      Object methodOutput = null;
      try {
        methodOutput = matchedMethod.getMethod().invoke(matchedMethod, inputs);
      } catch (IllegalAccessException | InvocationTargetException e) {
        e.printStackTrace();
      }

      BatchResponse response = new BatchResponse();
      if (methodOutput instanceof Response) {
        Response methodResponse = (Response) methodOutput;
        response.setEntity(methodResponse.getEntity());
        response.setStatus(methodResponse.getStatus());

        Map<String, String> headers = new HashMap<>();

        for (String headerName: methodResponse.getMetadata().keySet()) {
          List<Object> headerValues = methodResponse.getMetadata().get(headerName);
          for (Object headerValue: headerValues) {
            headers.put(headerName, String.valueOf(headerValue));
          }
        }
        response.setHeaders(headers);
        //TODO more stuff
      }
      else {
        response.setEntity(methodOutput);
        response.setStatus(200);
      }

      responses.add(response);
    }

    return Response.ok(responses).build();
  }
}
