package com.tech.pro.service.impl;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tech.pro.exception.ApiException;
import com.tech.pro.service.ImageService;
import com.tech.pro.util.IOUtils;

@Service
public class ImageServiceImpl implements ImageService {
		
	@Value("${techshop.path.img}")
	private String imageDir;
	
	@Override
	public byte[] getImage(String imgName) {		
		try {
			return IOUtils.getBytes(Path.of(System.getProperty("user.home"),imageDir, imgName));
		} catch (IOException e) {
			throw new ApiException(e.getMessage());
		}
	}

}
