package com.vylgin.run;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

public class GetImage extends HttpServlet {
    private static final String CONTENT_TYPE = "image/png; charset=UTF-8";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException,
                                                           IOException {
        String IdBook = "";
        try {
            IdBook = request.getParameter("idbook");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ServletContext cntx= getServletContext();
        
        String filename = cntx.getRealPath("/images/" + IdBook + ".png");
        File file = new File(filename);
        
        response.setContentType(CONTENT_TYPE);
        response.setContentLength((int)file.length());

        FileInputStream in = new FileInputStream(file);
        OutputStream out = response.getOutputStream();

         byte[] buf = new byte[1024];
         int count = 0;
         while ((count = in.read(buf)) >= 0) {
           out.write(buf, 0, count);
        }
         
        in.close();
        out.close();

    }
}
