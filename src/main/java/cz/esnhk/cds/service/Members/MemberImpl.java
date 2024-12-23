package cz.esnhk.cds.service.Members;

import cz.esnhk.cds.model.cards.ESNcard;
import cz.esnhk.cds.model.cards.SIMCard;
import cz.esnhk.cds.model.users.Member;
import cz.esnhk.cds.repository.ESNCardRepository;
import cz.esnhk.cds.repository.MembersRepository;
import cz.esnhk.cds.repository.SimCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberImpl implements MemberService {
    private final MembersRepository membersRepository;
    ESNCardRepository ESNCardRepository;
    SimCardRepository simCardRepository;

    @Autowired
    public MemberImpl(MembersRepository membersRepository, ESNCardRepository ESNCardRepository, SimCardRepository simCardRepository) {
        this.ESNCardRepository = ESNCardRepository;
        this.membersRepository = membersRepository;
        this.simCardRepository = simCardRepository;
    }

    @Override
    public List<Member> getAllMembers() {
        return membersRepository.findAll();
    }

    @Override
    public Member getMemberById(long id) {
        return membersRepository.findById(id).orElse(null);
    }

    @Override
    public void addMember(Member member) {
        membersRepository.save(member);
    }

    @Override
    public void addESNcard(long id, ESNcard esnCard) {
        //REFACTOR: cards already taken from database IDK if this is necessary
        Member member = membersRepository.findById(id).orElse(null);
        ESNcard esnCard1 = ESNCardRepository.findById(esnCard.getId()).orElse(null);
        if (member != null && esnCard1 != null) {
            member.addESNcard(esnCard1);
            ESNCardRepository.save(esnCard1);
            membersRepository.save(member);
        }
    }

    @Override
    public void assignSIMCard(long memberId, SIMCard simCard) {
        Member member = membersRepository.findById(memberId).orElse(null);
        SIMCard simCard1 = simCardRepository.findById(simCard.getId()).orElse(null);
        if (member != null && simCard1 != null) {
            member.addSimCard(simCard1);
            simCardRepository.save(simCard1);
            membersRepository.save(member);
        }
    }
}


//TODO: Join with international student -> do generic