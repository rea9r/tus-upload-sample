package com.amtkca.tusserver.config;

import me.desair.tus.server.TusFileUploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.io.IOException;

@Configuration
public class TusConfig {
    @Value("${tus.server.data.directory}")
    protected String tusStoragePath;
    @Value("${tus.server.data.expiration}")
    protected Long tusExpirationPeriod;

    @PreDestroy
    public void exit() throws IOException {
        // cleanup any expired uploads and stale locks
        tus().cleanup();
    }

    @Bean
    public TusFileUploadService tus() {
        return new TusFileUploadService()
                .withStoragePath(tusStoragePath)
                .withDownloadFeature()
                .withUploadExpirationPeriod(tusExpirationPeriod)
                .withUploadURI("/api/file/upload");
    }
}
