package com.example.democonfig.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.democonfig.entity.Config;

@Repository
public interface ConfigRepository extends JpaRepository<Config, Long> {
    Config findByKey(String key);
}
