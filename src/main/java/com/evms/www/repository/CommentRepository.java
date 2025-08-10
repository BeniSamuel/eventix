package com.evms.www.repository;

import com.evms.www.model.Comment;
import com.evms.www.model.Event;
import com.evms.www.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> getCommentsByEvent (Event event);
    List<Comment> getCommentsByAuthor (User author);
}
