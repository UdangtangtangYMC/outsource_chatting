package com.hyunho9877.outsource.repo;

import com.hyunho9877.outsource.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    @Query("select case when count(c)> 0 then true else false end from ChatRoom c where (c.roomUserOne.id=:userOne and c.roomUserTwo.id=:userTwo) or (c.roomUserOne.id=:userTwo and c.roomUserTwo.id=:userOne)")
    boolean existsByUserOneAndUserTwo(String userOne, String userTwo);

    @Query("select c from ChatRoom c where (c.roomUserOne.id=:userOne and c.roomUserTwo.id=:userTwo) or (c.roomUserOne.id=:userTwo and c.roomUserTwo.id=:userOne)")
    Optional<ChatRoom> findByRoomUserOneAndRoomUserTwo(String userOne, String userTwo);
}