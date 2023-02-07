package com.prgrms.be.app.domain.post.controller;

import com.prgrms.be.app.common.dto.ApiResponse;
import com.prgrms.be.app.common.dto.PageRequest;
import com.prgrms.be.app.common.dto.ResponseMessage;
import com.prgrms.be.app.domain.post.dto.PostCreateRequest;
import com.prgrms.be.app.domain.post.dto.PostDetailResponse;
import com.prgrms.be.app.domain.post.dto.PostUpdateRequest;
import com.prgrms.be.app.domain.post.dto.PostsResponse;
import com.prgrms.be.app.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ApiResponse<Long> save(@RequestBody @Valid PostCreateRequest postCreateRequest) {
        Long postId = postService.createPost(postCreateRequest);
        return ApiResponse.created(
                postId,
                ResponseMessage.CREATED);
    }

    @GetMapping("/{id}")
    public ApiResponse<PostDetailResponse> getOne(@PathVariable Long id) {
        PostDetailResponse postDetailResponse = postService.findById(id);
        return ApiResponse.ok(
                postDetailResponse,
                ResponseMessage.FINDED_ONE);
    }

    @GetMapping
    public ApiResponse<PostsResponse> getAll(@RequestBody PageRequest pageRequest) {
        PostsResponse postPages = postService.findAll(pageRequest.of());
        return ApiResponse.ok(
                postPages,
                ResponseMessage.FINDED_ALL
        );
    }

    @PatchMapping("/{id}")
    public ApiResponse<Long> update(
            @PathVariable Long id,
            @RequestBody @Valid PostUpdateRequest request) {
        Long postId = postService.updatePost(id, request);
        return ApiResponse.ok(
                postId,
                ResponseMessage.UPDATED);
    }
}
