package br.com.secret.friend.controller;

import br.com.secret.friend.dto.letterDTO.LetterRequesterDTO;
import br.com.secret.friend.dto.letterDTO.LetterResponseDTO;
import br.com.secret.friend.service.LetterService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/letter")
@SecurityRequirement(name = "bearer-key")
public class LetterController {
    @Autowired
    private LetterService letterService;

    @PostMapping()
    public ResponseEntity<LetterResponseDTO> registerLetter(@Valid @RequestBody LetterRequesterDTO letterDTO,
                                                           UriComponentsBuilder uriComponentsBuilder) {
        LetterResponseDTO responseDto = letterService.registerLetter(letterDTO);
        URI uri = uriComponentsBuilder
                .path("/api/letter/{id}")
                .buildAndExpand(responseDto.idLetter())
                .toUri();
        return ResponseEntity.created(uri).body(responseDto);
    }

    @GetMapping("/{userGroupId}")
    public ResponseEntity<LetterResponseDTO> getLetterByUserGroupID(@PathVariable Long userGroupId) {
        LetterResponseDTO responseDto = letterService.getLetterByUserGroupID(userGroupId);
        return ResponseEntity.ok(responseDto);
    }
}
