package backend_principal_tienda.jwtAuth.Service;

import backend_principal_tienda.Enum.Role;
import backend_principal_tienda.exceptions.InvalidCredentialsException;
import backend_principal_tienda.exceptions.UserNotFoundException;
import backend_principal_tienda.jwtAuth.authEntity.AuthResponse;
import backend_principal_tienda.jwtAuth.authEntity.LoginRequest;
import backend_principal_tienda.jwtAuth.authEntity.RegisterRequest;
import backend_principal_tienda.jwtAuth.authEntity.User;
import backend_principal_tienda.jwtAuth.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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
            // Verificar primero si el usuario existe
            UserDetails user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));

            // Autenticar credenciales
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            String token = jwtService.getToken(user);

            return AuthResponse.builder()
                    .token(token)
                    .build();

        } catch (UserNotFoundException e) {
            throw e; // Esto se convertirá en 404
        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException("Credenciales inválidas"); // Esto se convertirá en 401
        } catch (Exception e) {
            throw new RuntimeException("Error durante la autenticación: " + e.getMessage());
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
