package io.github.susimsek.hateoasbackend.bootstrap;

import io.github.susimsek.hateoasbackend.domain.Capability;
import io.github.susimsek.hateoasbackend.repository.CapabilityRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Component
@RequiredArgsConstructor
public class InitialDataLoader implements CommandLineRunner {

    final CapabilityRepository capabilityRepository;

    @Override
    public void run(String... args) {
        if (capabilityRepository.count() == 0) {
            createInitialCapabilities(capabilityRepository);
        }
    }


    private void createInitialCapabilities(CapabilityRepository capabilityRepository) {
        capabilityRepository.save(Capability.builder()
                .techStack("ReactJS")
                .numOfDevelopers(70)
                .numOfAvailableDevelopers(20)
                .build());
        capabilityRepository.save(Capability.builder()
                .techStack("Python")
                .numOfDevelopers(200)
                .numOfAvailableDevelopers(100)
                .build());
        capabilityRepository.save(Capability.builder()
                .techStack("Java#")
                .numOfDevelopers(17)
                .numOfAvailableDevelopers(7)
                .build());

        IntStream.rangeClosed(1, 10)
                .forEach(i -> capabilityRepository.save(Capability.builder()
                        .techStack("TechStack" + i)
                        .numOfDevelopers(50 + i)
                        .numOfAvailableDevelopers(20 + 3 * i)
                        .build()));
    }
}
