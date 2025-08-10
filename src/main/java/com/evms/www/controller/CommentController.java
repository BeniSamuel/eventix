package com.evms.www.controller;

import com.evms.www.dto.CommentDto;
import com.evms.www.model.Comment;
import com.evms.www.service.CommentService;
import com.evms.www.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evms/comment")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Comment>>> getAllComments () {
        return ApiResponse.ok("Successfully obtained all comments!!! 🎉🎉🎉", commentService.getAllComments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Comment>> getCommentById (@PathVariable Long id) {
        Comment comment = commentService.getCommentById(id);
        if (comment != null) {
            return ApiResponse.ok("Successfully obtained comment!!! 🎉🎉🎉", comment);
        }
        return ApiResponse.notFound("Failed to obtain comment not found!!! 💔😔😔", null);
    }

    @GetMapping("/event/{event_id}")
    public ResponseEntity<ApiResponse<List<Comment>>> getCommentsByEvent (@PathVariable Long event_id) {
        List<Comment> comments = commentService.getCommentsByEvent(event_id);
        if (!comments.isEmpty()) {
            return ApiResponse.ok("Successfully obtained all comments!!! 🎉🎉🎉", comments);
        }
        return ApiResponse.notFound("Failed to obtain comments!!! 💔😔😔", null);
    }

    @GetMapping("/author/{author_id}")
    public ResponseEntity<ApiResponse<List<Comment>>> getCommentsByAuthor (@PathVariable Long author_id) {
        List<Comment> comments = commentService.getCommentsByAuthor(author_id);
        if (!comments.isEmpty()) {
            return ApiResponse.ok("Successfully obtained all comments!!! 🎉🎉🎉", comments);
        }
        return ApiResponse.notFound("Failed to obtain comments!!! 💔😔😔", null);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Comment>> createComment (@RequestBody CommentDto commentDto) {
        Comment newComment = commentService.createComment(commentDto);
        if (newComment != null) {
            return ApiResponse.created("Successfully created a comment!!! 🎉🎉🎉", newComment);
        }
        return ApiResponse.badRequest("Failed to create a comment bad request!!! 💔😔😔", null);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteComment (@PathVariable Long id) {
        return commentService.deleteComment(id) ?
                ApiResponse.ok("Successfully deleted a comment!!! 🎉🎉🎉", true) :
                ApiResponse.notFound("Failed to delete a comment!!! 💔😔😔", false);
    }
}
