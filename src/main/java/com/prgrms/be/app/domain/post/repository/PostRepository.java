package com.prgrms.be.app.domain.post.repository;

import com.prgrms.be.app.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
