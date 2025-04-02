package backend_principal_tienda.jwtAuth.Controller;

import backend_principal_tienda.jwtAuth.authEntity.AuthResponse;
import backend_principal_tienda.jwtAuth.authEntity.LoginRequest;
import backend_principal_tienda.jwtAuth.authEntity.RegisterRequest;
import backend_principal_tienda.jwtAuth.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/backend-principal-tienda/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authService.login(loginRequest));
    }
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authService.register(registerRequest));
    }
}
