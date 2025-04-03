package backend_principal_tienda.jwtAuth.Service;

import backend_principal_tienda.Enum.Role;
import backend_principal_tienda.jwtAuth.authEntity.AuthResponse;
import backend_principal_tienda.jwtAuth.authEntity.LoginRequest;
import backend_principal_tienda.jwtAuth.authEntity.RegisterRequest;
import backend_principal_tienda.jwtAuth.authEntity.User;
import backend_principal_tienda.jwtAuth.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            UserDetails user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            String token = jwtService.getToken(user);

            return AuthResponse.builder()
                    .token(token)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException("Authentication failed: " + e.getMessage());
        }
    }

    public AuthResponse register(RegisterRequest request) {
            User user = User.builder()
                    .username(request.getUsername())
                    .lastName(request.getLastName())
                    .firstName(request.getFirstName())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.ADMIN)
                    .build();
            userRepository.save(user);

            return AuthResponse.builder()
                    .token(jwtService.getToken(user))
                    .build();

    }
}
