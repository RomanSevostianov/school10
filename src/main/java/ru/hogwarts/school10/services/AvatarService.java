package ru.hogwarts.school10.services;


import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school10.model.Avatar;
import ru.hogwarts.school10.model.Student;
import ru.hogwarts.school10.repositories.AvatarRepository;
import ru.hogwarts.school10.repositories.StudentRepository;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarService {

    private final Logger logger = LoggerFactory.getLogger(AvatarService.class);
    @Value("${path.to.avatars.folder}")
    private String avatarsDir;

    private final StudentService studentService;
    private final AvatarRepository avatarRepositoriy;
    private StudentRepository studentRepositoriy;

    public AvatarService(StudentService studentService, AvatarRepository avatarRepositoriy) {
        this.studentService = studentService;
        this.avatarRepositoriy = avatarRepositoriy;
    }

    public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        logger.info("Method uploadAvatar");

        Student student = studentService.getStudentId(studentId);

        Path filePath = Path.of(avatarsDir, studentId + "." + getExtensions(avatarFile.getOriginalFilename()));

        Files.createDirectories(filePath.getParent());//если папки нет то она будет создана
        Files.deleteIfExists(filePath);//если фаил по такому адресу существует то он его удаляет

        try (
                InputStream is = avatarFile.getInputStream(); // открываем входной поток и читаем данные/ Откуда данные читать
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW); //создаем место куда будем добавлять данные /Куда данные читать

                BufferedInputStream bis = new BufferedInputStream(is, 1024);// расширяем InputStream /колличество информации брать за раз
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024); // колличество информации передавать за раз
        ) {
            bis.transferTo(bos); // метод переклыдывает из bis в bos
        }

        Avatar avatar = findAvatar(studentId);// проверяем имеется ли у нас такая книга. Если нет создаем.
        avatar.setStudent(student);//указываем книгу к которой загружаем обложку
        avatar.setFilePath(filePath.toString()); //указываем путь к файлу, который сохранили на диске
        avatar.setFileSize(avatarFile.getSize());//указываем размер
        avatar.setMediaType(avatarFile.getContentType());//тип файла
        avatar.setPreview(generateImagePreview(filePath));//уменьшаем

        avatarRepositoriy.save(avatar);


    }

    private byte[] generateImagePreview(Path filePath) throws IOException {
        try (
                InputStream is = Files.newInputStream(filePath);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            BufferedImage image = ImageIO.read(bis);

            int height = image.getHeight() / (image.getWidth() / 100);
            BufferedImage preview = new BufferedImage(100, height, image.getType());
            Graphics2D graphics = preview.createGraphics();
            graphics.drawImage(image, 0, 0, 100, height, null);
            graphics.dispose();

            ImageIO.write(preview, getExtensions(filePath.getFileName().toString()), baos);
            return baos.toByteArray();
        }
    }

    public Avatar findAvatar(Long Id) {
        logger.info("Method findAvatar");
        return avatarRepositoriy.findAvatarByIdStudent(Id).orElse(new Avatar());

    }

    private String getExtensions(String fileName) {
        logger.info("Method getExtensions");
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public List<Avatar> getAllAvatar(Integer pageNumber, Integer numberSize) {
        logger.info("Method getAllAvatar");

        PageRequest pageRequest = PageRequest.of(pageNumber - 1, numberSize);
        return avatarRepositoriy.findAll(pageRequest).getContent();
    }

}



