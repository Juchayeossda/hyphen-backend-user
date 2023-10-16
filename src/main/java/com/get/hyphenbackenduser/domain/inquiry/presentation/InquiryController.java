package com.get.hyphenbackenduser.domain.inquiry.presentation;

import com.get.hyphenbackenduser.domain.inquiry.presentation.dto.request.MakeInquiryRequest;
import com.get.hyphenbackenduser.domain.inquiry.presentation.dto.response.GetInquiryResponse;
import com.get.hyphenbackenduser.domain.inquiry.presentation.dto.response.GetMyInquirysResponse;
import com.get.hyphenbackenduser.domain.inquiry.presentation.dto.response.MakeInquiryResponse;
import com.get.hyphenbackenduser.domain.inquiry.service.InquiryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

// InquiryController("/api/inquiry")
@Tag(name = "InquiryController", description = "문의 API")
@RestController
@RequestMapping("/api/inquiry")
@RequiredArgsConstructor
public class InquiryController {

    private final InquiryService inquiryService;

    // POST("")
    @Operation(method = "POST", summary = "문의 API", description = "서비스 문의 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "<b>[Success]</b> 스캔 성공",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MakeInquiryResponse.class),
                            examples = @ExampleObject(value = "{\"status\":\"문의 성공\"}")
                    )
            ),
            @ApiResponse(responseCode = "401", description = "<b>[Unauthorized]</b> 인가 기능이 확인되지 않은 접근", content = @Content),
            @ApiResponse(responseCode = "404", description = "<b>[NotFound]</b> 존재하지 않는 리소스 접근", content = @Content),
            @ApiResponse(responseCode = "500", description = "<b>[InternalError]</b> 서버 오류 발생", content = @Content)
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "문의 정보",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = MakeInquiryRequest.class),
                    examples = @ExampleObject(value = "{\"title\":\"test\",\"content\":\"test...\"}")
            )
    )
    @PostMapping("")
    public ResponseEntity<MakeInquiryResponse> makeInquiry(@Validated @RequestBody MakeInquiryRequest makeInquiryRequest) {
        return ResponseEntity.ok(inquiryService.makeInquiry(makeInquiryRequest));
    }

    // GET("/{id}")
    @Operation(method = "GET", summary = "문의 상세 조회 API", description = "서비스 문의 상세 정보 조회 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "<b>[Success]</b> 스캔 성공",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GetInquiryResponse.class),
                            examples = @ExampleObject(value = "{\"inquiry\":[" +
                                    "{\"createdDateTime\":\"2023-09-11 13:55:14\",\"modifiedDateTime\":\"2023-09-11 13:55:14\",\"id\":5,\"uid\":\"root_admin\",\"inquiryStatus\":\"NO_ANSWER\",\"title\":\"test\",\"content\":\"testtest\",\"answer\":null}" +
                                    "]}")
                    )
            ),
            @ApiResponse(responseCode = "401", description = "<b>[Unauthorized]</b> 인가 기능이 확인되지 않은 접근", content = @Content),
            @ApiResponse(responseCode = "404", description = "<b>[NotFound]</b> 존재하지 않는 리소스 접근", content = @Content),
            @ApiResponse(responseCode = "500", description = "<b>[InternalError]</b> 서버 오류 발생", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<GetInquiryResponse> getInquiry(@PathVariable("id") String id) {
        return ResponseEntity.ok(inquiryService.getInquiry(id));
    }

    // GET("")
    @Operation(method = "GET", summary = "내 문의 조회 API", description = "내 서비스 문의 조회 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "<b>[Success]</b> 스캔 성공",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GetMyInquirysResponse.class),
                            examples = @ExampleObject(value = "{\"inquiries\":[" +
                                    "{\"createdDateTime\":\"2023-09-10 16:44:42\",\"modifiedDateTime\":\"2023-09-10 16:44:42\",\"id\":2,\"uid\":\"root_admin\",\"inquiryStatus\":\"NO_ANSWER\",\"title\":\"test\",\"content\":\"testtest\",\"answer\":null}," +
                                    "{\"createdDateTime\":\"2023-09-10 16:47:10\",\"modifiedDateTime\":\"2023-09-10 16:47:10\",\"id\":3,\"uid\":\"root_admin\",\"inquiryStatus\":\"NO_ANSWER\",\"title\":\"test\",\"content\":\"testtest\",\"answer\":null}" +
                                    "]}")
                    )
            ),
            @ApiResponse(responseCode = "401", description = "<b>[Unauthorized]</b> 인가 기능이 확인되지 않은 접근", content = @Content),
            @ApiResponse(responseCode = "404", description = "<b>[NotFound]</b> 존재하지 않는 리소스 접근", content = @Content),
            @ApiResponse(responseCode = "500", description = "<b>[InternalError]</b> 서버 오류 발생", content = @Content)
    })
    @GetMapping("")
    public ResponseEntity<GetMyInquirysResponse> getMyInquirys() {
        return ResponseEntity.ok(inquiryService.getMyInquirys());
    }
}
