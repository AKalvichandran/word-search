package com.assignment.resources;

import com.assignment.service.WordSearchService;
import io.swagger.annotations.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/word/search")
@Api(basePath = "/word/search",
        value = "Find the List of the files which matches the search word",
        description = "Get the List of File which contains search word"
)
public class WordSearchResource {

    private final List<String> fileList;
    private final WordSearchService wordSearchService;

    public WordSearchResource(final WordSearchService wordSearchService, final List<String> fileList) {
        this.fileList = fileList;
        this.wordSearchService = wordSearchService;
    }

    @ApiOperation(
            value = "Get the List of File which contains search word"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "The succesful response for the given search word"),
            @ApiResponse(code = 500, message = "An error occurred during the request")
    })
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHelloWorld(final @ApiParam(required = true, example = "Java") @QueryParam("words") String words) {
        List<String> files = wordSearchService.findFileList(fileList, words);
        return Response.ok(files).build();
    }
}
