package com.moovie.moovienetwork.Repository;

import com.moovie.moovienetwork.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
