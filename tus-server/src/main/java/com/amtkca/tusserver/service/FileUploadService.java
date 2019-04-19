package com.amtkca.tusserver.service;

import lombok.extern.slf4j.Slf4j;
import me.desair.tus.server.TusFileUploadService;
import me.desair.tus.server.exception.TusException;
import me.desair.tus.server.upload.UploadInfo;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Service
public class FileUploadService {
    @NotNull
    TusFileUploadService tusFileUploadService;

    @Autowired
    FileUploadService(TusFileUploadService tusFileUploadService) {
        this.tusFileUploadService = tusFileUploadService;
    }

    public void upload(HttpServletRequest request, HttpServletResponse response) {
        try {
            // Process a tus upload request
            tusFileUploadService.process(request, response);

            // Get upload information
            UploadInfo uploadInfo = tusFileUploadService.getUploadInfo(request.getRequestURI());

            if (uploadInfo != null && !uploadInfo.isUploadInProgress()) {
                // Progress status is successful: Create file
                InputStream is = tusFileUploadService.getUploadedBytes(request.getRequestURI());
                File file = new File(uploadInfo.getFileName());
                FileUtils.copyInputStreamToFile(is, file);

                // Delete an upload associated with the given upload url
                tusFileUploadService.deleteUpload(request.getRequestURI());
            }
        } catch (IOException | TusException e) {
            log.error("exception was occurred. message={}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
