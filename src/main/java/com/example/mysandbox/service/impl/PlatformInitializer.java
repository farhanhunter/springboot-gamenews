package com.example.mysandbox.service.impl;

import com.example.mysandbox.entity.Platform;
import com.example.mysandbox.enums.PlatformType;
import com.example.mysandbox.repository.PlatformRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class PlatformInitializer implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(PlatformInitializer.class);
    private final PlatformRepository platformRepository;

    @Override
    public void run(String... args) {
        LOG.debug("Initializing platforms...");
        if (platformRepository.count() == 0) {
            Arrays.stream(PlatformType.values())
                    .forEach(type -> {
                        Platform platform = new Platform();
                        platform.setName(type);
                        Platform saved = platformRepository.save(platform);
                        LOG.debug("Created platform: {}", saved);
                    });
        } else {
            LOG.debug("Platforms already initialized");
        }
    }
}
