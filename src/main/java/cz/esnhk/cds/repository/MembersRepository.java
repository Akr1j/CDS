package cz.esnhk.cds.repository;

import cz.esnhk.cds.model.users.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MembersRepository extends JpaRepository<Member, Long> {
    List<Member> findByActiveTrueAndConfirmedTrue();
}
