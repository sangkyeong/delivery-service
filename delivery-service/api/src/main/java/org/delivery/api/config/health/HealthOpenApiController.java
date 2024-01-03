package org.delivery.api.config.health;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.api.common.rabbitmq.Producer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/open-api")
@Slf4j
@RequiredArgsConstructor
public class HealthOpenApiController {

    @GetMapping("/health")
    public void health(){
      log.info("health call");
    }
}
