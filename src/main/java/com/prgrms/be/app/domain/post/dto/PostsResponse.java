package com.prgrms.be.app.domain.post.dto;

import java.util.List;

public record PostsResponse(
        List<PostDetailResponse> posts,
        int totalPages,
        boolean hasNext) {
}


