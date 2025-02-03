package cz.esnhk.cds.controller;

import cz.esnhk.cds.model.cards.CardStatusType;
import cz.esnhk.cds.model.cards.ESNcard;
import cz.esnhk.cds.model.cards.SIMCard;
import cz.esnhk.cds.model.users.Member;
import cz.esnhk.cds.service.Members.MemberService;
import cz.esnhk.cds.service.card.esnCards.EsnCardService;
import cz.esnhk.cds.service.card.simCards.SimCardService;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/members/")
public class MembersController {

    private final EsnCardService esnCardService;
    private final SimCardService simCardService;
    private final MemberService memberService;

    public MembersController(MemberService memberService, EsnCardService esnCardService, SimCardService simCardService) {
        this.memberService = memberService;
        this.esnCardService = esnCardService;
        this.simCardService = simCardService;
    }

    @RequestMapping("/")
    public String list(Model model) {
        model.addAttribute("members", memberService.getAllMembers());
        return "member/members_list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("member", new Member());
        return "member/member_add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Member member) {
        memberService.addMember(member);
        return "redirect:/members/";
    }

    @GetMapping("/profile/{id}")
    public String profile(Model model, @PathVariable long id) {
        //TODO: not found page
        Member member = memberService.getMemberById(id);
        System.out.println(memberService.getAllMembers());
        if (member != null) {
            model.addAttribute("member", member);
            return "member/member_profile";
        }
        return "redirect:/members/";
    }

    @GetMapping("/profile/{id}/addESNcard/")
    public String addESNcard(Model model, @PathVariable long id) {
        //Deprecated ??? TODO
        Member member = memberService.getMemberById(id);
        if (member != null) {
            model.addAttribute("member", member);
            model.addAttribute("esnCards", esnCardService.getAvailableEsnCards());
            model.addAttribute("esnCardId", null);
            return "member/member_add_esn_card";
        }
        return "redirect:/members/";
    }

    @PostMapping("/profile/{memberId}/assignESNcard/")
    public String addESNcard(@PathVariable long memberId, @RequestParam("esnCardId") long esnCardId) {
        Member member = memberService.getMemberById(memberId);
        ESNcard esnCard = esnCardService.getEsnCardById(esnCardId);
        esnCard.setDateOfIssue(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        esnCard.setCardStatus(CardStatusType.ISSUED);

        SecurityContext context = SecurityContextHolder.getContext();
        int issuerId = Integer.parseInt(context.getAuthentication().getPrincipal().toString());
        esnCard.setIssuedBy(issuerId);

        if (member != null) {
            memberService.addESNcard(memberId, esnCard);
            return "redirect:/members/profile/" + memberId;
        }
        return "redirect:/501";
    }

    @GetMapping("/profile/{id}/assignSIMCard/")
    public String assignSIMCard(Model model, @PathVariable long id) {
        //Deprecated ??? TODO
        Member member = memberService.getMemberById(id);
        if (member != null) {
            model.addAttribute("member", member);
            model.addAttribute("esnCards", simCardService.getAvailableSimCards());
            model.addAttribute("simCardId", null);
            return "member/member_add_sim_card";
        }
        return "redirect:/members/";
    }

    @PostMapping("/profile/{memberId}/assignSIMCard/")
    public String assignSIMCard(@PathVariable long memberId, @RequestParam("simCardId") long simCardId) {
        Member member = memberService.getMemberById(memberId);
        SIMCard simCard = simCardService.getSimCardById(simCardId);
        simCard.setDateOfIssue(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        simCard.setCardStatus(CardStatusType.ISSUED);

        SecurityContext context = SecurityContextHolder.getContext();
        int issuerId = Integer.parseInt(context.getAuthentication().getPrincipal().toString());
        simCard.setIssuedBy(issuerId);

        if (member != null) {
            memberService.assignSIMCard(memberId, simCard);
            return "redirect:/members/profile/" + memberId;
        }
        return "redirect:/501";
    }
}
