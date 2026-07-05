## 🚀 Part 3 (Next)

Next we'll build the Authentication Module, including:

User entity
Role entity
UserRole mapping
Flyway migration (V1__Create_tables.sql)
Spring Data repositories
Registration API
Login API
JWT generation
JWT validation filter
Spring Security configuration
Password encryption with BCrypt
Refresh token support
Authentication unit tests


🚀 Part 3 – Authentication Module

This module will contain approximately 32 Java classes.

authentication
│
├── controller
│      AuthController
│
├── dto
│      LoginRequest
│      LoginResponse
│      RegisterRequest
│      RegisterResponse
│      JwtResponse
│
├── entity
│      User
│      Role
│      UserRole
│
├── repository
│      UserRepository
│      RoleRepository
│      UserRoleRepository
│
├── service
│      AuthService
│      JwtService
│      CustomUserDetailsService
│
├── security
│      JwtAuthenticationFilter
│      JwtAuthenticationEntryPoint
│      SecurityConfig
│
├── mapper
│      UserMapper
│
└── validator