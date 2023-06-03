package com.OJTProject.InsuranceAndClaimApp.controller;

import com.OJTProject.InsuranceAndClaimApp.config.CustomUserDetails;
import com.OJTProject.InsuranceAndClaimApp.model.PdfFile;
import com.OJTProject.InsuranceAndClaimApp.model.User;
import com.OJTProject.InsuranceAndClaimApp.repository.PdfFileRepository;
import com.OJTProject.InsuranceAndClaimApp.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class PdfController {
    private final PdfFileRepository pdfFileRepository;

    private final UserRepository userRepository;

    @Autowired
    public PdfController(PdfFileRepository pdfFileRepository, UserRepository userRepository) {
        this.pdfFileRepository = pdfFileRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("files", pdfFileRepository.findAll());
        return "index1";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file,
                             @RequestParam("claimer") String claimer,
                             @RequestParam("client") String client,
                             @RequestParam("claim_amount") int claim_amount,
                             @RequestParam("status") String status,@AuthenticationPrincipal CustomUserDetails loggedUser) throws IOException {
        long userId = loggedUser.getId();
        User user = userRepository.findById(userId).get();
        PdfFile pdfFile = new PdfFile();
        pdfFile.setFileName(file.getOriginalFilename());
        pdfFile.setContentType(file.getContentType());
        pdfFile.setData(file.getBytes());
        pdfFile.setClaimer(claimer);
        pdfFile.setClient(client);
        pdfFile.setClaim_amount(claim_amount);
        pdfFile.setStatus("For_Verification");
        pdfFile.setUser(user);
        pdfFileRepository.save(pdfFile);
        return "redirect:/user-claims";
    }

    @GetMapping("/display/{id}")
    public void displayFile(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {
        PdfFile pdfFile = pdfFileRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid file Id:" + id));

        response.setContentType(pdfFile.getContentType());
        response.setContentLength(pdfFile.getData().length);
        response.setHeader("Content-Disposition", "inline; filename=\"" + pdfFile.getFileName() + "\"");

        FileCopyUtils.copy(pdfFile.getData(), response.getOutputStream());
    }
}
