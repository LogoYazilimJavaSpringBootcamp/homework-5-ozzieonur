package com.example.moovienetwork.Repository;

import com.example.moovienetwork.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
