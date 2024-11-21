package com.example.mysandbox.service.impl;

import com.example.mysandbox.entity.Tag;
import com.example.mysandbox.enums.TagType;
import com.example.mysandbox.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
@RequiredArgsConstructor
public class TagInitializer implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(TagInitializer.class);
    private final TagRepository tagRepository;

    @Override
    public void run(String... args) throws Exception {
        LOG.debug("Initializing tags...");
        if (tagRepository.count() == 0) {
            Arrays.stream(TagType.values())
                    .forEach(tagType -> {
                        Tag tag = new Tag();
                        tag.setName(tagType);
                        Tag saved = tagRepository.save(tag);
                        LOG.debug("Tag {} initialized", saved.getName());
                    });
        } else {
            LOG.debug("Tags already initialized");
        }
    }
}
