package com.evms.www.controller;

import com.evms.www.dto.LikeDto;
import com.evms.www.model.Like;
import com.evms.www.service.LikeService;
import com.evms.www.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evms/like")
@AllArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Like>>> getAllLikes () {
        return ApiResponse.ok("Successfully obtained all likes!!! 🎉🎉🎉", likeService.getAllLikes());
    }

    @GetMapping("/event/{event_id}")
    public ResponseEntity<ApiResponse<List<Like>>> getLikesByEvent (@PathVariable Long event_id) {
        List<Like> likes = likeService.getLikesByEvent(event_id);
        if (!likes.isEmpty()) {
            return ApiResponse.ok("Successfully obtained likes!!! 🎉🎉🎉", likes);
        }
        return ApiResponse.notFound("Failed to obtain likes!!! 💔😔😔", null);
    }

    @GetMapping("/author/{author_id}")
    public ResponseEntity<ApiResponse<List<Like>>> getLikesByAuthor (@PathVariable Long author_id) {
        List<Like> likes = likeService.getLikesByAuthor(author_id);
        if (!likes.isEmpty()) {
            return ApiResponse.ok("Successfully obtained likes!!! 🎉🎉🎉", likes);
        }
        return ApiResponse.notFound("Failed to obtain likes!!! 💔😔😔", null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Like>> getLikeById (@PathVariable Long id) {
        Like like = likeService.getLikeById(id);
        if (like != null) {
            return ApiResponse.ok("Successfully obtained like!!! 🎉🎉🎉", like);
        }
        return ApiResponse.notFound("Failed to obtain like!!! 💔😔😔", null);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Like>> createLike (@RequestBody LikeDto likeDto) {
        Like newLike = likeService.createLike(likeDto);
        if (newLike != null) {
            return ApiResponse.created("Successfully created like!!! 🎉🎉🎉", newLike);
        }
        return ApiResponse.badRequest("Failed to create a like bad request!!! 💔😔😔", null);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteLikeById (@PathVariable Long id) {
        return likeService.deleteLikeById(id) ?
                ApiResponse.ok("Successfully deleted like!!! 🎉🎉🎉", true) :
                ApiResponse.notFound("Failed to delete like!!! 💔😔😔", false);
    }
}
