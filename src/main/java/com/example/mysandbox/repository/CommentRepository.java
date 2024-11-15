package com.example.mysandbox.repository;

import com.example.mysandbox.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByArticleId(Long articleId);

    @Query("SELECT c FROM Comment c WHERE c.article.id = :articleId AND c.parent IS NULL")
    List<Comment> findRootCommentsByArticleId(Long articleId);

    List<Comment> findByParentId(Long parentId);
}
