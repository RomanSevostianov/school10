package ru.hogwarts.school10.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school10.model.Avatar;
import ru.hogwarts.school10.services.AvatarServices;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("avatar")
public class AvatarController {

    public AvatarServices avatarServices;

    //Post - создание
    @PostMapping(value = "/studentId}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
// в адрес запроса передаем индефикатор студента и ожидаем получение нужного типа файла
    public ResponseEntity<String> uploadAvatar(@PathVariable Long studentId, @RequestParam MultipartFile avatar) throws IOException {

        if (avatar.getSize() > 1024 * 300) {
            return ResponseEntity.badRequest().body("Фаил слишком большой");
        }
        avatarServices.uploadAvatar(studentId, avatar);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "{id}/cover/preview")
    public ResponseEntity<byte[]> downloardCover(@PathVariable long id) {
        Avatar avatar = avatarServices.findAvatar(id);

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getPreview().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getPreview());
    }


    @GetMapping(value = "{id}/cover")
    public void dounloadCover(@PathVariable Long id, HttpServletResponse responce) throws IOException {
        Avatar avatar = avatarServices.findAvatar(id);

        Path path = Path.of(avatar.getFilePath());

        try (
                InputStream is = Files.newInputStream(path);// находим фаил, открываем его
                OutputStream os = responce.getOutputStream();
                )
        {
            responce.setStatus(200);
            responce.setContentType (avatar.getMediaType());
            responce.setContentLength ((int) avatar.getFileSize());
            is.transferTo(os);
        }


    }
}
