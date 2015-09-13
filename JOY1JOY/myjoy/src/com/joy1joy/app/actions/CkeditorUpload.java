package com.joy1joy.app.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.joy1joy.app.actions.base.BaseAction;

@Namespace("/ckeditorUpload")
public class CkeditorUpload extends BaseAction {  
  
    private File upload;  
    private String uploadContentType;  
    private String uploadFileName;  
  
  
   
    @Action(value = "upload")
    public String ckeditUpload() throws Exception {  
        HttpServletResponse response = ServletActionContext.getResponse();  
        response.setCharacterEncoding("UTF-8");  
        PrintWriter out = response.getWriter();  
  
        //设置返回“图像”选项卡  
        String callback = ServletActionContext.getRequest().getParameter("CKEditorFuncNum");  
        //对文件进行校验  
        if(upload==null || uploadContentType==null || uploadFileName==null){  
            out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",''," + "'*请选择上传文件');");
            return null;
        }  
          
        if ((uploadContentType.equals("image/pjpeg") || uploadContentType.equals("image/jpeg"))  
                && uploadFileName.substring(uploadFileName.length() - 4).toLowerCase().equals(".jpg")) {  
            //IE6上传jpg图片的headimageContentType是image/pjpeg，而IE9以及火狐上传的jpg图片是image/jpeg  
        }else if(uploadContentType.equals("image/png") && uploadFileName.substring(uploadFileName.length() - 4).toLowerCase().equals(".png")){  
              
        }else if(uploadContentType.equals("image/gif") && uploadFileName.substring(uploadFileName.length() - 4).toLowerCase().equals(".gif")){  
              
        }else if(uploadContentType.equals("image/bmp") && uploadFileName.substring(uploadFileName.length() - 4).toLowerCase().equals(".bmp")){  
              
        }else{  
        	out.println("<script type=\"text/javascript\">");
        	out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",''," + "'文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）');");
        	out.println("</script>");
            return null;
        }  
        if(upload.length() > 2*1024*1024){  
        	out.println("<script type=\"text/javascript\">");
        	out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",''," + "'*文件大小不得大于2M');");
        	out.println("</script>"); 
            return null;
        }  
          
        //将文件保存到项目目录下  
        InputStream is = new FileInputStream(upload);  
        // add by dusny 20150913
//        String os1 = System.getProperty("os.name");  
//        String uploadPath="/opt/images/ckeditor";
//        if(os1.toLowerCase().startsWith("win")){  
//        	uploadPath="D:/images/ckeditor";
//        } 
        // end by duansy 20150913
        String uploadPath = ServletActionContext.getServletContext().getRealPath("images/ckeditor");   //设置保存目录  
        File folder=new File(uploadPath);
		if(!folder.exists()&& !folder .isDirectory())
		{
			folder.mkdir();
		}
        String fileName = String.valueOf(new Date().getTime());  //采用UUID的方式随机命名  
        fileName += uploadFileName.substring(uploadFileName.length() - 4);  
        File toFile = new File(uploadPath, fileName);  
        OutputStream os = new FileOutputStream(toFile);     
        byte[] buffer = new byte[1024];     
        int length = 0;  
        while ((length = is.read(buffer)) > 0) {     
            os.write(buffer, 0, length);     
        }     
        is.close();  
        os.close();  
          
          
	    String URL=request.getContextPath()+"/";  
	    out.println("<script type=\"text/javascript\">");   
	    out.println("window.parent.CKEDITOR.tools.callFunction("+ callback + ",\'" +URL+"images/ckeditor/"+fileName+ "\',\'\');");
	    out.println("</script>");  
        return null;
    }  
    public File getUpload() {  
        return upload;  
    }  
  
    public void setUpload(File upload) {  
          
        this.upload = upload;  
    }  
  
    public String getUploadContentType() {  
        return uploadContentType;  
    }  
  
    public void setUploadContentType(String uploadContentType) {  
        this.uploadContentType = uploadContentType;  
    }  
  
    public String getUploadFileName() {  
        return uploadFileName;  
    }  
  
    public void setUploadFileName(String uploadFileName)
    {  
        this.uploadFileName = uploadFileName;  
    }  
}  
