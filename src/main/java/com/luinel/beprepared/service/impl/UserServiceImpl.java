package com.luinel.beprepared.service.impl;

import com.luinel.beprepared.dto.response.StatsResponse;
import com.luinel.beprepared.exception.BadRequestException;
import com.luinel.beprepared.model.User;
import com.luinel.beprepared.model.enums.role.Role;
import com.luinel.beprepared.repository.AlertRepository;
import com.luinel.beprepared.repository.CitizenRepository;
import com.luinel.beprepared.repository.UserRepository;
import com.luinel.beprepared.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AlertRepository  alertRepository;
    private final CitizenRepository citizenRepository;

    @Override
    @Transactional
    public String creaeteUser(User user) {
        if(userRepository.existsByEmail(user.getEmail())){
            throw  new BadRequestException("Já existe um usuario com esse e-mail");
        }
        user.setRole(Role.ADMIN);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "Usuário criado com sucesso!";
    }

    @Override
    @Transactional
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Usuario não encontrado!"));
    }

    @Override
    @Transactional(readOnly = true)
    public StatsResponse getAllStats() {
        return StatsResponse.builder()
                .citizens(citizenRepository.count())
                .totalAlerts(alertRepository.count())
                .activeAlerts(alertRepository.countByActive(true))
                .build();
    }
}
