package com.get.hyphenbackenduser.domain.user.presentation;

import com.get.hyphenbackenduser.domain.user.presentation.dto.request.RenameRequest;
import com.get.hyphenbackenduser.domain.user.presentation.dto.request.UpdatePasswordRequest;
import com.get.hyphenbackenduser.domain.user.presentation.dto.response.*;
import com.get.hyphenbackenduser.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

// RestController("/api/user")
@Tag(name = "UserController", description = "회원 관리 API")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // GET("/image")
    @Operation(method = "GET", summary = "프로필 이미지 조회 API", description = "마이 프로필 이미지 조회 API")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "<b>[Success]</b> 스캔 성공",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GetProfileImageResponse.class),
                            examples = @ExampleObject(value = "{\"imageUrl\":\"http://101.101.217.155:8083/api/siss/extract/image/test.png\"}")
                    )
            ),
            @ApiResponse(responseCode = "401", description = "<b>[Unauthorized]</b> 인가 기능이 확인되지 않은 접근", content = @Content),
            @ApiResponse(responseCode = "404", description = "<b>[NotFound]</b> 존재하지 않는 리소스 접근", content = @Content),
            @ApiResponse(responseCode = "500", description = "<b>[InternalError]</b> 서버 오류 발생", content = @Content)
    })
    @GetMapping("/image")
    public ResponseEntity<GetProfileImageResponse> getProfileImage() throws IOException {
        return ResponseEntity.ok(userService.getImage());
    }

    // GET("")
    @Operation(method = "GET", summary = "내 정보 조회 API", description = "내 프로필 정보 조회 API")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "<b>[Success]</b> 스캔 성공",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MyInfoResponse.class),
                            examples = @ExampleObject(value = "{\"createdDateTime\":\"2023-09-11 13:55:14\",\"modifiedDateTime\":\"2023-09-11 13:55:14\",\"deletedDateTime\":null,\"uid\":\"hyun234\",\"name\":\"Hyun\",\"email\":\"hyun@email.com\",\"userStatus\":\"ACTIVE\",\"socialType\":null,\"socialId\":null,\"imagePath\":null}")
                    )
            ),
            @ApiResponse(responseCode = "401", description = "<b>[Unauthorized]</b> 인가 기능이 확인되지 않은 접근", content = @Content),
            @ApiResponse(responseCode = "404", description = "<b>[NotFound]</b> 존재하지 않는 리소스 접근", content = @Content),
            @ApiResponse(responseCode = "500", description = "<b>[InternalError]</b> 서버 오류 발생", content = @Content)
    })
    @GetMapping("")
    public ResponseEntity<MyInfoResponse> getMyInfo() {
        return ResponseEntity.ok(userService.getInfo());
    }

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
                            examples = @ExampleObject(value = "{\"status\":\"SUCCESS\",\"description\":\"success to image upload\"}")
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "<b>[BadReqeust]</b> 잘못된 요청",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ReimageResponse.class),
                            examples = @ExampleObject(value = "{\"status\":\"SUCCESS\",\"description\":\"image is not in a recognized format\"}")
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "<b>[NotFound]</b> 존재하지 않는 리소스 접근",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ReimageResponse.class),
                            examples = @ExampleObject(value = "{\"status\":\"FAILURE\",\"description\":\"can't found file in from\"}")
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "<b>[InternalError]</b> 서버 오류 발생",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ReimageResponse.class),
                            examples = @ExampleObject(value = "{\"status\":\"FAILURE\",\"description\":\"can't create image / no open file from backend\"}")
                    )
            )
    })
    @Parameter(
            name = "image",
            description = "변경할 프로필 이미지 파일",
            content = @Content(
                    mediaType = MediaType.IMAGE_PNG_VALUE,
                    schema = @Schema(implementation = MultipartFile.class)
                    )
    )
    @PatchMapping("/image")
    public ResponseEntity<ReimageResponse> reimage(@RequestParam("multipart-file-image") MultipartFile image) {
        return ResponseEntity.ok(userService.reimage(image));
    }

    // PATCH("/password")
    @Operation(method = "PATCH", summary = "패스워드 수정 API", description = "내 패스워드 수정 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "<b>[Success]</b> 스캔 성공",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UpdatePasswordResponse.class),
                            examples = @ExampleObject(value = "{\"status\":\"SUCCESS\"}")
                    )
            ),
            @ApiResponse(responseCode = "401", description = "<b>[Unauthorized]</b> 인가 기능이 확인되지 않은 접근", content = @Content),
            @ApiResponse(responseCode = "404", description = "<b>[NotFound]</b> 존재하지 않는 리소스 접근", content = @Content),
            @ApiResponse(responseCode = "500", description = "<b>[InternalError]</b> 서버 오류 발생", content = @Content)
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "패스워드 변경 정보",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = UpdatePasswordRequest.class),
                    examples = @ExampleObject(value = "{\"originPassword\":\"originpassword\",\"newPassword\":\"newpassword\"}")
            )
    )
    @PatchMapping("/password")
    public ResponseEntity<UpdatePasswordResponse> updatePassword(@Validated @RequestBody UpdatePasswordRequest updatePasswordRequest) {
        return ResponseEntity.ok(userService.updatePassword(updatePasswordRequest));
    }

    // DELETE("")
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
    @DeleteMapping("")
    public ResponseEntity<WithdrawalUserResponse> withdrawalUser() {
        return ResponseEntity.ok(userService.withdrawal());
    }

    // DELETE("image")
    @Operation(method = "DELETE", summary = "프로필 이미지 삭제 API", description = "마이 프로필 이미지 삭제 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "<b>[Success]</b> 스캔 성공",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DeleteProfileImageResponse.class),
                            examples = @ExampleObject(value = "{\"status\":\"SUCCESS\"}")
                    )
            ),
            @ApiResponse(responseCode = "401", description = "<b>[Unauthorized]</b> 인가 기능이 확인되지 않은 접근", content = @Content),
            @ApiResponse(responseCode = "404", description = "<b>[NotFound]</b> 존재하지 않는 리소스 접근", content = @Content),
            @ApiResponse(responseCode = "500", description = "<b>[InternalError]</b> 서버 오류 발생", content = @Content)
    })
    @DeleteMapping("/image")
    public ResponseEntity<DeleteProfileImageResponse> deleteProfileImage() {
        return ResponseEntity.ok(userService.deleteImage());
    }
}
