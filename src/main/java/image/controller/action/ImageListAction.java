package image.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import image.model.ImageDao;
import image.model.ImageResponseDto;
import util.Action;

public class ImageListAction implements Action {
	
	private String[] commands;
	
	public ImageListAction(String[] commands) {
		this.commands = commands;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String type = commands[1];
				
		String contentType = commands[2];
		
		ImageDao imageDao = ImageDao.getInstance();
		List<ImageResponseDto> images = imageDao.findImagesAllByTypeAndContentType(type, contentType);
	
		JSONArray resObj = new JSONArray(images);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		
		response.getWriter().append(resObj.toString());
		
	}

}
