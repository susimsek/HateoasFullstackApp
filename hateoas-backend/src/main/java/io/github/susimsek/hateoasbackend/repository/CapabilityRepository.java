package io.github.susimsek.hateoasbackend.repository;

import io.github.susimsek.hateoasbackend.domain.Capability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CapabilityRepository extends JpaRepository<Capability, Long> {
}
