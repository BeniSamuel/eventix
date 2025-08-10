package com.evms.www.service;

import com.evms.www.dto.LikeDto;
import com.evms.www.model.Event;
import com.evms.www.model.Like;
import com.evms.www.model.User;
import com.evms.www.repository.LikeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserService userService;
    private final EventService eventService;

    public List<Like> getAllLikes () {
        return likeRepository.findAll();
    }

    public List<Like> getLikesByEvent (Long event_id) {
        Event event = eventService.getEventById(event_id);
        if (event != null) {
            return likeRepository.getLikesByEvent(event);
        }
        return null;
    }

    public List<Like> getLikesByAuthor (Long author_id) {
        User author = userService.getUserById(author_id);
        if (author != null) {
            return likeRepository.getLikesByAuthor(author);
        }
        return null;
    }

    public Like getLikeById (Long id) {
        return likeRepository.findById(id).orElse(null);
    }

    public Like createLike (LikeDto likeDto) {
        Event event = eventService.getEventById(likeDto.getEvent_id());
        User author = userService.getUserById(likeDto.getAuthor_id());

        if (event == null && author == null) {
            return null;
        }

        Like newLike = new Like(event, author, likeDto.getLiked_at());
        return likeRepository.save(newLike);
    }

    public Boolean deleteLikeById (Long id) {
        Like like = getLikeById(id);
        if (like != null) {
            likeRepository.delete(like);
            return true;
        }
        return false;
    }
}
