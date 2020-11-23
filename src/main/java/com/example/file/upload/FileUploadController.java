package com.example.file.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class FileUploadController {
	
	@PostMapping(value="/api/upload", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public String fileUpload(@RequestBody final MultipartFile file) throws IOException {
		File convertedFile = new File("C:\\Users\\sseela\\Desktop\\"+file.getOriginalFilename());
		convertedFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convertedFile);
		fos.write(file.getBytes());
		fos.close();
		return "file uploaded successfully";
	}
	
	@GetMapping(value="/download")
	public ResponseEntity<Object> downloadFile() throws IOException {
		String filename = "C:\\Users\\sseela\\Desktop\\int.txt";
		File file = new File(filename);
		InputStreamResource resource = new InputStreamResource(new
		FileInputStream(file));
		/*HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", String.format("attachment;filename=\"%s\"", file.getName()));
		headers.add("Cache-Control", "no-cache, no-store, mustrevalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");*/
		//ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("application/txt")).body(resource);
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(resource, HttpStatus.OK);
		return responseEntity;
	}
}
