package com.luinel.beprepared.service;

import com.luinel.beprepared.dto.response.StatsResponse;
import com.luinel.beprepared.model.User;

public interface UserService {

    String creaeteUser(User user);

    User getUserById(Long id);

    StatsResponse getAllStats();
}
