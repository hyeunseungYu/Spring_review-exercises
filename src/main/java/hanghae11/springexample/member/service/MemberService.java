package hanghae11.springexample.member.service;

import hanghae11.springexample.entity.MemberRoleEnum;
import hanghae11.springexample.jwt.JwtUtil;
import hanghae11.springexample.member.dto.SignupRequestDto;
import hanghae11.springexample.member.dto.SignupRequestMsgDto;
import hanghae11.springexample.entity.Member;
import hanghae11.springexample.member.repository.MemberRpository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRpository memberRpository;
    private final JwtUtil jwtUtil;
    public SignupRequestMsgDto signup (SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();
        MemberRoleEnum role = MemberRoleEnum.MEMBER;

        //사용자 확인
        Member existMember = memberRpository.findByUsername(username);
        if (existMember != null) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }
        //사용자 저장
        Member member = new Member(username, password, role);
        memberRpository.save(member);

        //메시지 반환
        return new SignupRequestMsgDto("회원가입 성공","200");
    }

    public SignupRequestMsgDto login(SignupRequestDto signupRequestDto, HttpServletResponse response) {
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();

        //사용자 확인
        Member existMember = memberRpository.findByUsername(username);
        if (existMember == null) {
            throw new IllegalArgumentException("등록된 사용자가 없습니다.");
        }

        if (!existMember.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        //헤더에 토큰 추가
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(existMember.getUsername(), existMember.getRole()));
        return new SignupRequestMsgDto("로그인 성공", "200");
    }
}
