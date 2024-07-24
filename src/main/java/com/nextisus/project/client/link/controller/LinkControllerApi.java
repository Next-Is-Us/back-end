package com.nextisus.project.client.link.controller;

import com.nextisus.project.util.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Tag(name = "링크 관련 API")
public interface LinkControllerApi {

    @Operation(summary = "[ALL] 링크 생성 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\n" +
                                                            "    \"timestamp\": \"2024-07-22T13:00:21.582846\",\n" +
                                                            "    \"isSuccess\": true,\n" +
                                                            "    \"code\": \"200\",\n" +
                                                            "    \"message\": \"호출에 성공하였습니다.\",\n" +
                                                            "    \"data\": \"264ba70a-36e9-42c9-b0a4-ab6c3fa0fa05\"\n" +
                                                            "}"),
                            schema = @Schema(implementation = SuccessResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\n" +
                                                            "    \"timestamp\": \"2024-07-22T13:00:21.582846\",\n" +
                                                            "    \"isSuccess\": false,\n" +
                                                            "    \"code\": \"400\",\n" +
                                                            "    \"message\": \"잘못된 요청입니다.\",\n" +
                                                            "    \"data\": null\n" +
                                                            "}"),
                            schema = @Schema(implementation = SuccessResponse.class)))
    })
    @PostMapping
    SuccessResponse<String> link();
}
