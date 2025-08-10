package com.evms.www.service;

import com.evms.www.dto.CommentDto;
import com.evms.www.model.Comment;
import com.evms.www.model.Event;
import com.evms.www.model.User;
import com.evms.www.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final EventService eventService;
    private final UserService userService;

    public List<Comment> getAllComments () {
        return commentRepository.findAll();
    }

    public Comment getCommentById (Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    public List<Comment> getCommentsByEvent (Long event_id) {
        Event event = eventService.getEventById(event_id);
        if (event != null) {
            return commentRepository.getCommentsByEvent(event);
        }
        return null;
    }

    public List<Comment> getCommentsByAuthor (Long author_id) {
        User author = userService.getUserById(author_id);
        if (author != null) {
            return commentRepository.getCommentsByAuthor(author);
        }
        return null;
    }

    public Comment createComment (CommentDto commentDto) {
        Event event = eventService.getEventById(commentDto.getEvent_id());
        User author = userService.getUserById(commentDto.getAuthor_id());

        if (event == null && author == null) {
            return null;
        }

        Comment newComment = new Comment(commentDto.getStatement(), event, author, commentDto.getCreation_date());
        return commentRepository.save(newComment);
    }

    public Boolean deleteComment (Long id) {
        Comment comment = getCommentById(id);
        if (comment != null) {
            commentRepository.delete(comment);
            return true;
        }
        return false;
    }
}
