package com.app.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.app.models.Runner;

@Service
public interface RunnerService extends JpaRepository<Runner, Integer> {
	public List<Runner> findByName(String name);
	public List<Runner> findByPb(String pb);
}
