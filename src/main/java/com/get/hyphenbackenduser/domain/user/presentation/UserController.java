package com.get.hyphenbackenduser.domain.user.presentation;

import com.get.hyphenbackenduser.domain.user.presentation.dto.request.RenameRequest;
import com.get.hyphenbackenduser.domain.user.presentation.dto.response.MyInfoResponse;
import com.get.hyphenbackenduser.domain.user.presentation.dto.response.ReimageResponse;
import com.get.hyphenbackenduser.domain.user.presentation.dto.response.RenamedResponse;
import com.get.hyphenbackenduser.domain.user.presentation.dto.response.WithdrawalUserResponse;
import com.get.hyphenbackenduser.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

// RestController("/api/user")
@Tag(name = "UserController", description = "회원 관리 API")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // TODO: Read
    // GET("/image")
    @Operation(method = "GET", summary = "프로필 이미지 조회 API", description = "마이 프로필 이미지 조회 API")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "<b>[Success]</b> 스캔 성공",
                    headers = @Header(name = HttpHeaders.CONTENT_DISPOSITION, description = "File name"),
                    content = @Content(
                            mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE,
                            schema = @Schema(implementation = Resource.class),
                            examples = @ExampleObject(value = "resource")
                    )
            ),
            @ApiResponse(responseCode = "401", description = "<b>[Unauthorized]</b> 인가 기능이 확인되지 않은 접근", content = @Content),
            @ApiResponse(responseCode = "404", description = "<b>[NotFound]</b> 존재하지 않는 리소스 접근", content = @Content),
            @ApiResponse(responseCode = "500", description = "<b>[InternalError]</b> 서버 오류 발생", content = @Content)
    })
    @GetMapping("/image")
    public ResponseEntity<Resource> getProfileImage(HttpServletRequest httpServletRequest) throws IOException {
        return userService.getImage(httpServletRequest);
    }

    // GET("/info")
    @Operation(method = "GET", summary = "내 정보 조회 API", description = "내 프로필 정보 조회 API")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "<b>[Success]</b> 스캔 성공",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MyInfoResponse.class),
                            examples = @ExampleObject(value = "{\"uid\":\"hyun234\",\"name\":\"Hyun\",\"email\":\"hyun@email.com\",\"userStatus\":\"ACTIVE\",\"socialType\":null,\"socialId\":null}")
                    )
            ),
            @ApiResponse(responseCode = "401", description = "<b>[Unauthorized]</b> 인가 기능이 확인되지 않은 접근", content = @Content),
            @ApiResponse(responseCode = "404", description = "<b>[NotFound]</b> 존재하지 않는 리소스 접근", content = @Content),
            @ApiResponse(responseCode = "500", description = "<b>[InternalError]</b> 서버 오류 발생", content = @Content)
    })
    @GetMapping("/info")
    public ResponseEntity<MyInfoResponse> getMyInfo() {
        return ResponseEntity.ok(userService.getInfo());
    }

    // TODO: Update
    // PATCH("/name")
    @Operation(method = "PATCH", summary = "이름 수정 API", description = "회원 이름 수정 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "<b>[Success]</b> 스캔 성공",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RenamedResponse.class),
                            examples = @ExampleObject(value = "{\"uid\":\"hyun234\",\"originName\":\"Jahyun\",\"newName\":\"Hyun\"}")
                    )
            ),
            @ApiResponse(responseCode = "401", description = "<b>[Unauthorized]</b> 인가 기능이 확인되지 않은 접근", content = @Content),
            @ApiResponse(responseCode = "404", description = "<b>[NotFound]</b> 존재하지 않는 리소스 접근", content = @Content),
            @ApiResponse(responseCode = "500", description = "<b>[InternalError]</b> 서버 오류 발생", content = @Content)
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "변경할 유저 닉네임",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = RenameRequest.class),
                    examples = @ExampleObject(value = "{\"newName\":\"Hyun\"}")
            )
    )
    @PatchMapping("/name")
    public ResponseEntity<RenamedResponse> rename(@Validated @RequestBody RenameRequest renameRequest) {
        return ResponseEntity.ok(userService.rename(renameRequest.getNewName()));
    }

    // PATCH("/image")
    @Operation(method = "PATCH", summary = "프로필 이미지 수정 API", description = "마이 프로필 이미지 수정 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "<b>[Success]</b> 스캔 성공",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ReimageResponse.class),
                            examples = @ExampleObject(value = "{\"uid\":\"hyun234\",\"imageId\":1,\"imageName\":\"hyphen-logo.png\",\"imagePath\":\"/Users/kujahyun/Documents/GitHub/hyphen-backend-user/src/main/java/com/get/hyphenbackenduser/domain/user/profileImage/hyun234/hyphen-logo.png\"}")
                    )
            ),
            @ApiResponse(responseCode = "401", description = "<b>[Unauthorized]</b> 인가 기능이 확인되지 않은 접근", content = @Content),
            @ApiResponse(responseCode = "404", description = "<b>[NotFound]</b> 존재하지 않는 리소스 접근", content = @Content),
            @ApiResponse(responseCode = "500", description = "<b>[InternalError]</b> 서버 오류 발생", content = @Content)
    })
    @Parameter(
            name = "imageFile",
            description = "변경할 프로필 이미지 파일",
            content = @Content(
                    mediaType = MediaType.IMAGE_PNG_VALUE,
                    schema = @Schema(implementation = MultipartFile.class)
            )
    )
    @PatchMapping("/image")
    public ResponseEntity<ReimageResponse> reimage(@RequestParam("imageFile") MultipartFile imageFile) {
        return ResponseEntity.ok(userService.reimage(imageFile)); // TODO: SISS를 사용하는 코드로 리팩토링
    }

    // TODO: Delete
    // DELETE("/drop")
    @Operation(method = "DELETE", summary = "탈퇴 API", description = "회원 탈퇴 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "<b>[Success]</b> 스캔 성공",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = WithdrawalUserResponse.class),
                            examples = @ExampleObject(value = "{\"uid\":\"hyun234\",\"userStatus\":\"BANNED\",\"userRole\":\"MEMBER\"}")
                    )
            ),
            @ApiResponse(responseCode = "401", description = "<b>[Unauthorized]</b> 인가 기능이 확인되지 않은 접근", content = @Content),
            @ApiResponse(responseCode = "404", description = "<b>[NotFound]</b> 존재하지 않는 리소스 접근", content = @Content),
            @ApiResponse(responseCode = "500", description = "<b>[InternalError]</b> 서버 오류 발생", content = @Content)
    })
    @DeleteMapping("/drop")
    public ResponseEntity<WithdrawalUserResponse> withdrawalUser() {
        return ResponseEntity.ok(userService.withdrawal());
    }
}
