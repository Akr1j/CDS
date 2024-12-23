package cz.esnhk.cds.service.Members;

import cz.esnhk.cds.model.cards.ESNcard;
import cz.esnhk.cds.model.cards.SIMCard;
import cz.esnhk.cds.model.users.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MemberService {
    List<Member> getAllMembers();

    Member getMemberById(long id);

    void addMember(Member member);

    void addESNcard(long id, ESNcard cardNumber);

    void assignSIMCard(long id, SIMCard cardNumber);
}