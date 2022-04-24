package pro.sky3.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pro.sky3.example.model.Book;
import pro.sky3.example.model.BookCover;
import pro.sky3.example.repository.BookCoverRepository;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class BookCoverService {

    @Value("${books.cover.dir.path}")
    private String coversDir;

    private final BookServiceImpl bookService;
    private final BookCoverRepository bookCoverRepository;

    public BookCoverService(BookServiceImpl bookService, BookCoverRepository bookCoverRepository) {
        this.bookService = bookService;
        this.bookCoverRepository = bookCoverRepository;
    }

    public void uploadCover(Long bookId, MultipartFile file) throws IOException {
        Book book = bookService.findBook(bookId);

        Path filePath = Path.of(coversDir, bookId + "." +
                getExtension(file.getOriginalFilename()));

        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
        }

        BookCover bookCover = findBookCover(bookId);
        bookCover.setBook(book);
        bookCover.setFilePath(filePath.toString());
        bookCover.setFileSize(file.getSize());
        bookCover.setMediaType(file.getContentType());
//        bookCover.setPreview(generateImagePreview(filePath));

        bookCoverRepository.save(bookCover);
    }

//    private byte[] generateImagePreview(Path filePath) throws IOException {
//        try (InputStream is = Files.newInputStream(filePath);
//        BufferedInputStream bis = new BufferedInputStream(is, 1024);
//        ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
//            BufferdImage image = ImageIO.read(bis);
//
//            int height = image.getHeight() / (image.getWidth() / 100);
//            BufferedImage preview = new BufferedImage(100, height, image.getType();
//            Graphics2D graphics = preview.createGraphics();
//            graphics.drawImage(0, 0, 100, height, null);
//            graphics.dispose();
//
//            ImageIO.wriet(preview, getExtension(filePath.getFileName().toString()), baos);
//            return baos.toByteArray();
//        }
//    }

    public BookCover findBookCover(Long bookId) {
        return bookCoverRepository.findByBookId(bookId).orElse(new BookCover());
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
