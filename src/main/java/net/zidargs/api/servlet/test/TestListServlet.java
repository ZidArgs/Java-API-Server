package net.zidargs.api.servlet.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import net.zidargs.api.resource.ServletResource;
import net.zidargs.api.util.SearchRequestData;
import net.zidargs.api.util.SearchResponseData;
import net.zidargs.api.util.test.TestRecordListBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@ServletResource
@Path("test")
public class TestListServlet {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sayHello(InputStream inputStream) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            SearchRequestData requestData = mapper.readValue(inputStream, SearchRequestData.class);

            // START - collect records
            List<TestEntityData> entityDataList = TestRecordListBuilder.buildTestRecordList();

            SearchResponseData<TestEntityData> responseData = new SearchResponseData<>(
                    true,
                    false,
                    requestData,
                    entityDataList.size(),
                    entityDataList
            );
            // END - collect records

            return Response.ok(mapper.writeValueAsString(responseData)).build();
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
            return Response.serverError().build();
        }
    }

}
