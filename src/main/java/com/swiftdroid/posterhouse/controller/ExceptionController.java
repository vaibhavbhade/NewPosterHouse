package com.swiftdroid.posterhouse.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;

import com.swiftdroid.posterhouse.model.User;


@ControllerAdvice
@Controller
public class ExceptionController {
	
	 @ExceptionHandler(Exception.class)
	    public final String handleAllExceptions(Exception ex, WebRequest request) {
	        List<String> details = new ArrayList<>();
	        details.add(ex.getLocalizedMessage());
	        return "badRequestPage";
	    }

	/*
	 * @ExceptionHandler(FileUploadBase.FileSizeLimitExceededException.class) public
	 * String uploadedAFileTooLarge(FileUploadBase.FileSizeLimitExceededException e)
	 * { return "File upload error"; }
	 */

    @ExceptionHandler(MultipartException.class)
    public String handleMultipartException(MultipartException e, Model model) {
    	model.addAttribute("message","problem");
        return "redirect:/fetchProduct";
    }
    }
