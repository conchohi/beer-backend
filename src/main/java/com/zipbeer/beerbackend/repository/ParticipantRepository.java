package com.zipbeer.beerbackend.repository;

import com.zipbeer.beerbackend.entity.ParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Long> {
    @Modifying
    @Query("delete from ParticipantEntity p where p.user.userId = :userId and p.room.roomNo = :roomNo")
    void deleteByUserUserIdAndRoomRoomNo(@Param("userId") String userId, @Param("roomNo")Long roomNo);
}
