package com.company.rating.util;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.rating.form.UploadViewData;

/**
 * Servlet implementation class for Servlet: FileViewer
 *
 */
 public class FileViewer extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	public FileViewer() {
		super();
	}   	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
             HttpSession sesion=request.getSession(false);
             UploadViewData bean=(UploadViewData) sesion.getAttribute("uploadViewData");
             String file=bean.getGelirbilancoLink();
   			response.setHeader("Content-Disposition","inline; filename=GelirBilanco.pdf"); //Dosya UploadView.java da üretiliyor
		
		response.setContentType("application/pdf");
		  
		   ServletOutputStream stream = null;
		   BufferedInputStream buf = null;
		   try {
			   InputStream   inputStream=new FileInputStream(file);
   			 stream = response.getOutputStream();
				  buf = new BufferedInputStream(inputStream);
			      int readBytes = 0;
			      while ((readBytes = buf.read()) != -1)
			        stream.write(readBytes);
			    } catch (IOException ioe) {
			      throw new ServletException(ioe.getMessage());
			    } finally {
			      if (stream != null)
			        stream.close();
			      if (buf != null)
			        buf.close();
			    }
			  }
		   
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}   	  	    
}