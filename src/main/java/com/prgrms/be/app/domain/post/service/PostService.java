package com.prgrms.be.app.domain.post.service;

import com.prgrms.be.app.domain.post.Post;
import com.prgrms.be.app.domain.post.dto.PostCreateRequest;
import com.prgrms.be.app.domain.post.dto.PostDetailResponse;
import com.prgrms.be.app.domain.post.dto.PostUpdateRequest;
import com.prgrms.be.app.domain.post.dto.PostsResponse;
import com.prgrms.be.app.domain.post.repository.PostRepository;
import com.prgrms.be.app.domain.user.User;
import com.prgrms.be.app.domain.user.repository.UserRepository;
import com.prgrms.be.app.util.PostConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostConverter postConverter;

    public PostsResponse findAll(Pageable pageable) {
        Page<Post> all = postRepository.findAll(pageable);
        return postConverter.convertToPostsResponse(all);
    }

    public PostDetailResponse findById(Long id) {
        return postRepository.findById(id)
                .map(postConverter::convertToPostDetailResponse)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 게시글입니다."));
    }

    @Transactional
    public Long createPost(PostCreateRequest postCreateDto) {
        User user = userRepository.findById(postCreateDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 유저입니다."));
        Post post = postConverter.convertToPost(postCreateDto, user);

        return postRepository.save(post).getId();
    }

    @Transactional
    public Long updatePost(Long postId, PostUpdateRequest postUpdateDto) {
        Post post = postRepository.findById(postId) // 이렇게 하는게 나을까 findById 반환 데이터 타입을 Post로 바꾸고 해당 메서드를 이용하는게 나을까?
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 게시글입니다."));
        post.changePost(postUpdateDto.getTitle(), postUpdateDto.getContent());
        return post.getId();
    }
}
