package com.example.electionbooth.repository;

import com.example.electionbooth.model.ChoiceVote;
import com.example.electionbooth.model.ChoiceVoteCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChoiceVoteRepository extends JpaRepository<ChoiceVote, Long> {
    @Query("SELECT NEW com.example.electionbooth.model.ChoiceVoteCount(v.choice.id, count(v.id)) FROM ChoiceVote v WHERE v.poll.id in :pollIds GROUP BY v.choice.id")
    List<ChoiceVoteCount> countByPollIdInGroupByChoiceId(@Param("pollIds") List<Long> pollIds);

    @Query("SELECT NEW com.example.electionbooth.model.ChoiceVoteCount(v.choice.id, count(v.id)) FROM ChoiceVote v WHERE v.poll.id = :pollId GROUP BY v.choice.id")
    List<ChoiceVoteCount> countByPollIdGroupByChoiceId(@Param("pollId") Long pollId);
}
