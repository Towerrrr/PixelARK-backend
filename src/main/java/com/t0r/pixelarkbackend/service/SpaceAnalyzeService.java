package com.t0r.pixelarkbackend.service;

import com.t0r.pixelarkbackend.model.dto.space.SpaceAnalyzeRequest;
import com.t0r.pixelarkbackend.model.entity.User;

public interface SpaceAnalyzeService {

    private void checkSpaceAnalyzeAuth(SpaceAnalyzeRequest spaceAnalyzeRequest, User loginUser);
}
