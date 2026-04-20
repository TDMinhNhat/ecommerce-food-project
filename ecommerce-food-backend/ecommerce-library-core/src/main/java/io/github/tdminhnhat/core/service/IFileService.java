package io.github.tdminhnhat.core.service;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService<P extends Number> {

    Object uploadOrUpdateFile(MultipartFile file, P id);

    Object deleteFile(P id);

    Object getUrlFile(P id);
}
