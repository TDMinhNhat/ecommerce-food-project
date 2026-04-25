package io.github.tdminhnhat.core.service;

import io.github.tdminhnhat.core.model.vo.BaseVo;
import org.springframework.web.multipart.MultipartFile;

public interface IFileService<P extends Number> {

    <R extends BaseVo> R uploadOrUpdateFile(MultipartFile file, P id);

    <R extends BaseVo> R deleteFile(P id);

    <R extends BaseVo> R getUrlFile(P id);
}
