package hanghae11.springexample.member.service;

import hanghae11.springexample.entity.MemberRoleEnum;
import hanghae11.springexample.jwt.JwtUtil;
//import hanghae11.springexample.member.dto.AdminRequestDto;
import hanghae11.springexample.member.dto.AdminRequestDto;
import hanghae11.springexample.member.dto.SignupRequestDto;
import hanghae11.springexample.member.dto.SignupRequestMsgDto;
import hanghae11.springexample.entity.Member;
import hanghae11.springexample.member.repository.MemberRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    //다른 방법 찾기
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public SignupRequestMsgDto signup (SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        MemberRoleEnum role = MemberRoleEnum.MEMBER;



        //사용자 확인
        Member existMember = memberRepository.findByUsername(username);

        if (existMember != null) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        //사용자 저장
        Member member = Member.builder()
                .username(username)
                .password(password)
                .role(role).build();

        memberRepository.save(member);

        //메시지 반환
        return new SignupRequestMsgDto("회원가입 성공", HttpStatus.OK.value());
    }

    @Transactional(readOnly = true)
    public SignupRequestMsgDto login(SignupRequestDto signupRequestDto, HttpServletResponse response) {
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();


        //사용자 확인
        Member existMember = memberRepository.findByUsername(username);

        if (existMember == null) {
            throw new IllegalArgumentException("등록된 사용자가 없습니다.");
        }
        //단방향 암호화니까 matches 사용
        if (!passwordEncoder.matches(password, existMember.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        //헤더에 토큰 추가
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(existMember.getUsername(), existMember.getRole()));
        return new SignupRequestMsgDto("로그인 성공", HttpStatus.OK.value());
    }

    @Transactional
    public SignupRequestMsgDto giveAdmin(Long id, AdminRequestDto adminRequestDto, HttpServletRequest request) {
        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        System.out.println(adminRequestDto.getAdminToken());
        Claims claims;

        if (token != null) {
            // Token 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            Member member = memberRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("error")
            );


            if (!adminRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }

            MemberRoleEnum role = MemberRoleEnum.ADMIN;

            member.adminUpdate(role);

            return new SignupRequestMsgDto("권한을 부여하였습니다.", HttpStatus.OK.value());

        }else {
            throw new IllegalArgumentException("토큰이 확인되지 않음");
        }


    }

}
