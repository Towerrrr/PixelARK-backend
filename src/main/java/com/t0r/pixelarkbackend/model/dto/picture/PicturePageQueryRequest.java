package com.t0r.pixelarkbackend.model.dto.picture;

import com.t0r.pixelarkbackend.common.PageRequest;
import lombok.Data;

@Data
public class PicturePageQueryRequest {

    private PageRequest pageRequest;

    private PictureQueryRequest pictureQueryRequest;
}
