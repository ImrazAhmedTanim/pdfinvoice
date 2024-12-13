package com.example.pdfinvoice.controller;

import com.example.pdfinvoice.model.User;
import com.example.pdfinvoice.model.InsurancePolicy;
import com.example.pdfinvoice.repository.UserRepository;
import com.example.pdfinvoice.repository.InsurancePolicyRepository;
import com.example.pdfinvoice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.io.File;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InsurancePolicyRepository policyRepository;

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    public String showForm(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("policies", policyRepository.findAll());
        return "invoice-form";
    }

    @PostMapping("/generate")
    public String generateInvoice(@RequestParam("userId") Long userId, @RequestParam Long policyId, Model model) {
        User user = userRepository.findById(userId).orElseThrow();
        InsurancePolicy policy = policyRepository.findById(policyId).orElseThrow();

        String filePath = invoiceService.generateInvoice(user, policy);
        model.addAttribute("filePath", filePath);
        return "invoice-success";
    }
    @RestController
    public class HealthController {

        @GetMapping("/health")
        public String healthCheck() {
            return "Application is running!";
        }
    }
    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        File file = new File(fileName);

        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        Resource resource = new FileSystemResource(file);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(resource);
    }
}
    

