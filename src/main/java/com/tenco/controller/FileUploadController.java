package com.tenco.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
/**
 * 서블릿 스펙에서 파일 처리를 할려면
 * 반드시 어노테이션 하나가 더 필요하다.
 */
@WebServlet("/upload")
@MultipartConfig // 반드시 선언
public class FileUploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FileUploadController() {
    }

    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("11111111111");
		// 'mFile' 이라는 key 값으로 input 태그로부터 파일 데이터를 가져올 수 있다.
		// 파일은 getPart 활용
		Part filePart = request.getPart("mFile");
		System.out.println(filePart.getContentType());
		System.out.println(filePart.getSize());
		
		
		// 파일을 서버측에 업로드하는 처리 프로세스
		
		// 유효성 검사 
		if(filePart == null || filePart.getSize() == 0) {
			response.setContentType("text/html");
			response.getWriter().println("첨부 파일을 추가해주세요.");
			return;
		}
		
		// 사용자가 올린 파일 원본 이름을 가져온다.
		// String originFileNames = Paths.get(filePart.getSubmittedFileName()).toString();
		// System.out.println("originFileNames : " + originFileNames);

		String originFileName = filePart.getSubmittedFileName();
		
		// 1. 원본 파일명을 가져온다. 
		// 2. 가능한 절대 중복되지 않을 이름을 만들어 준다.
		// UUID를 통해서 고유한 파일명을 만들어 보자.
		// 3. 확장자를 분리해서 원본 파일명 + _ + 고유한 UUID를 생성해서
		// 새로운 파일명을 만들어 준다. 
		String uniqueFileName = UUID.randomUUID().toString();
		
		// a.png, b.jpeg == > a_xhdf.png
		// 파일 확장자를 추출하여 고유한 파일명 뒤에 추가합니다. 
		String extension = "";
		int i = originFileName.lastIndexOf(".");
		System.out.println("UNIQUE : " + uniqueFileName);
		System.out.println(". 인덱스 번호 : " + i); // 29
		
		
		if(i > 0) {
			// . 포함한 확장자를 추출
			extension = originFileName.substring(i); 
			System.out.println("extension:" + extension); // .png
		}
		
		uniqueFileName += extension; // .png가 붙을 예정
		System.out.println(uniqueFileName);
		
		// 4.  어디에 저장할지 경로를 설정해야 한다. 
		// 프로젝트 내에 폴더가 저장 되도록
		// C:\work_web\jsp_file_upload_ex1\src\main\webapp\images 
		
		File uploadDirFile = new File("C:\\work_web\\jsp_file_upload_ex1\\src\\main\\webapp\\images");
		// 주의 !  경로 복붙 시 마지막에 /는 없어야 함. 
		
		// 5. 해당 경로에 폴더가 존재하는지 확인 -> 없다면 폴더를 코드로 생성하기
		//exists() : 폴더가 있는지 없는지 true, false 반환
		
		if(!uploadDirFile.exists()) {
			// 없으면 생성
			// mkdir, mkdirs <-- 부모 폴더가 없으면 함께 생성해줌
			if(uploadDirFile.mkdirs()) {
				System.out.println("디렉토리가 생성되었습니다." + uploadDirFile); // F5 눌러야 생성됨.
			} else {
				throw new ServletException("디렉토리 생성에 실패했습니다. ");
			}
		}
		
		// 파일 생성
		File fileToSave = new File(uploadDirFile, uniqueFileName);
		System.out.println("fileToSave.getAbsolutePath():" + fileToSave.getAbsolutePath());
		
		// 파일을 서버에 저장(어디에 저장할지 경로를 넣어줘야 함)
		filePart.write(fileToSave.getAbsolutePath());
		
		// 응답 페이지 구성
		response.setContentType("text/html");
		response.getWriter().print("파일 업로드 성공");
		response.getWriter().print("<br>");
		response.getWriter().print("사용자가 올린 파일명:" + originFileName);
		response.getWriter().print("<br>");
		response.getWriter().print("서버에 저장된 파일명:" + uniqueFileName);
		
	}
}
