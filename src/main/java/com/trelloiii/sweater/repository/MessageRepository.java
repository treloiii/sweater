package com.trelloiii.sweater.repository;

import com.trelloiii.sweater.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message,Long> {
    List<Message> findByTag(String tag);
}
