package co.edu.udec.iwrmdms.user_ms.adapters.driving.controller;

import co.edu.udec.iwrmdms.user_ms.adapters.driving.dto.request.UserRequestDto;
import co.edu.udec.iwrmdms.user_ms.adapters.driving.dto.response.ProfileResponseDto;
import co.edu.udec.iwrmdms.user_ms.adapters.driving.handlers.IUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static co.edu.udec.iwrmdms.user_ms.configuration.Constants.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserRestController {
    private final IUserHandler userHandler;

    @Operation(summary = "Add a new user",
            responses = {
                    @ApiResponse(responseCode = "201", description = "User created",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "User already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
                    @ApiResponse(responseCode = "403", description = "Role not allowed for user creation",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PostMapping("/user")
    public ResponseEntity<Map<String, String>> saveUser(@RequestBody UserRequestDto userRequestDto) {
        userHandler.saveUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(RESPONSE_MESSAGE_KEY, USER_CREATED_MESSAGE));
    }

    @Operation(summary = "Delete a user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User deleted",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "404", description = "User not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @DeleteMapping("/user")
    public ResponseEntity<Map<String, String>> deleteUser(@RequestBody UserRequestDto userRequestDto) {
        userHandler.deleteUser(userRequestDto);
        return ResponseEntity.ok(Collections.singletonMap(RESPONSE_MESSAGE_KEY, USER_DELETED_MESSAGE));
    }

    @Operation(summary = "Get all inspectors",
            responses = {
                    @ApiResponse(responseCode = "200", description = "All inspectors returned",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ProfileResponseDto.class))))})
    @GetMapping("/user/inspector")
    public ResponseEntity<List<ProfileResponseDto>> getAllInspectors(@Parameter(description = "Number of the page to list inspectors") @RequestParam int page) {
        return ResponseEntity.ok(userHandler.getInspectors(page));
    }

    @Operation(summary = "Get all assistants",
            responses = {
                    @ApiResponse(responseCode = "200", description = "All assistants returned",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ProfileResponseDto.class))))})
    @GetMapping("/user/assistant")
    public ResponseEntity<List<ProfileResponseDto>> getAllAssistants(@Parameter(description = "Number of the page to list assistants") @RequestParam int page) {
        return ResponseEntity.ok(userHandler.getAssistants(page));
    }

    @Operation(summary = "Get a inspector user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Inspector user returned",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProfileResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "User not found with inspector role",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @GetMapping("/user/inspector/{id}")
    public ResponseEntity<ProfileResponseDto> getInspector(@PathVariable Long id) {
        return ResponseEntity.ok(userHandler.getInspector(id));
    }

    @Operation(summary = "Get a assistant user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Assistant user returned",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProfileResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "User not found with assistant role",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @GetMapping("/user/assistant/{id}")
    public ResponseEntity<ProfileResponseDto> getAssistant(@PathVariable Long id) {
        return ResponseEntity.ok(userHandler.getAssistant(id));
    }

    @Operation(summary = "Get a administrator user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Administrator user returned",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProfileResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "User not found with administrator role",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @GetMapping("/user/administrator/{id}")
    public ResponseEntity<ProfileResponseDto> getAdministrator(@PathVariable Long id) {
        return ResponseEntity.ok(userHandler.getAdministrator(id));
    }
}
