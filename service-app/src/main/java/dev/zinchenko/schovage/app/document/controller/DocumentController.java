package dev.zinchenko.schovage.app.document.controller;

import dev.zinchenko.schovage.app.common.utils.RequestUtils;
import dev.zinchenko.schovage.app.document.dto.DocumentMetadataResponse;
import dev.zinchenko.schovage.app.document.service.DocumentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/document")
public class DocumentController {
    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping
    public DocumentMetadataResponse create(@RequestHeader(name = "Schovage-Api-Key") String apiKey,
                                           @RequestParam MultipartFile document,
                                           HttpServletRequest request
    ) {
        return documentService.createDocument(apiKey, document, RequestUtils.getRequesterIp(request));
    }

    @GetMapping(produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public InputStreamResource read(@RequestHeader(name = "Schovage-Api-Key") String apiKey,
                                   @RequestParam String id) {
        return new InputStreamResource(documentService.readDocument(apiKey, id));
    }


}
