package com.evms.www.repository;

import com.evms.www.model.Event;
import com.evms.www.model.Like;
import com.evms.www.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> getLikesByEvent (Event event);
    List<Like> getLikesByAuthor (User author);
}
