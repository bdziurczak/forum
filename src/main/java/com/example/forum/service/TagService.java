package com.example.forum.service;

import com.example.forum.model.Tag;
import com.example.forum.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {
    private TagRepository tagRepository;
    public TagService(@Autowired TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Iterable<Tag> returnTags() {
        return tagRepository.findAll();
    }

    public void save(Tag tag) {
        tagRepository.save(tag);
    }
}
