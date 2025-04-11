package com.mko.chem_ques_gen.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mko.chem_ques_gen.entities.Image;
import com.mko.chem_ques_gen.repository.ImageRepository;

@Service
public class ImageService {
    
    @Autowired
    private ImageRepository imageRepository;

    public Image saveImage(MultipartFile file, int widthInCm, int heightInCm) throws IOException {
        Image image = new Image(file.getOriginalFilename(), file.getContentType(), file.getBytes(), widthInCm, heightInCm);
        return imageRepository.save(image);
    }

    public byte[] getImage(Long id) {
        Optional<Image> image = imageRepository.findById(id);
        return image.map(Image::getImageData).orElse(null);
    }
    
    public Image getImageByName(String name) {
        return imageRepository.findImageByName(name);
    }
}
