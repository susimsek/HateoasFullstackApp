package io.github.susimsek.hateoasbackend.repositories;

import io.github.susimsek.hateoasbackend.domain.Capability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CapabilityRepository extends JpaRepository<Capability, Long> {
}
