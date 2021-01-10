package com.klymchuk.school.service;

import com.klymchuk.school.error.exceptions.EntityNotFoundException;
import com.klymchuk.school.model.Topic;
import com.klymchuk.school.repo.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TopicService {

    private final TopicRepository topicRepository;

    private Topic getTopicById(int id){
        return topicRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Topic with id: " + "not found"));
    }

}
